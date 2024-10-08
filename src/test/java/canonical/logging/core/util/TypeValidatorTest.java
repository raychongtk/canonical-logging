package canonical.logging.core.util;

import canonical.logging.core.CanonicalLogConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TypeValidatorTest {
    @Test
    void isBasicType() {
        short shortValue = 1;
        long longValue = 1;
        int intValue = 1;
        double doubleValue = 1;
        float floatValue = 1;
        String stringValue = "stringValue";
        var stringBuilder = new StringBuilder();

        assertTrue(TypeValidator.isBasicType(shortValue));
        assertTrue(TypeValidator.isBasicType(longValue));
        assertTrue(TypeValidator.isBasicType(intValue));
        assertTrue(TypeValidator.isBasicType(doubleValue));
        assertTrue(TypeValidator.isBasicType(floatValue));
        assertTrue(TypeValidator.isBasicType(stringValue));
        assertFalse(TypeValidator.isBasicType(stringBuilder));
    }

    @Test
    void isJson() {
        assertTrue(TypeValidator.isJson(new CanonicalLogConfig()));
        assertFalse(TypeValidator.isJson("stringValue"));
    }
}