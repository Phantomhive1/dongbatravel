package com.tedu.pj.sys.controller;

import com.tedu.pj.common.vo.JsonResult;
import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.sys.entity.SysLog;
import com.tedu.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObject(String username, Integer pageCurrent) {
        PageObject<SysLog> pageObjects = sysLogService.findPageObjects(username, pageCurrent);
        return new JsonResult(pageObjects);
    }

    @RequestMapping("/doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer...ids) {
        sysLogService.deleteObjects(ids);
        return new JsonResult("delete ok!");
    }

    /**
     * @ExceptionHandler注解描述的方法为控制层的异常处理方法，此注解中传入的异常类型，为他描述的方法可以处理的异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new JsonResult(e);
    }
}
