package com.tedu.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleDao {

    @Delete("delete from sys_user_roles where role_id=#{role_id}")
    int deleteObjectByRoleId(Integer id);

    int insertObjects(@Param("userId")Integer userId, @Param("roleIds")Integer[] roleIds);

    List<Integer> findRoleIdsByUserId(Integer id);

}
