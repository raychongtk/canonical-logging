package com.canonicallog.logging.core.util;

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
}
