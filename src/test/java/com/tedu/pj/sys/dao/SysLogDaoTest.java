package com.tedu.pj.sys.dao;

import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.sys.entity.SysLog;
import com.tedu.pj.sys.service.SysLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysLogDaoTest {
    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    private SysLogService sysLogService;

    @Test
    void testGetRowCount() {
        int count = sysLogDao.getRowCount("admin");
        System.out.println(count);
    }

    @Test
    public void testFindPageObject() {
        List<SysLog> logs = sysLogDao.findPageObjects("admin", 0, 3);
        for (SysLog log:logs) {
            System.out.println(log);
        }
    }

    @Test
    public void testFindPageObjects() {

        PageObject<SysLog> pageObjects = sysLogService.findPageObjects("admin", 2);

        System.out.println(pageObjects);
    }

}
