package com.canonicallog.logging.core;

import com.canonicallog.logging.annotation.CanonicalLog;
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
            CanonicalLogTrace logTrace = canonicalLogTracer.start();
            logTrace.put("class_name", joinPoint.getSignature().getDeclaringType().getName());
            logTrace.put("method_name", joinPoint.getSignature().getName());

            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            logTrace.put("elapsed_time", endTime - startTime);
            return result;
        } finally {
            canonicalLogTracer.finish();
        }
    }
}
