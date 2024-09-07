package com.canonicallog.logging.core;

import com.canonicallog.logging.core.json.JsonMapper;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CanonicalLogTracer {
    public static final ThreadLocal<CanonicalLogTrace> CANONICAL_LOG = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogTracer.class);

    private final PerformanceWarningConfig performanceWarningConfig;

    public CanonicalLogTracer(@Nullable PerformanceWarningConfig performanceWarningConfig) {
        this.performanceWarningConfig = performanceWarningConfig;
    }

    public CanonicalLogTrace start() {
        CanonicalLogTrace logTrace = new CanonicalLogTrace(performanceWarningConfig);
        CANONICAL_LOG.set(logTrace);
        return logTrace;
    }

    public CanonicalLogTrace get() {
        return CANONICAL_LOG.get();
    }

    public void finish() {
        try {
            CanonicalLogTrace logTrace = CANONICAL_LOG.get();
            logTrace.put("end_time", LocalDateTime.now().toString());
            logTrace.put("log_message", "Canonical Log Line Done");
            String logMessage = JsonMapper.toJson(logTrace.formatLog());
            logger.info(logMessage);
        } finally {
            CANONICAL_LOG.remove();
        }
    }
}
