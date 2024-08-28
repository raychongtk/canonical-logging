package com.canonicallog.logging.autoconfigure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CanonicalLogLine {
    private final Map<String, List<String>> logContext;
    private final Map<String, Double> stats;

    public CanonicalLogLine() {
        this.logContext = new HashMap<>();
        this.stats = new HashMap<>();
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
        if (values == null || values.isEmpty()) return null;
        return values;
    }

    public void stat(String key, double value) {
        stats.compute(key, (k, v) -> v == null ? value : v + value);
    }

    public String formatLog() {
        Map<String, String> formattedLogs = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : logContext.entrySet()) {
            if (entry.getValue().size() > 1) {
                formattedLogs.put(entry.getKey(), entry.getValue().toString());
            } else {
                formattedLogs.put(entry.getKey(), entry.getValue().getFirst());
            }
        }

        for (Map.Entry<String, Double> entry : stats.entrySet()) {
            formattedLogs.put(entry.getKey(), entry.getValue().toString());
        }
        return formattedLogs.toString();
    }
}
