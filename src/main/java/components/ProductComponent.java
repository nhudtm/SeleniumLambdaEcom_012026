package components;

import commons.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.ProductActionComponentUI;

import java.time.Duration;

public class ProductComponent extends BasePage {

    WebElement productElement;

    public ProductComponent(WebDriver driver, WebElement productElement) {
        super(driver);
        this.productElement = productElement;
    }


    public String getProductName() {
        return productElement.findElement(getByLocator(ProductActionComponentUI.PRODUCT_NAME)).getText();
    }

    public float getProductPrice() {
        return Float.parseFloat(productElement.findElement(getByLocator(ProductActionComponentUI.PRODUCT_PRICE)).getText().replace("$", "").replace(",", ""));
    }

    public void hoverToProduct() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(productElement));
        Actions action = new Actions(driver);
        action.moveToElement(productElement).perform();
    }

    public void clickAddToCart() {
        //click by js: Khong can hover van click duoc
        sleepInSecond(3);
        WebElement button = productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_CART_BUTTON));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", button);
//        sleepInSecond(3);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        sleepInSecond(3);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(button));
//
        button.click();

        //Use selenium - Tuy nhien element bi che nen khong click duoc
//        WebElement image = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='entry_218396']//img")));

//        // Kiểm tra ảnh đã load đầy đủ (naturalWidth > 0)
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Boolean imageLoaded = (Boolean) js.executeScript(
//                "return arguments[0].complete && arguments[0].naturalWidth > 0;", image);
//
//        if (imageLoaded) {
//            hoverToProduct(); // Hover sau khi ảnh đã load
//            WebElement addToCartButton = productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_CART_BUTTON));
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.elementToBeClickable(addToCartButton))
//                    .click();
//        } else {
//            throw new RuntimeException("Ảnh chưa được load đầy đủ");
//        }

    }

    public void clickAddToCompare() {
        hoverToProduct();
        productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_COMPARE_BUTTON)).click();
    }

    public void clickQuickView() {
        hoverToProduct();
        productElement.findElement(getByLocator(ProductActionComponentUI.QUICK_VIEW_BUTTON)).click();
    }

    public boolean isAddToCartButtonDisplayed() {
        return productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_CART_BUTTON)).isDisplayed();
    }

    public boolean isAddToWishListButtonDisplayed() {
        return productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_WISH_LIST_BUTTON)).isDisplayed();
    }

    public boolean isAddToCompareButtonDisplayed() {
        return productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_COMPARE_BUTTON)).isDisplayed();
    }

    public boolean isQuickViewButtonDisplayed() {
        return productElement.findElement(getByLocator(ProductActionComponentUI.QUICK_VIEW_BUTTON)).isDisplayed();
    }

    public void clickAddToWishList() {
        WebElement button = productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_WISH_LIST_BUTTON));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", button);
    }
}
