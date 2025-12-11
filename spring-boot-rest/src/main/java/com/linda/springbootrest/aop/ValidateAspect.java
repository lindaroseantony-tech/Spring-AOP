package com.linda.springbootrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateAspect {
    private static final Logger LOGGER= LoggerFactory.getLogger(ValidateAspect.class);
    @Around("execution(* com.linda.springbootrest.service.JobService.getJob(..)) &&  args(postId)")
    public Object validateInput(ProceedingJoinPoint pjp, int postId) throws Throwable {
        if(postId<0){
            LOGGER.info("Negative JobId value");
            postId=-postId;
            LOGGER.info("Post ID value is changed");
        }
        Object obj=pjp.proceed(new Object[]{postId});
        return obj;
    }
}
