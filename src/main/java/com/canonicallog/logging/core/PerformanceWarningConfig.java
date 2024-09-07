package com.canonicallog.logging.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "logging.canonical.performance.warning.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "logging.canonical.performance.warning")
public class PerformanceWarningConfig {
    public int maxReadCount = 5;
    public int maxWriteCount = 5;
    public int maxTotalReadWrite = 10;

    public int getMaxReadCount() {
        return maxReadCount;
    }

    public void setMaxReadCount(int maxReadCount) {
        this.maxReadCount = maxReadCount;
    }

    public int getMaxWriteCount() {
        return maxWriteCount;
    }

    public void setMaxWriteCount(int maxWriteCount) {
        this.maxWriteCount = maxWriteCount;
    }

    public int getMaxTotalReadWrite() {
        return maxTotalReadWrite;
    }

    public void setMaxTotalReadWrite(int maxTotalReadWrite) {
        this.maxTotalReadWrite = maxTotalReadWrite;
    }
}
