package canonical.logging.core;

import canonical.logging.core.json.JsonMapper;
import canonical.logging.core.mask.LogMaskingConfig;
import canonical.logging.core.performance.PerformanceWarningConfig;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CanonicalLogTracer {
    public static final ThreadLocal<CanonicalLogTrace> CANONICAL_LOG = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CanonicalLogTracer.class);

    private final PerformanceWarningConfig performanceWarningConfig;
    private final LogMaskingConfig logMaskingConfig;

    public CanonicalLogTracer(@Nullable PerformanceWarningConfig performanceWarningConfig, LogMaskingConfig logMaskingConfig) {
        this.performanceWarningConfig = performanceWarningConfig;
        this.logMaskingConfig = logMaskingConfig;
    }

    public CanonicalLogTrace start() {
        CanonicalLogTrace logTrace = new CanonicalLogTrace(performanceWarningConfig, logMaskingConfig);
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
            logTrace.put("log_message", "canonical log done");
            String logMessage = JsonMapper.toJson(logTrace.aggregateKeyInformation());
            LOGGER.info(logMessage);
        } finally {
            CANONICAL_LOG.remove();
        }
    }
}
