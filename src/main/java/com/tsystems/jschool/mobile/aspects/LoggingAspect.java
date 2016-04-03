package com.tsystems.jschool.mobile.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getRootLogger();

    @Before("execution(* com.tsystems.jschool.mobile.services.CustomUserDetailsService.loadUserByUsername(..))")
    public void logTryAuthentication(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.warn(String.format("Trying to authenticate by user %s", args[0]));
    }

    @After("execution(* com.tsystems.jschool.mobile.services.CustomUserDetailsService.loadUserByUsername(..))")
    public void logAuthentication(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.warn(String.format("User authenticated as %s", args[0]));
    }

    @Before("execution(* com.tsystems.jschool.mobile.services.Impl.*.change*(..))")
    public void logTryToChange(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.warn(String.format("Trying to change on method %s with parameters: %s", methodName, args));
    }

    @After("execution(* com.tsystems.jschool.mobile.services.Impl.*.change*(..))")
    public void logChanged(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.warn(String.format("Changed object with id: %s in method %s", args[0], methodName));
    }

    @AfterThrowing(value = "execution(* com.tsystems.jschool.mobile.*.*.*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception thrown in method " + methodName + ", message: " + ex.getMessage());
    }

}

