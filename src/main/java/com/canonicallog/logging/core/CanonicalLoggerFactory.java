package com.canonicallog.logging.core;

import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CanonicalLoggerFactory {
    private static final Map<String, Logger> loggers = new ConcurrentHashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, (k) -> new CanonicalLogger(name));
    }
}
