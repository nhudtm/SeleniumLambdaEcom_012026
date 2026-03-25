package components;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.BasePage;
import pageUIs.ProductActionComponentUI;

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
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", productElement);
            new Actions(driver).moveToElement(productElement).perform();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles:true}));",
                    productElement);
        }
    }

    public void clickAddToCart() {
        hoverToProduct();

        try {
            WebElement button = productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_CART_BUTTON));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(button));
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(button));
            button.click();
        } catch (WebDriverException e) {
            WebElement button = productElement.findElement(getByLocator(ProductActionComponentUI.ADD_TO_CART_BUTTON));
            hoverToProduct();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

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
