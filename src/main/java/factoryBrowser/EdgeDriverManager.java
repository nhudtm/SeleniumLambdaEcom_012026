package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager  implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications"); // disable notification FOR EXAMPLE;
        options.addArguments("--lang=vi"); // set language of browser to vietnamese
        System.setProperty("SE_MSEDGEDRIVER_MIRROR_URL", "https://msedgedriver.microsoft.com");
        return new EdgeDriver(options);
    }
}
