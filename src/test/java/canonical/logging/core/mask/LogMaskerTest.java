package canonical.logging.core.mask;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LogMaskerTest {
    @Test
    void mask() {
        assertEquals("1******5", LogMasker.mask("12345"));
        assertEquals("a******b", LogMasker.mask("asjdisjdijsosdjosjidb"));
        assertEquals("******", LogMasker.mask("1"));
        assertEquals("******", LogMasker.mask("12"));
        assertEquals("1******3", LogMasker.mask("123"));
        assertNull(LogMasker.mask(""));
        assertNull(LogMasker.mask(null));
    }

    @Test
    void maskJson() {
        LogMasker.PATTERNS.put("password", Pattern.compile("(?<=\"password\":\")(.*?)(?=\")"));
        String singleLevelJson = "{\"username\":\"ray_chong\",\"password\":\"12345\",\"email\":\"ray@example.com\"}";
        String nestedJson = "{\"user\":{\"username\":\"ray_chong\",\"password\":\"12345\",\"email\":\"ray@example.com\"},\"profile\":{\"address\":\"123 Main St\",\"phone\":\"5551234\"}}";
        String emptyJson = "{}";
        String userNameJson = "{\"username\":\"ray_chong\"}";

        assertEquals("{\"username\":\"ray_chong\",\"password\":\"1******5\",\"email\":\"ray@example.com\"}", LogMasker.maskJson(singleLevelJson, "password"));
        assertEquals("{\"user\":{\"username\":\"ray_chong\",\"password\":\"1******5\",\"email\":\"ray@example.com\"},\"profile\":{\"address\":\"123 Main St\",\"phone\":\"5551234\"}}", LogMasker.maskJson(nestedJson, "password"));
        assertEquals("{}", LogMasker.maskJson(emptyJson, "password"));
        assertEquals("{\"username\":\"ray_chong\"}", LogMasker.maskJson(userNameJson, ""));
        assertEquals("{\"username\":\"ray_chong\"}", LogMasker.maskJson(userNameJson, "password"));
    }
}