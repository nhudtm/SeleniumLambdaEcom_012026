package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import models.Product;
import pageObjects.CartPO;
import pageObjects.ComparePO;
import pageObjects.HomePO;
import pageObjects.PageGenerator;

public class TC_Compare extends BaseTest {
    HomePO homePage;
    ComparePO comparePage;
    CartPO cartPage;

    @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env,@Optional("chrome") String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        System.out.println(
                "Thread id beforeClass TC_Compare: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    @Test(groups = {"regression", "compare"})
    public void TC_01_Add_To_Compare_Popup_1_Product() {
        log.info("TC_01_Add_To_Compare_Popup_1_Product ");
        int numOfProduct = 1;
        addProductToCompareAndVerifyPopup(homePage, numOfProduct);
        removeProductFromCompare(homePage, numOfProduct);

        // //Go to compare page
        // comparePage = homePage.clickProductCompareLinkInPopup();
        // //Verify compare page title and number of products
        // Assert.assertTrue(comparePage.isCompareTitleDisplayed());
        // homePage = comparePage.clickHomeMenuItem();
    }

    @Test
    public void TC_02_Add_To_Compare_Popup_2_Products() {
        log.info("TC_02_Add_To_Compare_Popup_2_Products ");
        int numOfProduct = 2;
        addProductToCompareAndVerifyPopup(homePage, numOfProduct);
        removeProductFromCompare(homePage, numOfProduct);
    }

    @Test
    public void TC_03_Add_To_Compare_Popup_4_Product() {
        log.info("TC_03_Add_To_Compare_Popup_4_Product ");
        int numOfProduct = 4;
        addProductToCompareAndVerifyPopup(homePage, numOfProduct);
        removeProductFromCompare(homePage, numOfProduct);
    }

    @Test
    public void TC_04_Add_To_Compare_Popup_5_Product() {
        log.info("TC_04_Add_To_Compare_Popup_5_Product ");
        int numOfProduct = 4;
        addProductToCompareAndVerifyPopup(homePage, numOfProduct);
        String fifthProductName = homePage.getProducNameInCollectionPopular(5);
        homePage.scrollToProductInCollectionPopular(5);
        homePage.hoverToProductInCollectionPopular(5);
        homePage.clickAddToCompareIconFromCollectionPopular(5);
        Assert.assertEquals(homePage.getCompareCartWishlistPopupTitleText(), "Product Compare (4)");
        Assert.assertTrue(homePage.getCompareCartWishListPopupText().replaceAll("\\s+",
                " ").trim().contains("Success: You have added " + fifthProductName + " to your product comparison"));
        homePage.clickToCartCompareWishlistClosePopup();
        removeProductFromCompare(homePage, numOfProduct);
    }

    // Cần bật db lên mới run được testcase 5-6-7-8
    //@Test
    public void TC_05_Verify_Compare_Page_1_Product() {
    log.info("TC_05_Verify_Compare_Page_1_Product ");
    int numOfProduct = 1;
    addProductToCompareAndVerifyComparePage(homePage, numOfProduct);
    }

   // @Test
    public void TC_06_Verify_Compare_Page_3_Product() {
    log.info("TC_06_Verify_Compare_Page_3_Product ");
    int numOfProduct = 3;
    addProductToCompareAndVerifyComparePage(homePage, numOfProduct);
    removeProductFromCompare(homePage, numOfProduct);
    }

   // @Test
    public void TC_07_Verify_Compare_Page_4_Product() {
    log.info("TC_07_Verify_Compare_Page_4_Product ");
    int numOfProduct = 4;
    addProductToCompareAndVerifyComparePage(homePage, numOfProduct);
    removeProductFromCompare(homePage, numOfProduct);
    }

   // @Test
    public void TC_08_Compare_Page_5_Product() {
    log.info("TC_08_Compare_Page_5_Product ");
    int firstProductIdInCollectionPopular =
    homePage.getProductIdInCollectionPopular(1);
    int fifthProductIdInCollectionPopular =
    homePage.getProductIdInCollectionPopular(5);

    //Add 4 + 1 product
    addProductToCompare(homePage,4);
    homePage.scrollToProductInCollectionPopular(5);
    homePage.hoverToProductInCollectionPopular(5);
    homePage.clickAddToCompareIconFromCollectionPopular(5);
    comparePage = homePage.clickProductCompareLinkInPopup();

    //Verify compare page title and number of products
    Assert.assertTrue(comparePage.isCompareTitleDisplayed());
    int numberOfProductsInComparePage =
    comparePage.getNumberOfProductsInComparePage();
    Assert.assertEquals(numberOfProductsInComparePage, 4);

    //Verify newly added product
    verifyProductInfoInComparePage(comparePage,4,fifthProductIdInCollectionPopular);

    //Verify 1st added product is removed
    Assert.assertTrue(comparePage.isFirstProductInComparePageRemoved(firstProductIdInCollectionPopular));

    homePage = comparePage.clickHomeMenuItem();
    removeProductFromCompare(homePage,4);
    }

    @Test(groups = {"regression", "compare"})
    public void TC_09_Compare_Page_Remove_1_Product() {
        log.info("TC_09_Compare_Page_Remove_1_Product ");
        int numOfProduct = 1;
        addProductToCompare(homePage, numOfProduct);
        removeProductFromCompare(homePage, numOfProduct);
    }

    @Test
    public void TC_10_Compare_Page_Remove_3_Product() {
        log.info("TC_10_Compare_Page_Remove_3_Product ");
        int numOfProduct = 2;
        addProductToCompare(homePage, numOfProduct);
        removeProductFromCompare(homePage, numOfProduct);
    }

    @Test
    public void TC_11_Compare_Page_Remove_4_Product() {
        log.info("TC_11_Compare_Page_Remove_4_Product ");
        int numOfProduct = 4;
        addProductToCompare(homePage, numOfProduct);
        removeProductFromCompare(homePage, numOfProduct);
    }

    @Test
    public void TC_13_Add_To_Compare_Popup_Same_Product_Twice() {
    log.info("TC_13_Add_To_Compare_Popup_Same_Product_Twice ");
    // Add first product to compare
    addProductToCompareAndVerifyPopup(homePage,1);

    // Vì trang web này khi click lần 2 nó không có work, nên phải click lần 2 này, để click lần 3
    homePage.hoverToProductInCollectionPopular(1);
    homePage.clickAddToCompareIconFromCollectionPopular(1);

    // Add the same product to compare again
    addProductToCompareAndVerifyPopup(homePage,1);
    removeProductFromCompare(homePage,1);
    }

//    @Test
    public void TC_14_Add_To_Compare_Page_Same_Product_Twice() {
        log.info("TC_14_Add_To_Compare_Page_Same_Product_Twice ");
        // Add first product to compare
        int productIdInCollectionPopular = homePage.getProductIdInCollectionPopular(1);
        addProductToCompare(homePage, 1);
        addProductToCompare(homePage, 1);

        // Go to compare page
        comparePage = homePage.clickCompareIcon();
        int numberOfProductsInComparePage = comparePage.getNumberOfProductsInComparePage();
        Assert.assertEquals(numberOfProductsInComparePage, 1);
        verifyProductInfoInComparePage(comparePage, 1, productIdInCollectionPopular);
        homePage = comparePage.clickHomeMenuItem();
        removeProductFromCompare(homePage, 1);
    }

//    @Test
    public void TC_15_Compare_Page_Add_To_Cart_Popup_Success() {
    log.info("TC_15_Compare_Page_Add_To_Cart_Popup_Success ");
    // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
    
    String productNameInCollectionPopular = 
    homePage.getProducNameInCollectionPopular(3);
    addProductToCompare(homePage,3);
    comparePage = homePage.clickCompareIcon();

    // Get product info in compare page
    String productPrice = comparePage.getProductPrice(3); //$134.00
    String productName = comparePage.getProductNameByIndex(3);
    int productId = comparePage.getProductIdByIndex(3);
    comparePage.clickAddToCartByIndex(3);

    // Verify product info in Add to cart quickview popup
    Assert.assertTrue(comparePage.isProductNameDisplayedInAddToCartPopup(productName));
    Assert.assertTrue(comparePage.isProductPriceDisplayedInAddToCartPopup(productPrice));
    // Assert.assertEquals(comparePage.getProductIdInAddToCartPopup(),productId);

    // Select product size
    String mediumSize = "Medium (-$28.80)";
    String largeSize = "Large (+$18.80)";
    comparePage.selectProductSizeInAddToCartPopup(mediumSize);

    // Click Add to cart button in popup
    comparePage.clickAddToCartInAddToCartPopup();

    // Verify Add to cart success message
    Assert.assertTrue(comparePage.getCartPopupText().replaceAll("\\s+", " ").trim()
    .contains("Success: You have added " + productNameInCollectionPopular + " to your shopping cart"));
    homePage.clickToCartCompareWishlistClosePopup();
    removeProductFromCompare(homePage,3);
    }

    @Test
    public void TC_16_Compare_Page_Add_To_Cart_Popup_Not_Select_Size() {
        log.info(
                "TC_16_Compare_Page_Add_To_Cart_Popup_Not_Select_Size - Step 1: Get product name in Collection Popular");
        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
        // type
        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
        addProductToCompare(homePage, 3);
        comparePage = homePage.clickCompareIcon();
        comparePage.clickAddToCartByIndex(3);

        // Click Add to cart button in popup
        comparePage.clickAddToCartInAddToCartPopup();

        // Verify Warning message
        Assert.assertTrue(comparePage.getSizeWarningMessageText().contains("Size required!"));

        // Verify Add to Cart success message not displayed
        Assert.assertTrue(comparePage.isAddToCartSuccessPopupUndisplayed());
        comparePage.clickCloseIconInAddToCartPopup();
        homePage = comparePage.clickHomeMenuItem();
        removeProductFromCompare(homePage, 3);
    }

    // Do trang web UI bị lỗi khi chọn size Large (+$18.00) nên tạm thời chưa chọn size này được, cần dev fix lại mới chạy được testcase này
    // @Test
    public void TC_17_Compare_Page_Add_To_Cart_Popup_Input_Quantity_0() {
        log.info("TC_17_Compare_Page_Add_To_Cart_Popup_Input_Quantity_0 ");
        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
        // type
        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
        addProductToCompare(homePage, 3);
        comparePage = homePage.clickCompareIcon();
        comparePage.clickAddToCartByIndex(1);

        // Select product size
        String largeSize = "Large (+$18.00)";
        comparePage.selectProductSizeInAddToCartPopup(largeSize);

        // Input quantity = 0
        comparePage.inputQuantityInAddToCartPopup("0");

        // Click Add to cart button in popup
        comparePage.clickAddToCartInAddToCartPopup();

        // Verify Add to cart success message
        Assert.assertTrue(comparePage.getCartPopupText().replaceAll("\\s+", " ").trim()
                .contains("Success: You have added " + productNameInCollectionPopular + " to your shopping cart"));

        // Remove all products from compare page
        comparePage.clickToCartCompareWishlistClosePopup();

        removeProductFromCompare(homePage, 3);
    }

    // @Test
    public void TC_18_Compare_Page_Add_To_Cart_Popup_Input_Quantity_Over() {
        log.info("TC_18_Compare_Page_Add_To_Cart_Popup_Input_Quantity_Over ");
        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
        // type
        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
        addProductToCompare(homePage, 3);
        comparePage = homePage.clickCompareIcon();
        comparePage.clickAddToCartByIndex(3);

        // Select product size
        String largeSize = "Large (+$18.00)";
        comparePage.selectProductSizeInAddToCartPopup(largeSize);

        // Input quantity = 1000
        comparePage.inputQuantityInAddToCartPopup("1000");

        // Click Add to cart button in popup
        comparePage.clickAddToCartInAddToCartPopup();

        // Verify Add to cart success message
        Assert.assertTrue(comparePage.getCartPopupText().replaceAll("\\s+", " ").trim()
                .contains("Success: You have added " + productNameInCollectionPopular + " to your shopping cart"));

        // Remove all products from compare page
        comparePage.clickToCartCompareWishlistClosePopup();

        removeProductFromCompare(homePage, 3);
    }

    // Thêm 1 case test có thể input quantity - done
    // 1 case submit without select product size -DONE
    // 1 case select quantity = 0
    // 1 case select quantity > available quantity

    // @Test
    public void TC_19_Compare_Page_Add_To_Cart_Popup_Increase_Quantity_Button() {
        log.info("TC_19_Compare_Page_Add_To_Cart_Popup_Increase_Quantity_Button ");
        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
        // type
        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
        addProductToCompare(homePage, 3);
        comparePage = homePage.clickCompareIcon();
        comparePage.clickAddToCartByIndex(3);

        // Select product size
        String largeSize = "Large (+$18.00)";
        comparePage.selectProductSizeInAddToCartPopup(largeSize);

        // Quantity button
        int quantityBefore = comparePage.getQuantityInAddToCartPopup();
        comparePage.clickPlusQuantityInAddToCartPopup(2);
        int quantityAfter = comparePage.getQuantityInAddToCartPopup();
        Assert.assertEquals(quantityBefore + 2, quantityAfter);

        comparePage.clickCloseIconInAddToCartPopup();
        homePage = comparePage.clickHomeMenuItem();
        removeProductFromCompare(homePage, 3);
    }

    // @Test
    public void TC_19_Compare_Page_Add_To_Cart_Popup_Decrease_Quantity_Button() {
        log.info("TC_19_Compare_Page_Add_To_Cart_Popup_Decrease_Quantity_Button ");
        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
        // type
        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
        addProductToCompare(homePage, 3);
        comparePage = homePage.clickCompareIcon();
        comparePage.clickAddToCartByIndex(3);

        // Select product size
        String largeSize = "Large (+$18.00)";
        comparePage.selectProductSizeInAddToCartPopup(largeSize);
        comparePage.clickPlusQuantityInAddToCartPopup(3);

        // Quantity button
        int quantityBefore = comparePage.getQuantityInAddToCartPopup(); // 5
        comparePage.clickMinusQuantityInAddToCartPopup(2);
        int quantityAfter = comparePage.getQuantityInAddToCartPopup(); // 3
        Assert.assertEquals(quantityBefore - 2, quantityAfter);

        comparePage.clickCloseIconInAddToCartPopup();
        homePage = comparePage.clickHomeMenuItem();
        removeProductFromCompare(homePage, 3);
    }

    // @Test
    public void TC_20_Compare_Page_Add_To_Cart_BuyNow() {
        log.info("TC_20_Compare_Page_Add_To_Cart_BuyNow ");
        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product
        // type
        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
        addProductToCompare(homePage, 3);
        comparePage = homePage.clickCompareIcon();

        // Get product info in compare page
        String productPrice = comparePage.getProductPrice(3); // $134.00
        String productName = comparePage.getProductNameByIndex(3);
        int productId = comparePage.getProductIdByIndex(3);

        comparePage.clickAddToCartByIndex(3);

        // Select product size
        String largeSize = "Large (+$18.00)";
        comparePage.selectProductSizeInAddToCartPopup(largeSize);

        // Verify product info in Add to cart popup
        Assert.assertTrue(comparePage.isProductNameDisplayedInAddToCartPopup(productName));
        Assert.assertTrue(comparePage.isProductPriceDisplayedInAddToCartPopup(productPrice));
        // Assert.assertEquals(comparePage.getProductIdInAddToCartPopup(),productId);

        // Click Add to cart button in popup
        cartPage = comparePage.clickBuyNowInAddToCartPopup();

        // Verify CartPage title displayed
        Assert.assertTrue(cartPage.getShoppingCartPageTitle().contains("Shopping Cart"));
        Assert.assertTrue(cartPage.getCartPageURL().contains("route=checkout/cart"));

        homePage = cartPage.clickHomeMenuItem();
        removeProductFromCompare(homePage, 3);

    }



    private void verifyProductInfoInComparePage(ComparePO comparePage, int index, int productIdsCollectionPopular) {
        // From UI
        int productIdComparePage = comparePage.getProductIdByIndex(index);
        String productName = comparePage.getProductNameByIndex(index);
        String productImageSrc = comparePage.getProductImageSrcByIndex(index);
        String productPrice = comparePage.getProductPrice(index);

        // From DB
        Product dbProduct = comparePage.getProductInfoFromDB(productIdComparePage);
        String dbProductName = dbProduct.getName();
        String dbProductPrice = String.valueOf(dbProduct.getPrice());
        List<String> dbMediaSrcList = comparePage.getProductMediaSrcFromDB(productIdComparePage);

        // Verify data
        // Assert.assertTrue(dbMediaSrcList.contains(splitUIImageSrc)); //Tạm bỏ qua
        // verify image do DB thiếu dữ liệu
        Assert.assertEquals(productIdComparePage, productIdsCollectionPopular);
        Assert.assertEquals(productName, dbProductName);
        Assert.assertTrue(productPrice.contains(dbProductPrice));
    }

    private void addProductToCompareAndVerifyComparePage(HomePO homePage, int numOfProduct) {
        for (int i = 1; i <= numOfProduct; i++) {

            int productIdInCollectionPopular = homePage.getProductIdInCollectionPopular(i);
            homePage.scrollToProductInCollectionPopular(i);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToCompareIconFromCollectionPopular(i);

            // vào trang compare - verify number of products
            comparePage = homePage.clickProductCompareLinkInPopup();
            int numberOfProductsInComparePage = comparePage.getNumberOfProductsInComparePage();
            Assert.assertTrue(comparePage.isCompareTitleDisplayed());
            Assert.assertEquals(numberOfProductsInComparePage, i);

            // verify product information: name, price, image
            verifyProductInfoInComparePage(comparePage, i, productIdInCollectionPopular);

            homePage.clickHomeMenuItem();
        }
    }

    private void addProductToCompare(HomePO homePage, int numOfProduct) {
        for (int i = 1; i <= numOfProduct; i++) {
            System.out.println("Adding product " + i + " to compare");
            String productName = homePage.getProducNameInCollectionPopular(i);
            System.out.println("productName = " + productName);
            homePage.scrollToProductInCollectionPopular(i);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToCompareIconFromCollectionPopular(i);

            // vào trang compare
            comparePage = homePage.clickProductCompareLinkInPopup();
            int numberOfProductsInComparePage = comparePage.getNumberOfProductsInComparePage();
            Assert.assertTrue(comparePage.isCompareTitleDisplayed());
            Assert.assertEquals(numberOfProductsInComparePage, i);
            homePage.clickHomeMenuItem();
        }
    }

    private void removeProductFromCompare(HomePO homePage, int numOfProduct) {

        homePage.clickHomeMenuItem();
        comparePage = homePage.clickCompareIcon();
        while (numOfProduct > 1) {
            comparePage.clickRemoveProductByIndex(numOfProduct);
            Assert.assertTrue(comparePage.getSuccessMessageText()
                    .contains("Success: You have modified your product comparison!"));
            numOfProduct--;
            System.out.println("Number of products after remove: " + numOfProduct);
            int remainProduct = comparePage.getNumberOfProductsInComparePage();
            System.out.println("Number of products displayed after remove: " + remainProduct);
            Assert.assertEquals(remainProduct, numOfProduct);
        }

        // Remove last product
        comparePage.clickRemoveProductByIndex(numOfProduct);
        System.out.println("Number of products after remove all: " + numOfProduct);
        Assert.assertTrue(comparePage.isNoProductMessageDisplayed());
        homePage.clickHomeMenuItem();
    }

    private void addProductToCompareAndVerifyPopup(HomePO homePage, int numOfProduct) {
        for (int i = 1; i <= numOfProduct; i++) {
            String productName = homePage.getProducNameInCollectionPopular(i);
            homePage.scrollToProductInCollectionPopular(i);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToCompareIconFromCollectionPopular(i);
            Assert.assertEquals(homePage.getCompareCartWishlistPopupTitleText(), "Product Compare (" + i + ")");
            Assert.assertTrue(homePage.getCompareCartWishListPopupText().replaceAll("\\s+", " ").trim()
                    .contains("Success: You have added " + productName + " to your product comparison"));
            homePage.clickToCartCompareWishlistClosePopup();
        }
    }

}
