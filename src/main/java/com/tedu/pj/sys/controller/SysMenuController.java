package com.tedu.pj.sys.controller;

import com.tedu.pj.common.vo.JsonResult;
import com.tedu.pj.sys.entity.SysMenu;
import com.tedu.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/doFindObjects")
    public JsonResult doFindObjects() {
        return new JsonResult(sysMenuService.findObjects());
    }

    @RequestMapping("/doDeleteObject")
    public JsonResult doDeleteObject(Integer id) {
        sysMenuService.deleteObject(id);
        return new JsonResult("delete ok!");
    }

    @RequestMapping("/doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes() {
        return new JsonResult(sysMenuService.findZtreeMenuNodes());
    }

    @RequestMapping("/doSaveObject")
    public JsonResult doSaveObject(SysMenu entity) {
        sysMenuService.insertObject(entity);
        return new JsonResult("insert ok!");
    }

    @RequestMapping("/doUpdateObject")
    public JsonResult doUpdateObject(SysMenu entity) {
        sysMenuService.updateObject(entity);
        return new JsonResult("update ok!");
    }
}

