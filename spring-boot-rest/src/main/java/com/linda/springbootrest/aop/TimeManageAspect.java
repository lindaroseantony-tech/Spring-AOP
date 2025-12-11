package com.linda.springbootrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeManageAspect {
    private final static Logger LOGGER= LoggerFactory.getLogger(TimeManageAspect.class);
    @Around("execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public Object calculateTime(ProceedingJoinPoint pjp) throws Throwable {
        long start=System.currentTimeMillis();
        Object obj=pjp.proceed();
        long end=System.currentTimeMillis();
        LOGGER.info("Time taken by "+pjp.getSignature().getName()+" "+(end-start)+" ms");
        return obj;
    }
}
