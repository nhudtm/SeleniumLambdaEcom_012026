package pageUIs;

public class AddOnsModuleUI {
    public static final String ADD_ONS_MODULES_TITLE = "xpath=//*[text()='Product Listing']" ;
    public static final String ADD_ONS_DESIGNS_TITLE = "xpath=//*[text()='Alert']";
    public static final String ADD_ONS_WIDGETS_TITLE = "xpath=//*[text()='Banner']";

    public static final String SLIDER_NEXT_ARROW = "xpath=//*[text()='Product Listing']/ancestor::div[contains(@class,'module') or contains(@class,'section') or contains(@class,'row')]//a[contains(@class,'swiper-nav-next') or contains(@class,'swiper-button-next')]";
    public static final String SLIDER_PREV_ARROW = "xpath=//*[text()='Product Listing']/ancestor::div[contains(@class,'module') or contains(@class,'section') or contains(@class,'row')]//a[contains(@class,'swiper-nav-prev') or contains(@class,'swiper-button-prev')]";
    
    // First active slide product
    public static final String FIRST_ACTIVE_PRODUCT = "xpath=(//*[text()='Product Listing']/ancestor::div[contains(@class,'module') or contains(@class,'section') or contains(@class,'row')]//div[contains(@class,'swiper-slide') and contains(@class,'swiper-slide-active') or (contains(@class,'swiper-slide') and not(contains(@class,'duplicate')))])[1]";
    public static final String FIRST_PRODUCT_IMAGE = FIRST_ACTIVE_PRODUCT + "//div[@class='image']/a";
    public static final String FIRST_PRODUCT_TITLE = FIRST_ACTIVE_PRODUCT + "//h4[@class='title']/a";
    public static final String FIRST_PRODUCT_ADD_TO_CART = "xpath=(//div[contains(@class,'product-layout')]//button[@title='Add to Cart'])[1]";
    public static final String FIRST_PRODUCT_PRICE = "xpath=(//div[contains(@class,'product-layout')]//span[@class='price-new'])[1]";
    public static final String CURRENCY_EUR_BUTTON = "css=button[name='EUR']";

    // Test Data Constants
    public static final String SEARCH_KEYWORD_IMAC = "iMac";
    public static final String CATEGORY_LAPTOPS = "Laptops";
    public static final String XSS_PAYLOAD = "<script>alert(1)</script>";
    public static final String EURO_SYMBOL = "€";

    public static final String FIRST_ARTICLE_TITLE = "xpath=(//div[contains(@id,'blog') or contains(@class,'blog') or contains(@class,'article')]//h4[@class='title']/a)[1]";
    
    // For brands, they are usually in a carousel
    public static final String FIRST_BRAND_LOGO = "xpath=(//div[contains(@class,'carousel') or contains(@class,'brand')]//div[contains(@class,'item') or contains(@class,'swiper-slide') and not(contains(@class,'duplicate'))]//a)[1]";
    
    // Category wall
    public static final String CATEGORY_WALL_DYNAMIC = "xpath=//div[contains(@class, 'm-category-wall')]//h4[text()='%s']/ancestor::a";
    public static final String BANNER_PAGINATION_DOT_DYNAMIC = "xpath=//div[contains(@class,'swiper-pagination-bullets')]//span[%s]";
    public static final String HEADER_WISHLIST = "css=a[aria-label='Wishlist']";
    public static final String HEADER_CART = "css=a.cart";
    public static final String HEADER_COMPARE = "css=a[aria-label='Compare']";
    public static final String LAZY_LOAD_IMAGES = "xpath=//img[@loading='lazy' or contains(@class,'lazyload')]";
    public static final String SWIPER_ACTIVE_PRODUCT_TITLE = "xpath=//div[contains(@class, 'swiper-slide-active')]//a[contains(@class, 'text-ellipsis-2')]";
    public static final String CURRENCY_DROPDOWN = "xpath=//a[contains(@class,'dropdown-toggle')]//span[contains(text(),'Currency')]";
}
