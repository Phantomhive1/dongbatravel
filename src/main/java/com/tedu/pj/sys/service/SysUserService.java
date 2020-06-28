package com.tedu.pj.sys.service;

import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.common.vo.SysUserDeptVo;
import com.tedu.pj.sys.entity.SysUser;

public interface SysUserService {
    PageObject<SysUserDeptVo> findPageObjects(String username,Integer pageCurrent);
    int saveObject(SysUser entity, Integer[] roleIds);
    int validById(Integer id,Integer valid);
}
