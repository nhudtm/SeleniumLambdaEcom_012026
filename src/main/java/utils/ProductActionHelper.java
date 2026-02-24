package utils;

import components.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class ProductActionHelper {
    public static void verifyProductActionDisplayed(List<ProductComponent> allProducts) {
        // Tam thoi chi hover 3 san pham dau tien
        for (ProductComponent product : allProducts.subList(0, 3)) {
            product.hoverToProduct();
            Assert.assertTrue(product.isAddToCartButtonDisplayed());
            Assert.assertTrue(product.isAddToWishListButtonDisplayed());
            Assert.assertTrue(product.isAddToCompareButtonDisplayed());
            Assert.assertTrue(product.isQuickViewButtonDisplayed());
        }
    }

    public static void verifyAddToCartFunctionality(List<ProductComponent> allProducts, WebDriver driver) {
        for (ProductComponent product : allProducts.subList(0, 3)) {
            product.hoverToProduct();
            product.clickAddToCart();
            System.out.println("Clicked Add to Cart for product: " + product.getProductName());
            String expectedBodyText = "Success: You have added " + product.getProductName() + " to your shopping cart";
            PopupHelper.verifyAddToCartPopupBodyText(driver, expectedBodyText);
//            Success: You have added iMac to your shopping cart!
            PopupHelper.closeCartPopup(driver);
//            PopupHelper.waitForPopupInvisible(driver);
        }
    }

    public static void verifyAddToWishListPopup(List<ProductComponent> allProducts, WebDriver driver) {
        // Tam thoi chi hover 3 san pham dau tien
        for (int i = 0; i < 3; i++) {
            String productName = allProducts.get(i).getProductName();
            allProducts.get(i).clickAddToWishList();
            System.out.println("Clicked Add to Wish List for product: " + productName);
            PopupHelper.verifyAddToWishListPopupBodyText(driver, "Success: You have added" + productName + " to your wish list");
            PopupHelper.verifyAddToWishListPopupTitle(driver, "Wish List");
            PopupHelper.closeWishlistPopup(driver);
            PopupHelper.waitForPopupInvisible(driver);
        }
    }

    public static void verifyAddToWishListPopup(ProductComponent product, WebDriver driver) {
        String productName = product.getProductName();
        System.out.println("Product name to add to wish list: " + productName);
        product.clickAddToWishList();
        String expectedBodyText = "Success: You have added " + productName + " to your wish list";
        PopupHelper.verifyAddToWishListPopupBodyText(driver, expectedBodyText);
        PopupHelper.verifyAddToWishListPopupTitle(driver, "Wish List");
        PopupHelper.closeWishlistPopup(driver);
        PopupHelper.waitForPopupInvisible(driver);
    }
}

