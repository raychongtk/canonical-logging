package com.canonicallog.logging.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CanonicalLogger {
    public static final ThreadLocal<CanonicalLogLine> CANONICAL_LOG = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogger.class);

    public CanonicalLogLine begin() {
        CanonicalLogLine log = new CanonicalLogLine();
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
            log.put("log_message", "Canonical Log Line Done");
            logger.info("{}", log.formatLog());
        } finally {
            CANONICAL_LOG.remove();
        }
    }
}
