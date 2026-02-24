package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications"); // disable notification FOR EXAMPLE;
        options.addArguments("--lang=vi"); // set language of browser to vietnamese
//        options.addPreference("profile.default_content_settings.popups", 0); // disable popup
        return new org.openqa.selenium.firefox.FirefoxDriver(options);
    }
}
