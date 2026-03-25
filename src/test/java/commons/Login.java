package commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.*;

public class Login extends BaseTest {
    WebDriver driver;
    HomePO homePage;
    WishListPO wishListPage;
    CartPO cartPage;
    ProductDetailPO productDetailPage;
    ComparePO comparePage;
    MenuCategoryPO menuCategoryPage;
    String email, password;
    MyAccountPO myAccount;


    @Parameters({"browser", "url"})
    @BeforeTest
    public void beforeTest(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        homePage = PageGenerator.getHomepage(driver);
        email = "lazy@gmail.com";
        password = "123456";

        // This website does not allow to add cookies, so we have to login with UI in before class of Test classes.
        // Or we can run this Login test class before running other test class to have login state
        MyAccountPO myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(email);
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
    }

    @AfterTest
    public void afterTest() {
        closeBrowserDriver();
    }

}
