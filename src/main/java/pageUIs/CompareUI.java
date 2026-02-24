package pageUIs;

public class CompareUI {
    public static final String COMPARE_PAGE_TITLE = "xpath=//div[@id='content']/h1[text()='Product Comparison']" ;
    public static final String SUCCESS_MESSAGE = "css=#product-compare div.alert-success" ;

    public static final String All_PRODUCTS ="xpath=//td[text()='Product']/following-sibling::td";
    public static final String DYNAMIC_PRODUCT_BY_NAME = "xpath=//a/strong[text()='%s']/parent::a" ;
    public static final String DYNAMIC_PRODUCT_BY_INDEX = "xpath=//td[text()='Product']/following-sibling::td[%s]/a";

    public static final String DYNAMIC_PRODUCT_PRICE_BY_INDEX = "xpath=//td[text()='Price']/following-sibling::td[%s]";
    public static final String DYNAMIC_IMAGE_BY_INDEX = "xpath=//td[text()='Image']/following-sibling::td[%s]/img";
    public static final String DYNAMIC_REMOVE_BUTTON_BY_INDEX = "xpath=(//td/input[@type='button'])[%s]/following-sibling::a" ;
    public static final String DYNAMIC_ADD_TO_CART_BUTTON_BY_INDEX = "xpath=(//td/input[@type='button'])[%s]" ;
    public static final String NO_PRODUCT_MESSAGE_TEXT = "xpath=//div[@id='content']/p";
    public static final String DYNAMIC_PRODUCT_NAME_BY_INDEX = "xpath=//td[text()='Product']/following-sibling::td[%s]";


}
