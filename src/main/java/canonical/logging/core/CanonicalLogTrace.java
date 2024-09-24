package canonical.logging.core;

import canonical.logging.core.json.JsonMapper;
import canonical.logging.core.mask.LogMasker;
import canonical.logging.core.mask.LogMaskingConfig;
import canonical.logging.core.performance.PerformanceMetric;
import canonical.logging.core.performance.PerformanceWarningConfig;
import canonical.logging.core.util.TypeValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CanonicalLogTrace {
    private final Map<String, List<Object>> logContext;
    private final Map<String, Double> stats;
    private final Map<String, PerformanceMetric> performanceTracking;
    private final PerformanceWarningConfig performanceWarningConfig;
    private final LogMaskingConfig logMaskingConfig;

    public CanonicalLogTrace(PerformanceWarningConfig performanceWarningConfig, LogMaskingConfig logMaskingConfig) {
        this.logContext = new HashMap<>();
        this.stats = new HashMap<>();
        this.performanceTracking = new HashMap<>();
        this.performanceWarningConfig = performanceWarningConfig;
        this.logMaskingConfig = logMaskingConfig;
        put("id", UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        put("start_time", LocalDateTime.now().toString());
    }

    public void put(String key, Object... values) {
        for (Object value : values) {
            List<Object> contextValues = logContext.getOrDefault(key, new ArrayList<>());
            if (logMaskingConfig.containsKey(key) && TypeValidator.isBasicType(value)) {
                contextValues.add(LogMasker.mask(String.valueOf(value)));
            } else if (logMaskingConfig.maskingEnabled() && TypeValidator.isJson(value)) {
                String json = JsonMapper.toJson(value);
                String maskedJson = LogMasker.maskJson(json, logMaskingConfig.getKeys());
                Object object = JsonMapper.fromJson(maskedJson, Object.class);
                contextValues.add(object);
            } else {
                contextValues.add(value);
            }

            logContext.put(key, contextValues);
        }
    }

    public List<Object> get(String key) {
        List<Object> values = logContext.get(key);
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
        for (Map.Entry<String, List<Object>> entry : logContext.entrySet()) {
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
