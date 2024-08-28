package com.canonicallog.logging.core;

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
    private CanonicalLogTracer canonicalLogTracer;

    @Around("@annotation(canonicalLog)")
    public Object log(ProceedingJoinPoint joinPoint, CanonicalLog canonicalLog) throws Throwable {
        try {
            CanonicalLogLine log = canonicalLogTracer.start();
            log.put("class_name", joinPoint.getSignature().getDeclaringType().getName());
            log.put("method_name", joinPoint.getSignature().getName());

            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            log.put("elapsed_time", Strings.format("{} ms", endTime - startTime));
            return result;
        } finally {
            canonicalLogTracer.finish();
        }
    }
}
