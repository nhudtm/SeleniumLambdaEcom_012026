package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.WishListUI;

import java.util.ArrayList;
import java.util.List;

public class WishListPO extends MenuCategoryPO {


    public WishListPO(WebDriver driver) {
        super(driver);

    }



    public boolean isYourWishListTitleDisplayed() {
        waitForElementVisible(  WishListUI.WISH_LIST_TITLE);
        return isElementDisplayed(  WishListUI.WISH_LIST_TITLE);
    }

    public String getPageTitleText() {
        waitForElementVisible(  WishListUI.WISH_LIST_TITLE);
        return getElementText(  WishListUI.WISH_LIST_TITLE);
    }

    public int getTotalProduct() {
        waitForAllElementsVisible(  WishListUI.TOTAL_PRODUCT_IN_WISH_LIST);
        return getElementList(  WishListUI.TOTAL_PRODUCT_IN_WISH_LIST).size();
    }

    public void clickRemoveButtonByIndex(int totalProduct) {
        waitForElementClickable(  WishListUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, String.valueOf(totalProduct));
        clickToElement(  WishListUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, String.valueOf(totalProduct));
    }

    public boolean isNoProductMessageDisplayed() {
        waitForElementVisible(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
        return isElementDisplayed(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
    }

    public String  getNoProductMessage() {
        waitForElementVisible(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
        return getElementText(  WishListUI.WISH_LIST_NO_PRODUCT_MESSAGE);
    }

    public String getProductNameByIndex(int i) {
        waitForElementVisible(  WishListUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(i));
        return getElementText(  WishListUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(i));
    }

    public String getProductPrice(int i) {
        waitForElementVisible(  WishListUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(i));
        return getElementText(  WishListUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(i));
    }

    public List<String> getAllProductNamesInWishlistPage() {

        List<WebElement> listName = getElementList(  WishListUI.ALL_PRODUCT_NAME);
        List<String> allNames = new ArrayList<>();
        for(WebElement webElement : listName) {
            allNames.add(webElement.getText());
        }
        return allNames;
    }
}
