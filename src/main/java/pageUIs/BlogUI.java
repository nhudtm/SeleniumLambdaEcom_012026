package pageUIs;

public class BlogUI {
    public static final String UPLOAD_FILE_TYPE = "xpath=//input[@type='file']";
    public static final String BLOG_TITLE = "xpath=//h3[normalize-space()='Latest Articles']";
    public static final String BLOG_HOME_CONTAINER = "id=blog-home";

    public static final String LATEST_ARTICLES_TITLE = "xpath=//div[@id='blog-home']//h3[normalize-space()='Latest Articles']";
    public static final String MOST_VIEWED_TITLE = "xpath=//div[@id='blog-home']//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'most viewed')]";

    public static final String ARTICLE_LISTING_MODULES = "xpath=//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')]";
    public static final String DYNAMIC_ARTICLE_CARDS_BY_SECTION = "xpath=//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//div[contains(@class,'swiper-slide')]//div[contains(@class,'article-thumb')]";
    public static final String DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//h4[contains(@class,'title')]/a)[1]";
    public static final String DYNAMIC_FIRST_ARTICLE_IMAGE_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//div[contains(@class,'image')]//img)[1]";
    public static final String DYNAMIC_FIRST_ARTICLE_THUMBNAIL_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//div[contains(@class,'image')]//*[self::a or self::img])[1]";
    public static final String DYNAMIC_FIRST_ARTICLE_AUTHOR_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//span[contains(@class,'author')]/a)[1]";
    public static final String DYNAMIC_FIRST_ARTICLE_COMMENT_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//span[contains(@class,'comment')])[1]";
    public static final String DYNAMIC_FIRST_ARTICLE_VIEW_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//span[contains(@class,'viewed')])[1]";
    public static final String DYNAMIC_FIRST_ARTICLE_DATE_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//span[contains(@class,'timestamp')])[1]";
    public static final String DYNAMIC_SLIDER_NEXT_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//a[contains(@class,'mz-swiper-nav-next')])[1]";
    public static final String DYNAMIC_SLIDER_PREV_BY_SECTION = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')][.//h3[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'%s')]]//a[contains(@class,'mz-swiper-nav-prev')])[1]";

    public static final String ARTICLE_DETAIL_HEADING = "xpath=//div[@id='content']//h1|//h1";
    public static final String ALL_ARTICLE_TITLE_LINKS = "xpath=//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')]//h4[contains(@class,'title')]/a";
    public static final String ALL_AUTHOR_LINKS = "xpath=//div[@id='blog-home']//span[contains(@class,'author')]/a";
    public static final String ALL_COMMENT_METADATA = "xpath=//div[@id='blog-home']//span[contains(@class,'comment')]";
    public static final String ALL_VIEW_METADATA = "xpath=//div[@id='blog-home']//span[contains(@class,'viewed')]";
    public static final String ALL_DATE_METADATA = "xpath=//div[@id='blog-home']//span[contains(@class,'timestamp')]";
    public static final String ALL_ARTICLE_IMAGES = "xpath=//div[@id='blog-home']//div[contains(@class,'module-mz_article_listing')]//div[contains(@class,'image')]//img";

    public static final String BLOG_CATEGORY_ITEMS = "xpath=//div[@id='blog-home']//div[contains(@class,'module-mz_blog_category')]//a[contains(@class,'list-group-item')]";
    public static final String FIRST_BLOG_CATEGORY_LINK = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_blog_category')]//a[contains(@class,'list-group-item')])[1]";

    public static final String SIDEBAR_LATEST_WIDGET_TITLE = "xpath=//div[@id='blog-home']//div[contains(@class,'module-mz_product_listing')]//h3[normalize-space()='Latest']";
    public static final String SIDEBAR_LATEST_PRODUCT_ITEMS = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_product_listing')]//a[contains(@href,'route=product/product')])[1]";

    public static final String BLOG_SEARCH_TEXTBOX_DESKTOP = "xpath=//form[contains(@action,'route=extension/maza/blog/search')]//input[@name='search' and not(@type='hidden')]";
    public static final String BLOG_SEARCH_TEXTBOX_MOBILE = "xpath=//form[contains(@action,'route=extension/maza/blog/search')]//input[@name='search' and not(@type='hidden')]";
    public static final String BLOG_SEARCH_SUBMIT_BUTTON = "xpath=//form[contains(@action,'route=extension/maza/blog/search')]//button[@type='submit']";
    public static final String BLOG_CONTENT_CONTAINER = "xpath=//div[contains(@class,'entry-section') or contains(@class,'entry-content') or contains(@class,'content-articles')]";
    public static final String BLOG_SEARCH_RESULT_ITEMS = "xpath=//div[contains(@class,'article-thumb')]";
    public static final String BLOG_NO_RESULT_MESSAGE = "xpath=//*[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'there is no article that matches the search criteria') or contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no article') or contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no results') or contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'not found')]";
    public static final String FIRST_BLOG_CATEGORY_LINK_VISIBLE = "xpath=(//div[@id='blog-home']//div[contains(@class,'module-mz_blog_category')]//a[contains(@class,'list-group-item') and not(contains(@style,'display: none'))])[1]";
}
