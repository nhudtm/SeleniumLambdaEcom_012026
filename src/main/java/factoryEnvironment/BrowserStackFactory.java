package factoryEnvironment;

import commons.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackFactory implements iEnvFactory {
    WebDriver driver;
    String browserName;
    String browserVersion;
    String osName;
    String osVersion;
    String url;

    public BrowserStackFactory(String browserName, String browserVersion, String osName, String osVersion) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.osName = osName;
        this.osVersion = osVersion;

    }

    public WebDriver createDriver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        capabilities.setCapability("browserName", browserName);
        bstackOptions.put("os", osName);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("browserVersion", browserVersion);
        bstackOptions.put("userName", GlobalConstants.BROWSER_STACK_USERNAME);
        bstackOptions.put("accessKey", GlobalConstants.BROWSER_STACK_ACCESS_KEY);
        bstackOptions.put("consoleLogs", "info");
        bstackOptions.put("projectName", "Lambda Ecommerce");
        bstackOptions.put("buildName", "Lambda Ecommerce");
        bstackOptions.put("sessionName", getClass().getSimpleName());
        capabilities.setCapability("bstack:options", bstackOptions);
        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSERSTACK_URL), capabilities);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return driver;
    }
}
