package com.tedu.pj.sys.dao;

import com.tedu.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogDao {
    int getRowCount(String username);

    List<SysLog> findPageObjects(String username, Integer startIndex, Integer pageSize);

    int deleteObjects(@Param("ids")Integer...ids);

    int insertObject(SysLog entity);
}
