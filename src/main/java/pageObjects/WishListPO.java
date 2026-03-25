package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import pageUIs.WishListUI;

public class WishListPO extends MenuCategoryPO {


    public WishListPO(WebDriver driver) {
        super(driver);
    }

    @Step("Check Your Wish List title is displayed")
    public boolean isYourWishListTitleDisplayed() {
        waitForElementVisible(  WishListUI.WISH_LIST_TITLE);
        return isElementDisplayed(  WishListUI.WISH_LIST_TITLE);
    }

    @Step("Get Your Wish List title text")
    public String getPageTitleText() {
        waitForElementVisible(  WishListUI.WISH_LIST_TITLE);
        return getElementText(  WishListUI.WISH_LIST_TITLE);
    }

    @Step("Get total product in wish list")
    public int getTotalProduct() {
        overrideGlobalTimeout(commons.GlobalConstants.SHORT_TIMEOUT);
        int total = getElementList(WishListUI.TOTAL_PRODUCT_IN_WISH_LIST).size();
        overrideGlobalTimeout(commons.GlobalConstants.LONG_TIMEOUT);
        return total;
    }

    @Step("Click to Remove button by index {0}")
    public void clickRemoveButtonByIndex(int totalProduct) {
        int beforeCount = getTotalProduct();
        waitForElementClickable(  WishListUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, String.valueOf(totalProduct));
        try {
            clickToElement(WishListUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, String.valueOf(totalProduct));
        } catch (Exception e) {
            clickToElementByJS(WishListUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, String.valueOf(totalProduct));
        }

        sleepInSecond(1);
        int afterCount = getTotalProduct();

        if (afterCount >= beforeCount) {
            String removeUrl = getElementDOMAttribute(WishListUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, "href",
                    String.valueOf(totalProduct));
            if (removeUrl != null && !removeUrl.isBlank()) {
                openPage(removeUrl);
                sleepInSecond(1);
            }
        }
    }

    @Step("Check No product message is displayed")
    public boolean isNoProductMessageDisplayed() {
        waitForElementVisible(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
        return isElementDisplayed(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
    }

    @Step("Get No product message text")
    public String  getNoProductMessage() {
        waitForElementVisible(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
        return getElementText(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
    }

    @Step("Get product name by index {0}")
    public String getProductNameByIndex(int i) {
        waitForElementVisible(  WishListUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(i));
        return getElementText(  WishListUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(i));
    }

    @Step("Get product price by index {0}")
    public String getProductPrice(int i) {
        waitForElementVisible(  WishListUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(i));
        return getElementText(  WishListUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(i));
    }

    @Step("Get all product names in wishlist page")
    public List<String> getAllProductNamesInWishlistPage() {
        List<WebElement> listName = getElementList(  WishListUI.ALL_PRODUCT_NAME);
        List<String> allNames = new ArrayList<>();
        for(WebElement webElement : listName) {
            allNames.add(webElement.getText());
        }
        return allNames;
    }
}
