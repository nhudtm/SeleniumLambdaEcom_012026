package tests;

import utils.DataFaker;
import utils.ProductActionHelper;
import commons.BaseTest;
import components.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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


    @Parameters({"browserName", "url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        homePage = PageGenerator.getHomepage(driver);
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

    @Test
    public void TC_Top_Product_Add_To_Cart_Success() {
        ProductActionHelper.verifyAddToCartFunctionality(allProductsTopProducts, driver);
    }

    @Test
    public void TC_Top_Collections_Product_Quick_Action_Icon_Displayed() {
        ProductActionHelper.verifyProductActionDisplayed(allProductsTopCollections);
    }

    @Test
    public void TC_Top_Collections_Add_To_Cart_Success() {
        ProductActionHelper.verifyAddToCartFunctionality(allProductsTopCollections, driver);
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



    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

}
