package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.GlobalConstants;
import components.FilterComponent;
import io.qameta.allure.Step;
import pageUIs.MenuCategoryUI;
import utils.DBUtils;

public class MenuCategoryPO extends BasePage {
    public MenuCategoryPO(WebDriver driver) {
        super(driver);
    }

    //Menu
    @Step("Click to 'My account' menu item")
    public MyAccountPO clickMyAccountMenuItem() {
        clickToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "My account");
        return PageGenerator.getMyAccountPage(driver);
    }


    @Step("Click to 'AddOns Designs' menu item")
    public AddOnsDesignsPO clickAddOnsDesigns() {
        waitForElementClickable(MenuCategoryUI.ADD_ONS_DESIGNS_LINK);
        clickToElement(MenuCategoryUI.ADD_ONS_DESIGNS_LINK);
        return PageGenerator.getAddOnsDesignsPage(driver);
    }

    @Step("Click to 'AddOns Modules' menu item")
    public AddOnsModulesPO clickAddOnsModules() {
        waitForElementClickable(MenuCategoryUI.ADD_ONS_MODULES_LINK);
        clickToElement(MenuCategoryUI.ADD_ONS_MODULES_LINK);
        return PageGenerator.getAddOnsModulesPage(driver);
    }

    @Step("Click to 'AddOns Widgets' menu item")
    public AddOnsWidgetPO clickAddOnsWidget() {
        waitForElementClickable(MenuCategoryUI.ADD_ONS_WIDGETS_LINK);
        clickToElement(MenuCategoryUI.ADD_ONS_WIDGETS_LINK);
        return PageGenerator.getAddOnsWidgetPage(driver);
    }

     @Step("Click to 'Blog' menu item")
    public MyAccountPO clickWishListIconWithoutLogin() {
        waitForElementClickable(MenuCategoryUI.WISHLIST_ICON);
        clickToElement(MenuCategoryUI.WISHLIST_ICON);
        return PageGenerator.getMyAccountPage(driver);
    }

    @Step("Click to 'Blog' menu item")
    public WishListPO clickWishListIconWithLoggedIn() {
        waitForElementClickable(MenuCategoryUI.WISHLIST_ICON);
        clickToElement(MenuCategoryUI.WISHLIST_ICON);
        return PageGenerator.getWishListPage(driver);
    }

    @Step("Click to 'Blog' menu item")
    public SpecialPO clickSpecialMenuItem() {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Special");
        clickToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Special");
        return PageGenerator.getSpecialPage(driver);
    }

    @Step("Click to 'Cart' icon")
    public CartPO clickCartIcon() {
        waitForElementClickable(MenuCategoryUI.CART_ICON);
        clickToElement(MenuCategoryUI.CART_ICON);
        return PageGenerator.getCartPage(driver);
    }

    @Step("Click to 'Compare' icon")
    public ComparePO clickCompareIcon() {
        waitForElementClickable(MenuCategoryUI.COMPARE_ICON);
        clickToElement(MenuCategoryUI.COMPARE_ICON);
        return PageGenerator.getComparePage(driver);
    }

    @Step("Click to 'Blog' menu item")
    public BlogPO clickBlogMenuItem() {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Blog");
        clickToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Blog");
        return PageGenerator.getBlogPage(driver);
    }

    @Step("Hover to 'My account' menu item")
    public void hoverToMyAccount() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "My account");
        hoverToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "My account");
    }

    @Step("Click to 'Login' link")
    public MyAccountPO clickLogin() {
        waitForElementVisible(MenuCategoryUI.LOGIN_LINK);
        hoverToElement(MenuCategoryUI.LOGIN_LINK);
        highligthElementByJS(MenuCategoryUI.LOGIN_LINK);
        sleepInSecond(2);
        clickToElement(MenuCategoryUI.LOGIN_LINK);
        return PageGenerator.getMyAccountPage(driver);
    }

    @Step("Click to 'Register' link")
    public RegisterPO clickRegister() {
        waitForElementVisible(MenuCategoryUI.REGISTER_LINK);
        hoverToElement(MenuCategoryUI.REGISTER_LINK);
        highligthElementByJS(MenuCategoryUI.REGISTER_LINK);
        sleepInSecond(2);
        clickToElement(MenuCategoryUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);
    }

    @Step("Click to 'Home' menu item")
    public HomePO clickHomeMenuItem() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Home");
        try {
            clickToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Home");
        } catch (Exception e) {
            clickToElementByJS(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Home");
        }

        sleepInSecond(2);
        if (!getPageURL().contains("route=common/home")) {
            clickToElementByJS(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Home");
            sleepInSecond(2);
        }
        return PageGenerator.getHomepage(driver);
    }

    @Step("Hover to 'AddOns' menu item")
    public void hoverToAddOnsMenuItem() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "AddOns");
        hoverToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "AddOns");
    }

    @Step("Click to 'Logo' icon")
    public HomePO clickLogoIcon() {
        waitForElementClickable(MenuCategoryUI.LOGO_ICON);
        clickToElement(MenuCategoryUI.LOGO_ICON);
        return PageGenerator.getHomepage(driver);
    }

    // Mega Menu
    @Step("Hover to 'Mega Menu'")
    public void hoverToMegaMenu() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Mega Menu");
        scrollToElementOnTopByJS(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Mega Menu");

        for (int attempt = 1; attempt <= 3; attempt++) {
            hoverToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Mega Menu");
            if (isAnyMegaMenuChildVisible()) {
                return;
            }

            clickToElementByJS(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Mega Menu");
            sleepInSecond(1);
            if (isAnyMegaMenuChildVisible()) {
                return;
            }
        }

        waitForElementVisible(MenuCategoryUI.MEGA_MENU_ALL_CHILD_ITEMS);
    }

    @Step("Get all child items in Mega Menu")
    public List<String> getAllChildItemsInMegaMenu() {
        hoverToMegaMenu();
        waitForAllElementsVisible(MenuCategoryUI.MEGA_MENU_ALL_CHILD_ITEMS);
        List<WebElement> allChildItems = getElementList(MenuCategoryUI.MEGA_MENU_ALL_CHILD_ITEMS);
        List<String> allChildItemNames = new ArrayList<>();
        for (WebElement childItem : allChildItems) {
            if (isDisplayedSafely(childItem)) {
                allChildItemNames.add(childItem.getDomAttribute("title"));
            }
        }
        return allChildItemNames;
    }

    @Step("Click to child item in Mega Menu")
    public ProductCategoryPO clickChildItemInMegaMenu(String childItemText) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            hoverToMegaMenu();
            WebElement targetChildItem = getVisibleMegaMenuChildItem(childItemText);
            if (targetChildItem == null) {
                continue;
            }

            try {
                highligthElementByJS(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
                targetChildItem.click();
                return PageGenerator.getProductCategoryPage(driver);
            } catch (Exception e) {
                clickToElementByJS(targetChildItem);
                return PageGenerator.getProductCategoryPage(driver);
            }
        }

        waitForElementClickable(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
        clickToElement(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
        return PageGenerator.getProductCategoryPage(driver);
    }

    private WebElement getVisibleMegaMenuChildItem(String childItemText) {
        List<WebElement> matchingItems = getElementList(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
        for (WebElement item : matchingItems) {
            if (isDisplayedSafely(item)) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", item);
                return item;
            }
        }
        return null;
    }

    private boolean isAnyMegaMenuChildVisible() {
        List<WebElement> allChildItems = getElementList(MenuCategoryUI.MEGA_MENU_ALL_CHILD_ITEMS);
        for (WebElement item : allChildItems) {
            if (isDisplayedSafely(item)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDisplayedSafely(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Search

    @Step("Enter text in search box")
    public void enterTextInSearchBox(String dress) {
        waitForElementVisible(MenuCategoryUI.SEARCH_TEXTBOX);
        sendKeyToElement(MenuCategoryUI.SEARCH_TEXTBOX, dress);
        sleepInSecond(3);
    }

    @Step("Click to search button")
    public SearchPO clickSearchButton() {
        waitForElementClickable(MenuCategoryUI.SEARCH_BUTTON);
        clickToElement(MenuCategoryUI.SEARCH_BUTTON);
        return PageGenerator.getSearchPage(driver);
    }

    @Step("Click to 'All Categories' parent dropdown")
    public void clickAllCategoriesParentDropdown() {
        waitForElementClickable(MenuCategoryUI.CATEGORIES_PARENT);
        clickToElement(MenuCategoryUI.CATEGORIES_PARENT);
        sleepInSecond(3);
    }

    @Step("Get all categories in search")
    public List<String> getAllCategoriesInSearch() {
        waitForElementVisible(MenuCategoryUI.All_CATEGORIES_CHILD);
        List<WebElement> allCategories = getElementList(MenuCategoryUI.All_CATEGORIES_CHILD);
        List<String> allCategoryNames = new ArrayList<>();
        for (WebElement category : allCategories) {
            allCategoryNames.add(category.getText());
        }
        return allCategoryNames;
    }

    @Step("Click to category in dropdown")
    public void clickCategoryInDropdown(String category) {
        waitForElementVisible(MenuCategoryUI.CATEGORIES_PARENT, category);
        selectItemInCustomDropdown(MenuCategoryUI.CATEGORIES_PARENT, MenuCategoryUI.All_CATEGORIES_CHILD, category);
    }

    @Step("Get selected category")
    public String getSelectedCategory() {
        waitForElementVisible(MenuCategoryUI.CATEGORIES_PARENT);
        return getElementText(MenuCategoryUI.CATEGORIES_PARENT);


    }

    @Step("Check if auto suggest list is displayed")
    public boolean isAutoSuggestListDisplayed() {
        waitForElementVisible(MenuCategoryUI.SUGGEST_LIST_CHILD);
        return !getElementList(MenuCategoryUI.SUGGEST_LIST_CHILD).isEmpty();
    }

    @Step("Select suggested product")
    public ProductDetailPO selectSuggestedProduct(String toBeSelectedProduct) {
        List<WebElement> allSuggestedProducts = getElementList(MenuCategoryUI.SUGGEST_LIST_CHILD);
        System.out.println("Number of suggested products: " + allSuggestedProducts.size());
        for (WebElement suggestedProduct : allSuggestedProducts) {
            if (suggestedProduct.getText().equals(toBeSelectedProduct)) {
                System.out.printf("Click to suggested product: %s\n", suggestedProduct.getText());
                clickToElementByJS(suggestedProduct);
                break;
            }
        }
        return PageGenerator.getProductDetailPage(driver);
    }

    @Step("Click to 'Shop by Category' menu")
    public void clickShopByCategoryMenu() {
        waitForElementClickable(MenuCategoryUI.SHOP_BY_CATEGORY_MENU);
        clickToElement(MenuCategoryUI.SHOP_BY_CATEGORY_MENU);

    }

    @Step("Get all categories in 'Shop by Category' menu")
    public List<String> getAllCategoriesInShopByCategoryMenu() {
//        waitForElementVisible(  MenuCategoryUI.SHOP_BY_CATEGORY_MENU_ITEM);
        List<WebElement> allCategories = getElementList(MenuCategoryUI.SHOP_BY_CATEGORY_MENU_ITEM);
        List<String> allCategoryNames = new ArrayList<>();
        for (WebElement category : allCategories) {
            allCategoryNames.add(category.getText());
        }
        return allCategoryNames;
    }

    @Step("Click to category in 'Shop by Category' menu")
    public ProductCategoryPO clickCategoryInShopByCategoryMenu(String category) {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU, category);
        highligthElementByJS(MenuCategoryUI.DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU, category);
        clickToElementByJS(MenuCategoryUI.DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU, category);

        return PageGenerator.getProductCategoryPage(driver);
    }

@Step("Wait for all popups disappeared")
    public void waitForAllPopupsDisappeared() {
        overrideGlobalTimeout(10);
        waitForElementInvisible(MenuCategoryUI.NOTIFICATION_POPUP);
        overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
    }

    @Step("Get all categories from DB")
    public List<String> getAllCategoryFromDB() {
        return DBUtils.getAllCategories();
    }

    @Step("Open product detail page by product id")
    public ProductDetailPO openProductDetailPageById(Integer productId) {
        openPage(GlobalConstants.DEV_URL + "/index.php?route=product/product&manufacturer_id=8&product_id=" + productId);
        return PageGenerator.getProductDetailPage(driver);
//        https://ecommerce-playground.lambdatest.io/index.php?route=product/product&manufacturer_id=8&product_id=58
    }

    // AddToCart Cart/Compare Popup
    @Step("Get text in Add to Cart popup")
    public String getCartPopupText() {
        waitForElementVisible(MenuCategoryUI.CART_POPUP_BODY_TEXT);
        return getElementText(MenuCategoryUI.CART_POPUP_BODY_TEXT);
    }

    @Step("Click to close icon in Add to Cart popup")
    public void clickToCartCompareWishlistClosePopup() {
        waitForElementClickable(MenuCategoryUI.CART_POPUP_CLOSE_BUTTON);
        highligthElementByJS(MenuCategoryUI.CART_POPUP_CLOSE_BUTTON);
        clickToElementByJS(MenuCategoryUI.CART_POPUP_CLOSE_BUTTON);
    }

    @Step("Check if Add to Cart popup is undisplayed")
    public boolean isAddToCartSuccessPopupUndisplayed() {
        overrideGlobalTimeout(5);
        boolean status = isElementUndisplayed(MenuCategoryUI.CART_POPUP_BODY_TEXT);
        overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
        return status;
    }

    @Step("Click checkout button in cart drawer")
    public void clickCheckoutButtonInCartDrawer() {
        waitForElementClickable(MenuCategoryUI.CART_DRAWER_CHECKOUT_BUTTON);
        clickToElement(MenuCategoryUI.CART_DRAWER_CHECKOUT_BUTTON);
    }

    @Step("Close cart page drawer")
    public void closeCartPageDrawer() {
        waitForElementClickable(MenuCategoryUI.CART_DRAWER_CLOSE_BUTTON);
        clickToElement(MenuCategoryUI.CART_DRAWER_CLOSE_BUTTON);

    }

    @Step("Check if shopping cart drawer is displayed")
    public boolean isShoppingCartDrawerDisplayed() {
        waitForElementVisible(MenuCategoryUI.CART_DRAWER);
        return isElementDisplayed(MenuCategoryUI.CART_DRAWER);
    }

    // AddToCart QuickView Popup
    @Step("Check if product name is displayed in Add to Cart popup")
    public boolean isProductNameDisplayedInAddToCartPopup(String productName) {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_PRODUCT_NAME_IN_ADD_TO_CART_POPUP, productName);
        return isElementDisplayed(MenuCategoryUI.DYNAMIC_PRODUCT_NAME_IN_ADD_TO_CART_POPUP, productName);
    }

    @Step("Check if product price is displayed in Add to Cart popup")
    public boolean isProductPriceDisplayedInAddToCartPopup(String productPrice) {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_PRODUCT_PRICE_IN_ADD_TO_CART_POPUP, productPrice);
        return isElementDisplayed(MenuCategoryUI.DYNAMIC_PRODUCT_PRICE_IN_ADD_TO_CART_POPUP, productPrice);
    }

    @Step("Select product size in Add to Cart popup")
    public void selectProductSizeInAddToCartPopup(String size) {
        waitForElementClickable(MenuCategoryUI.DROPDOWN_IN_ADD_TO_CART_POPUP, size);
        selectItemInDropdown(MenuCategoryUI.DROPDOWN_IN_ADD_TO_CART_POPUP, size);
        sleepInSecond(3);
    }

    @Step("Click Add to Cart button in Add to Cart popup")
    public void clickAddToCartInAddToCartPopup() {
//        scrollToElementOnTopByJS(MenuCategoryUI.DROPDOWN_IN_ADD_TO_CART_POPUP);
        waitForElementClickable(MenuCategoryUI.ADD_TO_CART_BUTTON_IN_ADD_TO_CART_POPUP);
        clickToElementByJS(MenuCategoryUI.ADD_TO_CART_BUTTON_IN_ADD_TO_CART_POPUP);
    }

    @Step("Get size warning message text")
    public String getSizeWarningMessageText() {
        waitForElementVisible(MenuCategoryUI.SIZE_WARNING_MESSAGE);
        return getElementText(MenuCategoryUI.SIZE_WARNING_MESSAGE);
    }

    @Step("Input quantity in Add to Cart popup")
    public void inputQuantityInAddToCartPopup(String number) {
        waitForElementVisible(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP);
        sendKeyToElement(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP, number);
    }

    @Step("Get quantity in Add to Cart popup")
    public int getQuantityInAddToCartPopup() {
        waitForElementVisible(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP);
        return Integer.parseInt(getElementProperties(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP, "value"));
    }

    @Step("Click plus quantity button in Add to Cart popup")
    public void clickPlusQuantityInAddToCartPopup(int times) {
        waitForElementClickable(MenuCategoryUI.PLUS_BUTTON_IN_ADD_TO_CART_POPUP);
        for (int i = 0; i < times; i++) {
            clickToElement(MenuCategoryUI.PLUS_BUTTON_IN_ADD_TO_CART_POPUP);
        }
    }

    @Step("Click minus quantity button in Add to Cart popup")
    public void clickMinusQuantityInAddToCartPopup(int i) {
        waitForElementClickable(MenuCategoryUI.MINUS_BUTTON_IN_ADD_TO_CART_POPUP);
        for (int j = 0; j < i; j++) {
            clickToElement(MenuCategoryUI.MINUS_BUTTON_IN_ADD_TO_CART_POPUP);
        }
    }

    @Step("Click buy now button in Add to Cart popup")
    public CartPO clickBuyNowInAddToCartPopup() {
        waitForElementClickable(MenuCategoryUI.BUY_NOW_BUTTON_IN_ADD_TO_CART_POPUP);
        clickToElement(MenuCategoryUI.BUY_NOW_BUTTON_IN_ADD_TO_CART_POPUP);
        return PageGenerator.getCartPage(driver);
    }

    @Step("Click close icon in Add to Cart popup")
    public void clickCloseIconInAddToCartPopup() {
        waitForElementClickable(MenuCategoryUI.CLOSE_ICON_IN_ADD_TO_CART_POPUP);
        clickToElement(MenuCategoryUI.CLOSE_ICON_IN_ADD_TO_CART_POPUP);
    }

    @Step("Get filter component")
    public FilterComponent getFilterComponent() {
        return new FilterComponent(driver);
    }

    @Step("Wait for loading icon to be undisplayed")
    public void waitForLoadingIconUndisplayed() {
        overrideGlobalTimeout(5);
        waitForElementInvisible(MenuCategoryUI.LOADING_ICON);
        overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
    }

    // For mobile view

    @Step("Click quick links icon")
    public void clickQuickLinksIcon() {
        clickToElementByJS(MenuCategoryUI.QUICK_LINKS_ICON);
    }

    @Step("Click My Account menu item in quick links")
    public MyAccountPO clickQuickLinksMyAccountMenuItem(String menuItem) {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MOBILE_VIEW_QUICK_LINKS_ITEM, menuItem);
        clickToElement(MenuCategoryUI.DYNAMIC_MOBILE_VIEW_QUICK_LINKS_ITEM, menuItem);
        return PageGenerator.getMyAccountPage(driver);

    }

    @Step("Click My Account")
    public MyAccountPO clickMyAccount() {
        WebElement menuIcon = getElement(MenuCategoryUI.QUICK_LINKS_ICON);
        if (menuIcon.isDisplayed()) {
            clickQuickLinksIcon();
            return clickQuickLinksMyAccountMenuItem("My account");
        } else {
            return clickMyAccountMenuItem();
        }
    }
}
