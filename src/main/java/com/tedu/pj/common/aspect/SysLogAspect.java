package com.tedu.pj.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.pj.common.utils.IPUtils;
import com.tedu.pj.sys.entity.SysLog;
import com.tedu.pj.sys.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    private Logger log = Logger.getLogger(SysLogAspect.class);

    @Pointcut("bean(sysUserServiceImpl)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("SysLogService.doAround()");
        long startTime = System.currentTimeMillis();
        try {
            Object result = jp.proceed();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            saveLog(jp, totalTime); //将用户行为信息写到数据库中
            return result;
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint point, long totalTime) throws Exception {
        //1.获取用户行为信息
        String ip = IPUtils.getIpAddr();
        String username = "admin";
        //1.3获取登录用户名
        //1.3.1获取目标方法对象
        Class<?> targetClass = point.getTarget().getClass();
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method targetMethod = targetClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        //1.3.2获取目标方法对象上的RequiredLog注解
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        //1.3.3获取注解中指定的操作名
        String operation = null;
        if (requiredLog!=null) {
            operation = requiredLog.operation();

        }
        //1.4获取目标方法的类全名以及方法名
        String method = targetClass.getName() + "." + targetMethod.getName();
        //1.5获取执行方法时传入的实际参数
        String params = new ObjectMapper().writeValueAsString(point.getArgs());

        //2.对用户行为信息进行封装
        SysLog userLog = new SysLog();
        userLog.setIp(ip);
        userLog.setUsername(username);
        userLog.setOperation(operation);
        userLog.setMethod(method);
        userLog.setParams(params);
        userLog.setTime(totalTime);
        userLog.setCreatedTime(new Date());

        //3.将用户行为信息写入数据库
        sysLogService.saveObject(userLog);

    }
}
