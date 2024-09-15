package canonical.logging.context;

import canonical.logging.core.CanonicalLogTrace;
import canonical.logging.core.CanonicalLogTracer;
import jakarta.annotation.Nullable;

import java.util.List;

public class CanonicalLogContext {
    public static void put(String key, Object... values) {
        CanonicalLogTrace logTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (logTrace != null) {
            logTrace.put(key, values);
        }
    }

    @Nullable
    public static List<Object> get(String key) {
        CanonicalLogTrace logTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (logTrace == null) return null;
        return logTrace.get(key);
    }

    public static void stat(String key, double value) {
        CanonicalLogTrace logTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (logTrace != null) {
            logTrace.stat(key, value);
        }
    }

    public static void trackReadOperation(String operation) {
        CanonicalLogTrace logTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (logTrace != null) {
            logTrace.trackReadOperation(operation);
        }
    }

    public static void trackWriteOperation(String operation) {
        CanonicalLogTrace logTrace = CanonicalLogTracer.CANONICAL_LOG.get();
        if (logTrace != null) {
            logTrace.trackWriteOperation(operation);
        }
    }
}
