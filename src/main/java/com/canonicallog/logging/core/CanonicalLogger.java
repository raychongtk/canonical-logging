package com.canonicallog.logging.core;

import com.canonicallog.logging.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.time.LocalDateTime;
import java.util.Map;

public class CanonicalLogger implements Logger {
    private final Logger logger = LoggerFactory.getLogger(CanonicalLogger.class);
    private final String name;

    public CanonicalLogger(String name) {
        this.name = name;
    }

    private String logMessage(String message) {
        CanonicalLogTrace canonicalLogTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (canonicalLogTrace == null) {
            return "canonical log not found";
        }
        Map<String, String> logs = canonicalLogTrace.formatLog();
        logs.put("end_time", LocalDateTime.now().toString());
        logs.put("log_message", message);
        return logs.toString();
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
        logger.trace(logMessage(message));
    }

    @Override
    public void trace(String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.trace(logMessage(message));
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.trace(logMessage(message));
    }

    @Override
    public void trace(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.trace(logMessage(message));
    }

    @Override
    public void trace(String message, Throwable throwable) {
        logger.trace(logMessage(message), throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(Marker marker, String message) {
        logger.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.trace(marker, logMessage(message));
    }

    @Override
    public void trace(Marker marker, String message, Throwable throwable) {
        logger.trace(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public void debug(String message) {
        logger.debug(logMessage(message));
    }

    @Override
    public void debug(String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.debug(logMessage(message));
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.debug(logMessage(message));
    }

    @Override
    public void debug(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.debug(logMessage(message));
    }

    @Override
    public void debug(String message, Throwable throwable) {
        logger.debug(logMessage(message), throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public void debug(Marker marker, String message) {
        logger.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.debug(marker, logMessage(message));
    }

    @Override
    public void debug(Marker marker, String message, Throwable throwable) {
        logger.debug(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public void info(String message) {
        logger.info(logMessage(message));
    }

    @Override
    public void info(String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.info(logMessage(message));
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.info(logMessage(message));
    }

    @Override
    public void info(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.info(logMessage(message));
    }

    @Override
    public void info(String message, Throwable throwable) {
        logger.info(logMessage(message), throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public void info(Marker marker, String message) {
        logger.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.info(marker, logMessage(message));
    }

    @Override
    public void info(Marker marker, String message, Throwable throwable) {
        logger.info(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(String message) {
        logger.warn(logMessage(message));
    }

    @Override
    public void warn(String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.warn(logMessage(message));
    }

    @Override
    public void warn(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.warn(logMessage(message));
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.warn(logMessage(message));
    }

    @Override
    public void warn(String message, Throwable throwable) {
        logger.warn(logMessage(message), throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String message) {
        logger.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.warn(marker, logMessage(message));
    }

    @Override
    public void warn(Marker marker, String message, Throwable throwable) {
        logger.warn(marker, logMessage(message), throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public void error(String message) {
        logger.error(logMessage(message));
    }

    @Override
    public void error(String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.error(logMessage(message));
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.error(logMessage(message));
    }

    @Override
    public void error(String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.error(logMessage(message));
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.error(logMessage(message), throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public void error(Marker marker, String message) {
        logger.error(marker, logMessage(message));
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        String message = Strings.format(format, arg);
        logger.error(logMessage(message));
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        String message = Strings.format(format, arg1, arg2);
        logger.error(logMessage(message));
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        String message = Strings.format(format, arguments);
        logger.error(marker, logMessage(message));
    }

    @Override
    public void error(Marker marker, String message, Throwable throwable) {
        logger.error(marker, logMessage(message), throwable);
    }
}
