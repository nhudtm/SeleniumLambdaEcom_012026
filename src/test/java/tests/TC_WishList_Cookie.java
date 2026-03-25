package tests;

import utils.LoginHelper;
import utils.PropertiesConfig;
import commons.BaseTest;
import components.ProductComponent;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.List;
import java.util.Set;

public class TC_WishList_Cookie extends BaseTest {
    WebDriver driver;
    HomePO homePage;
    ComparePO comparePage;
    CartPO cartPage;
    WishListPO wishListPage;
    List<ProductComponent> allProductsInCollectionPopular;
    MyAccountPO myAccount;
    String email, password;
    Set<Cookie> cookies;

      @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        log.info(
                "Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    
        email = PropertiesConfig.getProp("email");
        password = PropertiesConfig.getProp("password");

        // use cookie here for login state, but the website does not allow to add cookie, so we have to login with UI
        LoginHelper.login(homePage,email,password);
        homePage.sleepInSecond(5);
    }

    @Test(groups = {"regression", "wishlist"})
    public void TC_01_Add_To_WishList_Popup_1_Product() {
        //Go to wishlist page and remove all product if have
        wishListPage = homePage.clickWishListIconWithLoggedIn();
        removeAllProductInWishList();

        //Go to home page to add product to wishlist and verify popup
        homePage = wishListPage.clickHomeMenuItem();
        int numOfProduct = 1;
        addProductToWishLIstAndVerifyPopup(numOfProduct);
    }



   @Test
   public void TC_02_Add_To_WishList_Popup_2_Products() {
       int numOfProduct = 2;
       //Login
       myAccount = homePage.clickMyAccountMenuItem();
    //    myAccount.inputToEmailTextbox(email);
    //    myAccount.inputToPasswordTextbox(password);
    //    myAccount.clickLoginButton();

       //Go to wishlist page and remove all product if have
       wishListPage = myAccount.clickWishListIconWithLoggedIn();
       removeAllProductInWishList();

       //Go to home page to add product to wishlist and verify popup
       homePage = wishListPage.clickHomeMenuItem();
       addProductToWishLIstAndVerifyPopup(numOfProduct);

   }


//    @Test
//    public void TC_03_Add_To_WishList_Popup_4_Product() {
//        int numOfProduct = 4;
//        //Login
//        myAccount = homePage.clickMyAccountMenuItem();
//        myAccount.inputToEmailTextbox(email);
//        myAccount.inputToPasswordTextbox(password);
//        myAccount.clickLoginButton();
//
//        //Go to wishlist page and remove all product if have
//        wishListPage = myAccount.clickWishListIconWithLoggedIn();
//        removeAllProductInWishList();
//
//        //Go to home page to add product to wishlist and verify popup
//        homePage = wishListPage.clickHomeMenuItem();
//        addProductToWishLIstAndVerifyPopup(numOfProduct);
//
//
//    }


//    @Test
//    public void TC_05_Verify_WishList_Page_0_Product() {
////        Login
//        myAccount = homePage.clickMyAccountMenuItem();
//        myAccount.inputToEmailTextbox(email);
//        myAccount.inputToPasswordTextbox(password);
//        myAccount.clickLoginButton();
//
//        wishListPage = homePage.clickWishListIconWithLoggedIn();
//        removeAllProductInWishList();
//        Assert.assertTrue(wishListPage.isNoProductMessageDisplayed());
//        Assert.assertEquals(wishListPage.getNoProductMessage(), "No results!");
//        homePage = wishListPage.clickHomeMenuItem();
//    }

//
//    @Test
//    public void TC_06_Verify_WishList_Page_1_Product() {
//        int numOfProduct = 1;
//
//        //Login
//        myAccount = homePage.clickMyAccountMenuItem();
//        myAccount.inputToEmailTextbox(email);
//        myAccount.inputToPasswordTextbox(password);
//        myAccount.clickLoginButton();
//
//        //Go to wishlist page and remove all product if have
//        wishListPage = myAccount.clickWishListIconWithLoggedIn();
//        removeAllProductInWishList();
//
//        //Go to home page to add product to wishlist and verify popup
//        homePage = wishListPage.clickHomeMenuItem();
//        addProductToCompareAndVerifyWishlistPage(numOfProduct);
//    }

//    @Test
//    public void TC_07_Verify_WishList_Page_4_Product() {
//        int numOfProduct = 4;
//
//        //Login
//        myAccount = homePage.clickMyAccountMenuItem();
//        myAccount.inputToEmailTextbox(email);
//        myAccount.inputToPasswordTextbox(password);
//        myAccount.clickLoginButton();
//
//        //Go to wishlist page and remove all product if have
//        wishListPage = myAccount.clickWishListIconWithLoggedIn();
//        removeAllProductInWishList();
//
//        //Go to home page to add product to wishlist and verify popup
//        homePage = wishListPage.clickHomeMenuItem();
//        addProductToWishlistAndVerifyWishlistPage(numOfProduct);
//    }
//
//
//    @Test
//    public void TC_09_WishList_Page_Remove_1_Product() {
//        int numOfProduct = 1;
//
//    }
//
//    @Test
//    public void TC_10_WishList_Page_Remove_3_Product() {
//        int numOfProduct = 2;
//        addProductToCompare(numOfProduct);
//        removeProductFromCompare(numOfProduct);
//    }
//
//    @Test
//    public void TC_11_WishList_Page_Remove_4_Product() {
//        int numOfProduct = 4;
//        addProductToCompare(numOfProduct);
//        removeProductFromCompare(numOfProduct);
//    }
//
//
//
//    @Test
//    public void TC_13_Add_To_WishList_Verify_Popup_Same_Product_Twice() {
//        // Add first product to compare
//        addProductToCompareAndVerifyPopup(1);
//
//        // Vì trang web này khi click lần 2 nó không có work, nên phải click lần 2 này, để click lần 3
//        homePage.hoverToProductInCollectionPopular(1);
//        homePage.clickAddToCompareIconFromCollectionPopular(1);
//
//        // Add the same product to compare again
//        addProductToCompareAndVerifyPopup(1);
//    }
//
//    @Test
//    public void TC_14_Add_To_WishList_Verify_Page_Same_Product_Twice() {
//        // Add first product to compare
//        int productIdInCollectionPopular = homePage.getProductIdInCollectionPopular(1);
//        addProductToCompare(1);
//        addProductToCompare(1);
//
//        // Go to compare page
//        comparePage = homePage.clickCompareIcon();
//        int numberOfProductsInComparePage = comparePage.getNumberOfProductsInComparePage();
//        Assert.assertEquals(numberOfProductsInComparePage, 1);
//        verifyProductInfoInComparePage(1, productIdInCollectionPopular);
//    }
//
//    @Test
//    public void TC_15_WishList_Page_Add_To_Cart_Popup_Success() {
//        // Lấy số 3 vì product thứ 3 có bật Add to cart popup và có chọn được product type
//        String productNameInCollectionPopular = homePage.getProducNameInCollectionPopular(3);
//        addProductToCompare(3);
//        comparePage = homePage.clickCompareIcon();
//        comparePage.clickAddToCartByIndex(3);
//
//        // Get product info in compare page
//        String productPrice = comparePage.getProductPrice(3); //$134.00
//        String productName = comparePage.getProductNameByIndex(3);
//        int productId =  comparePage.getProductIdByIndex(3);
//
//        // Verify product info in Add to cart quickview popup
//        Assert.assertTrue(comparePage.isProductNameDisplayedInAddToCartPopup(productName));
//        Assert.assertTrue(comparePage.isProductPriceDisplayedInAddToCartPopup(productPrice));

    /// /        Assert.assertEquals(comparePage.getProductIdInAddToCartPopup(),productId);
//
//        // Select product size
//        String mediumSize = "Medium (-$28.80)";
//        String largeSize = "Large (+$18.80)";
//        comparePage.selectProductSizeInAddToCartPopup(mediumSize);
//
//        // Click Add to cart button in popup
//        comparePage.clickAddToCartInAddToCartPopup();
//
//        // Verify Add to cart success message
//        Assert.assertTrue(comparePage.getCartPopupText().replaceAll("\\s+", " ").trim().contains("Success: You have added " + productNameInCollectionPopular + " to your shopping cart"));
//        homePage.clickToCartOrCompareClosePopup();
//    }
//
//    @Test
//    public void TC_16_WishList_Page_Add_To_Cart_Popup_Success_2nd() {
//
//    }
//
//    @Test
//    public void TC_17_WishList_Page_Remove() {
//
//    }
//
//    @Test
//    public void TC_18_WishList_Page_Continue() {
//
//
//    }
    private void verifyProductInfoInWishlistPage(int index, int productIdsCollectionPopular) {
//        // From UI
//        String productName = wishListPage.getProductNameByIndex(index);
//        String productPrice = wishListPage.getProductPrice(index);
//
//        // From home page
//
//        // Verify data
//        // Assert.assertTrue(dbMediaSrcList.contains(splitUIImageSrc)); //Tạm bỏ qua verify image do DB thiếu dữ liệu
//        Assert.assertEquals(productIdComparePage, productIdsCollectionPopular);
//        Assert.assertEquals(productName, dbProductName);
//        Assert.assertTrue(productPrice.contains(dbProductPrice));
    }

    private void addProductToWishlistAndVerifyWishlistPage(int numOfProduct) {
        for (int i = 1; i <= numOfProduct; i++) {
            // Get product info from home
            String productNameFromHomePage = homePage.getProducNameInCollectionPopular(i);
            String productPriceFromHomePage = homePage.getProductPriceInCollectionPopular(i);
            System.out.println("Adding product " + i + " to wishlist: " + productNameFromHomePage + " with price: " + productPriceFromHomePage);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToWishListIconFromCollectionPopular(i);

            // vào trang compare - verify number of products. List product trong My wishlist theo thứ tự abc chứ không theo stack or queue
            wishListPage = homePage.clickWishlistLinkInWishlistPopup();
            int numberOfProductsInWishlistPage = wishListPage.getTotalProduct();
            List<String> allProductNamesInWishlistPage = wishListPage.getAllProductNamesInWishlistPage();
            for (String productName : allProductNamesInWishlistPage) {
                System.out.println("Product in wishlist page: " + productName);
            }
            Assert.assertTrue(allProductNamesInWishlistPage.contains(productNameFromHomePage));

            // verify product information: name, price, image
            Assert.assertTrue(wishListPage.isYourWishListTitleDisplayed());
            Assert.assertEquals(numberOfProductsInWishlistPage, i);


            homePage.clickHomeMenuItem();
        }
    }

    private void addProductToCompare(int numOfProduct) {
        for (int i = 1; i <= numOfProduct; i++) {
            System.out.println("Adding product " + i + " to compare");
            String productName = homePage.getProducNameInCollectionPopular(i);
            System.out.println("productName = " + productName);
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

    private void removeProductFromCompare(int numOfProduct) {
        comparePage = homePage.clickCompareIcon();
        while (numOfProduct > 1) {
            comparePage.clickRemoveProductByIndex(numOfProduct);
            Assert.assertTrue(comparePage.getSuccessMessageText().contains("Success: You have modified your product comparison!"));
            numOfProduct--;
            System.out.println("Number of products after remove: " + numOfProduct);
            int remainProduct = comparePage.getNumberOfProductsInComparePage();
            System.out.println("Number of products displayed after remove: " + remainProduct);
            Assert.assertEquals(remainProduct, numOfProduct);
        }

        //Remove last product
        comparePage.clickRemoveProductByIndex(numOfProduct);
        System.out.println("Number of products after remove all: " + numOfProduct);
        Assert.assertTrue(comparePage.isNoProductMessageDisplayed());
        homePage.clickHomeMenuItem();
    }

    private void addProductToWishLIstAndVerifyPopup(int numOfProduct) {
        for (int i = 1; i <= numOfProduct; i++) {
            String productName = homePage.getProducNameInCollectionPopular(i);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToWishListIconFromCollectionPopular(i);
            Assert.assertEquals(homePage.getCompareCartWishlistPopupTitleText(), "Wish List (" + i + ")");
            Assert.assertTrue(homePage.getCompareCartWishListPopupText().replaceAll("\\s+", " ").trim().contains("Success: You have added " + productName + " to your wish list"));
            homePage.clickToCartCompareWishlistClosePopup();
        }
    }

    private void removeAllProductInWishList() {
        int totalProduct = 0;
        wishListPage = homePage.clickWishListIconWithLoggedIn();
        try {
            totalProduct = wishListPage.getTotalProduct();
        } catch (Exception e) {
            System.out.println("Error getting total products: " + e.getMessage());
            Assert.assertEquals(wishListPage.getNoProductMessage(), "No results!");
            return;
        }
        totalProduct = wishListPage.getTotalProduct();

        System.out.println("Total products in wishlist before remove: " + totalProduct);
        while (totalProduct > 0) {
            wishListPage.clickRemoveButtonByIndex(totalProduct);
            totalProduct--;
            System.out.println("Total products in wishlist after remove: " + totalProduct);
        }
        Assert.assertEquals(wishListPage.getNoProductMessage(), "No results!");

    }

}

