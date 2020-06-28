package com.tedu.pj.sys.service;

import com.tedu.pj.common.exception.ServiceException;
import com.tedu.pj.common.vo.Node;
import com.tedu.pj.sys.dao.SysMenuDao;
import com.tedu.pj.sys.dao.SysRoleMenuDao;
import com.tedu.pj.sys.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<SysMenu> findObjects() {
        List<SysMenu> list = sysMenuDao.findObjects();
        if (list==null || list.size()==0)
            throw new ServiceException("No corresponding menu found!");
        return list;
    }

    @Override
    public int deleteObject(Integer id) {
        if (id==null | id<=0)
            throw new IllegalArgumentException("请先选择");
        int count = sysMenuDao.getChildCount(id);
        if (count>0)
            throw new ServiceException("请先删除子菜单！");
        sysRoleMenuDao.deleteObjectsByMenuId(id);
        int rows = sysMenuDao.deleteObject(id);
        if (rows==0)
            throw new ServiceException("此菜单已经不存在！");
        return rows;
    }

    @Override
    public int insertObject(SysMenu entity) {
        //参数校验略
        int rows = sysMenuDao.insertObject(entity);
        return rows;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @Override
    public int updateObject(SysMenu entity) {
        return sysMenuDao.updateObject(entity);
    }


}
