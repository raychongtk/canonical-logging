package canonical.logging.core.mask;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Configuration
@ConfigurationProperties(prefix = "logging.canonical.masking")
public class LogMaskingConfig {
    private Set<String> keys = new HashSet<>();

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
        initJsonMaskingPattern(keys);
    }

    public boolean containsKey(String key) {
        return keys.contains(key);
    }

    public boolean maskingEnabled() {
        return !keys.isEmpty();
    }

    private void initJsonMaskingPattern(Set<String> maskingKeys) {
        for (String key : maskingKeys) {
            Pattern pattern = Pattern.compile("(?<=\"" + key + "\":\")(.*?)(?=\")");
            LogMasker.PATTERNS.put(key, pattern);
        }
    }
}
