package pageUIs;

public class HomeUI {
    // Body
    public static final String DYNAMIC_MODULE_TITLE = "xpath=//div[contains(@class,'entry-section')]//h3[@class='module-title' and contains(text(),'&s')]";

    // Top Collection nav items
    public static final String POPULAR="xpath=//div[contains(@class,'entry-section')]//li/a[contains(text(),'Popular')]";
    public static final String LATEST="xpath=//div[contains(@class,'entry-section')]//li/a[contains(text(),'Latest')]";
    public static final String BEST_SELLER="xpath=//div[contains(@class,'entry-section')]//li/a[contains(text(),'Best Seller')]";
    public static final String PREVIOUS_ARROW="xpath=//div[contains(@class,'entry-section')]//a[@class='mz-swiper-nav-prev']";
    public static final String NEXT_ARROW="xpath=//div[contains(@class,'entry-section')]//a[@class='mz-swiper-nav-next']";
    public static final String CAROUSEL_SLIDER = "xpath=//div[@class='carousel slide ']" ;

    //Top products
    public static final String PRODUCTS_IN_TOP_PRODUCTS = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')]";
    public static final String FIRST_PRODUCTS_IN_TOP_PRODUCTS = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')][1]";
    public static final String PRODUCT_NAME_IN_TOP_PRODUCTS = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')][1]//h4/a";
    public static final String ACTION_ADD_TO_CART = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')][1]//button[@title='Add to Cart']";
    public static final String ACTION_ADD_TO_WISHLIST = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')][1]//button[@title='Add to Wish List']";
    public static final String ACTION_ADD_TO_COMPARE = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')][1]//button[@title='Compare this Product']";
    public static final String ACTION_QUICK_VIEW = "xpath=//div[@id='entry_218399']//div[contains(@class,'swiper-slide')][1]//button[@title='Quick view']";


    // Collections
    public static final String PRODUCTS_IN_TOP_COLLECTIONS = "xpath=//div[@id='entry_218404']//div[contains(@class,'swiper-slide')]";
    public static final String DYNAMIC_COLLECTION_PRODUCT = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]";
    public static final String DYNAMIC_COLLECTION_PRODUCT_NAME = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//h4/a";
    public static final String DYNAMIC_COLLECTION_PRODUCT_ADD_TO_WISHLIST = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//button[@title='Add to Wish List']";
    public static final String DYNAMIC_COLLECTION_PRODUCT_ADD_TO_CART = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//button[@title='Add to Cart']";
    public static final String DYNAMIC_COLLECTION_PRODUCT_QUICK_VIEW = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//button[@title='Quick view']";
    public static final String DYNAMIC_COLLECTION_PRODUCT_ADD_TO_COMPARE = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//button[@title='Compare this Product']";
    public static final String DYNAMIC_COLLECTION_PRODUCT_PRICE = "xpath= (//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//span[@class='price-new']" ;


    //Login warning
    public static final String LOGIN_POPUP_VIEW_CART_LINK = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']//div/a[contains(text(),'View Cart')]";
    public static final String LOGIN_POPUP_CHECKOUT_LINK = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']//div/a[contains(text(),'Checkout')]";
    public static final String LOGIN_POPUP_CLOSE_BUTTON = "xpath=//div[@id='notification-box-top']//div[contains(@class,'toast-header')]/button[@aria-label='Close']";
    public static final String LOGIN_POPUP_BODY_TEXT = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']/div/p";

    //Compare popup
    public static final String COMPARE_POPUP_BODY_TEXT = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']/div//p" ;
    public static final String COMPARE_POPUP_PRODUCT_COMPARE_BUTTON = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']/a";
    public static final String COMPARE_POPUP_TITLE = "xpath=//div[@id='notification-box-top']//div[@class='toast-header']/span";

    public static final String WISHLIST_POPUP_PRODUCT_WISHLIST_BUTTON = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']/a";

    //Product popup
    public static final String PRODUCT_POPUP_TITLE = "xpath=//div[@id='entry_212948']/h1";
    public static final String PRODUCT_POPUP_CLOSE_BUTTON = "xpath=//div[@id='quick-view']/div//button[@aria-label='close']";


    public static final String DYNAMIC_COLLECTION_PRODUCT_ID = "xpath=(//div[@id='entry_218404']//div[contains(@class,'swiper-slide')])[%s]//a[contains(@id,'mz-product-listing')]" ;
    public static final String CART_POPUP_CHECKOUT_BUTTON = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']//a[2]" ;
    public static final String CART_POPUP_VIEW_CART_BUTTON = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']//a[1]" ;


    public static final String PRODUCT_QUICK_VIEW_POPUP = "xpath=//div[@id='product-quick-view']";



}
