package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import commons.GlobalConstants;

public class PropertiesConfig {
    private static final Properties PROPS = new Properties();

    //Load file 1 lần duy nhất khi class được nạp vào JVM - thread-safe
    static
    {
        String filePath = GlobalConstants.PROPERTIES_FILE_PATH; // Đường dẫn đến file config.properties
        try (FileInputStream fis = new FileInputStream(filePath)) {
            PROPS.load(fis);
        } catch (IOException ignored) {
        }
    }

    public static String getProp(String key) {
        // Ưu tiên system property vì thường dùng để override trong CI/CD pipeline
        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.isBlank()) {
            return normalizeValue(systemProperty);
        }

        // Tiếp theo là environment variable (thường dùng trong container hoặc khi set trực tiếp trên OS)
        String envRawKey = System.getenv(key);
        if (envRawKey != null && !envRawKey.isBlank()) {
            return normalizeValue(envRawKey);
        }

        // Normalize key để tăng khả năng tìm thấy biến môi trường (e.g. devUrl -> DEVURL)
        String normalizedEnvKey = normalizeKey(key);
        String envNormalizedKey = System.getenv(normalizedEnvKey);
        if (envNormalizedKey != null && !envNormalizedKey.isBlank()) {
            return normalizeValue(envNormalizedKey);
        }

        String value = PROPS.getProperty(key);
        return normalizeValue(value);
    }

    public static String getRequiredProp(String key) {
        String value = getProp(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required config key: '" + key + "'. Please define it in system properties, environment variables, or src/test/resources/config.properties.");
        }
        return value;
    }

    private static String normalizeKey(String key) {
        return key.toUpperCase().replaceAll("[^A-Z0-9]", "_");
    }

    private static String normalizeValue(String value) {
        if (value == null) {
            return null;
        }

        String normalized = value.trim();
        if (normalized.length() >= 2 && normalized.startsWith("\"") && normalized.endsWith("\"")) {
            normalized = normalized.substring(1, normalized.length() - 1).trim();
        }
        return normalized;
    }

}
