package com.tedu.pj.sys.service;

import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.sys.entity.SysRole;

public interface SysRoleService {
    PageObject<SysRole> findPageObject(String name, Integer pageCurrent);

    //基于角色id删除角色关系数据以及自身信息
    int deleteObject(Integer id);
    
    int saveObject(SysRole entity, Integer[] menuIds);
}
