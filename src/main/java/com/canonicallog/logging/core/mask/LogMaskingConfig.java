package com.canonicallog.logging.core.mask;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "logging.canonical.masking")
public class LogMaskingConfig {
    private Set<String> keys = new HashSet<>();

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
    }

    public boolean containsKey(String key) {
        return keys.contains(key);
    }
}
