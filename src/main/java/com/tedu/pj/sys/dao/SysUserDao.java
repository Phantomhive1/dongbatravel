package com.tedu.pj.sys.dao;

import com.tedu.pj.common.vo.SysUserDeptVo;
import com.tedu.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserDao {
    int insertObject(SysUser entity);

    int getRowCount( String username);

    List<SysUserDeptVo> findPageObjects(String username, Integer startIndex, Integer pageSize);

    int validById(
            @Param("id")Integer id,
            @Param("valid")Integer valid,
            @Param("modifiedUser")String modifiedUser);

    @Select("select * from sys_users where username=#{username}")
    SysUser findUserByUserName(String username);
}
