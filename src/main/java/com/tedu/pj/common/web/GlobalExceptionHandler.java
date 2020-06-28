package com.tedu.pj.common.web;

import com.tedu.pj.common.vo.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new JsonResult(e);
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult doHandleShiroException(ShiroException e) {
        JsonResult j = new JsonResult();
        j.setState(0);
        if (e instanceof UnknownAccountException) {
            j.setMessage("账户不存在");
        }else if(e instanceof LockedAccountException) {
            j.setMessage("账户已被禁用");
        }else if(e instanceof IncorrectCredentialsException) {
            j.setMessage("密码不正确");
        }else if(e instanceof AuthorizationException) {
            j.setMessage("没有此操作权限");
        }else {
            j.setMessage("系统维护中");
        }
        e.printStackTrace();
        return j;
    }
}
