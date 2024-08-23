package com.canonicallog.logging;

import com.canonicallog.logging.log.CanonicalLogLine;
import com.canonicallog.logging.log.CanonicalLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CanonicalLogAspect {
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogAspect.class);

    @Autowired
    private CanonicalLogger canonicalLogger;

    @Pointcut("@annotation(com.canonicallog.logging.annotation.CanonicalLog)")
    public void canonicalLog() {

    }

    @Around("canonicalLog()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            CanonicalLogLine log = canonicalLogger.begin();
            log.put("controller", joinPoint.getSignature().getDeclaringType().getName());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object result = joinPoint.proceed();
            stopWatch.stop();

            log.put("elapsed_time", String.valueOf(stopWatch.getTotalTimeNanos()));
            return result;
        } finally {
            canonicalLogger.end();
        }
    }

    @AfterThrowing(value = "canonicalLog()", throwing = "ex")
    public void logThrowing(Exception ex) {
        logger.error(ex.getMessage(), ex);
    }
}
