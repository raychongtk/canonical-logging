package com.canonicallog.logging.context;

import com.canonicallog.logging.core.CanonicalLogTrace;
import com.canonicallog.logging.core.CanonicalLogTracer;
import jakarta.annotation.Nullable;

import java.util.List;

public class CanonicalLogContext {
    public static void put(String key, Object... values) {
        CanonicalLogTrace log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.put(key, values);
        }
    }

    @Nullable
    public static List<String> get(String key) {
        CanonicalLogTrace log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log == null) return null;
        return log.get(key);
    }

    public static void stat(String key, double value) {
        CanonicalLogTrace log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.stat(key, value);
        }
    }

    public static void trackReadOperation(String operation) {
        CanonicalLogTrace log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.trackReadOperation(operation);
        }
    }

    public static void trackWriteOperation(String operation) {
        CanonicalLogTrace log = CanonicalLogTracer.CANONICAL_LOG.get();
        if (log != null) {
            log.trackWriteOperation(operation);
        }
    }
}
