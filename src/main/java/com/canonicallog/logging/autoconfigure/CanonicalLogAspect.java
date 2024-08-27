package com.canonicallog.logging.autoconfigure;

import com.canonicallog.logging.annotation.CanonicalLog;
import com.canonicallog.logging.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            log.put("elapsed_time", Strings.format("{} ms", endTime - startTime));
            return result;
        } finally {
            canonicalLogger.end();
        }
    }
}
