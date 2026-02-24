package pageObjects;

import components.ProductComponent;
import org.openqa.selenium.WebDriver;
import pageUIs.HomeUI;

import java.util.List;

public class HomePO extends MenuCategoryPO {

    public HomePO(WebDriver driver) {
        super(driver);

    }

    public boolean isHomeSliderDisplayed() {
        waitForElementVisible(HomeUI.CAROUSEL_SLIDER);
        return isElementDisplayed(HomeUI.CAROUSEL_SLIDER);
    }

    public String getFirstProductNameInTopProducts() {
        waitForElementVisible(HomeUI.PRODUCT_NAME_IN_TOP_PRODUCTS);
        return getElementText(HomeUI.PRODUCT_NAME_IN_TOP_PRODUCTS);
    }

    public ProductDetailPO clickFirstProductInTopProductsSection() {
        waitForElementClickable(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        clickToElement(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        return PageGenerator.getProductDetailPage(driver);
    }

    public void hoverToFirstProductInTopProductsSection() {
        waitForElementVisible(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        hoverToElement(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        sleepInSecond(2);
    }

    public void clickToAddToCartButtonInProductAction() {
        waitForElementClickable(HomeUI.ACTION_ADD_TO_CART);
        clickToElementByJS(HomeUI.ACTION_ADD_TO_CART);
    }


    public void clickToAddToWishListButtonInProductAction() {
        waitForElementClickable(HomeUI.ACTION_ADD_TO_WISHLIST);
        clickToElementByJS(HomeUI.ACTION_ADD_TO_WISHLIST);
    }

    public String getLoginAlertMessageText() {
        waitForElementVisible(HomeUI.LOGIN_POPUP_BODY_TEXT);
        return getElementText(HomeUI.LOGIN_POPUP_BODY_TEXT);
    }

    public void clickToQuickViewButtonInProductAction() {
        waitForElementClickable(HomeUI.ACTION_QUICK_VIEW);
        clickToElementByJS(HomeUI.ACTION_QUICK_VIEW);
    }

    public void clickToAddToCompareButtonInProductAction() {
        waitForElementClickable(HomeUI.ACTION_ADD_TO_COMPARE);
        clickToElementByJS(HomeUI.ACTION_ADD_TO_COMPARE);
    }

    public String getCompareCartWishListPopupText() {
        waitForElementVisible(HomeUI.COMPARE_POPUP_BODY_TEXT);
        return getElementText(HomeUI.COMPARE_POPUP_BODY_TEXT);
    }


    public String getProductPopupTitleText() {
        waitForElementVisible(HomeUI.PRODUCT_POPUP_TITLE);
        return getElementText(HomeUI.PRODUCT_POPUP_TITLE);
    }

    public void clickCloseIconInProductPopup() {
        waitForElementClickable(HomeUI.PRODUCT_POPUP_CLOSE_BUTTON);
        clickToElementByJS(HomeUI.PRODUCT_POPUP_CLOSE_BUTTON);
    }

    public void clickToLoginAlertClosePopup() {
        waitForElementClickable(HomeUI.LOGIN_POPUP_CLOSE_BUTTON);
        highligthElementByJS(HomeUI.LOGIN_POPUP_CLOSE_BUTTON);
        System.out.println("highlighted");
        clickToElementByJS(HomeUI.LOGIN_POPUP_CLOSE_BUTTON);
        System.out.println("clicked");

    }

//    public List<String> addToCompare(int numOfProduct) {
//        List<String> productNames = new java.util.ArrayList<>();
//        for (int i = 1; i <= numOfProduct; i++) {
//            String productName = getElementText(  HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(i));
//            hoverToElement(  HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(i));
//            waitForElementClickable(  HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE, String.valueOf(i));
//            clickToElement(  HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE, String.valueOf(i));
//            sleepInSecond(2);
//            productNames.add(productName);
//        }
//        return productNames;
//    }


    public void clickAddToCompareIconFromCollectionPopular(int productIndex) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE, String.valueOf(productIndex));
        clickToElementByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE, String.valueOf(productIndex));
        sleepInSecond(2);
    }

    public void clickAddToWishListIconFromCollectionPopular(int productIndex) {
        waitForElementClickable(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_WISHLIST, String.valueOf(productIndex));
        clickToElementByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_WISHLIST, String.valueOf(productIndex));
        sleepInSecond(2);
    }

    public void getProductNameCollectionPopular(int productIndex) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
        getElementText(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
    }

    public String getCompareCartWishlistPopupTitleText() {
        waitForElementVisible(HomeUI.COMPARE_POPUP_TITLE);
        highligthElementByJS(HomeUI.COMPARE_POPUP_TITLE);
        return getElementText(HomeUI.COMPARE_POPUP_TITLE);
    }


    public ComparePO clickProductCompareLinkInPopup() {
        waitForElementClickable(HomeUI.COMPARE_POPUP_PRODUCT_COMPARE_BUTTON);
        clickToElement(HomeUI.COMPARE_POPUP_PRODUCT_COMPARE_BUTTON);
        return PageGenerator.getComparePage(driver);
    }

    public void hoverToProductInCollectionPopular(int productIndex) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(productIndex));
        hoverToElement(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(productIndex));
        sleepInSecond(2);
    }

    public String getProducNameInCollectionPopular(int productIndex) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
        scrollToElementOnTopByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
        return getElementText(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
    }

    public int getProductIdInCollectionPopular(int index) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ID, String.valueOf(index));
        String productURL = getElementDOMAttribute(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ID, "href", String.valueOf(index));
        return Integer.parseInt(productURL.split("product_id=")[1].split("&")[0]);
    }

    public void clickAddToCartInCollectionPopular(int i) {
        waitForElementClickable(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_CART, String.valueOf(i));
        clickToElementByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_CART, String.valueOf(i));
        sleepInSecond(2);

    }

    public CartPO clickCheckoutButtonInCartPopup() {
        waitForElementClickable(HomeUI.CART_POPUP_CHECKOUT_BUTTON);
        clickToElement(HomeUI.CART_POPUP_CHECKOUT_BUTTON);
        return PageGenerator.getCartPage(driver);
    }

    public String getProductPriceInCollectionPopular(int i) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_PRICE, String.valueOf(i));
        return getElementText(HomeUI.DYNAMIC_COLLECTION_PRODUCT_PRICE, String.valueOf(i));
    }

    public boolean isProductQuickViewPopupUnDisplayed() {
        overrideGlobalTimeout(2);
        boolean status = isElementUndisplayed(HomeUI.PRODUCT_QUICK_VIEW_POPUP);
        overrideGlobalTimeout(15);
        return status;
    }

    public List<ProductComponent> getAllProductsInTopProducts() {
        waitForAllElementPresence(HomeUI.PRODUCTS_IN_TOP_PRODUCTS);
        List<ProductComponent> products = getElementList(HomeUI.PRODUCTS_IN_TOP_PRODUCTS).stream()
                .map(product -> new ProductComponent(driver, product)).toList();
        return products;
    }

    public List<ProductComponent> getAllProductsInTopCollections() {
        waitForAllElementPresence(HomeUI.PRODUCTS_IN_TOP_COLLECTIONS);
        List<ProductComponent> products = getElementList(HomeUI.PRODUCTS_IN_TOP_COLLECTIONS).stream()
                .map(product -> new ProductComponent(driver, product)).toList();
        return products;
    }

    public WishListPO clickWishlistLinkInWishlistPopup() {
        waitForElementClickable(HomeUI.WISHLIST_POPUP_PRODUCT_WISHLIST_BUTTON);
        clickToElement(HomeUI.WISHLIST_POPUP_PRODUCT_WISHLIST_BUTTON);
        return PageGenerator.getWishListPage(driver);
    }

    public void scrollToProductInCollectionPopular(int i) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(i));
        scrollToElementOnTopByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(i));
    }


    //Banner
    //Top Trending Ca
    //Top Products
    //Top Collections
    //From The Blog
}
