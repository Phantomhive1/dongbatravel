package com.tedu.pj.sys.dao;

import com.tedu.pj.common.vo.Node;
import com.tedu.pj.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {
    /**
     * 查询菜单表中所有的菜单记录
     * 一行菜单记录映射为一个map对象（key为字段名，值为字段对应值）
     * @return
     */
    List<SysMenu> findObjects();

    @Select("select count(*) from sys_menus where parenId=#{id}")
    int getChildCount(Integer id);

    @Delete("delete from sys_menus where id=#{id}")
    int deleteObject(Integer id);

    List<Node> findZtreeMenuNodes();

    int insertObject(SysMenu entity);
    int updateObject(SysMenu entity);

    List<String> findPermissions(@Param("menuIds") Integer[] menuIds);

}
