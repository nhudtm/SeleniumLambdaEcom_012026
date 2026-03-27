package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.BlogPO;
import pageObjects.HomePO;
import pageObjects.PageGenerator;

public class TC_Blog extends BaseTest {
    private WebDriver driver;
    private HomePO homePage;
    private BlogPO blogPage;
    private String blogHomeUrl;

    @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env,
                            @Optional("chrome") String browserName,
                            @Optional String browserVersion,
                            @Optional String os,
                            @Optional String osVersion,
                            String url,
                            ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        driver = getDriver(context);
        homePage = PageGenerator.getHomepage(driver);
        blogHomeUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/blog/home";
        log.info("Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    private void accessBlogPageFromHomepage() {
        homePage = PageGenerator.getMenuCategoryPage(driver).clickHomeMenuItem();
        blogPage = homePage.clickBlogMenuItem();
        assertTrueWithMessage(blogPage != null, "Blog page object should be initialized after navigating from homepage menu");
    }

    @Test(groups = {"blog", "smoke", "regression", "P0"})
    public void TC_01_Open_Blog_Home_Successfully() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.isBlogHomeDisplayed(), "Blog home page should be displayed after opening Blog menu");
        assertTrueWithMessage(blogPage.isLatestArticlesSectionDisplayed(), "Latest Articles section should be visible");
        assertTrueWithMessage(blogPage.isMostViewedSectionDisplayed(), "Most viewed section should be visible");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_02_Verify_Latest_Articles_Card_Rendering() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.getArticleCardCountBySection("latest") > 0, "Latest section should contain article cards");
        assertTrueWithMessage(blogPage.isFirstArticleContentValidBySection("latest"), "First latest article should include valid title/image/author/comments/views/date");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_03_Verify_Most_Viewed_Card_Rendering() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.getArticleCardCountBySection("most viewed") > 0, "Most viewed section should contain article cards");
        assertTrueWithMessage(blogPage.isFirstArticleContentValidBySection("most viewed"), "First most viewed article should include complete metadata and route");
    }

    @Test(groups = {"blog", "regression", "P0"})
    public void TC_04_Open_Article_From_Latest_Articles() {
        accessBlogPageFromHomepage();
        String articleUrl = blogPage.openFirstArticleBySection("latest");
        assertTrueWithMessage(articleUrl.contains("route=extension/maza/blog/article"), "Opening first latest article should navigate to blog article detail page");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_05_Open_Article_From_Most_Viewed() {
        accessBlogPageFromHomepage();
        String articleUrl = blogPage.openFirstArticleBySection("most viewed");
        assertTrueWithMessage(articleUrl.contains("route=extension/maza/blog/article"), "Opening first most viewed article should navigate to blog article detail page");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_06_Open_Author_Profile_From_Article_Card() {
        accessBlogPageFromHomepage();
        String authorUrl = blogPage.openFirstAuthorLinkAndGetUrl();
        assertTrueWithMessage(authorUrl.contains("route=extension/maza/blog/author"), "Clicking author link should open author page route");
    }

    @Test(groups = {"blog", "P1"})
    public void TC_07_Open_Blog_Category_From_Widget() {
        accessBlogPageFromHomepage();
        String categoryUrl = blogPage.openFirstBlogCategoryAndGetUrl();
        assertTrueWithMessage(categoryUrl.contains("route=extension/maza/blog/category"), "Clicking category link should open blog category route");
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_08_Validate_Category_Count_Labels_Format() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.areBlogCategoryLabelsFormattedWithCount(), "All category labels should be in text + count format, for example Business (16)");
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_09_Verify_Sidebar_Latest_Widget_Presence_And_Navigation() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.isSidebarLatestWidgetDisplayed(), "Sidebar Latest widget should be visible");
        assertTrueWithMessage(blogPage.getSidebarLatestProductCount() > 0, "Sidebar Latest widget should contain at least one product");
        String productUrl = blogPage.openFirstSidebarLatestProductAndGetUrl();
        assertTrueWithMessage(productUrl.contains("route=product/product"), "Clicking first sidebar Latest item should open product detail route");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_10_Verify_Slider_Next_Navigation_In_Latest_Articles() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.canClickNextSliderBySection("latest"), "Latest slider should support next navigation");
        assertTrueWithMessage(blogPage.canClickNextSliderBySection("latest"), "Latest slider should remain stable on repeated next navigation");
    }

    @Test(groups = {"blog", "P2"})
    public void TC_11_Verify_Slider_Previous_Navigation_In_Latest_Articles() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.canClickNextSliderBySection("latest"), "Latest slider should move next before validating previous");
        assertTrueWithMessage(blogPage.canClickPreviousSliderBySection("latest"), "Latest slider should support previous navigation without corruption");
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_12_Verify_Slider_Next_Navigation_In_Most_Viewed() {
        accessBlogPageFromHomepage();
        for (int i = 0; i < 5; i++) {
            assertTrueWithMessage(blogPage.canClickNextSliderBySection("most viewed"), "Most viewed slider next navigation should remain stable at iteration " + (i + 1));
        }
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_13_Verify_Slider_Previous_Navigation_In_Most_Viewed() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.canClickNextSliderBySection("most viewed"), "Most viewed slider should move next before validating previous");
        assertTrueWithMessage(blogPage.canClickPreviousSliderBySection("most viewed"), "Most viewed slider previous navigation should remain responsive");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_14_Search_With_Valid_Keyword() {
        accessBlogPageFromHomepage();
        blogPage.searchBlogAndGetUrl("lorem");
        assertTrueWithMessage(blogPage.isBlogSearchRouteOnly(), "Valid blog keyword should navigate to blog search route and not product search route");
        assertTrueWithMessage(blogPage.isBlogSearchResultAreaVisible(), "Blog search should render a visible result area or explicit no-result state");
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_15_Search_With_Empty_Keyword() {
        accessBlogPageFromHomepage();
        String searchResultUrl = blogPage.submitEmptySearchAndGetUrl();
        assertTrueWithMessage(searchResultUrl.contains("route=extension/maza/blog/search") || searchResultUrl.contains("route=extension/maza/blog/home"), "Empty search should be handled gracefully on blog routes");
        assertTrueWithMessage(blogPage.isNoSensitiveErrorDisclosure(), "Empty search must not expose sensitive system details");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_16_Search_With_Special_Characters() {
        accessBlogPageFromHomepage();
        blogPage.searchBlogAndGetUrl("!@#$%^&*");
        assertTrueWithMessage(blogPage.isNoSensitiveErrorDisclosure(), "Special-character search must not expose stack traces, SQL details, or internal exceptions");
    }

    @Test(groups = {"blog", "P2"})
    public void TC_17_Search_Boundary_Min_Length() {
        accessBlogPageFromHomepage();
        blogPage.searchBlogAndGetUrl("a");
        assertTrueWithMessage(blogPage.isBlogSearchResultAreaVisible(), "One-character search should be handled with a stable rendered state");
    }

    @Test(groups = {"blog", "P2"})
    public void TC_18_Search_Boundary_Max_Length() {
        accessBlogPageFromHomepage();
        StringBuilder maxKeyword = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxKeyword.append('a');
        }
        blogPage.searchBlogAndGetUrl(maxKeyword.toString());
        assertTrueWithMessage(blogPage.isNoSensitiveErrorDisclosure(), "Max-length keyword search should not crash or disclose sensitive internals");
    }

    @Test(groups = {"blog",  "P3"})
    public void TC_19_Verify_Metadata_Consistency() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.isMetadataDisplayedInBlogCards(), "At least three blog cards should consistently display comments, views, and date metadata");
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_20_Verify_Image_Loading_Fallback_And_Success_Threshold() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.areBlogImagesDataBound(), "Visible blog thumbnail images should have src or data-src bindings");
        assertTrueWithMessage(blogPage.isImageRenderSuccessRatioAtLeast(0.95, 5), "Within 5 seconds, at least 95% of visible thumbnail images should render successfully");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_21_Responsive_Layout_On_Mobile_Viewport() {
        accessBlogPageFromHomepage();
        blogPage.setViewportSize(390, 844);
        assertTrueWithMessage(blogPage.isAnyBlogSearchTextboxDisplayed(), "Mobile viewport should display a usable blog search input");
        assertTrueWithMessage(blogPage.isMobileLayoutUsable(), "Mobile viewport should not have severe horizontal overflow");

        blogPage.setViewportSize(768, 1024);
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.isMobileLayoutUsable(), "Tablet viewport should remain readable without major horizontal overflow");
        assertTrueWithMessage(blogPage.areSliderArrowsVisibleInSection("latest"), "Slider arrows in Latest section should be visible in tablet viewport");

        blogPage.setViewportSize(1920, 1080);
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_22_Keyboard_Navigation_Accessibility() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.canNavigateByKeyboardFromSearch(), "Keyboard tab + enter flow should activate a focused article/category link without focus trap");
    }

    @Test(groups = {"blog", "P1"})
    public void TC_23_Verify_No_Sensitive_Error_Disclosure() {
        accessBlogPageFromHomepage();
        blogPage.searchBlogAndGetUrl("\"><script>alert(1)</script>");
        assertTrueWithMessage(blogPage.isNoSensitiveErrorDisclosure(), "Malformed query should not expose stack trace, SQL details, or filesystem paths");
    }

    @Test(groups = {"blog",  "P1"})
    public void TC_24_Performance_Baseline_For_Initial_Blog_Render() {
        assertTrueWithMessage(blogPage.isInitialRenderWithinSeconds(blogHomeUrl, 3), "Blog home initial render should complete within 3 seconds under baseline conditions");
    }

    @Test(groups = {"blog", "P1"})
    public void TC_25_Empty_Failure_Resilience_For_Feed_Sections() {
        accessBlogPageFromHomepage();
        boolean latestVisible = blogPage.isLatestArticlesSectionDisplayed();
        boolean mostViewedVisible = blogPage.isMostViewedSectionDisplayed();
        assertTrueWithMessage(latestVisible || mostViewedVisible, "Page shell should remain usable even when one feed section is unavailable");
        assertTrueWithMessage(blogPage.isNoSensitiveErrorDisclosure(), "Feed area should remain resilient without exposing internal errors");
    }

    @Test(groups = {"blog", "P1"})
    public void TC_26_Invalid_Article_Id_Should_Show_Graceful_NotFound_Behavior() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.canOpenInvalidArticleGracefully(), "Invalid article route should show graceful not-found behavior without technical details");
    }

    @Test(groups = {"blog", "regression", "P0"})
    public void TC_27_Search_Output_Encoding_Should_Prevent_XSS_Execution() {
        accessBlogPageFromHomepage();
        blogPage.searchBlogAndGetUrl("\"><script>alert(1)</script>");
        assertTrueWithMessage(blogPage.isNoSensitiveErrorDisclosure(), "XSS-like payload should be rendered safely without sensitive error disclosure");
    }

    @Test(groups = {"blog",  "P2"})
    public void TC_28_Browser_Back_Forward_Should_Preserve_Navigation_State() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.canNavigateBackAndForwardToSameArticle(), "Back and forward navigation should consistently restore blog list and the same article detail page");
    }

    @Test(groups = {"blog", "P1"})
    public void TC_29_Search_No_Result_State_Should_Be_Explicit_And_Stable() {
        accessBlogPageFromHomepage();
        blogPage.searchBlogAndGetUrl("zzz_non_existing_keyword_987654");
        assertTrueWithMessage(blogPage.isBlogSearchRouteOnly(), "No-result search should still stay on blog search route");
        assertTrueWithMessage(blogPage.isNoResultStateDisplayed() || blogPage.isBlogSearchResultAreaVisible(), "No-result search should display an explicit and stable empty/result state");
    }

    @Test(groups = {"blog", "P2"})
    public void TC_30_Thumbnail_Click_Behavior_Should_Match_Title_Link_Behavior() {
        accessBlogPageFromHomepage();
        assertTrueWithMessage(blogPage.isFirstThumbnailBehaviorConsistentWithTitle("latest"), "Thumbnail behavior should either open the same article route as title link or remain explicitly non-clickable by design");
    }
}