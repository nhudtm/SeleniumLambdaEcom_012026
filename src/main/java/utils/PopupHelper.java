package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageUIs.PopupUI;

import java.time.Duration;

public class PopupHelper {

    // Add methods to handle popups if needed
    public static void waitForPopupDisplayed(WebDriver driver) {
        // Implementation here
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id(PopupUI.NOTIFICATION_POPUP)));
    }

    public static void waitForPopupInvisible(WebDriver driver) {
        // Implementation here
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id(PopupUI.NOTIFICATION_POPUP)));
    }
    public static void verifyAddToCartPopupBodyText(WebDriver driver, String expectedText) {
        // Implementation here
        waitForPopupDisplayed(driver);
       WebElement cartPopupBody = driver.findElement(By.xpath(PopupUI.CART_POPUP_BODY_TEXT));
       String actualText = cartPopupBody.getText().replaceAll("\\s+", " ").trim();
        System.out.printf("Actual cart popup text: %s%n", actualText);
        System.out.printf("Expected cart popup text: %s%n", expectedText);
       Assert.assertTrue(actualText.contains(expectedText));
    }

    public static void closeCartPopup(WebDriver driver) {
        // Implementation here
        WebElement closeButton = driver.findElement(By.xpath(PopupUI.CLOSE_CART_POPUP_BUTTON));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        System.out.println("Clicked close button on cart popup.");
    }

    public static void closeWishlistPopup(WebDriver driver) {
        // Implementation here
        WebElement closeButton = driver.findElement(By.xpath(PopupUI.CLOSE_WISHLIST_POPUP_BUTTON));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        System.out.println("Clicked close button on cart popup.");
    }

    public static void verifyAddToWishListPopupBodyText(WebDriver driver, String expectedText) {
        waitForPopupDisplayed(driver);
        WebElement wishListPopupBody = driver.findElement(By.xpath(PopupUI.WISH_LIST_POPUP_BODY_TEXT));
        String actualText = wishListPopupBody.getText().replaceAll("\\s+", " ").trim();
        System.out.printf("Actual wish list popup text: %s%n", actualText);
        System.out.printf("Expected wish list popup text: %s%n", expectedText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    public static void verifyAddToWishListPopupTitle(WebDriver driver, String expectedTitle) {
        waitForPopupDisplayed(driver);
        WebElement wishListPopupTitle = driver.findElement(By.xpath(PopupUI.WISH_LIST_POPUP_TITLE));
        String actualTitle = wishListPopupTitle.getText();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }





}
