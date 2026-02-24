package pageObjects;

import components.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.SpecialUI;

import java.util.List;

public class SpecialPO extends MenuCategoryPO {


    public SpecialPO(WebDriver driver) {
        super(driver);
 
    }

    public boolean isSpecialPageTitleDisplayed() {
        waitForElementVisible(  SpecialUI.SPECIAL_PAGE_TITLE);
        return isElementDisplayed(  SpecialUI.SPECIAL_PAGE_TITLE);
    }

    public List<ProductComponent> getAllProductsInPage() {
        waitForElementVisible(  SpecialUI.PRODUCT_ITEM);
        List<WebElement> productElements = getElementList(  SpecialUI.PRODUCT_ITEM);
        return productElements.stream().map(productElement -> new ProductComponent( driver, productElement)).toList();
    }


    public String getNoProductMessage() {
        waitForElementVisible(  SpecialUI.NO_PRODUCT_MESSAGE);
        return getElementText(  SpecialUI.NO_PRODUCT_MESSAGE);
    }

    public boolean isNextPageButtonUndisplayed() {
        return isElementUndisplayed(  SpecialUI.NEXT_PAGE_BUTTON);
    }

    public void clickNextPageButton() {
        scrollToElement(  SpecialUI.NEXT_PAGE_BUTTON);
        clickToElement(  SpecialUI.NEXT_PAGE_BUTTON);
    }
}
