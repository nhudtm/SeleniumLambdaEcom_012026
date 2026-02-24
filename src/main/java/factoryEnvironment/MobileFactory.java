package factoryEnvironment;

import utils.DeviceConfig;
import commons.BrowserList;
import org.openqa.selenium.WebDriver;

public class MobileFactory implements iEnvFactory {
    private WebDriver driver;
    private String browserName;
    private String osName; // là platform (ios or android)
    DeviceConfig deviceConfig;

    public MobileFactory(String browserName, String osName) {
        this.browserName = browserName;
        this.osName = osName;
    }

    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        deviceConfig = new DeviceConfig(osName);
        switch (browserList) {
            case MOBILE_SAFARI:
                driver = new factoryBrowser.MobileSafariDriverManager(deviceConfig).getBrowserDriver();
                break;
            case MOBILE_CHROME:
                driver = new factoryBrowser.MobileChromeDriverManager(deviceConfig).getBrowserDriver();
            default:
                throw new RuntimeException("Please input valid browser name value!");
        }
        return driver;
    }
}
