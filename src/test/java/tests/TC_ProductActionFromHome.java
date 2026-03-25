package tests;

import utils.DataFaker;
import utils.ProductActionHelper;
import commons.BaseTest;
import components.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.List;

public class TC_ProductActionFromHome extends BaseTest {
    WebDriver driver;
    protected HomePO homePage;
    protected ProductCategoryPO productCategoryPage;
    protected ProductDetailPO productDetailPage;
    protected MenuCategoryPO menuCategoryPage;
    protected DataFaker dataFaker;
    List<ProductComponent> allProductsTopProducts;
    List<ProductComponent> allProductsTopCollections;

  @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        allProductsTopProducts = homePage.getAllProductsInTopProducts();
        allProductsTopCollections = homePage.getAllProductsInTopCollections();


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
    @Test
    public void TC_Top_Product_Product_Quick_Action_Icon_Displayed() {
        ProductActionHelper.verifyProductActionDisplayed(allProductsTopProducts);
    }

    @Test(groups = {"regression", "productAction"})
    public void TC_Top_Product_Add_To_Cart_Success() {
        ProductActionHelper.verifyAddToCartFunctionality(allProductsTopProducts, getDriver());
    }

    @Test
    public void TC_Top_Collections_Product_Quick_Action_Icon_Displayed() {
        ProductActionHelper.verifyProductActionDisplayed(allProductsTopCollections);
    }

    @Test
    public void TC_Top_Collections_Add_To_Cart_Success() {
        ProductActionHelper.verifyAddToCartFunctionality(allProductsTopCollections, getDriver());
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
