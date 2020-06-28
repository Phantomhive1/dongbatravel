package com.tedu.pj.sys.dao;

import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.common.vo.SysUserDeptVo;
import com.tedu.pj.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AopTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testSysUserService() {
        PageObject<SysUserDeptVo> po = sysUserService.findPageObjects("admin", 1);
        System.out.println(po.getRowCount());
    }
}
