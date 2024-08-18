package com.canonicallog.canonicallogging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Order(0)
public class CanonicalLogAspect {
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogAspect.class);

    @Autowired
    private CanonicalLogger canonicalLogger;

    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||"
                    + "@annotation(org.springframework.web.bind.annotation.PutMapping) ||"
                    + "@annotation(org.springframework.web.bind.annotation.GetMapping) ||"
                    + "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||"
                    + "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void httpMethodMapping() {

    }

    @Pointcut("@annotation(com.canonicallog.canonicallogging.CanonicalLog)")
    public void canonicalLog() {

    }

    @Around("httpMethodMapping() || canonicalLog()")
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

    @AfterThrowing(value = "httpMethodMapping()", throwing = "ex")
    public void logThrowing(Exception ex) {
        logger.error(ex.getMessage(), ex);
    }
}
