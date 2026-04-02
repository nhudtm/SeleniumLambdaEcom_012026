package tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import components.ProductComponent;
import pageObjects.HomePO;
import pageObjects.MenuCategoryPO;
import pageObjects.PageGenerator;
import pageObjects.ProductCategoryPO;
import pageObjects.ProductDetailPO;
import utils.DataFaker;
import utils.ProductActionHelper;

public class TC_ProductActionFromCategory extends BaseTest {
    protected HomePO homePage;
    protected ProductCategoryPO productCategoryPage;
    protected ProductDetailPO productDetailPage;
    protected MenuCategoryPO menuCategoryPage;
    protected DataFaker dataFaker;
    List<ProductComponent> allProducts;


      @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional("chrome") String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
                WebDriver currentDriver = getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
                menuCategoryPage = PageGenerator.getMenuCategoryPage(currentDriver);
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        allProducts = productCategoryPage.getAllProductsInPage();
    }

    // Kiểm tra hiển thị đúng thông tin của product (name, code, brand, viewed number, Avalability, Price
    // Kiểm tra chức năng add to cart, add to wishlist, add to compare
    // Kiểm tra view Size chart, Popup, Ask Question
    // Kiểm tra Related Product section
    // Kiểm tra FAQ section
    // Kiểm tra Review section
    // Kiểm tra Description, Reviews, Custom section
    // Kiểm tra view product image/ video
    // Navigation from Product detail to Category by Image
    @Test(groups = {"regression", "productAction"})
    public void TC_Product_Quick_Action_Icon_Displayed() {
        ProductActionHelper.verifyProductActionDisplayed(allProducts);
    }

    @Test
    public void TC_Add_To_Cart_Success() {
        ProductActionHelper.verifyAddToCartFunctionality(allProducts, getDriver());
    }

    @Test
    public void TC_Add_To_Compare_Success() {

    }

    @Test
    public void TC_Add_To_WishList_Success() {

    }

    @Test
    public void TC_Quick_View_Success() {

    }
}
