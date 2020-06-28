package com.tedu.pj.sys.service;

import com.tedu.pj.common.exception.ServiceException;
import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.sys.dao.SysRoleDao;
import com.tedu.pj.sys.dao.SysRoleMenuDao;
import com.tedu.pj.sys.dao.SysUserRoleDao;
import com.tedu.pj.sys.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public PageObject<SysRole> findPageObject(String name, Integer pageCurrent) {
        if (pageCurrent == null || pageCurrent<1)
            throw new IllegalArgumentException("当前页码值无效");
        int rowCount = sysRoleDao.getRowCount(name);
        if (rowCount == 0)
            throw new ServiceException("没有找到对应的记录");
        int pageSize = 2;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
        //4.对查询结果进行封装并返回
        return new PageObject<>(pageCurrent, pageSize, rowCount, records);
    }

    @Override
    public int deleteObject(Integer id) {
        if (id==null || id<1)
            throw new IllegalArgumentException("id值无效！");
        sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectByRoleId(id);
        int rows = sysRoleDao.deleteObject(id);
        if (rows == 0)
            throw new ServiceException("记录已经可能不存在！");
        return rows;
    }

    @Override
    public int saveObject(SysRole entity, Integer[] menuIds) {
        if (entity == null )
            throw new IllegalArgumentException("保存对象不能为空！");
        if (StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不能为空！");
        if (menuIds==null || menuIds.length==0)
            throw new IllegalArgumentException("需要为角色分配权限！");
        int rows = sysRoleDao.insertObject(entity);
        sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
        return rows;
    }
}
