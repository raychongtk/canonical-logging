package com.canonicallog.logging.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CanonicalLogTracer {
    public static final ThreadLocal<CanonicalLogTrace> CANONICAL_LOG = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogTracer.class);

    public CanonicalLogTrace start() {
        CanonicalLogTrace logTrace = new CanonicalLogTrace();
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
            logger.info(logTrace.formatLog().toString());
        } finally {
            CANONICAL_LOG.remove();
        }
    }
}
