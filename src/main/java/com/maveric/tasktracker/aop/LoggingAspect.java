package com.maveric.tasktracker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.maveric.tasktracker.constants.AppConstants.*;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(LOGGING_POINTCUT_PATH)
    public void logging() {
    }

    @Before(LOGGING_POINTCUT_NAME)
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info(LOGGING_BEFORE, methodName, className);
    }

    @After(LOGGING_POINTCUT_NAME)
    public void logMethodExit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info(LOGGING_AFTER, methodName, className);
    }
}