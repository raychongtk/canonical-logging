package canonical.logging.core.mask;

public class LogMasker {
    private static final String MASK = "******";

    public static String mask(String value) {
        if (value == null || value.isBlank()) return null;
        if (value.length() <= 2) return MASK;
        char first = value.charAt(0);
        char last = value.charAt(value.length() - 1);
        return first + MASK + last;
    }
}
