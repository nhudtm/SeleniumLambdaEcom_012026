package pageUIs;

public class WishListUI {
    public static final String WISH_LIST_TITLE = "xpath=//h1[text()='My Wish List']" ;
    public static final String TOTAL_PRODUCT_IN_WISH_LIST = "xpath=//h1[text()='My Wish List']/following-sibling::div//tbody/tr" ;
    public static final String DYNAMIC_REMOVE_BUTTON_BY_INDEX = "xpath=//h1[text()='My Wish List']/following-sibling::div//tbody/tr[%s]//i[@class='fa fa-times']" ;
    public static final String WISH_LIST_NO_PRODUCT_MESSAGE = "xpath=//h1/following-sibling::p[text()='No results!']";
    public static final String DYNAMIC_PRODUCT_NAME_BY_INDEX = "xpath=//h1[text()='My Wish List']/following-sibling::div//tbody/tr[%s]/td[2]/a"  ;
    public static final String ALL_PRODUCT_NAME = "xpath=//h1[text()='My Wish List']/following-sibling::div//tbody/tr/td[2]/a"  ;
    public static final String DYNAMIC_PRODUCT_PRICE_BY_INDEX = "xpath=//h1[text()='My Wish List']/following-sibling::div//tbody/tr[%s]/td[5]/div"  ;
}
