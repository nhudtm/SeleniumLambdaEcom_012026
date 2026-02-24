package pageUIs;

public class MenuCategoryUI {
    public static final String LOGO_ICON = "css=#entry_217821 .figure a";

    public static final String SHOP_BY_CATEGORY= "xpath=//div[@id='main-header']//a[@aria-label='Shop by Category']";
    public static final String DYNAMIC_MENU_ITEM = "xpath=//div[@id='main-navigation']//div/span[@class='title' and contains(text(),'%s')]";
    public static final String DYNAMIC_MOBILE_VIEW_QUICK_LINKS_ITEM = "xpath=//div[@id='widget-navbar-217843']//span[@class='title' and contains(text(),'%s')]";

    //Search
    public static final String CATEGORIES_PARENT = "xpath=//div[@id='main-header']//button[@class='btn dropdown-toggle']";
    public static final String CATEGORY_CHILD = "xpath=//div[contains(@class,'dropdown-menu-left show')]//a[text()='%s']";
    public static final String SEARCH_BUTTON = "xpath=//div[@id='main-header']//button[@type='submit']";
    public static final String SEARCH_TEXTBOX = "xpath=//div[@id=\"main-header\"]//input[@name=\"search\"]";
    public static final String COMPARE_ICON = "xpath=//div[@id='entry_217823']//a[@aria-label='Compare']";
    public static final String CART_ICON = "xpath=//div[@id='entry_217825']//div[@class='cart-icon']";
    public static final String WISHLIST_ICON = "xpath=//div[@id='entry_217824']//a[@aria-label='Wishlist']";
    public static final String All_CATEGORIES_CHILD = "xpath=//div[contains(@class,'dropdown-menu-left show')]//a";


    //CART POPUP
    public static final String CART_POPUP_VIEW_CART_LINK = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']//div/a[contains(text(),'View Cart')]";
    public static final String CART_POPUP_CHECKOUT_LINK = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']//div/a[contains(text(),'Checkout')]";
    public static final String CART_POPUP_CLOSE_BUTTON = "xpath=//div[@id='notification-box-top']//div[@class='toast-header']/button[@aria-label='Close']";
    public static final String CART_POPUP_BODY_TEXT = "xpath=//div[@id='notification-box-top']//div[@class='toast-body']/div//p";

    // Cart drawer
    public static final String CART_DRAWER = "xpath=//div[@id='cart-total-drawer']"; // neu khong duoc thi dung //div[@id='cart-total-drawer']/h5[text()='Cart ']
    public static final String CART_DRAWER_CHECKOUT_BUTTON = "xpath=//div[@id='entry_217851']/a" ;
    public static final String CART_DRAWER_EDIT_CART_BUTTON = "xpath=//div[@id='entry_217850']/a";
    public static final String CART_DRAWER_CLOSE_BUTTON = "xpath=//div[@id='cart-total-drawer']//a[@aria-label='close']";


    // Add To cart quickview popup
    public static final String PRODUCT_NAME_IN_ADD_TO_CART_POPUP = "css=#product-quick-view h1";
    public static final String DYNAMIC_PRODUCT_NAME_IN_ADD_TO_CART_POPUP = "xpath=//div[@id='product-quick-view']//h1[text()='%s']";
    public static final String DYNAMIC_PRODUCT_PRICE_IN_ADD_TO_CART_POPUP = "xpath=//div[@id='product-quick-view']//h3[text()='%s']";
    public static final String DROPDOWN_IN_ADD_TO_CART_POPUP = "xpath=//select[@id='input-option231-212958']";
    public static final String ADD_TO_CART_BUTTON_IN_ADD_TO_CART_POPUP = "xpath=//button[@title='Add to Cart']" ;
    public static final String BUY_NOW_BUTTON_IN_ADD_TO_CART_POPUP = "xpath=//button[@title='Buy now']" ;
    public static final String SIZE_WARNING_MESSAGE = "css=#product-quick-view div.text-danger";
    public static final String QUANTITY_IN_ADD_TO_CART_POPUP = "xpath=//input[@name='quantity']";
    public static final String PLUS_BUTTON_IN_ADD_TO_CART_POPUP = "css=div.input-group-append button";
    public static final String MINUS_BUTTON_IN_ADD_TO_CART_POPUP = "css=div.input-group-prepend button";
    public static final String CLOSE_ICON_IN_ADD_TO_CART_POPUP = "xpath=//button[@aria-label='close']";


    public static final String LOGIN_LINK = "xpath=//ul[contains(@class,'dropdown-menu show')]//span[contains(text(),'Login')]" ;
    public static final String REGISTER_LINK = "xpath=//ul[contains(@class,'dropdown-menu show')]//span[contains(text(),'Register')]" ;
    public static final String ADD_ONS_MODULES_LINK = "xpath=//ul[contains(@class,'dropdown-menu show')]//span[contains(text(),'Modules')]";
    public static final String ADD_ONS_DESIGNS_LINK = "xpath=//ul[contains(@class,'dropdown-menu show')]//span[contains(text(),'Designs')]" ;
    public static final String ADD_ONS_WIDGETS_LINK = "xpath=//ul[contains(@class,'dropdown-menu show')]//span[contains(text(),'Widgets')]" ;


    public static final String MEGA_MENU_ALL_CHILD_ITEMS = "xpath=//div[contains(@class,'menu-items')]//li[@class='nav-item']/a" ;
    public static final String DYNAMIC_MEGA_MENU_CHILD_ITEM = "xpath=//div[contains(@class,'menu-items')]//li[@class='nav-item']/a[@title='%s']";
    public static final String DYNAMIC_PRODUCT_TITLE = "xpath=//h1[text()='%s']" ;

    public static final String AUTO_SUGGEST_LIST_PARENT = "xpath=//div[@id='entry_217822']//ul[contains(@class,'dropdown')]";
    public static final String SUGGEST_LIST_CHILD = "xpath=//div[@id='entry_217822']//ul[contains(@class,'dropdown')]/li/div/h4/a";
    public static final String SHOP_BY_CATEGORY_MENU = "xpath=//div[@id='entry_217832']//a" ;
    public static final String SHOP_BY_CATEGORY_MENU_ITEM = "xpath=//div[@id='widget-navbar-217841']/ul[@class='navbar-nav vertical']/li//span" ;

    public static final String DYNAMIC_CATEGORY_IN_SHOP_BY_CATEGORY_MENU = "xpath=//div[@id='widget-navbar-217841']/ul[@class='navbar-nav vertical']/li//span[contains(text(),'%s')]" ;;
    public static final String NOTIFICATION_POPUP = "id=notification-box-top";
    public static final String LOADING_ICON = "xpath=//div[@class='mz-filter-loader']/div[@class='loader-spinner']";

    public static final String QUICK_LINKS_ICON = "xpath=//i[@class='icon fas fa-user-cog']";
}
