package tests;

import utils.DataFaker;
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

public class TC_ProductDetail_2 extends BaseTest {
    WebDriver driver;
    protected HomePO homePage;
    protected ProductCategoryPO productCategoryPage;
    protected ProductDetailPO productDetailPage;
    protected MenuCategoryPO menuCategoryPage;
    protected DataFaker dataFaker;


      @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        log.info(
                "Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
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
//    @Test
//    public void TC_Add_To_Cart_Valid_Quantity() {
//
//    }
//
//    @Test
//    public void TC_Add_To_Cart_InValid_Quantity() {
//// Quá số lượng cho tồn kho
//    }
//
//    @Test
//    public void TC_Add_To_Cart_Quantity_Price() {
//
//    }
//
//    @Test
//    public void TC_Buy_Now() {
//
//    }

    @Test(groups = {"regression", "productDetail","debug"})
    public void TC_Submit_Review_Success() {
        String name = "abc";
        String reviewText = generateRandomNumber() + "This is an automated."; //25 characters
        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        Assert.assertEquals(productDetailPage.getProductName(), "iPod Nano");
        productDetailPage.selectStarInRating(4);
        productDetailPage.enterNameToReview(name);
        productDetailPage.enterReviewText(reviewText);
        productDetailPage.clickWriteReviewButton();
        Assert.assertEquals(productDetailPage.getSuccessMessageText(), "Thank you for your review. It has been submitted to the webmaster for approval.");
    }
@Test(groups = {"regression", "productDetail"})
    public void TC_Submit_Review_No_Rating() {
        String name = "This is an automated 1234";
        String reviewText = generateRandomNumber() + "This is an automated review. Please ignore.";
        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        Assert.assertEquals(productDetailPage.getProductName(), "iPod Nano");
        productDetailPage.enterNameToReview(name);
        productDetailPage.enterReviewText(reviewText);
        productDetailPage.clickWriteReviewButton();
        Assert.assertEquals(productDetailPage.getWarningMessageText(), "Warning: Please select a review rating!");
    }

@Test
    public void TC_Submit_Review_Name_Less_Than_3() {
        String name = "ab";
        String reviewText = generateRandomNumber() + "This is an automated review. Please ignore.";
        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        Assert.assertEquals(productDetailPage.getProductName(), "iPod Nano");
        productDetailPage.selectStarInRating(4);
        productDetailPage.enterNameToReview(name);
        productDetailPage.enterReviewText(reviewText);
        productDetailPage.clickWriteReviewButton();
        Assert.assertEquals(productDetailPage.getWarningMessageText(), "Warning: Review Name must be between 3 and 25 characters!");
    }

    @Test
    public void TC_Submit_Review_Name_Over_25() {
        String name = "Thank you for your review. It has been submitted to the webmaster for approval.";
        String reviewText = generateRandomNumber() + "This is an automated review. Please ignore.";
        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        Assert.assertEquals(productDetailPage.getProductName(), "iPod Nano");
        productDetailPage.selectStarInRating(4);
        productDetailPage.enterNameToReview(name);
        productDetailPage.enterReviewText(reviewText);
        productDetailPage.clickWriteReviewButton();
        Assert.assertEquals(productDetailPage.getWarningMessageText(), "Warning: Review Name must be between 3 and 25 characters!");
    }

    @Test
    public void TC_Submit_Review_TextArea_Less_Than_25() {
        String name = dataFaker.getFirstName();
        String reviewText = generateRandomNumber() + " automated.";
        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        Assert.assertEquals(productDetailPage.getProductName(), "iPod Nano");
        productDetailPage.selectStarInRating(4);
        productDetailPage.enterNameToReview(name);
        productDetailPage.enterReviewText(reviewText);
        productDetailPage.clickWriteReviewButton();
        Assert.assertEquals(productDetailPage.getWarningMessageText(), "Warning: Review Text must be between 25 and 1000 characters!");
    }

//    @Test
//    public void TC_Verify_Product_Description_Tab() {
//
//    }
//
//    @Test
//    public void TC_Verify_Product_Size_Chart() {
//
//    }
//
//    @Test
//    public void TC_Verify_Product_Ask_Questions() {
//
//    }
//
//    @Test
//    public void TC_Verify_Breadcum_Displayed() {
////        🔍 Test Steps:
////        Truy cập trang chi tiết sản phẩm: https://ecommerce-playground.lambdatest.io/index.php?route=product/product&manufacturer_id=8&product_id=32
////        Xác định khu vực breadcrumb trên giao diện (thường nằm phía trên tiêu đề sản phẩm).//
////        Kiểm tra số lượng các mục breadcrumb hiển thị (ví dụ: Home > Brand > Product).//
////        So sánh từng mục breadcrumb với cấu trúc danh mục thực tế của sản phẩm.//
////                Kiểm tra tên từng mục breadcrumb có hiển thị đúng và không bị lỗi font/ký tự.//
////        Kiểm tra có dấu phân cách hợp lệ giữa các mục (ví dụ: dấu “>” hoặc “/”).//
////✅ Expected Result://
//// Breadcrumb hiển thị đầy đủ và đúng thứ tự: Home > [Danh mục] > [Tên sản phẩm].//
////        Các mục breadcrumb có thể click được (trừ mục cuối là tên sản phẩm).
//
//    }
//
//    @Test
//    public void TC_Verify_Breadcrumb_Navigation() {
////        Tại trang chi tiết sản phẩm, xác định các mục breadcrumb có thể click:
////“Home”
////“Danh mục” hoặc “Thương hiệu”
////        Click vào mục “Home” trong breadcrumb.//
////        Kiểm tra trình duyệt có điều hướng về trang chủ hay không.//
////        Quay lại trang sản phẩm.//
////                Click vào mục “Danh mục” hoặc “Thương hiệu” trong breadcrumb.//
////        Kiểm tra trình duyệt có điều hướng đến đúng danh mục chứa sản phẩm hay không.//
////✅ Expected Result:
////        Click vào “Home” → chuyển đến trang chủ: https://ecommerce-playground.lambdatest.io///
////        Click vào danh mục → chuyển đến trang danh mục tương ứng (ví dụ: Brand hoặc Category).//
////        Không xảy ra lỗi 404 hoặc điều hướng sai.
//    }
//
//    @Test
//    public void TC_Verify_FAQ() {
//
//    }
//
//    @Test
//    public void TC_Verify_Product_Reviews_Tab() {
//
//    }


}
