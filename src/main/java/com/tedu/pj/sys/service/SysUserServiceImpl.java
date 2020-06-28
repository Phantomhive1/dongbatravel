package com.tedu.pj.sys.service;

import com.tedu.pj.common.aspect.RequiredLog;
import com.tedu.pj.common.exception.ServiceException;
import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.common.vo.SysUserDeptVo;
import com.tedu.pj.sys.dao.SysUserDao;
import com.tedu.pj.sys.dao.SysUserRoleDao;
import com.tedu.pj.sys.entity.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @RequiredLog(operation = "分页查询")
    //@Override
    public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("当前页码值无效");
        //2.查询总记录数并进行校验
        int rowCount=sysUserDao.getRowCount(username);
        if(rowCount==0)
            throw new ServiceException("没有找到对应记录");
        //3.查询当前页记录
        int pageSize=2;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysUserDeptVo> records= sysUserDao.findPageObjects(username, startIndex, pageSize);
        //4.对查询结果进行封装并返回
        return new PageObject<>(pageCurrent, pageSize, rowCount, records);
    }

    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {

        String salt = UUID.randomUUID().toString();
        SimpleHash sh = new SimpleHash("MD5", entity.getPassword(), salt, 1);
        entity.setSalt(salt);
        entity.setPassword(sh.toHex());
        int rows = sysUserDao.insertObject(entity);
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        return rows;

    }

    @Override
    public int validById(Integer id, Integer valid) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("参数不合法,id="+id);
        if(valid!=1&&valid!=0)
            throw new ServiceException("参数不合法,valie="+valid);
        //2.执行禁用或启用操作(admin为后续登陆用户）
        int rows=sysUserDao.validById(id, valid,"admin");
        //3.判定结果,并返回
        if(rows==0)
            throw new ServiceException("此记录可能已经不存在");
        return rows;
    }
}
