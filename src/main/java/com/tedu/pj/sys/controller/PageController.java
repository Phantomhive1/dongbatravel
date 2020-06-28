package com.tedu.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/doIndexUI")
    public String doIndexUI() {
        return "starter";
    }

    @RequestMapping("/doPageUI")
    public String doPageUI() {
        return "common/page";
    }

    @RequestMapping("/doLoginUI")
    public String doLoginUI() {
        return "login";
    }

//    @RequestMapping("/log/log_list")
//    public String doLogUI() {
//        return "sys/log_list";
//    }
//
//    @RequestMapping("/menu/menu_list")
//    public String doMenuUI() {
//        return "sys/menu_list";
//    }

    //rest风格的URL定义：优先级比较低
    //@PathVariable 注解用于获取url中与方法参数相同的变量值
    @RequestMapping("/{module}/{moduleUI}")
    public String doMenuUI(@PathVariable String moduleUI) {
        return "sys/"+moduleUI;
    }
}
