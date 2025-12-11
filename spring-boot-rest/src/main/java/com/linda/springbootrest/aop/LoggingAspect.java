package com.linda.springbootrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER= LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodCall(JoinPoint jp){
        LOGGER.info("Method called "+jp.getSignature().getName());
    }

    @After("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodExecuted(JoinPoint jp){
        LOGGER.info("Method executed "+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodErrorWhileExecution(JoinPoint jp){
        LOGGER.info("Method threw error "+jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodSucessfullyExecuted(JoinPoint jp){
        LOGGER.info("Method executed successfully "+jp.getSignature().getName());
    }
}
