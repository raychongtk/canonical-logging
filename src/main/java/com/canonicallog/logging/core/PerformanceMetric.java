package com.canonicallog.logging.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceMetric {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMetric.class);

    private final PerformanceWarningConfig performanceWarningConfig;
    public int totalReadWrite;
    public int readCount;
    public int writeCount;
    public PerformanceMetric(PerformanceWarningConfig performanceWarningConfig) {
        this.performanceWarningConfig = performanceWarningConfig;
    }

    public void trackRead() {
        readCount++;
        totalReadWrite++;

        if (performanceWarningConfig != null) {
            if (totalReadWrite > performanceWarningConfig.maxTotalReadWrite) {
                LOGGER.warn("total I/O read/write count exceeds configured maxTotalReadWrite, current count={}, max count={}", totalReadWrite, performanceWarningConfig.maxTotalReadWrite);
            }

            if (readCount > performanceWarningConfig.maxReadCount) {
                LOGGER.warn("read count exceeds configured maxReadCount, current count={}, max count={}", readCount, performanceWarningConfig.maxReadCount);
            }
        }
    }

    public void trackWrite() {
        writeCount++;
        totalReadWrite++;

        if (performanceWarningConfig != null) {
            if (totalReadWrite > performanceWarningConfig.maxTotalReadWrite) {
                LOGGER.warn("total I/O read/write count exceeds configured maxTotalReadWrite, current count={}, max count={}", totalReadWrite, performanceWarningConfig.maxTotalReadWrite);
            }

            if (writeCount > performanceWarningConfig.maxWriteCount) {
                LOGGER.warn("write count exceeds configured maxReadCount, current count={}, max count={}", writeCount, performanceWarningConfig.maxWriteCount);
            }
        }
    }
}
