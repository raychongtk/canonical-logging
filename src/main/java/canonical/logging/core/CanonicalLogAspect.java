package canonical.logging.core;

import canonical.logging.annotation.CanonicalLog;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

            attemptPutHttpMetadata(logTrace);

            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            logTrace.put("elapsed_time", endTime - startTime);
            return result;
        } finally {
            canonicalLogTracer.finish();
        }
    }

    private void attemptPutHttpMetadata(CanonicalLogTrace logTrace) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            if (servletRequestAttributes != null) {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                logTrace.put("http_method", request.getMethod());
                logTrace.put("uri", request.getRequestURI());
                String queryString = request.getQueryString();
                if (queryString != null) {
                    logTrace.put("query_string", queryString);
                }
            }
        } catch (Exception ignore) {
        }
    }
}
