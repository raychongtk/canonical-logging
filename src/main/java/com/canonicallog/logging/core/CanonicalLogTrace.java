package com.canonicallog.logging.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CanonicalLogTrace {
    private final Map<String, List<String>> logContext;
    private final Map<String, Double> stats;
    private final Map<String, PerformanceMetric> performanceTracking;
    private final PerformanceWarningConfig performanceWarningConfig;

    public CanonicalLogTrace(PerformanceWarningConfig performanceWarningConfig) {
        this.logContext = new HashMap<>();
        this.stats = new HashMap<>();
        this.performanceTracking = new HashMap<>();
        this.performanceWarningConfig = performanceWarningConfig;
        put("id", UUID.randomUUID().toString());
        put("start_time", LocalDateTime.now().toString());
    }

    public void put(String key, Object... values) {
        for (Object value : values) {
            List<String> contextValues = logContext.getOrDefault(key, new ArrayList<>());
            contextValues.add(String.valueOf(value));
            logContext.put(key, contextValues);
        }
    }

    public List<String> get(String key) {
        List<String> values = logContext.get(key);
        if (values == null || values.isEmpty()) return new ArrayList<>();
        return values;
    }

    public void stat(String key, double value) {
        stats.compute(key, (k, v) -> v == null ? value : v + value);
    }

    public void trackReadOperation(String operation) {
        PerformanceMetric performance = performanceTracking.getOrDefault(operation, new PerformanceMetric(performanceWarningConfig, operation));
        performance.trackRead();
        performanceTracking.put(operation, performance);
    }

    public void trackWriteOperation(String operation) {
        PerformanceMetric performance = performanceTracking.getOrDefault(operation, new PerformanceMetric(performanceWarningConfig, operation));
        performance.trackWrite();
        performanceTracking.put(operation, performance);
    }

    public Map<String, Object> aggregateKeyInformation() {
        Map<String, Object> formattedLogs = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : logContext.entrySet()) {
            if (entry.getValue().size() > 1) {
                formattedLogs.put(entry.getKey(), entry.getValue().toString());
            } else {
                formattedLogs.put(entry.getKey(), entry.getValue().getFirst());
            }
        }

        formattedLogs.putAll(stats);
        formattedLogs.putAll(performanceTracking);
        return formattedLogs;
    }
}
