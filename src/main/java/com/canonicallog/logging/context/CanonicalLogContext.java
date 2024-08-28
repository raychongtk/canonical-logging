package com.canonicallog.logging.context;

import com.canonicallog.logging.core.CanonicalLogLine;
import com.canonicallog.logging.core.CanonicalLogTracer;

import java.util.List;

public class CanonicalLogContext {
    public static void put(String key, Object... values) {
        CanonicalLogLine log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.put(key, values);
        }
    }

    public static List<String> get(String key) {
        CanonicalLogLine log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log == null) return null;
        return log.get(key);
    }

    public static void stat(String key, double value) {
        CanonicalLogLine log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.stat(key, value);
        }
    }

    public static void increase(String key) {
        CanonicalLogLine log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.stat(key, 1);
        }
    }
}
