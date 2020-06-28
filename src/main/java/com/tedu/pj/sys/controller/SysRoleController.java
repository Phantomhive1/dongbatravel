package com.tedu.pj.sys.controller;

import com.tedu.pj.common.vo.JsonResult;
import com.tedu.pj.sys.entity.SysRole;
import com.tedu.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/doFindPageObjects")
    public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
        return new JsonResult(sysRoleService.findPageObject(name, pageCurrent));
    }

    @RequestMapping("/doDeleteObject")
    public JsonResult doDeleteObject(Integer id) {
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok!");
    }

    @RequestMapping("/doSaveObject")
    public JsonResult doSaveObject(SysRole entity, Integer[] menuIds) {
        sysRoleService.saveObject(entity, menuIds);
        return new JsonResult("Save OK!");
    }
}
