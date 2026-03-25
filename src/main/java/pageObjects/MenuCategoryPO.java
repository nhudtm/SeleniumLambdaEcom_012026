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

    
    public AddOnsWidgetPO clickAddOnsWidget() {
        waitForElementClickable(MenuCategoryUI.ADD_ONS_WIDGETS_LINK);
        clickToElement(MenuCategoryUI.ADD_ONS_WIDGETS_LINK);
        return PageGenerator.getAddOnsWidgetPage(driver);
    }

    public MyAccountPO clickWishListIconWithoutLogin() {
        waitForElementClickable(MenuCategoryUI.WISHLIST_ICON);
        clickToElement(MenuCategoryUI.WISHLIST_ICON);
        return PageGenerator.getMyAccountPage(driver);
    }

    public WishListPO clickWishListIconWithLoggedIn() {
        waitForElementClickable(MenuCategoryUI.WISHLIST_ICON);
        clickToElement(MenuCategoryUI.WISHLIST_ICON);
        return PageGenerator.getWishListPage(driver);
    }

    public SpecialPO clickSpecialMenuItem() {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Special");
        clickToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Special");
        return PageGenerator.getSpecialPage(driver);
    }

    public CartPO clickCartIcon() {
        waitForElementClickable(MenuCategoryUI.CART_ICON);
        clickToElement(MenuCategoryUI.CART_ICON);
        return PageGenerator.getCartPage(driver);
    }

    public ComparePO clickCompareIcon() {
        waitForElementClickable(MenuCategoryUI.COMPARE_ICON);
        clickToElement(MenuCategoryUI.COMPARE_ICON);
        return PageGenerator.getComparePage(driver);
    }

    public BlogPO clickBlogMenuItem() {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Blog");
        clickToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Blog");
        return PageGenerator.getBlogPage(driver);
    }

    public void hoverToMyAccount() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "My account");
        hoverToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "My account");
    }

    public MyAccountPO clickLogin() {
        waitForElementVisible(MenuCategoryUI.LOGIN_LINK);
        hoverToElement(MenuCategoryUI.LOGIN_LINK);
        highligthElementByJS(MenuCategoryUI.LOGIN_LINK);
        sleepInSecond(2);
        clickToElement(MenuCategoryUI.LOGIN_LINK);
        return PageGenerator.getMyAccountPage(driver);
    }

    public RegisterPO clickRegister() {
        waitForElementVisible(MenuCategoryUI.REGISTER_LINK);
        hoverToElement(MenuCategoryUI.REGISTER_LINK);
        highligthElementByJS(MenuCategoryUI.REGISTER_LINK);
        sleepInSecond(2);
        clickToElement(MenuCategoryUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);
    }

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

    public void hoverToAddOnsMenuItem() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "AddOns");
        hoverToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "AddOns");
    }

    public HomePO clickLogoIcon() {
        waitForElementClickable(MenuCategoryUI.LOGO_ICON);
        clickToElement(MenuCategoryUI.LOGO_ICON);
        return PageGenerator.getHomepage(driver);
    }

    // Mega Menu
    public void hoverToMegaMenu() {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Mega Menu");
        hoverToElement(MenuCategoryUI.DYNAMIC_MENU_ITEM, "Mega Menu");
    }

    public List<String> getAllChildItemsInMegaMenu() {
        waitForAllElementsVisible(MenuCategoryUI.MEGA_MENU_ALL_CHILD_ITEMS);
        List<WebElement> allChildItems = getElementList(MenuCategoryUI.MEGA_MENU_ALL_CHILD_ITEMS);
        List<String> allChildItemNames = new ArrayList<>();
        for (WebElement childItem : allChildItems) {
            allChildItemNames.add(childItem.getDomAttribute("title"));
        }
        return allChildItemNames;
    }

    public ProductCategoryPO clickChildItemInMegaMenu(String childItemText) {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
        highligthElementByJS(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
        clickToElement(MenuCategoryUI.DYNAMIC_MEGA_MENU_CHILD_ITEM, childItemText);
        return PageGenerator.getProductCategoryPage(driver);
    }

    // Search
    public void enterTextInSearchBox(String dress) {
        waitForElementVisible(MenuCategoryUI.SEARCH_TEXTBOX);
        sendKeyToElement(MenuCategoryUI.SEARCH_TEXTBOX, dress);
        sleepInSecond(3);
    }

    public SearchPO clickSearchButton() {
        waitForElementClickable(MenuCategoryUI.SEARCH_BUTTON);
        clickToElement(MenuCategoryUI.SEARCH_BUTTON);
        return PageGenerator.getSearchPage(driver);
    }

    public void clickAllCategoriesParentDropdown() {
        waitForElementClickable(MenuCategoryUI.CATEGORIES_PARENT);
        clickToElement(MenuCategoryUI.CATEGORIES_PARENT);
        sleepInSecond(3);
    }

    public List<String> getAllCategoriesInSearch() {
        waitForElementVisible(MenuCategoryUI.All_CATEGORIES_CHILD);
        List<WebElement> allCategories = getElementList(MenuCategoryUI.All_CATEGORIES_CHILD);
        List<String> allCategoryNames = new ArrayList<>();
        for (WebElement category : allCategories) {
            allCategoryNames.add(category.getText());
        }
        return allCategoryNames;
    }

    public void clickCategoryInDropdown(String category) {
        waitForElementVisible(MenuCategoryUI.CATEGORIES_PARENT, category);
        selectItemInCustomDropdown(MenuCategoryUI.CATEGORIES_PARENT, MenuCategoryUI.All_CATEGORIES_CHILD, category);
    }

    public String getSelectedCategory() {
        waitForElementVisible(MenuCategoryUI.CATEGORIES_PARENT);
        return getElementText(MenuCategoryUI.CATEGORIES_PARENT);


    }

    public boolean isAutoSuggestListDisplayed() {
        waitForElementVisible(MenuCategoryUI.SUGGEST_LIST_CHILD);
        return !getElementList(MenuCategoryUI.SUGGEST_LIST_CHILD).isEmpty();
    }

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

    public void clickShopByCategoryMenu() {
        waitForElementClickable(MenuCategoryUI.SHOP_BY_CATEGORY_MENU);
        clickToElement(MenuCategoryUI.SHOP_BY_CATEGORY_MENU);

    }

    public List<String> getAllCategoriesInShopByCategoryMenu() {
//        waitForElementVisible(  MenuCategoryUI.SHOP_BY_CATEGORY_MENU_ITEM);
        List<WebElement> allCategories = getElementList(MenuCategoryUI.SHOP_BY_CATEGORY_MENU_ITEM);
        List<String> allCategoryNames = new ArrayList<>();
        for (WebElement category : allCategories) {
            allCategoryNames.add(category.getText());
        }
        return allCategoryNames;
    }

    public ProductCategoryPO clickCategoryInShopByCategoryMenu(String category) {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU, category);
        highligthElementByJS(MenuCategoryUI.DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU, category);
        clickToElementByJS(MenuCategoryUI.DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU, category);

        return PageGenerator.getProductCategoryPage(driver);
    }


    public void waitForAllPopupsDisappeared() {
        overrideGlobalTimeout(10);
        waitForElementInvisible(MenuCategoryUI.NOTIFICATION_POPUP);
        overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
    }

    public List<String> getAllCategoryFromDB() {
        return DBUtils.getAllCategories();
    }

    public ProductDetailPO openProductDetailPageById(Integer productId) {
        openPage(GlobalConstants.DEV_URL + "/index.php?route=product/product&manufacturer_id=8&product_id=" + productId);
        return PageGenerator.getProductDetailPage(driver);
//        https://ecommerce-playground.lambdatest.io/index.php?route=product/product&manufacturer_id=8&product_id=58
    }

    // AddToCart Cart/Compare Popup
    public String getCartPopupText() {
        waitForElementVisible(MenuCategoryUI.CART_POPUP_BODY_TEXT);
        return getElementText(MenuCategoryUI.CART_POPUP_BODY_TEXT);
    }

    public void clickToCartCompareWishlistClosePopup() {
        waitForElementClickable(MenuCategoryUI.CART_POPUP_CLOSE_BUTTON);
        highligthElementByJS(MenuCategoryUI.CART_POPUP_CLOSE_BUTTON);
        clickToElementByJS(MenuCategoryUI.CART_POPUP_CLOSE_BUTTON);
    }

    public boolean isAddToCartSuccessPopupUndisplayed() {
        overrideGlobalTimeout(5);
        boolean status = isElementUndisplayed(MenuCategoryUI.CART_POPUP_BODY_TEXT);
        overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
        return status;
    }

    public void clickCheckoutButtonInCartDrawer() {
        waitForElementClickable(MenuCategoryUI.CART_DRAWER_CHECKOUT_BUTTON);
        clickToElement(MenuCategoryUI.CART_DRAWER_CHECKOUT_BUTTON);
    }

    public void closeCartPageDrawer() {
        waitForElementClickable(MenuCategoryUI.CART_DRAWER_CLOSE_BUTTON);
        clickToElement(MenuCategoryUI.CART_DRAWER_CLOSE_BUTTON);

    }

    public boolean isShoppingCartDrawerDisplayed() {
        waitForElementVisible(MenuCategoryUI.CART_DRAWER);
        return isElementDisplayed(MenuCategoryUI.CART_DRAWER);
    }

    // AddToCart QuickView Popup
    public boolean isProductNameDisplayedInAddToCartPopup(String productName) {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_PRODUCT_NAME_IN_ADD_TO_CART_POPUP, productName);
        return isElementDisplayed(MenuCategoryUI.DYNAMIC_PRODUCT_NAME_IN_ADD_TO_CART_POPUP, productName);
    }

    public boolean isProductPriceDisplayedInAddToCartPopup(String productPrice) {
        waitForElementVisible(MenuCategoryUI.DYNAMIC_PRODUCT_PRICE_IN_ADD_TO_CART_POPUP, productPrice);
        return isElementDisplayed(MenuCategoryUI.DYNAMIC_PRODUCT_PRICE_IN_ADD_TO_CART_POPUP, productPrice);
    }

    public void selectProductSizeInAddToCartPopup(String size) {
        waitForElementClickable(MenuCategoryUI.DROPDOWN_IN_ADD_TO_CART_POPUP, size);
        selectItemInDropdown(MenuCategoryUI.DROPDOWN_IN_ADD_TO_CART_POPUP, size);
        sleepInSecond(3);
    }

    public void clickAddToCartInAddToCartPopup() {
//        scrollToElementOnTopByJS(MenuCategoryUI.DROPDOWN_IN_ADD_TO_CART_POPUP);
        waitForElementClickable(MenuCategoryUI.ADD_TO_CART_BUTTON_IN_ADD_TO_CART_POPUP);
        clickToElementByJS(MenuCategoryUI.ADD_TO_CART_BUTTON_IN_ADD_TO_CART_POPUP);
    }

    public String getSizeWarningMessageText() {
        waitForElementVisible(MenuCategoryUI.SIZE_WARNING_MESSAGE);
        return getElementText(MenuCategoryUI.SIZE_WARNING_MESSAGE);
    }

    public void inputQuantityInAddToCartPopup(String number) {
        waitForElementVisible(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP);
        sendKeyToElement(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP, number);
    }

    public int getQuantityInAddToCartPopup() {
        waitForElementVisible(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP);
        return Integer.parseInt(getElementProperties(MenuCategoryUI.QUANTITY_IN_ADD_TO_CART_POPUP, "value"));
    }

    public void clickPlusQuantityInAddToCartPopup(int times) {
        waitForElementClickable(MenuCategoryUI.PLUS_BUTTON_IN_ADD_TO_CART_POPUP);
        for (int i = 0; i < times; i++) {
            clickToElement(MenuCategoryUI.PLUS_BUTTON_IN_ADD_TO_CART_POPUP);
        }
    }

    public void clickMinusQuantityInAddToCartPopup(int i) {
        waitForElementClickable(MenuCategoryUI.MINUS_BUTTON_IN_ADD_TO_CART_POPUP);
        for (int j = 0; j < i; j++) {
            clickToElement(MenuCategoryUI.MINUS_BUTTON_IN_ADD_TO_CART_POPUP);
        }
    }

    public CartPO clickBuyNowInAddToCartPopup() {
        waitForElementClickable(MenuCategoryUI.BUY_NOW_BUTTON_IN_ADD_TO_CART_POPUP);
        clickToElement(MenuCategoryUI.BUY_NOW_BUTTON_IN_ADD_TO_CART_POPUP);
        return PageGenerator.getCartPage(driver);
    }

    public void clickCloseIconInAddToCartPopup() {
        waitForElementClickable(MenuCategoryUI.CLOSE_ICON_IN_ADD_TO_CART_POPUP);
        clickToElement(MenuCategoryUI.CLOSE_ICON_IN_ADD_TO_CART_POPUP);
    }

    public FilterComponent getFilterComponent() {
        return new FilterComponent(driver);
    }

    public void waitForLoadingIconUndisplayed() {
        overrideGlobalTimeout(5);
        waitForElementInvisible(MenuCategoryUI.LOADING_ICON);
        overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
    }

    // For mobile view
    public void clickQuickLinksIcon() {
        clickToElementByJS(MenuCategoryUI.QUICK_LINKS_ICON);
    }


    public MyAccountPO clickQuickLinksMyAccountMenuItem(String menuItem) {
        waitForElementClickable(MenuCategoryUI.DYNAMIC_MOBILE_VIEW_QUICK_LINKS_ITEM, menuItem);
        clickToElement(MenuCategoryUI.DYNAMIC_MOBILE_VIEW_QUICK_LINKS_ITEM, menuItem);
        return PageGenerator.getMyAccountPage(driver);

    }

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
