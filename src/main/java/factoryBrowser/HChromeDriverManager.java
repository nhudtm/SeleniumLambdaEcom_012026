package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HChromeDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // disable notification FOR EXAMPLE;
        options.addArguments("--lang=vi"); // set language of browser to vietnamese
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
