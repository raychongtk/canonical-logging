package com.canonicallog.logging.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CanonicalLogger {
    public static final ThreadLocal<CanonicalLogLine> CANONICAL_LOG = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogger.class);

    public CanonicalLogLine begin() {
        String id = UUID.randomUUID().toString();
        String startTime = LocalDateTime.now().toString();
        CanonicalLogLine log = new CanonicalLogLine(id, startTime);
        CANONICAL_LOG.set(log);
        return log;
    }

    public CanonicalLogLine get() {
        return CANONICAL_LOG.get();
    }

    public void end() {
        try {
            CanonicalLogLine log = CANONICAL_LOG.get();
            log.put("end_time", LocalDateTime.now().toString());
            logger.info("{}, Canonical Log Line Done", log);
        } finally {
            CANONICAL_LOG.remove();
        }
    }
}