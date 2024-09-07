package com.canonicallog.logging.core;

import com.canonicallog.logging.core.json.JsonMapper;
import com.canonicallog.logging.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.time.LocalDateTime;
import java.util.Map;

public class CanonicalLogger implements Logger {
    private static final Logger LOGGER = LoggerFactory.getLogger(CanonicalLogger.class);
    private final String name;

    public CanonicalLogger(String name) {
        this.name = name;
    }

    private String logMessage(String message) {
        CanonicalLogTrace canonicalLogTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (canonicalLogTrace == null) {
            return "canonical log not found";
        }

        Map<String, Object> logs = canonicalLogTrace.aggregateKeyInformation();
        logs.put("end_time", LocalDateTime.now().toString());
        logs.put("log_message", message);
        return JsonMapper.toJson(logs);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void trace(String message) {
        LOGGER.trace(logMessage(message));
    }

    @Override
    public void trace(String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.trace(logMessage(message));
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.trace(logMessage(message));
    }

    @Override
    public void trace(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.trace(logMessage(message));
    }

    @Override
    public void trace(String message, Throwable throwable) {
        LOGGER.trace(logMessage(message), throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(Marker marker, String message) {
        LOGGER.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String message, Throwable throwable) {
        LOGGER.trace(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public void debug(String message) {
        LOGGER.debug(logMessage(message));
    }

    @Override
    public void debug(String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.debug(logMessage(message));
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.debug(logMessage(message));
    }

    @Override
    public void debug(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.debug(logMessage(message));
    }

    @Override
    public void debug(String message, Throwable throwable) {
        LOGGER.debug(logMessage(message), throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public void debug(Marker marker, String message) {
        LOGGER.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String message, Throwable throwable) {
        LOGGER.debug(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public void info(String message) {
        LOGGER.info(logMessage(message));
    }

    @Override
    public void info(String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.info(logMessage(message));
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.info(logMessage(message));
    }

    @Override
    public void info(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.info(logMessage(message));
    }

    @Override
    public void info(String message, Throwable throwable) {
        LOGGER.info(logMessage(message), throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public void info(Marker marker, String message) {
        LOGGER.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String message, Throwable throwable) {
        LOGGER.info(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(String message) {
        LOGGER.warn(logMessage(message));
    }

    @Override
    public void warn(String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.warn(logMessage(message));
    }

    @Override
    public void warn(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.warn(logMessage(message));
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.warn(logMessage(message));
    }

    @Override
    public void warn(String message, Throwable throwable) {
        LOGGER.warn(logMessage(message), throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String message) {
        LOGGER.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String message, Throwable throwable) {
        LOGGER.warn(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public void error(String message) {
        LOGGER.error(logMessage(message));
    }

    @Override
    public void error(String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.error(logMessage(message));
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.error(logMessage(message));
    }

    @Override
    public void error(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.error(logMessage(message));
    }

    @Override
    public void error(String message, Throwable throwable) {
        LOGGER.error(logMessage(message), throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public void error(Marker marker, String message) {
        LOGGER.error(marker, logMessage(message));
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        LOGGER.error(logMessage(message));
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        LOGGER.error(logMessage(message));
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        LOGGER.error(marker, logMessage(message));
    }

    @Override
    public void error(Marker marker, String message, Throwable throwable) {
        LOGGER.error(marker, logMessage(message), throwable);
    }
}
