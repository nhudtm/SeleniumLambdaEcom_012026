package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications"); // disable notification FOR EXAMPLE;
        chromeOptions.addArguments("--lang=vi"); // set language of browser to vietnamese
        chromeOptions.addArguments("--incognito"); // open browser in incognito mode
        return new ChromeDriver(chromeOptions);
    }
}
