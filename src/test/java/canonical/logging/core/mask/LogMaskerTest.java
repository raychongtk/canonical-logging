package canonical.logging.core.mask;

import org.junit.jupiter.api.Test;

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
}