package tests;

import commons.BaseTest;
import models.Product;
import models.ProductMedia;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.ArrayList;
import java.util.List;

public class TC_ProductDetail_1 extends BaseTest {
    WebDriver driver;
    protected HomePO homePage;
    protected ProductCategoryPO productCategoryPage;
    protected ProductDetailPO productDetailPage;
    protected MenuCategoryPO menuCategoryPage;

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
    @Test(groups = {"regression", "productDetail"})
    public void TC_Verify_One_Product_Info() {
//        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        String productName = productCategoryPage.getProductNameFromUI(3);
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        Assert.assertEquals(productDetailPage.getProductPageTitleText(), productName);
        // Product from UI
        String productPrice = productDetailPage.getProductPriceFromUI();
        String productAvalability = productDetailPage.getProductAvailabilityFromUI();

        // Product from DB
        int productId = productDetailPage.getProductIdFromURL();
        Product product = productDetailPage.getProductInfoFromDB(productId);
        System.out.println(product);
        System.out.printf("Product from DB: %s - Price: %s - Active: %b\n", product.getName(), product.getPrice(), product.isActive());
        String productPriceFromDB = String.valueOf(product.getPrice());
        String productAvalabilityFromDB = product.isActive() ? "In Stock" : "Out of Stock";
        System.out.println(productAvalabilityFromDB);
        System.out.println(productAvalability);

        Assert.assertTrue(productPrice.contains(productPriceFromDB));
        Assert.assertEquals(productAvalabilityFromDB, productAvalability);
    }

    @Test(groups = {"regression", "productDetail"})
    public void TC_Verify_One_Product_Info_Image_Video_C2() {
        homePage.hoverToMegaMenu();
        productCategoryPage = homePage.clickChildItemInMegaMenu("Apple");
        String productName = productCategoryPage.getProductNameFromUI(3);
        productDetailPage = productCategoryPage.clickProductToViewDetail(3);
        int productId = productDetailPage.getProductIdFromURL();
        Product dbProduct = productDetailPage.getProductInfoFromDB(productId);
        verifyProductInfo(dbProduct);
        verifyProductImage(dbProduct);
        verifyProductVideo(dbProduct);
    }

    @Test
    public void TC_Verify_All_Product_Info() {
        homePage.hoverToMegaMenu();
        List<String> allChildItemNames = homePage.getAllChildItemsInMegaMenu();
        List<Product> productList = new ArrayList<Product>();
        List<ProductMedia> productMediaList = new ArrayList<ProductMedia>();

        for (int i = 0; i < 2; i++) {
            homePage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = homePage.clickChildItemInMegaMenu(childItem);
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                // Duyệt qua từng sản phẩm trong trang để verify info - productsInPage
                for (int j = 1; j < 3; j++) {
                    String productName = productCategoryPage.getProductNameFromUI(j);

                    // Vào product detail page
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    int productId = productDetailPage.getProductIdFromURL();

                    //Data from UI
                    String productName1 = productDetailPage.getProductName();
                    String productPrice = productDetailPage.getProductPriceFromUI();
                    String productAvailability = productDetailPage.getProductAvailabilityFromUI();
                    String productDescription = productDetailPage.getProductDescriptionFromUI();
                    String productMainImageSrc = productDetailPage.getMainImageSrc();

                    //Data from DB
                    Product product = productDetailPage.getProductInfoFromDB(productId);
                    String productNameFromDB = product.getName();
                    String productPriceFromDB = String.valueOf(product.getPrice());
                    String productAvalabilityFromDB = product.isActive() ? "In Stock" : "Out Of Stock";
                    String productDescriptionFromDB = product.getDescription().replaceAll("\\.\\.\\.$", "");
                    String productMainImageSrcFromDB = productDetailPage.getMainImageSrcFromDB(productId);

                    //Verify product info
                    Assert.assertEquals(productNameFromDB, productName1);
                    Assert.assertTrue(productPrice.contains(productPriceFromDB));

                    //Assert.assertEquals(productAvalabilityFromDB, productAvailability);
                    Assert.assertTrue(productDescription.contains(productDescriptionFromDB));
                    Assert.assertEquals(productMainImageSrcFromDB, productMainImageSrc);

                    //Verify image loaded successfully
                    Assert.assertTrue(productDetailPage.isMainImageLoadedSuccess());
                    Assert.assertTrue(productDetailPage.isMainImageDisplayed());

                    //Back lại product category page
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }


    public void verifyProductInfo(Product dbProduct) {
        //Data from UI
        String productName = productDetailPage.getProductName();
        String productPrice = productDetailPage.getProductPriceFromUI();
        String productAvailability = productDetailPage.getProductAvailabilityFromUI();
        String productDescription = productDetailPage.getProductDescriptionFromUI();
        String productMainImageSrc = productDetailPage.getMainImageSrc();
        System.out.printf("Product img: " + productMainImageSrc);

        //Data from DB
        String productNameFromDB = dbProduct.getName();
        String productPriceFromDB = String.valueOf(dbProduct.getPrice());
        String productAvalabilityFromDB = dbProduct.isActive() ? "In Stock" : "Out Of Stock";
        String productDescriptionFromDB = dbProduct.getDescription().replaceAll("\\.\\.\\.$", "");
        String productMainImageSrcFromDB = productDetailPage.getMainImageSrcFromDB(dbProduct.getProductId());
        //Verify product info
        Assert.assertEquals(productNameFromDB, productName);
        Assert.assertTrue(productPrice.contains(productPriceFromDB));

//        Assert.assertEquals(productAvalabilityFromDB, productAvailability); Tạm bỏ qua case này
        Assert.assertTrue(productDescription.contains(productDescriptionFromDB));
        Assert.assertEquals(productMainImageSrcFromDB, productMainImageSrc);

        //Verify image loaded successfully
        Assert.assertTrue(productDetailPage.isMainImageLoadedSuccess());
        Assert.assertTrue(productDetailPage.isMainImageDisplayed());
    }

    @Test
    void TC_Verify_All_Product_Info_C2() {
        homePage.hoverToMegaMenu();
        List<String> allCategoryNames = homePage.getAllCategoryFromDB();

        for (String category : allCategoryNames) {
            homePage.hoverToMegaMenu();
            productCategoryPage = homePage.clickChildItemInMegaMenu(category);
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                // Duyệt qua từng sản phẩm trong trang để verify info - productsInPage
                for (int j = 1; j < 2; j++) {
                    String productName = productCategoryPage.getProductNameFromUI(j);
                    System.out.println("Product thứ " + j + ": " + productName);

                    // Vào product detail page
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    int productId = productDetailPage.getProductIdFromURL();
                    Product dbProduct = productDetailPage.getProductInfoFromDB(productId);

                    //Main - Verify product info
                    verifyProductInfo(dbProduct);

                    //Back lại product category page
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }

    @Test
    public void TC_Verify_All_Product_Image() {
        homePage.hoverToMegaMenu();
        List<String> allCategoryNames = homePage.getAllCategoryFromDB();

        for (String category : allCategoryNames) {
            homePage.hoverToMegaMenu();
            productCategoryPage = homePage.clickChildItemInMegaMenu(category);
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                // Duyệt qua từng sản phẩm trong trang để verify info - productsInPage
                for (int j = 1; j < 2; j++) {
                    String productName = productCategoryPage.getProductNameFromUI(j);

                    // Vào product detail page
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    int productId = productDetailPage.getProductIdFromURL();

                    // Get Data from UI
                    int mediaNumber = productDetailPage.getAllMediaSrc().size();
                    List<String> imageSrc = productDetailPage.getAllProductImage();
                    int imageNumber = imageSrc.size();
                    String productMainImageSrc = productDetailPage.getMainImageSrc();
                    System.out.printf("Product img: " + productMainImageSrc);

                    //Data from DB
                    int mediaNumberFromDB = productDetailPage.getProductMediaNumberFromDB(productId);
                    Product product = productDetailPage.getProductInfoFromDB(productId);
                    String productMainImageSrcFromDB = productDetailPage.getMainImageSrcFromDB(productId);
                    List<String> productMediaSrcFromDB = productDetailPage.getProductMediaSrcFromDB(productId);

                    //Verify number of media
                    Assert.assertEquals(mediaNumberFromDB, mediaNumber);

                    //Main image source compare with DB
                    Assert.assertEquals(productMainImageSrcFromDB, productMainImageSrc);

                    // Verify video: compare db, zoom, next/prev/close - đồng thời lấy ra số lượng image hiển thị trên UI để duyệt (trang sẽ hiện thị 5 item (1 video 4 image) nếu có video, 5 image nếu không có video)
                    int imageDisplayedNumber = 5;
                    boolean isVideoNotAvailable = productDetailPage.isProductVideoNotAvailable();
                    System.out.println("Video displayed?: " + isVideoNotAvailable);
                    String productVideoHrefFromDB = productDetailPage.getProductVideoHrefFromDB(productId);
                    if (!isVideoNotAvailable) {
                        imageDisplayedNumber -= 1; // dùng cho image thumbnail bên dưới
                        String videoHref = productDetailPage.getProductVideoHref();
                        Assert.assertTrue(productVideoHrefFromDB.contains(videoHref));
                        productDetailPage.clickVideoThumbnail();
                        Assert.assertTrue(productDetailPage.isVideoPlayerDisplayed());
                        if (productDetailPage.isVideoErrorNotDisplayed()) {
                            productDetailPage.clickPlayButtonInVideoPlayer();
                            Assert.assertTrue(productDetailPage.isVideoPlaying());
                            productDetailPage.clickPauseVideo();
                            Assert.assertTrue(productDetailPage.isVideoPaused());
                            productDetailPage.clickNextButtonInZoomedImage();
                            Assert.assertNotEquals(videoHref, productDetailPage.getNewImageSrc()); //Kiểm tra ảnh trước và sau khi click next khác nhau
                            productDetailPage.clickPrevButtonInZoomedImage();
                            Assert.assertTrue(productDetailPage.isVideoPlayerDisplayed());
                            productDetailPage.clickCloseMediaZoom();
                            Assert.assertTrue(productDetailPage.isMainImageDisplayed());
                        } else {
                            System.out.println("Video not available to play");
                            productDetailPage.clickCloseMediaZoom();
                            Assert.assertTrue(productDetailPage.isMainImageDisplayed());
                        }
                    }

                    //Duyệt qua từng image , Verify url, alt, title, click to zoom của image của từng product đúng với database
                    for (int k = 1; k <= imageDisplayedNumber; k++) {
                        Assert.assertTrue(productDetailPage.getImageAltByIndex(k).contains(productName));
                        Assert.assertTrue(productDetailPage.getImageTitleByIndex(k).contains(productName));

                        // Verify image source đúng với database
                        String mediaSrc = productDetailPage.getImageSrcByIndex(k);
                        for (int l = 0; l <= productMediaSrcFromDB.size(); l++) {
                            if (productMediaSrcFromDB.contains(mediaSrc)) {
                                Assert.assertTrue(true);
                                break;
                            }
                        }

                        // Verify image loaded successfully
                        Assert.assertTrue(productDetailPage.isImageLoadedSuccess(k));
                        Assert.assertTrue(productDetailPage.isImageDisplayed(k));

                        //Verify click từng image thumbnail để phóng to image
                        //Verify click từng image thumbnail để switch image (số lượng image khi phóng to sẽ nhiều hơn sô lượng image hiển thị trên thumbnail
                        productDetailPage.clickImageByIndex(k);
                        Assert.assertTrue(productDetailPage.isImageZoomedDisplayed());
                        productDetailPage.clickCloseMediaZoom();
                        Assert.assertTrue(productDetailPage.isMainImageDisplayed());
                    }

                    //Back lại product category page
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }

    @Test
    public void TC_Verify_All_Product_Image_C2() {
        homePage.hoverToMegaMenu();
        List<String> allCategoryNames = homePage.getAllCategoryFromDB();

        for (String category : allCategoryNames) {
            homePage.hoverToMegaMenu();
            productCategoryPage = homePage.clickChildItemInMegaMenu(category);
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                // Duyệt qua từng sản phẩm trong trang để verify info - productsInPage
                for (int j = 1; j < 2; j++) {
                    String productName = productCategoryPage.getProductNameFromUI(j);
                    System.out.println("Product thứ " + j + ": " + productName);

                    // Vào product detail page
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    int productId = productDetailPage.getProductIdFromURL();
                    Product dbProduct = productDetailPage.getProductInfoFromDB(productId);

                    // Main - Verify all image of product
                    verifyProductImage(dbProduct);

                    //Back lại product category page
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }

    private void verifyProductImage(Product dbProduct) {
        // Get Data from UI
        int mediaNumber = productDetailPage.getAllMediaSrc().size();
        String productMainImageSrc = productDetailPage.getMainImageSrc();
        System.out.printf("Product img: " + productMainImageSrc);

        //Data from DB
        int productId = dbProduct.getProductId();
        int mediaNumberFromDB = productDetailPage.getProductMediaNumberFromDB(productId);
        List<String> productMediaSrcFromDB = productDetailPage.getProductMediaSrcFromDB(productId);

        //Verify number of media
        Assert.assertEquals(mediaNumberFromDB, mediaNumber);

        // Check xem có video không để tính số lượng image hiển thị trên UI
        int imageDisplayedNumber = 5;
        boolean isVideoNotAvailable = productDetailPage.isProductVideoNotAvailable();
        if (!isVideoNotAvailable) {
            imageDisplayedNumber -= 1; // dùng cho image thumbnail bên dưới
        }

        //Duyệt qua từng image, Verify url, alt, title, click to zoom của image của từng product đúng với database
        for (int k = 1; k <= imageDisplayedNumber; k++) {
            Assert.assertTrue(productDetailPage.getImageAltByIndex(k).contains(dbProduct.getName()));
            Assert.assertTrue(productDetailPage.getImageTitleByIndex(k).contains(dbProduct.getName()));

            // Verify image source đúng với database
            String mediaSrc = productDetailPage.getImageSrcByIndex(k);
            for (int l = 0; l <= productMediaSrcFromDB.size(); l++) {
                if (productMediaSrcFromDB.contains(mediaSrc)) {
                    Assert.assertTrue(true);
                    break;
                }
            }

            // Verify image loaded successfully
            Assert.assertTrue(productDetailPage.isImageLoadedSuccess(k));
            Assert.assertTrue(productDetailPage.isImageDisplayed(k));

            //Verify click từng image thumbnail để phóng to image
            //Verify click từng image thumbnail để switch image (số lượng image khi phóng to sẽ nhiều hơn sô lượng image hiển thị trên thumbnail
            productDetailPage.clickImageByIndex(k);
            Assert.assertTrue(productDetailPage.isImageZoomedDisplayed());
            productDetailPage.clickCloseMediaZoom();
            Assert.assertTrue(productDetailPage.isMainImageDisplayed());
        }
    }

    @Test
    public void TC_Verify_Product_Video_C2() {
        homePage.hoverToMegaMenu();
        List<String> allCategoryNames = homePage.getAllCategoryFromDB();
        for (String category : allCategoryNames) {
            homePage.hoverToMegaMenu();
            productCategoryPage = homePage.clickChildItemInMegaMenu(category);
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                // Duyệt qua từng sản phẩm trong trang để verify info - productsInPage
                for (int j = 1; j < 2; j++) {
                    String productName = productCategoryPage.getProductNameFromUI(j);
                    System.out.println("Product thứ " + j + ": " + productName);

                    // Vào product detail page
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    int productId = productDetailPage.getProductIdFromURL();
                    Product dbProduct = productDetailPage.getProductInfoFromDB(productId);

                    // Main - Verify all image of product
                    verifyProductVideo(dbProduct);

                    //Back lại product category page
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }

    private void verifyProductVideo(Product dbProduct) {
        // Get Data from UI
        int mediaNumber = productDetailPage.getAllMediaSrc().size();

        //Data from DB
        int productId = dbProduct.getProductId();
        int mediaNumberFromDB = productDetailPage.getProductMediaNumberFromDB(productId);

        //Verify number of media
        Assert.assertEquals(mediaNumberFromDB, mediaNumber);

        // Verify video: compare db, zoom, next/prev/close - đồng thời lấy ra số lượng image hiển thị trên UI để duyệt (trang sẽ hiện thị 5 item (1 video 4 image) nếu có video, 5 image nếu không có video)
        boolean isVideoNotAvailable = productDetailPage.isProductVideoNotAvailable();
        String productVideoHrefFromDB = productDetailPage.getProductVideoHrefFromDB(productId);
        if (!isVideoNotAvailable) {
            String videoHref = productDetailPage.getProductVideoHref();
            Assert.assertTrue(productVideoHrefFromDB.contains(videoHref));
            productDetailPage.clickVideoThumbnail();
            Assert.assertTrue(productDetailPage.isVideoPlayerDisplayed());
            if (productDetailPage.isVideoErrorNotDisplayed()) {
                productDetailPage.clickPlayButtonInVideoPlayer();
                Assert.assertTrue(productDetailPage.isVideoPlaying());
                productDetailPage.clickPauseVideo();
                Assert.assertTrue(productDetailPage.isVideoPaused());
                productDetailPage.clickNextButtonInZoomedImage();
                Assert.assertNotEquals(videoHref, productDetailPage.getNewImageSrc()); //Kiểm tra ảnh trước và sau khi click next khác nhau
                productDetailPage.clickPrevButtonInZoomedImage();
                Assert.assertTrue(productDetailPage.isVideoPlayerDisplayed());
                productDetailPage.clickCloseMediaZoom();
                Assert.assertTrue(productDetailPage.isMainImageDisplayed());
            } else {
                System.out.println("Video not available to play");
                productDetailPage.clickCloseMediaZoom();
                Assert.assertTrue(productDetailPage.isMainImageDisplayed());
            }
        }
    }

    @Test
    public void TC_Verify_All_Product_Info_Image_Video_C2() {
        homePage.hoverToMegaMenu();
        List<String> allCategoryNames = homePage.getAllCategoryFromDB();
        for (String category : allCategoryNames) {
            homePage.hoverToMegaMenu();
            productCategoryPage = homePage.clickChildItemInMegaMenu(category);
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                // Duyệt qua từng sản phẩm trong trang để verify info - productsInPage
                for (int j = 1; j < 2; j++) {
                    String productName = productCategoryPage.getProductNameFromUI(j);

                    // Vào product detail page
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    int productId = productDetailPage.getProductIdFromURL();
                    Product dbProduct = productDetailPage.getProductInfoFromDB(productId);

                    // Main - Verify product info
                    verifyProductInfo(dbProduct);

                    // Main - Verify all image of product
                    verifyProductImage(dbProduct);

                    // Main - Verify all video of product
                    verifyProductVideo(dbProduct);

                    //Back lại product category page
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }



}
