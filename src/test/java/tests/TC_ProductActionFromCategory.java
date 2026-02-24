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

public class TC_ProductActionFromCategory extends BaseTest {
    WebDriver driver;
    protected HomePO homePage;
    protected ProductCategoryPO productCategoryPage;
    protected ProductDetailPO productDetailPage;
    protected MenuCategoryPO menuCategoryPage;
    protected DataFaker dataFaker;
    List<ProductComponent> allProducts;


    @Parameters({"browserName", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(driver);
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
    @Test
    public void TC_Product_Quick_Action_Icon_Displayed() {
        ProductActionHelper.verifyProductActionDisplayed(allProducts);
    }

    @Test
    public void TC_Add_To_Cart_Success() {
        ProductActionHelper.verifyAddToCartFunctionality(allProducts, driver);
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
