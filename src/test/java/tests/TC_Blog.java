package tests;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_Blog extends BaseTest {
    WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
    }

    @Test
    public void TC_01() {


    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

}
