package pageUIs;

public class ProductCategoryUI {
    public static final String DYNAMIC_CATEGORY_TITLE = "xpath=//h1[text()='%s']";
    public static final String PRODUCT_ITEM = "xpath=//div[contains(@class,'product-layout')]";
    public static final String NEXT_PAGE = "xpath=//ul[@class='pagination']/li[@class='active page-item']/following-sibling::li[1]" ; // lấy từ active page toi next page
    public static final String NEXT_PAGE_BUTTON = "xpath=//ul[@class='pagination']//li/a[text()='>']" ;
    public static final String PREVIOUS_PAGE_BUTTON = "xpath=//ul[@class='pagination']//li/a[text()='<']" ;
    public static final String LAST_PAGE_BUTTON = "xpath=//ul[@class='pagination']//li/a[text()='>|']" ;
    public static final String FIRST_PAGE_BUTTON = "xpath=//ul[@class='pagination']//li/a[text()='|<']" ;
    public static final String DYNAMIC_PRODUCT_NAME_BY_INDEX = "xpath=//div[contains(@class,'product-layout')][%s]//div/h4/a";
    public static final String ALL_PRODUCT_NAME = "xpath=//div[contains(@class,'product-layout')]//div/h4/a";
    public static final String DYNAMIC_PRODUCT_PRICE_BY_INDEX = "xpath=//div[contains(@class,'product-layout')][%s]//div[@class='price']/span" ;
    public static final String NO_PRODUCT_MESSAGE = "css=#entry_212439 p" ;
    public static final String ACTIVE_PAGE = "xpath=//ul[@class='pagination']/li[@class='active page-item']/span" ;
    public static final String DYNAMIC_PAGE_NUMBER_BUTTON = "xpath=//ul[@class='pagination']/li[@class='page-item']/a[text()='%s']";
    public static final String PAGINATION_TEXT = "xpath=//div[contains(@class,'content-pagination')]//div[contains(@class,'text-right')]";
    public static final String SORT_BY_DROPDOWN = "xpath=//select[@id='input-sort-212434']";
}
