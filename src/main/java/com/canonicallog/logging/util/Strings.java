package com.canonicallog.logging.util;

import org.slf4j.helpers.MessageFormatter;

public class Strings {
    public static String format(String pattern, Object... args) {
        return MessageFormatter.arrayFormat(pattern, args).getMessage();
    }
}
