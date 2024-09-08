package com.canonicallog.logging.core.performance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceMetric {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMetric.class);

    private final PerformanceWarningConfig performanceWarningConfig;

    @JsonIgnore
    private final String operation;
    @JsonProperty("total_read_write")
    public int totalReadWrite;
    @JsonProperty("read_count")
    public int readCount;
    @JsonProperty("write_count")
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