package com.tedu.pj.sys.controller;

import com.tedu.pj.common.vo.JsonResult;
import com.tedu.pj.sys.entity.SysUser;
import com.tedu.pj.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/doFindPageObjects")
    public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
        return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
    }

    @RequestMapping("/doSaveObject")
    public JsonResult doSaveObject(SysUser entity, Integer[] roleIds){
        sysUserService.saveObject(entity,roleIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("doValidById")
    public JsonResult doValidById(Integer id,Integer valid){
        sysUserService.validById(id,valid);
        return new JsonResult("update ok");
    }

    @RequestMapping("/doLogin")
    public JsonResult doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return new JsonResult("login oK!");
    }
}
