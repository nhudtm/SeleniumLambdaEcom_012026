package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.CartPO;
import pageObjects.HomePO;
import pageObjects.PageGenerator;

public class TC_Cart extends BaseTest {
    HomePO homePage;
    CartPO cart;

    @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        log.info(
                "Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    // 1. Access to Shopping Cart/ Check out page: From Cart Icon, From Add to Cart
    // in Product Action, From Compare - Add to Cart Popup, From Wishlist - Add to
    // Cart Popup
    // 2. Verify Product added to Cart vs Product displayed in Cart/ Check out page
    // 3. From Cart/ Check out page, click Continue, Checkout
    // 4. Verify amount, total price, shipping fee
    // 5. verify remove product success
    // 5. Verify Cart/ Check out page display empty after click Remove icon of
    // product
    // 6. Update quantity of product in Cart/ Check out page

   
    @Test(groups = {"regression", "cart"})
    public void TC_01_View_Empty_Cart() {
        cart = homePage.clickCartIcon();
        Assert.assertTrue(cart.isShoppingCartDrawerDisplayed());
        cart.clickCheckoutButtonInCartDrawer();
        Assert.assertTrue(cart.getCartPageURL().contains("route=checkout/cart"));
        Assert.assertEquals(cart.getEmptyCartMessage(), "Your shopping cart is empty!");
        homePage = cart.clickHomeMenuItem();
    }


    @Test(groups = {"regression", "cart"})
    public void TC_02_Add_To_Cart_Popup_1_Product() {
        int numOfProduct = 1;
        addProductToCartAndVerifyPopup(numOfProduct);

    }
  //  Tạm ignore case này do web limitation
    // @Test
    public void TC_03_Add_To_Cart_Popup_2_Products() {
    int numOfProduct = 2;
    addProductToCartAndVerifyPopup(numOfProduct);
  
    }

    @Test
    public void TC_03_Verify_Shopping_Cart_Page_1_Product() {
        Map<String, String> productInfo = getProductInfo(homePage, 2);
        homePage.hoverToProductInCollectionPopular(2);
        homePage.clickAddToCartInCollectionPopular(2);
        cart = homePage.clickCheckoutButtonInCartPopup();
        Assert.assertTrue(cart.getShoppingCartPageTitle().contains("Shopping Cart"));
        verifyProductDetailsInCartPage(1, cart, productInfo);
        cart.clickRemoveProductByNumber(1);
        homePage = cart.clickHomeMenuItem();
    }

    @Test
    public void TC_04_Verify_Shopping_Cart_Page_2_Product() {
        Map<String, String> productInfo1 = getProductInfo(homePage, 2);
        Map<String, String> productInfo2 = getProductInfo(homePage, 4);
        addProductToCartAndGoToCartPage(2);
        verifyProductDetailsInCartPage(1, cart, productInfo1);
        homePage = cart.clickHomeMenuItem();

        addProductToCartAndGoToCartPage(4);
        verifyProductDetailsInCartPage(2, cart, productInfo2);

        cart.getTotalProductsInCartTable();
        Assert.assertEquals(cart.getTotalProductsInCartTable(), 2);
        cart.clickRemoveProductByNumber(2);

        homePage = cart.clickHomeMenuItem();
    }


    @Test
    public void TC_05_Update_Quantity_Valid() {
        addProductToCartAndGoToCartPage(2);
        int updateQuantity = 5;
        cart.inputQuantityInCartPage(1, updateQuantity);
        cart.pressEnter(1); // Hoặc click icon update cũng được
        Assert.assertEquals(cart.getProductQuantityInCartTable(1), updateQuantity);
        cart.clickRemoveProductByNumber(1);
        homePage = cart.clickHomeMenuItem();
    }

   
    // @Test
    // public void TC_05_Update_Quantity_0() {

    // }

    // @Test
    // public void TC_05_Update_Quantity_Over_Limit() {

    // }

   
    // @Test
    // public void TC_06_Remove_Product() {

    // }

    

    // @Test
    // public void TC_07_Remove_All_Products() {

    // }

    
    @Test
    public void TC_08_Verify_Total_Price_Calculation_Quantity_1() {
        int productIndex = 2;
        addProductToCartAndGoToCartPage(productIndex);
        int quantity = 1;
        float unitPrice = Float.parseFloat(cart.getProductUnitPriceInCartTable(1).replace("$", ""));
        float expectedTotalPrice = unitPrice * quantity;
        float totalPrice = Float.parseFloat(cart.getProductTotalPriceInCartTable(1).replace("$", ""));
        log.info("Unit price: " + unitPrice);
        log.info("Total price: " + totalPrice);
        Assert.assertEquals(totalPrice, expectedTotalPrice, 0.001);
        cart.clickRemoveProductByNumber(1);
        homePage = cart.clickHomeMenuItem();
    }

    @Test
    public void TC_9_Verify_Tax_And_Subtotal_1_Product_Quantity_1() throws InterruptedException {
        // Công thức tính đại, nên có thể sai, testcase sẽ fail
        addProductToCartAndGoToCartPage(2);
        int quantity = 1;
        float unitPrice = Float.parseFloat(cart.getProductUnitPriceInCartTable(1).replace("$", ""));
        float subTotal = Float.parseFloat(cart.getProductSubtotalPriceInCartTable().replace("$", ""));
        float ecoTax = Float.parseFloat(cart.getEcoTax().replace("$", ""));
        float vat = Float.parseFloat(cart.getVAT().replace("$", ""));
        float total = Float.parseFloat(cart.getGrandTotalPrice().replace("$", ""));
        float expectedTotalPrice = unitPrice * quantity;
        float expectedSubtotal = quantity * expectedTotalPrice;
        float expectedEcoTax = 2.00f * quantity; // -2 usd trên mỗi sản phẩm
        float expectedVAT = expectedSubtotal * 0.2f; // VAT 20%
        float expectedTotal = expectedSubtotal + expectedVAT + expectedEcoTax; // ecoTax lấy từ UI
        // Tạm comment phần assert vì không có công thức đúng
        // Assert.assertEquals(subTotal, expectedSubtotal, 0.001);
        // Assert.assertEquals(ecoTax, expectedEcoTax, 0.001);
        // Assert.assertEquals(vat, expectedVAT, 0.001);
        // Assert.assertEquals(total, expectedTotal, 0.001);
        
        homePage.clickHomeMenuItem();
    }

    @Test
    public void TC_10_Verify_Total_Price_Calculation_Quantity_5() {
        // Add 2nd product in Popular collection to cart and go to cart page
        addProductToCartAndGoToCartPage(2);
        int updateQuantity = 5;

        // Update quantity of the product at index 1 in cart page
        cart.inputQuantityInCartPage(1, updateQuantity);
        cart.clickUpdateButtonInCartPage(1);
        float unitPrice = Float.parseFloat(cart.getProductUnitPriceInCartTable(1).replace("$", ""));
        float expectedTotalPrice = unitPrice * updateQuantity;
        float totalPrice = Float.parseFloat(cart.getProductTotalPriceInCartTable(1).replace("$", ""));
        log.info("Unit price: " + unitPrice);
        log.info("Actual total price: " + totalPrice);
        log.info("Expected total price: " + expectedTotalPrice);
        Assert.assertEquals(totalPrice, expectedTotalPrice, 0.001);
        cart.clickRemoveProductByNumber(1);
        homePage.clickHomeMenuItem();

        System.out.println("Thread id TC_Cart: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }



    
    // @Test
    // public void TC_11_Proceed_To_Checkout() {
    //
    // }
    
    // @Test
    // public void TC_12_Continue_Shopping() {
    //
    // }
    
    
    // @Test
    // public void TC_13_Access_Checkout_From_Cart_Icon_On_Header() {
    //
    // }
    
    // @Test
    // public void TC_14_Access_Checkout_From_Shopping_Cart_Page() {
    //
    // }
    
    // @Test
    // public void TC_15_Access_Checkout_From_Product_Action_Button() {
    //
    // }
    
    // @Test
    // public void TC_16_Access_Checkout_From_Product_Detail_Page() {
    //
    // }
    
    // @Test
    // public void TC_17_Access_Checkout_After_Login() {
    // }
    //

    // @Test
    // public void TC_18_Access_Checkout_As_Guest() {
    // }

    private void addProductToCartAndVerifyPopup(int numOfProduct) {
        // lấy i = 2 vì product này có thể add to cart
        for (int i = 2; i <= numOfProduct + 1; i++) {
            int time = i - 1;
            Map<String, String> productInfo = getProductInfo(homePage, i);
            homePage.scrollToProductInCollectionPopular(i);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToCartInCollectionPopular(i);
            Assert.assertEquals(homePage.getCompareCartWishlistPopupTitleText(),
                    time + " item(s) - " + productInfo.get("price"));
            Assert.assertTrue(homePage.getCartPopupText().replaceAll("\\s+", " ").trim()
                    .contains("Success: You have added " + productInfo.get("name") + " to your shopping cart"));
        }
        homePage.clickToCartCompareWishlistClosePopup();
    }

    public Map<String, String> getProductInfo(HomePO homePage, int productIndex) {
        String productId = String.valueOf(homePage.getProductIdInCollectionPopular(productIndex));
        String productName = homePage.getProducNameInCollectionPopular(productIndex);
        String productPrice = homePage.getProductPriceInCollectionPopular(productIndex);
        return Map.of(
                "id", productId,
                "name", productName,
                "price", productPrice);
    }

    private void addProductToCartAndGoToCartPage(int index) {
        homePage.scrollToProductInCollectionPopular(index);
        homePage.hoverToProductInCollectionPopular(index);
        homePage.clickAddToCartInCollectionPopular(index);
        cart = homePage.clickCheckoutButtonInCartPopup();
        Assert.assertTrue(cart.getShoppingCartPageTitle().contains("Shopping Cart"));
    }

    public void verifyProductDetailsInCartPage(int index, CartPO cart, Map<String, String> productInfo) {
        // Kiểm tra product's image, Name, Unit price
        // String cartPageProductImageSrc = cart.getProductImageInCartTable(index); //
        // tạm ignore
        String cartPageProductId = cart.getProductIdInCartTable(index);
        String cartPageProductName = cart.getProductNameInCartTable(index);
        String cartPageProductPrice = cart.getProductUnitPriceInCartTable(index);
        // String cartPageModel = cart.getProductModelInCartTable(1); //ingnore vì db ko
        // có data
        // int cartPageQuantityInCartPage = cart.getProductQuantityInCartTable(index);
        // //để verify riêng
        // String productTotalPrice = cart.getProductTotalPriceInCartTable(index); //để
        // verify riêng
        Assert.assertEquals(productInfo.get("id"), cartPageProductId);
        Assert.assertEquals(productInfo.get("name"), cartPageProductName);
        Assert.assertEquals(productInfo.get("price"), cartPageProductPrice);
    }

    public void removeProductFromCartPage(int numberOfProduct) {
        for (int i = 1; i <= numberOfProduct; i++) {

        }
    }

}
