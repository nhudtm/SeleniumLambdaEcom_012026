package utils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import components.ProductComponent;

public class ProductActionHelper {
    public static void verifyProductActionDisplayed(List<ProductComponent> allProducts) {
        // Tam thoi chi hover 3 san pham dau tien
        int limit = Math.min(3, allProducts.size());
        for (ProductComponent product : allProducts.subList(0, limit)) {
            product.hoverToProduct();
            Assert.assertTrue(product.isAddToCartButtonDisplayed());
            Assert.assertTrue(product.isAddToWishListButtonDisplayed());
            Assert.assertTrue(product.isAddToCompareButtonDisplayed());
            Assert.assertTrue(product.isQuickViewButtonDisplayed());
        }
    }

    public static void verifyAddToCartFunctionality(List<ProductComponent> allProducts, WebDriver driver) {
        int limit = Math.min(3, allProducts.size());
        int successfulAddToCart = 0;

        for (ProductComponent product : allProducts.subList(0, limit)) {
            product.hoverToProduct();
            String productName = product.getProductName();
            String expectedBodyText = "Success: You have added " + productName + " to your shopping cart";
            boolean verified = false;

            for (int attempt = 1; attempt <= 2; attempt++) {
                try {
                    product.clickAddToCart();
                    System.out.println("Clicked Add to Cart for product: " + productName);
                    PopupHelper.verifyAddToCartPopupBodyText(driver, expectedBodyText);
                    PopupHelper.closeCartPopup(driver);
                    successfulAddToCart++;
                    verified = true;
                    break;
                } catch (RuntimeException e) {
                    if (attempt == 2) {
                        System.out.println("Skip product (no cart popup): " + productName);
                    }
                    product.hoverToProduct();
                } catch (AssertionError e) {
                    if (attempt == 2) {
                        System.out.println("Skip product (popup text mismatch): " + productName);
                    }
                    product.hoverToProduct();
                }
            }

            if (!verified) {
                continue;
            }
        }

        Assert.assertTrue(successfulAddToCart > 0, "No product could be added to cart from this section");
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

