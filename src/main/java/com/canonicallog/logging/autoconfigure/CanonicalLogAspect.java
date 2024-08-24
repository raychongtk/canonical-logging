package com.canonicallog.logging.autoconfigure;

import com.canonicallog.logging.annotation.CanonicalLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class CanonicalLogAspect {
    @Autowired
    private CanonicalLogger canonicalLogger;

    @Around("@annotation(canonicalLog)")
    public Object log(ProceedingJoinPoint joinPoint, CanonicalLog canonicalLog) throws Throwable {
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
}
