package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import components.ProductComponent;
import io.qameta.allure.Step;
import pageUIs.HomeUI;

public class HomePO extends MenuCategoryPO {

    public HomePO(WebDriver driver) {
        super(driver);
    }

    @Step("Check Home page slider is displayed")
    public boolean isHomeSliderDisplayed() {
        waitForElementVisible(HomeUI.CAROUSEL_SLIDER);
        return isElementDisplayed(HomeUI.CAROUSEL_SLIDER);
    }

    @Step("Get first product name in Top Products section")
    public String getFirstProductNameInTopProducts() {
        waitForElementVisible(HomeUI.PRODUCT_NAME_IN_TOP_PRODUCTS);
        return getElementText(HomeUI.PRODUCT_NAME_IN_TOP_PRODUCTS);
    }

    @Step("Click first product in Top Products section")    
    public ProductDetailPO clickFirstProductInTopProductsSection() {
        waitForElementClickable(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        clickToElement(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        return PageGenerator.getProductDetailPage(driver);
    }

    @Step("Hover to first product in Top Products section")
    public void hoverToFirstProductInTopProductsSection() {
        waitForElementVisible(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        hoverToElement(HomeUI.FIRST_PRODUCTS_IN_TOP_PRODUCTS);
        sleepInSecond(2);
    }

    @Step("Click Add to Cart button in Product Action")
    public void clickToAddToCartButtonInProductAction() {
        waitForElementPresence(HomeUI.ACTION_ADD_TO_CART);
        clickToElementByJS(HomeUI.ACTION_ADD_TO_CART);
    }

    @Step("Click to Add to Wish List button in Product Action")
    public void clickToAddToWishListButtonInProductAction() {
        waitForElementPresence(HomeUI.ACTION_ADD_TO_WISHLIST);
        clickToElementByJS(HomeUI.ACTION_ADD_TO_WISHLIST);
    }

    @Step("Get Login alert message text")
    public String getLoginAlertMessageText() {
        waitForElementVisible(HomeUI.LOGIN_POPUP_BODY_TEXT);
        return getElementText(HomeUI.LOGIN_POPUP_BODY_TEXT);
    }

    @Step("Click to Quick View button in Product Action")
    public void clickToQuickViewButtonInProductAction() {
        waitForElementClickable(HomeUI.ACTION_QUICK_VIEW);
        clickToElementByJS(HomeUI.ACTION_QUICK_VIEW);
    }

    @Step("Click to Add to Compare button in Product Action")
    public void clickToAddToCompareButtonInProductAction() {
        waitForElementPresence(HomeUI.ACTION_ADD_TO_COMPARE);
        clickToElementByJS(HomeUI.ACTION_ADD_TO_COMPARE);
    }

    @Step("Get Compare/Cart/WishList popup message text")
    public String getCompareCartWishListPopupText() {
        waitForElementVisible(HomeUI.COMPARE_POPUP_BODY_TEXT);
        return getElementText(HomeUI.COMPARE_POPUP_BODY_TEXT);
    }

    @Step("Get product popup title text")
    public String getProductPopupTitleText() {
        waitForElementVisible(HomeUI.PRODUCT_POPUP_TITLE);
        return getElementText(HomeUI.PRODUCT_POPUP_TITLE);
    }

    @Step("Click close icon in product popup")
    public void clickCloseIconInProductPopup() {
        waitForElementClickable(HomeUI.PRODUCT_POPUP_CLOSE_BUTTON);
        clickToElementByJS(HomeUI.PRODUCT_POPUP_CLOSE_BUTTON);
    }

    @Step("Click close button in Login alert popup")
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


    @Step("Click to Add to Compare icon from Collection Popular by index {0}")
    public void clickAddToCompareIconFromCollectionPopular(int productIndex) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE, String.valueOf(productIndex));
        clickToElementByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE, String.valueOf(productIndex));
        sleepInSecond(2);
    }

    @Step("Click to Add to Wish List icon from Collection Popular by index {0}")
    public void clickAddToWishListIconFromCollectionPopular(int productIndex) {
        waitForElementClickable(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_WISHLIST, String.valueOf(productIndex));
        clickToElementByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_WISHLIST, String.valueOf(productIndex));
        sleepInSecond(2);
    }

    @Step("Get product name from Collection Popular by index {0}")
    public String getProductNameCollectionPopular(int productIndex) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
        return getElementText(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
    }

    @Step("Get Compare/Cart/WishList popup title text")
    public String getCompareCartWishlistPopupTitleText() {
        waitForElementVisible(HomeUI.COMPARE_POPUP_TITLE);
        highligthElementByJS(HomeUI.COMPARE_POPUP_TITLE);
        return getElementText(HomeUI.COMPARE_POPUP_TITLE);
    }


    @Step("Click product compare link in Compare popup")
    public ComparePO clickProductCompareLinkInPopup() {
        waitForElementClickable(HomeUI.COMPARE_POPUP_PRODUCT_COMPARE_BUTTON);
        clickToElement(HomeUI.COMPARE_POPUP_PRODUCT_COMPARE_BUTTON);
        return PageGenerator.getComparePage(driver);
    }

    @Step("Hover to product in Collection Popular by index {0}")
    public void hoverToProductInCollectionPopular(int productIndex) {
        scrollToProductInCollectionPopular(productIndex);
        try {
            waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(productIndex));
            hoverToElement(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(productIndex));
        } catch (Exception e) {
            waitForElementPresence(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(productIndex));
        }
        sleepInSecond(2);
    }

    @Step("Get product name in Collection Popular by index {0}")
    public String getProducNameInCollectionPopular(int productIndex) {
        try {
            waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
            scrollToElementOnTopByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
            return getElementText(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
        } catch (Exception e) {
            waitForElementPresence(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, String.valueOf(productIndex));
            String fallbackText = getElementProperty(HomeUI.DYNAMIC_COLLECTION_PRODUCT_NAME, "textContent",
                    String.valueOf(productIndex));
            return fallbackText == null ? "" : fallbackText.trim();
        }
    }

    @Step("Get product ID in Collection Popular by index {0}")
    public int getProductIdInCollectionPopular(int index) {
        waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ID, String.valueOf(index));
        String productURL = getElementDOMAttribute(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ID, "href", String.valueOf(index));
        return Integer.parseInt(productURL.split("product_id=")[1].split("&")[0]);
    }

    @Step("Click to Add to Cart button in Collection Popular by index {0}")
    public void clickAddToCartInCollectionPopular(int i) {
        waitForElementClickable(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_CART, String.valueOf(i));
        clickToElementByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT_ADD_TO_CART, String.valueOf(i));
        sleepInSecond(2);

    }

    @Step("Click checkout button in Cart popup")
    public CartPO clickCheckoutButtonInCartPopup() {
        waitForElementClickable(HomeUI.CART_POPUP_CHECKOUT_BUTTON);
        clickToElement(HomeUI.CART_POPUP_CHECKOUT_BUTTON);
        return PageGenerator.getCartPage(driver);
    }

    @Step("Get product price in Collection Popular by index {0}")
    public String getProductPriceInCollectionPopular(int i) {
        try {
            waitForElementVisible(HomeUI.DYNAMIC_COLLECTION_PRODUCT_PRICE, String.valueOf(i));
            return getElementText(HomeUI.DYNAMIC_COLLECTION_PRODUCT_PRICE, String.valueOf(i));
        } catch (Exception e) {
            waitForElementPresence(HomeUI.DYNAMIC_COLLECTION_PRODUCT_PRICE, String.valueOf(i));
            String fallbackText = getElementProperty(HomeUI.DYNAMIC_COLLECTION_PRODUCT_PRICE, "textContent",
                    String.valueOf(i));
            return fallbackText == null ? "" : fallbackText.trim();
        }
    }

    @Step("Check product quick view popup is undisplayed")
    public boolean isProductQuickViewPopupUnDisplayed() {
        overrideGlobalTimeout(2);
        boolean status = isElementUndisplayed(HomeUI.PRODUCT_QUICK_VIEW_POPUP);
        overrideGlobalTimeout(15);
        return status;
    }

    @Step("Get all products in Top Products section")
    public List<ProductComponent> getAllProductsInTopProducts() {
        waitForAllElementPresence(HomeUI.PRODUCTS_IN_TOP_PRODUCTS);
        List<ProductComponent> products = getElementList(HomeUI.PRODUCTS_IN_TOP_PRODUCTS).stream()
            .filter(this::isVisibleNonDuplicateSlide)
            .map(product -> new ProductComponent(driver, product))
            .toList();
        return products;
    }

    @Step("Get all products in Top Collections section")
    public List<ProductComponent> getAllProductsInTopCollections() {
        waitForAllElementPresence(HomeUI.PRODUCTS_IN_TOP_COLLECTIONS);
        List<ProductComponent> products = getElementList(HomeUI.PRODUCTS_IN_TOP_COLLECTIONS).stream()
            .filter(this::isVisibleNonDuplicateSlide)
            .map(product -> new ProductComponent(driver, product))
            .toList();
        return products;
    }

    @Step("Check if the product is visible and not a duplicate slide")
        private boolean isVisibleNonDuplicateSlide(WebElement product) {
            String classNames = product.getDomAttribute("class");
        boolean duplicateSlide = classNames != null && classNames.contains("swiper-slide-duplicate");
        return product.isDisplayed() && !duplicateSlide;
        }

    @Step("Click to Wish List link in Wish List popup")
    public WishListPO clickWishlistLinkInWishlistPopup() {
        waitForElementClickable(HomeUI.WISHLIST_POPUP_PRODUCT_WISHLIST_BUTTON);
        clickToElement(HomeUI.WISHLIST_POPUP_PRODUCT_WISHLIST_BUTTON);
        return PageGenerator.getWishListPage(driver);
    }

    @Step("Scroll to product in Collection Popular by index {0}")
    public void scrollToProductInCollectionPopular(int i) {
        waitForElementPresence(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(i));
        scrollToElementOnTopByJS(HomeUI.DYNAMIC_COLLECTION_PRODUCT, String.valueOf(i));
    }


    //Banner
    //Top Trending Ca
    //Top Products
    //Top Collections
    //From The Blog
}
