package tests;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class TC_Home extends BaseTest {
    WebDriver driver;
    HomePO homePage;
    WishListPO wishListPage;
    CartPO cartPage;
    ProductDetailPO productDetailPage;
    ComparePO comparePage;
    MenuCategoryPO menuCategoryPage;

      @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        log.info(
                "Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    //Banner
    //Top Trending Ca
    //Top Products
    //Top Collections
    //From The Blog


    @Test(groups = {"regression", "home"})
    public void TC_01_View_Products_Details() {
        String productName = homePage.getFirstProductNameInTopProducts();
        productDetailPage = homePage.clickFirstProductInTopProductsSection();
        Assert.assertEquals(productDetailPage.getProductPageTitleText(), productName);
        homePage = productDetailPage.clickHomeMenuItem();
    }

    @Test(groups = {"regression", "home"})
    public void TC_02_Add_Product_To_Cart() {
        String productName = homePage.getFirstProductNameInTopProducts();
        homePage.hoverToFirstProductInTopProductsSection();
        homePage.clickToAddToCartButtonInProductAction();
        //Note: \\s+ sẽ thay thế tất cả khoảng trắng, tab, xuống dòng… bằng một dấu cách.

        Assert.assertTrue(homePage.getCartPopupText().replaceAll("\\s+", " ").trim().contains("Success: You have added " + productName + " to your shopping cart"));
        homePage.clickToCartCompareWishlistClosePopup();
    }

    @Test
    public void TC_03_Action_Quick_View() {
        String productName = homePage.getFirstProductNameInTopProducts();
        homePage.hoverToFirstProductInTopProductsSection();
        homePage.clickToQuickViewButtonInProductAction();
        Assert.assertEquals(homePage.getProductPopupTitleText(), productName);
        homePage.clickCloseIconInProductPopup();
    }

    @Test
    public void TC_04_Action_Add_Product_To_WishList_Without_Login() {
        String productName = homePage.getFirstProductNameInTopProducts();
        homePage.hoverToFirstProductInTopProductsSection();
        homePage.clickToAddToWishListButtonInProductAction();
        Assert.assertTrue(homePage.getLoginAlertMessageText().replaceAll("\\s+", " ").trim().contains("You must login or create an account to save " + productName + " to your wish list"));
        homePage.clickToLoginAlertClosePopup();
    }

    @Test void TC_05_Action_Add_Product_To_Compare() {
        String productName = homePage.getFirstProductNameInTopProducts();
        homePage.hoverToFirstProductInTopProductsSection();
        homePage.clickToAddToCompareButtonInProductAction();
        Assert.assertTrue(homePage.getCompareCartWishListPopupText().replaceAll("\\s+", " ").trim().contains("Success: You have added " + productName + " to your product comparison"));
        homePage.clickToCartCompareWishlistClosePopup();

    }


    
}
