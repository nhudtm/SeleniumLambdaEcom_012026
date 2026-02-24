
package pageUIs;

public class CartUI {




    public static final String SHOPPING_CART_TITLE = "css=h1";
    public static final String DYNAMIC_PRODUCT_NAME_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[2]/a";
    public static final String DYNAMIC_PRODUCT_IMAGE_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[1]/a";
    public static final String DYNAMIC_PRODUCT_QUANTITY_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[4]//input";
    public static final String DYNAMIC_PRODUCT_UNIT_PRICE_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[5]";
    public static final String DYNAMIC_PRODUCT_TOTAL_PRICE_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[6]";
    public static final String SHOPPING_CART_EMPTY_MESSAGE = "xpath=//div[@id='content']/p";
    public static final String ALL_PRODUCTS_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr";
    public static final String DYNAMIC_REMOVE_BUTTON_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[4]//button[2]";
    public static final String DYNAMIC_UPDATE_BUTTON_IN_CART_PAGE = "xpath=//div[@id='content']/h1/following-sibling::form//tbody/tr[%s]/td[4]//button[1]";

    // Cart totals
    public static final String SUB_TOTAL = "xpath=//div[@id='content']/h1/following-sibling::div[1]//td[text()='Sub-Total:']/following-sibling::td";
    public static final String ECO_TAX = "xpath=//div[@id='content']/h1/following-sibling::div[1]//td[text()='Eco Tax (-2.00):']/following-sibling::td";
    public static final String VAT = "xpath=//div[@id='content']/h1/following-sibling::div[1]//td[text()='VAT (20%):']//following-sibling::td";
    public static final String GRAND_TOTAL = "xpath=//div[@id='content']/h1/following-sibling::div[1]//td[text()='Total:']/following-sibling::td";
    public static final String CHECKOUT_BUTTON = "xpath=//div[@id='content']/h1/following-sibling::div[2]/a[text()='Checkout']";
    public static final String CONTINUE_SHOPPING_BUTTON = "xpath=//div[@id='content']/h1/following-sibling::div[2]/a[text()='Continue Shopping']";
}
