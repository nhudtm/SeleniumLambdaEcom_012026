package utils;

import commons.GlobalConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DeviceConfig {
    private Properties properties;
    private String platform;

    public DeviceConfig(String platform) {
        properties = new Properties();
        String configFilePath = GlobalConstants.DEVICE_CONFIG_PATH;
        try {
            properties.load(new FileInputStream(configFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc file cấu hình thiết bị", e);
        }
        this.platform = platform.toLowerCase();
    }

    public String get(String key) {
        return properties.getProperty(platform + "." + key);
    }

    public String getDeviceName() {
        return get("deviceName");
    }

    public String getPlatformVersion() {
        return get("platformVersion");
    }

    public String getPlatformName() {
        return get("platformName");
    }

    public String getAutomationName() {

        return get("automationName");
    }

    public String getBrowserName() {
        return get("browserName");
    }

    public boolean isUseSimulator() {
        return Boolean.parseBoolean(get("useSimulator"));
    }

    public String getUdid() {
        return get("udid");
    }

    public String getPort() {
        return get("port");
    }

    public int getWdaLocalPort() {
        return Integer.parseInt(get("wdaLocalPort"));
    }
}
