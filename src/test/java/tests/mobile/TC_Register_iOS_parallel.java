package tests.mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.HomePO;
import pageObjects.MyAccountPO;
import pageObjects.PageGenerator;
import pageObjects.RegisterPO;
import pageUIs.MenuCategoryUI;
import utils.DataFaker;

@Epic("Login - Register")
@Feature("Register Tests")
public class TC_Register_iOS_parallel extends BaseTest {
    WebDriver driver;
    HomePO homepage;
    MenuCategoryUI menuCategory;
    MyAccountPO myAccount;
    RegisterPO registerPO;
    DataFaker dataFaker;
    String firstName, lastName, email, phone, password;
    WebElement element;

    @Description("Register to system with valid information")
    @Story("Register")
    @Severity(SeverityLevel.MINOR)
    @Parameters({"env", "browserName", "browserVersion", "osName","osVersion","url"})
    @BeforeClass
    public void beforeClass(@Optional("local") String env, @Optional("chrome") String browserName, @Optional("133.0") String browserVersion, @Optional("OS X") String osName, @Optional("14.4") String osVersion, @Optional("test") String url) {
        driver = getBrowserDriverAllEnvironment(env, browserName, browserVersion, osName, osVersion, url);
        homepage = PageGenerator.getHomepage(driver);
        dataFaker = new DataFaker();
        firstName = dataFaker.getFirstName();
        lastName = dataFaker.getLastName();
        email = dataFaker.getEmail();
        phone = dataFaker.getPhone();
        password = dataFaker.getPassword();

    }

//    Multi field empty
    @Description("Register to system with blank data")
    @Story("Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_01_Register_Blank_Data() {
        myAccount = homepage.clickMyAccount();
        registerPO = myAccount.clickContinueButton();
        registerPO.clickToContinueButtonAtResigterPage();
        Assert.assertEquals(registerPO.getErrorMessage("First Name"),"First Name must be between 1 and 32 characters!");
        Assert.assertEquals(registerPO.getErrorMessage("Last Name"),"Last Name must be between 1 and 32 characters!");
        Assert.assertEquals(registerPO.getErrorMessage("E-Mail"),"E-Mail Address does not appear to be valid!");
        Assert.assertEquals(registerPO.getErrorMessage("Telephone"),"Telephone must be between 3 and 32 characters!");
        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password must be between 4 and 20 characters!");
        Assert.assertTrue(registerPO.getPrivacyPolicyErrorMessage().contains("Warning: You must agree to the Privacy Policy!"));
    }

    @Description
            ("Register to system with First Name blank/ over max")
    @Story("Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_02_Register_FirstName_Blank() {
        myAccount = homepage.clickMyAccount();
        registerPO = myAccount.clickContinueButton();
        registerPO.inputToLastNameTextbox(lastName);
        registerPO.inputToEmailTextbox(email);
        registerPO.inputToPhoneTextbox(phone);
        registerPO.inputToPasswordTextbox(password);
        registerPO.inputToConfirmPasswordTextbox(password);
        registerPO.clickToPrivacyPolicyCheckbox();
        registerPO.clickToContinueButtonAtResigterPage();
        Assert.assertEquals(registerPO.getErrorMessage("First Name"), "First Name must be between 1 and 32 characters!");
    }

    public static IOSDriver getIOSDriver(String deviceName) {
        IOSDriver driver;
        // Khởi tạo cấu hình cho iOS (tương tự như Android)
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("17.5")
                .setDeviceName(deviceName) // hoặc tên thiết bị thật
//                .setBundleId("com.tuhuynh.sdetproecommerce")
//                .setApp(System.getProperty("user.dir")+"/apps/sdetpro-ecommerce.app")
                .setAutomationName("XCUITest");
        options.setCapability("browserName", "Safari");
        options.setCapability("safari:useSimulator", true);
        try {
            URL appiumServerUrl = new URL("http://127.0.0.1:4723/");
            driver = new IOSDriver(appiumServerUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Không thể khởi tạo phiên Appium cho iOS", e);
        }
        return driver;
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriverThreadSafe();
    }
}
