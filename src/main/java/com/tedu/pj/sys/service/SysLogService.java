package com.tedu.pj.sys.service;

import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.sys.entity.SysLog;

public interface SysLogService {
    PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);
    int deleteObjects(Integer...ids);
    void saveObject(SysLog entity);
}
