package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.CartUI;

public class CartPO extends MenuCategoryPO {
    public CartPO(WebDriver driver) {
        super(driver);
    }


    public String getCartPageURL() {
        return getPageURL();
    }

    public String getShoppingCartPageTitle() {
        waitForElementVisible(  CartUI.SHOPPING_CART_TITLE);
        return getElementText(  CartUI.SHOPPING_CART_TITLE);
    }


    public String getProductNameInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_NAME_IN_CART_PAGE, String.valueOf(index));
        return getElementText(  CartUI.DYNAMIC_PRODUCT_NAME_IN_CART_PAGE, String.valueOf(index));
    }

    public String getProductIdInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_IMAGE_IN_CART_PAGE, String.valueOf(index));
        return getElementDOMAttribute(  CartUI.DYNAMIC_PRODUCT_NAME_IN_CART_PAGE, "href", String.valueOf(index)).split("product_id=")[1];
    }

    public int getProductQuantityInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, String.valueOf(index));
        return Integer.parseInt(getElementProperties(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, "value", String.valueOf(index)));
    }

    public String getProductUnitPriceInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_UNIT_PRICE_IN_CART_PAGE, String.valueOf(index));
        return getElementText(  CartUI.DYNAMIC_PRODUCT_UNIT_PRICE_IN_CART_PAGE, String.valueOf(index));

    }

    public String getProductTotalPriceInCartTable(int index) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_TOTAL_PRICE_IN_CART_PAGE, String.valueOf(index));
        return getElementText(  CartUI.DYNAMIC_PRODUCT_TOTAL_PRICE_IN_CART_PAGE, String.valueOf(index));
    }



    public String getEmptyCartMessage() {
        waitForElementVisible(  CartUI.SHOPPING_CART_EMPTY_MESSAGE);
        return getElementText(  CartUI.SHOPPING_CART_EMPTY_MESSAGE);
    }

    public int getTotalProductsInCartTable() {
        waitForAllElementsVisible(  CartUI.ALL_PRODUCTS_IN_CART_PAGE);
        return getElementList(  CartUI.ALL_PRODUCTS_IN_CART_PAGE).size();
    }

    public void inputQuantityInCartPage(int i, int updateQuantity) {
        waitForElementVisible(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, String.valueOf(i));
        sendKeyToElement(  CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, String.valueOf(updateQuantity),String.valueOf(i));


    }

    public void clickUpdateButtonInCartPage(int index) {
        waitForElementClickable(  CartUI.DYNAMIC_UPDATE_BUTTON_IN_CART_PAGE, String.valueOf(index));
        clickToElement(  CartUI.DYNAMIC_UPDATE_BUTTON_IN_CART_PAGE, String.valueOf(index));
    }

    public String getProductSubtotalPriceInCartTable() {
        waitForElementVisible(  CartUI.SUB_TOTAL, "1");
        return getElementText(  CartUI.SUB_TOTAL, "1");
    }

    public String getEcoTax() {
        waitForElementVisible(  CartUI.ECO_TAX);
        return getElementText(  CartUI.ECO_TAX);
    }

    public String getVAT() {
        waitForElementVisible(  CartUI.VAT);
        return getElementText(  CartUI.VAT);
    }

    public  String getGrandTotalPrice() {
        waitForElementVisible(  CartUI.GRAND_TOTAL);
        return getElementText(  CartUI.GRAND_TOTAL);
    }

    public void clickRemoveProductByNumber(int numOfProduct) {
        waitForElementClickable(  CartUI.DYNAMIC_REMOVE_BUTTON_IN_CART_PAGE, String.valueOf(numOfProduct));
        clickToElement(  CartUI.DYNAMIC_REMOVE_BUTTON_IN_CART_PAGE , String.valueOf(numOfProduct));
        sleepInSecond(2);
    }

    public void pressEnter(int index){
        pressKeyToElement(CartUI.DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE, Keys.ENTER, String.valueOf(index));
    }
}
