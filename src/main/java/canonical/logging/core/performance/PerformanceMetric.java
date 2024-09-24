package canonical.logging.core.performance;

import canonical.logging.core.CanonicalLoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PerformanceMetric {
    private static final Logger LOGGER = CanonicalLoggerFactory.getLogger(PerformanceMetric.class);

    private final PerformanceWarningConfig performanceWarningConfig;

    @JsonIgnore
    private final String operation;
    public int totalReadWrite;
    public int readCount;
    public int writeCount;

    public PerformanceMetric(@Nullable PerformanceWarningConfig performanceWarningConfig, String operation) {
        this.performanceWarningConfig = performanceWarningConfig;
        this.operation = operation;
    }

    public void trackRead() {
        readCount++;
        totalReadWrite++;

        if (performanceWarningConfig != null) {
            if (totalReadWrite > performanceWarningConfig.maxTotalReadWrite) {
                LOGGER.warn("high total I/O, operation={}, current count={}, max count={}", operation, totalReadWrite, performanceWarningConfig.maxTotalReadWrite);
            }

            if (readCount > performanceWarningConfig.maxReadCount) {
                LOGGER.warn("high read I/O, operation={}, current count={}, max count={}", operation, readCount, performanceWarningConfig.maxReadCount);
            }
        }
    }

    public void trackWrite() {
        writeCount++;
        totalReadWrite++;

        if (performanceWarningConfig != null) {
            if (totalReadWrite > performanceWarningConfig.maxTotalReadWrite) {
                LOGGER.warn("high total I/O, operation={}, current count={}, max count={}", operation, totalReadWrite, performanceWarningConfig.maxTotalReadWrite);
            }

            if (writeCount > performanceWarningConfig.maxWriteCount) {
                LOGGER.warn("high write I/O, operation={}, current count={}, max count={}", operation, writeCount, performanceWarningConfig.maxWriteCount);
            }
        }
    }
}
