package pageObjects;

import utils.DBUtils;
import models.Product;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageUIs.CompareUI;

import java.util.List;

public class ComparePO extends MenuCategoryPO {
   

    public ComparePO(WebDriver driver) {
        super(driver);
    }

    @Step("Check Compare page title is displayed")
    public boolean isCompareTitleDisplayed() {
        waitForElementVisible(  pageUIs.CompareUI.COMPARE_PAGE_TITLE);
        return isElementDisplayed(  pageUIs.CompareUI.COMPARE_PAGE_TITLE);
    }

    @Step("Get number of products in Compare page")
    public int getNumberOfProductsInComparePage() {
        waitForAllElementsVisible(  CompareUI.All_PRODUCTS);
        return getElementList(  CompareUI.All_PRODUCTS).size();
    }

    @Step("Get product ID by product name in Compare page")
    public int getProductIdByName(String productName) {
        waitForElementVisible(  CompareUI.DYNAMIC_PRODUCT_BY_NAME, productName);
        String productURL = getElementDOMAttribute(  CompareUI.DYNAMIC_PRODUCT_BY_NAME, "href", productName);
        return Integer.parseInt(productURL.split("product_id=")[1].split("&")[0]);
    }

    @Step("Get product price by index in Compare page")
    public String getProductPrice(int productIndex) {
        waitForElementVisible(  CompareUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(productIndex));
        return getElementText(  CompareUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(productIndex));

    }

    @Step("Get product media src from database by product ID")
    public List<String> getProductMediaSrcFromDB(int productId) {
        return DBUtils.getProductMediaSrc(productId);
    }

    @Step("Get product ID by index in Compare page")
    public int getProductIdByIndex(int j) {
        waitForElementVisible(  CompareUI.DYNAMIC_PRODUCT_BY_INDEX, String.valueOf(j));
        String productURL = getElementDOMAttribute(  CompareUI.DYNAMIC_PRODUCT_BY_INDEX, "href", String.valueOf(j));
        return Integer.parseInt(productURL.split("product_id=")[1].split("&")[0]);
    }

    @Step("Get product image src by index in Compare page")
    public String getProductImageSrcByIndex(int j) {
        waitForElementVisible(  CompareUI.DYNAMIC_IMAGE_BY_INDEX, String.valueOf(j));
        return getElementDOMAttribute(  CompareUI.DYNAMIC_IMAGE_BY_INDEX, "src", String.valueOf(j));
    }

    @Step("Get product info from database by product ID")
    public Product getProductInfoFromDB(int productId) {
        return DBUtils.getProductInfoById(productId);
    }

    @Step("Click to Remove button in Compare page by index {0}")
    public void clickRemoveProductByIndex(int numOfProduct) {
        waitForElementClickable(  CompareUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX, String.valueOf(numOfProduct));
        clickToElement(  CompareUI.DYNAMIC_REMOVE_BUTTON_BY_INDEX , String.valueOf(numOfProduct));
        sleepInSecond(2);
    }

    @Step("Check No product message is displayed in Compare page")
    public boolean isNoProductMessageDisplayed() {
        waitForElementVisible(  CompareUI.NO_PRODUCT_MESSAGE_TEXT);
        return isElementDisplayed(  CompareUI.NO_PRODUCT_MESSAGE_TEXT);

    }

    @Step("Get No product message text in Compare page")
    public String getSuccessMessageText() {
        waitForElementVisible(  CompareUI.SUCCESS_MESSAGE);
        return getElementText(  CompareUI.SUCCESS_MESSAGE);
    }

    @Step("Get product name by index in Compare page")
    public String getProductNameByIndex(int index) {
        waitForElementVisible(  CompareUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(index));
        return getElementText(  CompareUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(index));
    }

    @Step("Check if the first product in Compare page is removed")
    public boolean isFirstProductInComparePageRemoved(int firstProductIdInCollectionPopular) {
        int firstProductIdInComparePage = getProductIdByIndex(1);
        return firstProductIdInCollectionPopular != firstProductIdInComparePage;
    }

    @Step("Click to Add to Cart button in Compare page by index {0}")
    public void clickAddToCartByIndex(int i) {
        waitForElementClickable(  CompareUI.DYNAMIC_ADD_TO_CART_BUTTON_BY_INDEX, String.valueOf(i));
        clickToElement(  CompareUI.DYNAMIC_ADD_TO_CART_BUTTON_BY_INDEX, String.valueOf(i));
        sleepInSecond(2);
    }

//    public String getProductNameInAddToCartPopup() {
//        waitForElementVisible(  CompareUI.PRODUCT_NAME_IN_ADD_TO_CART_POPUP);
//        return getElementText(  CompareUI.PRODUCT_NAME_IN_ADD_TO_CART_POPUP);
//    }
//
//    public String getProductPriceInAddToCartPopup() {
//        waitForElementVisible(  CompareUI.PRODUCT_PRICE_IN_ADD_TO_CART_POPUP);
//        return getElementText(  CompareUI.PRODUCT_PRICE_IN_ADD_TO_CART_POPUP);
//    }
//
//    public int getProductIdInAddToCartPopup() {
//        waitForElementVisible(  CompareUI.PRODUCT_LINK_IN_ADD_TO_CART_POPUP);
//        String productURL = getElementAttribute(  CompareUI.PRODUCT_LINK_IN_ADD_TO_CART_POPUP, "href");
//        return Integer.parseInt(productURL.split("product_id=")[1].split("&")[0]);
//    }


}
