package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageUIs.CartUI;

public class CartPO extends MenuCategoryPO {
    public CartPO(WebDriver driver) {
        super(driver);
    }
    @Step("Get Cart page URL")
    public String getCartPageURL() {
        return getPageURL();
    }

    @Step("Get Cart page title")
    public String getShoppingCartPageTitle() {
        waitForElementVisible(CartUI.SHOPPING_CART_TITLE);
        return getElementText(CartUI.SHOPPING_CART_TITLE);
    }

    @Step("Get product name in cart table at index {0}")
    public String getProductNameInCartTable(int index) {
        waitForElementVisible(CartUI.DYNAMIC_PRODUCT_NAME_IN_CART_PAGE, String.valueOf(index));
        return getElementText(CartUI.DYNAMIC_PRODUCT_NAME_IN_CART_PAGE, String.valueOf(index));
    }

    @Step("Get product ID in cart table at index {0}")
    public String getProductIdInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_IMAGE_IN_CART_PAGE, String.valueOf(index));
        return getElementDOMAttribute(  CartUI.DYNAMIC_PRODUCT_NAME_IN_CART_PAGE, "href", String.valueOf(index)).split("product_id=")[1];
    }

    @Step("Get product quantity in cart table at index {0}")
    public int getProductQuantityInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, String.valueOf(index));
        return Integer.parseInt(getElementProperties(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, "value", String.valueOf(index)));
    }

    @Step("Get product unit price in cart table at index {0}")
    public String getProductUnitPriceInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_UNIT_PRICE_IN_CART_PAGE, String.valueOf(index));
        return getElementText(  CartUI.DYNAMIC_PRODUCT_UNIT_PRICE_IN_CART_PAGE, String.valueOf(index));

    }

    @Step("Get product total price in cart table at index {0}")
    public String getProductTotalPriceInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_TOTAL_PRICE_IN_CART_PAGE, String.valueOf(index));
        return getElementText(  CartUI.DYNAMIC_PRODUCT_TOTAL_PRICE_IN_CART_PAGE, String.valueOf(index));
    }

    @Step("Check if the cart is empty")
    public String getEmptyCartMessage() {
        waitForElementVisible(  CartUI.SHOPPING_CART_EMPTY_MESSAGE);
        return getElementText(  CartUI.SHOPPING_CART_EMPTY_MESSAGE);
    }

    @Step("Get total number of products in cart table")
    public int getTotalProductsInCartTable() {
        waitForAllElementsVisible(  CartUI.ALL_PRODUCTS_IN_CART_PAGE);
        return getElementList(  CartUI.ALL_PRODUCTS_IN_CART_PAGE).size();
    }

    @Step("Input quantity {0} to product in cart table at index {1}")
    public void inputQuantityInCartPage(int i, int updateQuantity) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, String.valueOf(i));
        sendKeyToElement(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, String.valueOf(updateQuantity),String.valueOf(i));
    }

    @Step("Click to Update button in Cart page")
    public void clickUpdateButtonInCartPage(int index) {
        waitForElementClickable(  CartUI.DYNAMIC_UPDATE_BUTTON_IN_CART_PAGE, String.valueOf(index));
        clickToElement(  CartUI.DYNAMIC_UPDATE_BUTTON_IN_CART_PAGE, String.valueOf(index));
    }

    @Step("Get product subtotal price in cart table at index {0}")
    public String getProductSubtotalPriceInCartTable() {
        waitForElementVisible(  CartUI.SUB_TOTAL, "1");
        return getElementText(  CartUI.SUB_TOTAL, "1");
    }

    @Step("Get product eco tax in cart table at index {0}")
    public String getEcoTax() {
        waitForElementVisible(  CartUI.ECO_TAX);
        return getElementText(  CartUI.ECO_TAX);
    }

    @Step("Get product VAT price in cart table at index {0}")
    public String getVAT() {
        waitForElementVisible(CartUI.VAT);
        return getElementText(CartUI.VAT);
    }

    @Step("Get product grand total price in cart table at index {0}")
    public  String getGrandTotalPrice() {
        waitForElementVisible(  CartUI.GRAND_TOTAL);
        return getElementText(  CartUI.GRAND_TOTAL);
    }

    @Step("Click to Remove button in Cart page by index {0}")
    public void clickRemoveProductByNumber(int numOfProduct) {
        waitForElementClickable(  CartUI.DYNAMIC_REMOVE_BUTTON_IN_CART_PAGE, String.valueOf(numOfProduct));
        clickToElement(  CartUI.DYNAMIC_REMOVE_BUTTON_IN_CART_PAGE , String.valueOf(numOfProduct));
        sleepInSecond(2);
    }

    @Step("Press Enter key in quantity textbox in Cart page by index {0}")
    public void pressEnter(int index){
        pressKeyToElement(CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, Keys.ENTER, String.valueOf(index));
    }
}
