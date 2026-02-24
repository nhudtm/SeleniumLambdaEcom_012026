package tests;

import commons.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CartPO;
import pageObjects.HomePO;
import pageObjects.PageGenerator;

import java.util.Map;

public class TC_Cart extends BaseTest {

    HomePO homePage;
    CartPO cart;

    @Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})
    @BeforeClass
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion, @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriver2(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        System.out.println("Thread id beforeClass TC_Cart: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }


    // 1. Access to Shopping Cart/ Check out page: From Cart Icon, From Add to Cart in Product Action, From Compare - Add to Cart Popup, From Wishlist - Add to Cart Popup
    // 2. Verify Product added to Cart vs Product displayed in Cart/ Check out page
    // 3. From Cart/ Check out page, click Continue, Checkout
    // 4. Verify amount, total price, shipping fee
    // 5. verify remove product success
    // 5. Verify Cart/ Check out page display empty after click Remove icon of product
    // 6. Update quantity of product in Cart/ Check out page

    //## 🛒 **Nhóm Test Case: Hiển thị và chức năng cơ bản**

    //     ### ✅ TC_02_View_Empty_Cart
//- **Mục tiêu**: Kiểm tra giao diện khi giỏ hàng trống.
//- **Kỳ vọng**: Hiển thị thông báo “Your shopping cart is empty!”
    @Test
    public void TC_01_View_Empty_Cart() {
        cart = homePage.clickCartIcon();
        Assert.assertTrue(cart.isShoppingCartDrawerDisplayed());
        cart.clickCheckoutButtonInCartDrawer();
        Assert.assertTrue(cart.getCartPageURL().contains("route=checkout/cart"));
        Assert.assertEquals(cart.getEmptyCartMessage(), "Your shopping cart is empty!");
        homePage = cart.clickHomeMenuItem();
    }

// ### ✅ TC_01_View_Shopping_Cart_Page
//- **Mục tiêu**: Kiểm tra giao diện trang giỏ hàng khi có sản phẩm.
//- **Kỳ vọng**: Hiển thị đúng tên sản phẩm, ảnh, giá, số lượng, tổng tiền, nút cập nhật và xóa.
    @Test
    public void TC_02_Add_To_Cart_Popup_1_Product() {
//        System.out.println("Thread id TC_02: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        int numOfProduct = 1;
        addProductToCartAndVerifyPopup(numOfProduct);
    }

//    @Test
//    public void TC_03_Add_To_Cart_Popup_2_Products() {
//        int numOfProduct = 2;
////    addProductToCartAndVerifyPopup(numOfProduct); Tạm ignore case này do web nay no vay.
//    }

    @Test
    public void TC_03_Verify_Shopping_Cart_Page_1_Product() {
//        System.out.println("Thread id TC_03: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        Map<String, String> productInfo = getProductInfo(homePage, 2);
        homePage.hoverToProductInCollectionPopular(2);
        homePage.clickAddToCartInCollectionPopular(2);
        cart = homePage.clickCheckoutButtonInCartPopup();
        Assert.assertTrue(cart.getShoppingCartPageTitle().contains("Shopping Cart"));
        verifyProductDetailsInCartPage(1, cart, productInfo);
        homePage = cart.clickHomeMenuItem();
    }

    @Test
    public void TC_04_Verify_Shopping_Cart_Page_2_Product() {
//        System.out.println("Thread id TC_04: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        Map<String, String> productInfo1 = getProductInfo(homePage, 2);
        Map<String, String> productInfo2 = getProductInfo(homePage, 4);
        addProductToCartAndGoToCartPage(2);
        verifyProductDetailsInCartPage(1, cart, productInfo1);
        homePage = cart.clickHomeMenuItem();

        addProductToCartAndGoToCartPage(4);
        verifyProductDetailsInCartPage(2, cart, productInfo2);

        cart.getTotalProductsInCartTable();
        Assert.assertEquals(cart.getTotalProductsInCartTable(), 2);
        homePage = cart.clickHomeMenuItem();
    }


    // ## 🔄 **Nhóm Test Case: Cập nhật số lượng**
    // ### ✅ TC_03_Update_Quantity_Valid
    //- **Thực hiện**: Thay đổi số lượng từ 1 → 2 → click Update.
    //- **Kỳ vọng**: Tổng tiền thay đổi đúng, thông báo cập nhật thành công.
    @Test
    public void TC_05_Update_Quantity_Valid() {
//        System.out.println("Thread id TC_05: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        addProductToCartAndGoToCartPage(2);
        int updateQuantity = 5;
        cart.inputQuantityInCartPage(1, updateQuantity);
        Assert.assertEquals(cart.getProductQuantityInCartTable(1), updateQuantity);
        cart.clickHomeMenuItem();
    }

    // ### ✅ TC_04_Update_Quantity_To_Zero
    //- **Thực hiện**: Nhập số lượng = 0 → click Update.
    //- **Kỳ vọng**: Sản phẩm bị xóa khỏi giỏ hàng, hiển thị thông báo.
    @Test
    public void TC_05_Update_Quantity_0() {

    }

    @Test
    public void TC_05_Update_Quantity_Over_Limit() {

    }


    // ## ❌ **Nhóm Test Case: Xóa sản phẩm**
    // ### ✅ TC_06_Remove_Product
    //- **Thực hiện**: Click biểu tượng xóa (trash icon).
    //            - **Kỳ vọng**: Sản phẩm bị xóa, tổng tiền cập nhật, hiển thị thông báo.
    @Test
    public void TC_06_Remove_Product() {

    }

    // ### ✅ TC_07_Remove_All_Products
    //- **Thực hiện**: Xóa tất cả sản phẩm trong giỏ.
    //- **Kỳ vọng**: Giỏ hàng trống, hiển thị thông báo tương ứng.

    @Test
    public void TC_07_Remove_All_Products() {

    }

//     ## 💰 **Nhóm Test Case: Tính toán giá tiền**
//
//    ### ✅ TC_08_Verify_Total_Price_Calculation
//    - **Thực hiện**: Thêm nhiều sản phẩm, kiểm tra tổng tiền.
//    - **Kỳ vọng**: Tổng tiền = đơn giá × số lượng từng sản phẩm.
    @Test
    public void TC_08_Verify_Total_Price_Calculation_Quantity_1() {
        System.out.println("Thread id TC_08: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        addProductToCartAndGoToCartPage(2);
        int quantity = 1;
        float unitPrice = Float.parseFloat(cart.getProductUnitPriceInCartTable(1).replace("$", ""));
        float expectedTotalPrice = unitPrice * quantity;
        float totalPrice = Float.parseFloat(cart.getProductTotalPriceInCartTable(1).replace("$", ""));
        System.out.println(unitPrice);
        System.out.println(totalPrice);
        Assert.assertEquals(totalPrice, expectedTotalPrice, 0.001);
        homePage = cart.clickHomeMenuItem();
    }

    @Test
    public void TC_09_Verify_Total_Price_Calculation_Quantity_5() {
        System.out.println("Thread id TC_09: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        // Add 2nd product in Popular collection to cart and go to cart page
        addProductToCartAndGoToCartPage(2);
        int updateQuantity = 5;

        // Update quantity of the product at index 1 in cart page
        cart.inputQuantityInCartPage(1, updateQuantity);
        cart.clickUpdateButtonInCartPage(1);
        float unitPrice = Float.parseFloat(cart.getProductUnitPriceInCartTable(1).replace("$", ""));
        float expectedTotalPrice = unitPrice * updateQuantity;
        float totalPrice = Float.parseFloat(cart.getProductTotalPriceInCartTable(1).replace("$", ""));
        System.out.println(unitPrice);
        System.out.println(totalPrice);
        Assert.assertEquals(totalPrice, expectedTotalPrice, 0.001);
        homePage.clickHomeMenuItem();

        System.out.println("Thread id TC_Cart: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }


//    / /### ✅ TC_10_Verify_Tax_And_Subtotal
//    / /- **Thực hiện**: Kiểm tra hiển thị Subtotal, Tax, Total.
//    / /- **Kỳ vọng**: Các giá trị được tính đúng theo cấu hình.

    @Test
    public void TC_10_Verify_Tax_And_Subtotal_1_Product_Quantity_1() {
        System.out.println("Thread id TC_10: " + Thread.currentThread().getId() + " - " + getDriver().toString());
        // Công thức tính đại, nên có thể sai
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
        Assert.assertEquals(subTotal, expectedSubtotal, 0.001);
        Assert.assertEquals(ecoTax, expectedEcoTax, 0.001);
        Assert.assertEquals(vat, expectedVAT, 0.001);
        Assert.assertEquals(total, expectedTotal, 0.001);
        homePage.clickHomeMenuItem();
    }

//    @Test
//    public void TC_10_Verify_Tax_And_Subtotal_1_Product_Quantity_5() {
//
//    }
//
//    @Test
//    public void TC_10_Verify_Tax_And_Subtotal_2_Product_Quantity_1() {
//
//    }
//
//    @Test
//    public void TC_10_Verify_Tax_And_Subtotal_2_Product_Quantity_5() {
//
//    }
//
//    //## 🚚 **Nhóm Test Case: Chuyển tiếp và hành vi**
//    //### ✅ TC_10_Proceed_To_Checkout
//    //- **Thực hiện**: Click nút “Checkout”.
//    //            - **Kỳ vọng**: Điều hướng đến trang thanh toán.
//    @Test
//    public void TC_10_Proceed_To_Checkout() {
//
//    }
//
//    // ### ✅ TC_11_Continue_Shopping
//    //- **Thực hiện**: Click nút “Continue Shopping”.
//    //- **Kỳ vọng**: Quay lại trang sản phẩm hoặc homepage.
//    @Test
//    public void TC_11_Continue_Shopping() {
//
//    }
//
//    //    ✅ TC_15_Access_Checkout_From_Cart_Icon_On_Header
//    //    Thực hiện: Click vào biểu tượng giỏ hàng ở header sau khi thêm sản phẩm.
//    //    Kỳ vọng: Hiển thị popup/cart dropdown → click “Checkout” → điều hướng đến trang Checkout.
//
//    @Test
//    public void TC_15_Access_Checkout_From_Cart_Icon_On_Header() {
//
//    }
//
//    //✅ TC_16_Access_Checkout_From_Shopping_Cart_Page
//    //    Thực hiện: Thêm sản phẩm → vào trang giỏ hàng → click nút “Checkout”.
//    //    Kỳ vọng: Điều hướng đến trang Checkout.
//
//    @Test
//    public void TC_16_Access_Checkout_From_Shopping_Cart_Page() {
//
//    }
//
//    //✅ TC_17_Access_Checkout_From_Product_Action_Button
//    //    Thực hiện: Tại danh sách sản phẩm, click “
//    //    Add to Cart” → click “Checkout” từ popup.
//    //    Kỳ vọng: Điều hướng đến trang Checkout.
//
//    @Test
//    public void TC_17_Access_Checkout_From_Product_Action_Button() {
//
//    }
//
//    //✅ TC_18_Access_Checkout_From_Product_Detail_Page
//    //    Thực hiện: Vào trang chi tiết sản phẩm → click “Add to Cart” → click “Checkout” từ popup.
//    //    Kỳ vọng: Điều hướng đến trang Checkout.
//
//    @Test
//    public void TC_18_Access_Checkout_From_Product_Detail_Page() {
//
//    }
//
//    //✅ TC_19_Access_Checkout_After_Login
//    //    Thực hiện: Đăng nhập → thêm sản phẩm → click “Checkout”.
//    //
//    //    Kỳ vọng: Điều hướng đến trang Checkout với thông tin người dùng được điền sẵn.
//    @Test
//    public void TC_19_Access_Checkout_After_Login() {
//    }
//
//    //✅ TC_20_Access_Checkout_As_Guest
//    //    Thực hiện: Thêm sản phẩm → click “Checkout” khi chưa đăng nhập.
//    //    Kỳ vọng: Điều hướng đến trang Checkout với lựa chọn “Guest Checkout” hoặc yêu cầu đăng nhập.

//    @Test
//    public void TC_20_Access_Checkout_As_Guest() {
//    }
    private void addProductToCartAndVerifyPopup(int numOfProduct) {
        // lấy i = 2 vì product này có thể add to cart
        for (int i = 2; i <= numOfProduct + 1; i++) {
            int time = i - 1;
            Map<String, String> productInfo = getProductInfo(homePage, i);
            homePage.scrollToProductInCollectionPopular(i);
            homePage.hoverToProductInCollectionPopular(i);
            homePage.clickAddToCartInCollectionPopular(i);
            Assert.assertEquals(homePage.getCompareCartWishlistPopupTitleText(), time + " item(s) - " + productInfo.get("price"));
            Assert.assertTrue(homePage.getCartPopupText().replaceAll("\\s+", " ").trim().contains("Success: You have added " + productInfo.get("name") + " to your shopping cart"));
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
                "price", productPrice
        );
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
//        String cartPageProductImageSrc = cart.getProductImageInCartTable(index); // tạm ignore
        String cartPageProductId = cart.getProductIdInCartTable(index);
        String cartPageProductName = cart.getProductNameInCartTable(index);
        String cartPageProductPrice = cart.getProductUnitPriceInCartTable(index);
//        String cartPageModel =  cart.getProductModelInCartTable(1); //ingnore vì db ko có data
//        int cartPageQuantityInCartPage = cart.getProductQuantityInCartTable(index); //để verify riêng
//        String productTotalPrice = cart.getProductTotalPriceInCartTable(index); //để verify riêng
        Assert.assertEquals(productInfo.get("id"), cartPageProductId);
        Assert.assertEquals(productInfo.get("name"), cartPageProductName);
        Assert.assertEquals(productInfo.get("price"), cartPageProductPrice);
    }





}
