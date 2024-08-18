package com.canonicallog.canonicallogging;

public class CanonicalLogContext {
    public static void put(String key, Object... values) {
        CanonicalLogLine log = CanonicalLogger.CANONICAL_LOG.get();
        if (log != null) {
            log.put(key, values);
        }
    }

    public static String get(String key) {
        CanonicalLogLine log = CanonicalLogger.CANONICAL_LOG.get();
        if (log == null) return null;
        return log.get(key);
    }
}
