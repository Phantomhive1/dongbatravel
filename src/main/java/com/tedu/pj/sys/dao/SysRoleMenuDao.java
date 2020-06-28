package com.tedu.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {
    int deleteObjectsByMenuId(Integer id);

    @Delete("delete from sys_role_menus where role_id=#{role_id}")
    int deleteObjectsByRoleId(Integer id);

    int insertObjects(Integer roleId, Integer[] menuIds);

    List<Integer> findMenuIdsByRoleIds(@Param("roleIds")Integer[] roleIds);
}
