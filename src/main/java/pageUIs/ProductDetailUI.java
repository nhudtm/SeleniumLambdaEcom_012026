package pageUIs;

public class ProductDetailUI {
    public static final String PRODUCT_PAGE_TITLE = "xpath=//h1";

    public static final String PRODUCT_PRICE = "xpath=//div[@class='price']/h3";
    public static final String PRODUCT_AVAILABILITY = "xpath=//span[text()='Availability:']/following-sibling::span";
    public static final String PRODUCT_DESCRIPTION = "css=#entry_216814 div.entry-content.content-description" ;
    public static final String MAIN_PRODUCT_IMAGE ="xpath=//div[@id='image-gallery-216811']/div[@class='image-thumb d-flex']//img";// image lớn
    public static final String ALL_PRODUCT_MEDIA = "xpath=//div[@id='entry_216811']//a"; //Bao gồm toàn bỗ image + video lớn nhỏ
    public static final String PRODUCT_THUMBNAIL_VIDEO ="xpath=//div[@id='entry_216811']//a[@class='thumbnail mfp-iframe']";
    public static final String ALL_PRODUCT_IMAGE ="xpath=//div[@id='entry_216811']//a/img";// bao gồm all image, không bao gồm video
    public static final String DYNAMIC_IMAGE = "xpath=(//div[@id='entry_216811']//a/img)[%s]"; //tất cả image, không bao gồm video

    public static final String VIDEO_IFRAME = "xpath=//div[@class='mfp-content']//iframe";
    public static final String VIDEO_PLAYER = "id=player" ; //video trong iframe
    public static final String VIDEO_PLAY_BUTTON = "css=div.ytp-cued-thumbnail-overlay >button.ytp-large-play-button"; //nút play trong video
    public static final String VIDEO_PAUSE_BUTTON = "xpath=//div[@class='ytp-left-controls']/button"; //nút pause trong video
    public static final String MEDIA_ZOOM_CLOSE_BUTTON = "xpath=//button[contains(text(),'×')]"; //button[@title='Close (Esc)']
    public static final String MEDIA_ZOOM_NEXT_BUTTON = "css=button.mfp-arrow-right";
    public static final String MEDIA_ZOOM_PREV_BUTTON = "css=button.mfp-arrow-left";
    public static final String ZOOM_IMAGE = "css=img.mfp-img";
    public static final String VIDEO_ERROR = "css=div.ytp-error";
    public static final String REVIEW_SUCCESS_MESSAGE = "xpath=//div[contains(@class,'alert-success')]";
    public static final String REVIEW_NAME_TEXTBOX = "id=input-name" ;
    public static final String REVIEW_TEXT_TEXTAREA = "id=input-review";
    public static final String REVIEW_SUBMIT_BUTTON = "id=button-review" ;
    public static final String DYNAMIC_REVIEW_STAR_RATING = "css=label[for='rating-%s-216860']";
    public static final String REVIEW_WARNING_MESSAGE = "xpath=//div[contains(@class,'alert-danger')]";

//div[@class='ytp-cued-thumbnail-overlay']/button

}

