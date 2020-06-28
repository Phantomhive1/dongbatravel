package com.tedu.pj.sys.service.realm;

import com.tedu.pj.sys.dao.SysMenuDao;
import com.tedu.pj.sys.dao.SysRoleMenuDao;
import com.tedu.pj.sys.dao.SysUserDao;
import com.tedu.pj.sys.dao.SysUserRoleDao;
import com.tedu.pj.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysMenuDao sysMenuDao;


    /**
     * 授权信息的获取和封装
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取用户登录信息
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Integer id = user.getId();
        //2.基于用户id获取用户拥有的角色
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(id);
        if (roleIds == null || roleIds.size() == 0)
            throw new AuthorizationException();
        //3.基于角色id获取菜单id
        Integer[] array = {};
        List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
        if (menuIds == null || menuIds.size() == 0)
            throw new AuthorizationException();
        //4.基于菜单id获取权限标识
        List<String> permissions = sysMenuDao.findPermissions(menuIds.toArray(array));
        //5.对权限标识信息进行封装并返回
        Set<String> set = new HashSet<>();
        for (String per : permissions) {
            if (!StringUtils.isEmpty(per)) {
                set.add(per);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    /**
     * 认证信息的获取和封装
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户名
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username =  upToken.getUsername();
        //2.基于用户名查询用户信息
        SysUser user = sysUserDao.findUserByUserName(username);
        if (user == null)
            throw new UnknownAccountException();
        if (user.getValid() == 0)
            throw new LockedAccountException();

        //3.封装用户信息
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(
                        user, //身份
                        user.getPassword(), //已加密用户凭证
                        credentialsSalt, //凭证盐
                        getName()); //real名称
        return info;
    }

    /**
     * 获取加密凭证匹配器对象
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1);
        super.setCredentialsMatcher(matcher);
    }
}
