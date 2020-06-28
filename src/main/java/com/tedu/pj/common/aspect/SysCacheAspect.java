package com.tedu.pj.common.aspect;

import com.tedu.pj.common.cache.DefaultMapCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysCacheAspect {

    @Autowired
    private DefaultMapCache mapCache;

    @Pointcut("@annotation(com.tedu.pj.common.aspect.RequiredCache)")
    public void doCache() {}

    @Pointcut("@annotation(com.tedu.pj.common.aspect.ClearCache)")
    public void doClear(){}

    @AfterReturning("doClear()")
    public void doAfterReturning() {
        mapCache.clear();
    }

    @Around("doCache()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        //System.out.println("Get data from cache.");
        Object obj = mapCache.getObject("deptCache");
        if (obj!=null) return obj;
        Object result = jp.proceed();
        //System.out.println("put data to cache.");

        mapCache.putObject("deptCache", result);
        return result;
    }

}
