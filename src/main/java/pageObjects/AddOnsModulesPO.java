package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.AddOnsModuleUI;
import pageUIs.MenuCategoryUI;

public class AddOnsModulesPO extends MenuCategoryPO {

    public AddOnsModulesPO(WebDriver driver) {

        super(driver);

    }

    public boolean isAddOnsModulesTitleDisplayed() {
        waitForElementVisible( AddOnsModuleUI.ADD_ONS_MODULES_TITLE);
        return isElementDisplayed( AddOnsModuleUI.ADD_ONS_MODULES_TITLE);
    }

    public void clickSliderNextArrow() {
        waitForElementClickable(AddOnsModuleUI.SLIDER_NEXT_ARROW);
        clickToElement(AddOnsModuleUI.SLIDER_NEXT_ARROW);
    }

    public void clickSliderPrevArrow() {
        waitForElementClickable(AddOnsModuleUI.SLIDER_PREV_ARROW);
        clickToElement(AddOnsModuleUI.SLIDER_PREV_ARROW);
    }

    public ProductDetailPO clickFirstProductImage() {
        waitForElementClickable(AddOnsModuleUI.FIRST_PRODUCT_IMAGE);
        // Using JS click to bypass potential slider overlays
        clickToElementByJS(AddOnsModuleUI.FIRST_PRODUCT_IMAGE);
        return PageGenerator.getProductDetailPage(driver);
    }

    public void hoverFirstProduct() {
        waitForElementVisible(AddOnsModuleUI.FIRST_ACTIVE_PRODUCT);
        // Sometimes hover requires scrolling into view first
        scrollToElementOnTopByJS(AddOnsModuleUI.FIRST_ACTIVE_PRODUCT);
        hoverToElement(AddOnsModuleUI.FIRST_ACTIVE_PRODUCT);
    }

    public void clickAddToCartFirstProduct() {
        waitForElementClickable(AddOnsModuleUI.FIRST_PRODUCT_ADD_TO_CART);
        clickToElementByJS(AddOnsModuleUI.FIRST_PRODUCT_ADD_TO_CART);
    }

    public ProductDetailPO clickFirstProductTitle() {
        waitForElementClickable(AddOnsModuleUI.FIRST_PRODUCT_TITLE);
        clickToElementByJS(AddOnsModuleUI.FIRST_PRODUCT_TITLE);
        return PageGenerator.getProductDetailPage(driver);
    }

    public BlogPO clickFirstArticleTitle() {
        waitForElementClickable(AddOnsModuleUI.FIRST_ARTICLE_TITLE);
        clickToElementByJS(AddOnsModuleUI.FIRST_ARTICLE_TITLE);
        return PageGenerator.getBlogPage(driver);
    }

    public ProductCategoryPO clickFirstBrandLogo() {
        waitForElementClickable(AddOnsModuleUI.FIRST_BRAND_LOGO);
        clickToElementByJS(AddOnsModuleUI.FIRST_BRAND_LOGO);
        return PageGenerator.getProductCategoryPage(driver);
    }

    public ProductCategoryPO clickCategoryWallIcon(String categoryName) {
        String locator = String.format(AddOnsModuleUI.CATEGORY_WALL_DYNAMIC, categoryName);
        waitForElementClickable(locator);
        clickToElementByJS(locator);
        return PageGenerator.getProductCategoryPage(driver);
    }

    public void clickBannerPaginationDot(String index) {
        String locator = String.format(AddOnsModuleUI.BANNER_PAGINATION_DOT_DYNAMIC, index);
        waitForElementClickable(locator);
        clickToElementByJS(locator);
    }

    public String getFirstProductPrice() {
        waitForElementVisible(AddOnsModuleUI.FIRST_PRODUCT_PRICE);
        return getElementText(AddOnsModuleUI.FIRST_PRODUCT_PRICE);
    }

    public MyAccountPO clickHeaderWishlist() {
        waitForElementClickable(AddOnsModuleUI.HEADER_WISHLIST);
        clickToElement(AddOnsModuleUI.HEADER_WISHLIST);
        return PageGenerator.getMyAccountPage(driver);
    }

    public CartPO clickHeaderCart() {
        waitForElementClickable(AddOnsModuleUI.HEADER_CART);
        clickToElement(AddOnsModuleUI.HEADER_CART);
        return PageGenerator.getCartPage(driver);
    }

    public void selectEuroCurrency() {
        waitForElementClickable(AddOnsModuleUI.CURRENCY_EUR_BUTTON);
        clickToElement(AddOnsModuleUI.CURRENCY_EUR_BUTTON);
    }

    public void enterSearchKeyword(String keyword) {
        waitForElementVisible(MenuCategoryUI.SEARCH_TEXTBOX);
        sendKeyToElement(MenuCategoryUI.SEARCH_TEXTBOX, keyword);
    }

    public void pressTabKey(int times) {
        for (int i = 0; i < times; i++) {
            pressKeyToElement(AddOnsModuleUI.SWIPER_ACTIVE_PRODUCT_TITLE, org.openqa.selenium.Keys.TAB);
        }
    }

    public void pressEnterKey() {
        pressKeyToElement(AddOnsModuleUI.SWIPER_ACTIVE_PRODUCT_TITLE, org.openqa.selenium.Keys.ENTER);
    }

    public void scrollToBottom() {
        scrollToBottomByJS();
        sleepInSecond(2);
    }

    public boolean areLazyLoadImagesDisplayed() {
        return isElementDisplayed(AddOnsModuleUI.LAZY_LOAD_IMAGES);
    }

    public void performSwipeOnSlider() {
        // Simulating swipe by a horizontal scroll on the swiper container
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", getElement(AddOnsModuleUI.SLIDER_NEXT_ARROW));
        // Swipe simulation usually requires TouchActions or specialized JS for swiper. 
        // For standard UI automation, a simple horizontal drag or JS scroll is often used.
        scrollToElementOnTopByJS(AddOnsModuleUI.SLIDER_NEXT_ARROW);
    }
}
