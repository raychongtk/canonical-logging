package com.canonicallog.logging.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanonicalLogLine {
    private final Map<String, List<String>> logContext;

    public CanonicalLogLine(String id, String startTime) {
        this.logContext = new HashMap<>();
        put("id", id);
        put("start_time", startTime);
    }

    public void put(String key, Object... values) {
        for (Object value : values) {
            List<String> contextValues = logContext.getOrDefault(key, new ArrayList<>());
            contextValues.add(String.valueOf(value));
            logContext.put(key, contextValues);
        }
    }

    public String get(String key) {
        List<String> values = logContext.get(key);
        if (values == null || values.isEmpty()) return null;
        if (values.size() > 1) return values.toString();
        return values.getFirst();
    }

    @Override
    public String toString() {
        Map<String, String> formattedLogs = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : logContext.entrySet()) {
            if (entry.getValue().size() > 1) {
                formattedLogs.put(entry.getKey(), entry.getValue().toString());
            } else {
                formattedLogs.put(entry.getKey(), entry.getValue().getFirst());
            }
        }
        return formattedLogs.toString();
    }
}
