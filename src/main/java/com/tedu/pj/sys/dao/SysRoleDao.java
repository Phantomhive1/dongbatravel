package com.tedu.pj.sys.dao;

import com.tedu.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleDao {
    /**
     * 基于角色名统计角色个数
     */
    int getRowCount(@Param("name") String name);

    /**
     * 基于条件查询当前页记录
     */
    List<SysRole> findPageObjects(@Param("name")String name, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

    @Delete("delete fro sys_roles where id=#{id}")
    int deleteObject(Integer id);

    /**
     *  保存角色自身信息
     */
    int insertObject(SysRole entity);
}
