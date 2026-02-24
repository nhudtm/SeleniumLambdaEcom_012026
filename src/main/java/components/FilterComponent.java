package components;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.FilterUI;

public class FilterComponent extends BasePage {


    public FilterComponent(WebDriver driver) {
        super(driver);
    }

    public void filterByInputPriceRange(String minPrice, String maxPrice) {
           waitForElementVisible(  FilterUI.MIN_PRICE);
        sendKeyToElement(  FilterUI.MIN_PRICE, minPrice);
        sendKeyToElement(  FilterUI.MAX_PRICE, maxPrice);
    }

    public String getMinPriceFilterValues() {
        waitForElementVisible(  FilterUI.MIN_PRICE);
        return getElementProperties(  FilterUI.MIN_PRICE, "value");
    }

    public String getMaxPriceFilterValues() {
        waitForElementVisible(  FilterUI.MAX_PRICE);
        return getElementProperties(  FilterUI.MAX_PRICE, "value");
    }

    public void clearPriceFilter() {
        waitForElementVisible(  FilterUI.CLEAR_PRICE_FILTER_BUTTON);
        clickToElement(  FilterUI.CLEAR_PRICE_FILTER_BUTTON);
    }

    public void filterByColor(String color) {
        waitForElementVisible(  FilterUI.DYNAMIC_COLOR_FILTER_OPTION,color);
        clickToElement(  FilterUI.DYNAMIC_COLOR_FILTER_OPTION,color);
    }

    public void filterBySize(String id) {
        waitForElementVisible(  FilterUI.DYNAMIC_SIZE_FILTER_OPTION_BY_ID, id);
        clickToElementByJS(  FilterUI.DYNAMIC_SIZE_FILTER_OPTION_BY_ID,id);
    }

    public boolean isSizeSelected(String id) {
        waitForElementVisible(  FilterUI.DYNAMIC_SIZE_FILTER_OPTION_BY_ID, id);
        return isElementSelected(  FilterUI.DYNAMIC_SIZE_FILTER_OPTION_BY_ID, id);
    }
}
