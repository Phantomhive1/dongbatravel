package com.tedu.pj.sys.service;

import com.tedu.pj.common.vo.Node;
import com.tedu.pj.sys.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    //查询所有菜单以及此菜单对应的上级菜单
    List<SysMenu> findObjects();

    int deleteObject(Integer id);

    int insertObject(SysMenu entity);

    List<Node> findZtreeMenuNodes();

    int updateObject(SysMenu entity);
}
