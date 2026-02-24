package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariOptions;


public class SafariDriverManager implements BrowserFactory{

    @Override
    public WebDriver getBrowserDriver() {
        SafariOptions options = new SafariOptions();
        return new org.openqa.selenium.safari.SafariDriver();

    }
}
