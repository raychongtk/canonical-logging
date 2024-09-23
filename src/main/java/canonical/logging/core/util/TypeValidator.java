package canonical.logging.core.util;

import canonical.logging.core.json.JsonMapper;

public final class TypeValidator {
    public static boolean isBasicType(Object value) {
        return switch (value) {
            case String ignored -> true;
            case Integer ignored -> true;
            case Long ignored -> true;
            case Short ignored -> true;
            case Double ignored -> true;
            case Float ignored -> true;
            default -> false;
        };
    }

    public static boolean isJson(Object value) {
        String json = JsonMapper.toJson(value).trim();
        return (json.startsWith("{") && json.endsWith("}")) || (json.startsWith("[") && json.endsWith("]"));
    }
}
