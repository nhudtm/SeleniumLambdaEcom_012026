package factoryEnvironment;

import commons.BrowserList;
import org.openqa.selenium.WebDriver;

public class LocalFactory implements iEnvFactory {
    private WebDriver driver;
    private String browserName;

    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new factoryBrowser.ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new factoryBrowser.FirefoxDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new factoryBrowser.EdgeDriverManager().getBrowserDriver();
                break;
            case HCHROME:
                driver = new factoryBrowser.HChromeDriverManager().getBrowserDriver();
                break;
            case HFIREFOX:
                driver = new factoryBrowser.HFirefoxDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new factoryBrowser.SafariDriverManager().getBrowserDriver();
                break;
            default:
                throw new RuntimeException("Please input valid browser name value!");
        }
        return driver;
    }

}
