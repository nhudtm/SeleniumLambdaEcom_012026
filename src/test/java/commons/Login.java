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

        // use cookie
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
