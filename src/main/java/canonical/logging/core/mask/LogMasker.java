package canonical.logging.core.mask;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogMasker {
    private static final String MASK = "******";

    public static String mask(String value) {
        if (value == null || value.isBlank()) return null;
        if (value.length() <= 2) return MASK;
        char first = value.charAt(0);
        char last = value.charAt(value.length() - 1);
        return first + MASK + last;
    }

    public static String maskJson(String json, Set<String> keys) {
        for (String key : keys) {
            json = maskJson(json, key);
        }
        return json;
    }

    public static String maskJson(String json, String keyToMask) {
        if (json == null || json.isBlank()) return null;
        Pattern pattern = Pattern.compile("(?<=\"" + keyToMask + "\":\")(.*?)(?=\")");
        Matcher matcher = pattern.matcher(json);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            String value = matcher.group(1);
            matcher.appendReplacement(stringBuilder, mask(value));
        }
        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }
}
