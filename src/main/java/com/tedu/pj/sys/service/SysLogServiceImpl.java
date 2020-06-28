package com.tedu.pj.sys.service;

import com.tedu.pj.common.exception.ServiceException;
import com.tedu.pj.common.vo.PageObject;
import com.tedu.pj.sys.dao.SysLogDao;
import com.tedu.pj.sys.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
        if (pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("当前页码不正确");
        int rowCount = sysLogDao.getRowCount(username);
        if (rowCount == 0) throw new ServiceException("无记录");
        int pageSize = 2;
        int startIndex = (pageCurrent-1) * pageSize;
        List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);

        PageObject<SysLog> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount-1)/pageSize+1);
        return pageObject;
    }

    @Override
    public int deleteObjects(Integer... ids) {
        if (ids==null || ids.length==0)
            throw new IllegalArgumentException("参数值无效！");
        int rows = sysLogDao.deleteObjects(ids);
        if (rows == 0)
            throw new ServiceException("记录不存在！");
        return rows;
    }

    @Override
    public void saveObject(SysLog entity) {
        sysLogDao.insertObject(entity);
    }
}
