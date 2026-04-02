package tests;

import commons.BaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePO;
import pageObjects.AddOnsModulesPO;
import pageObjects.PageGenerator;
import pageObjects.SearchPO;
import pageObjects.ProductDetailPO;
import pageObjects.BlogPO;
import pageObjects.ProductCategoryPO;
import pageObjects.MyAccountPO;
import pageObjects.CartPO;
import pageUIs.AddOnsModuleUI;

public class TC_Modules extends BaseTest {
    private WebDriver driver;
    private HomePO homePage;
    private AddOnsModulesPO modulesPage;
    private SearchPO searchPage;
    private ProductDetailPO productDetailPage;
    private BlogPO blogPage;
    private ProductCategoryPO productCategoryPage;
    private MyAccountPO accountPage;
    private CartPO cartPage;

    @Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass( String env, @Optional("chrome") String browserName, @Optional String browserVersion, @Optional String os, @Optional String osVersion, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomepage(driver);
    }

    @Description("TC-MODULE-001: Navigation to AddOns > Module")
    @Test(priority = 1, groups = {"regression", "addons"})
    public void TC_MODULE_001_Navigation_To_AddOns_Module() {
        homePage.hoverToAddOnsMenuItem();
        modulesPage = homePage.clickAddOnsModules();
        assertTrueWithMessage(modulesPage.isAddOnsModulesTitleDisplayed(), "Available Modules header is not visible");
    }

    @Description("TC-MODULE-002: Header Search with valid keywords")
    @Test(priority = 2, groups = {"regression", "addons"})
    public void TC_MODULE_002_Header_Search_With_Valid_Keywords() {
        modulesPage.enterSearchKeyword(AddOnsModuleUI.SEARCH_KEYWORD_IMAC);
        searchPage = modulesPage.clickSearchButton();
        assertTrueWithMessage(searchPage.getPageTitle().contains(AddOnsModuleUI.SEARCH_KEYWORD_IMAC), "Search results page for iMac not loaded");
        
        modulesPage = searchPage.clickAddOnsModules();
    }

    @Description("TC-MODULE-003: Product Listing Slider - Manual Navigation")
    @Test(priority = 3, groups = {"regression", "addons"})
    public void TC_MODULE_003_Product_Listing_Slider_Manual_Navigation() {
        modulesPage.clickSliderNextArrow();
        modulesPage.clickSliderPrevArrow();
    }

    @Description("TC-MODULE-004a: Product Redirect - Image Click")
    @Test(priority = 4, groups = {"regression", "addons"})
    public void TC_MODULE_004a_Product_Redirect_Image_Click() {
        productDetailPage = modulesPage.clickFirstProductImage();
        assertTrueWithMessage(productDetailPage.getPageURL().contains("product_id"), "Redirect to product detail page failed");
        
        homePage = productDetailPage.clickLogoIcon();
        modulesPage = homePage.clickAddOnsModules();
    }

    @Description("TC-MODULE-004b: Product Redirect - Title Click")
    @Test(priority = 5, groups = {"regression", "addons"})
    public void TC_MODULE_004b_Product_Redirect_Title_Click() {
        productDetailPage = modulesPage.clickFirstProductTitle();
        assertTrueWithMessage(productDetailPage.getPageURL().contains("product_id"), "Redirect to product detail page via title failed");
        
        homePage = productDetailPage.clickLogoIcon();
        modulesPage = homePage.clickAddOnsModules();
    }

    @Description("TC-MODULE-005: Article Listing Slider - Read More")
    @Test(priority = 6, groups = {"regression", "addons"})
    public void TC_MODULE_005_Article_Listing_Slider_Read_More() {
        blogPage = modulesPage.clickFirstArticleTitle();
        assertTrueWithMessage(blogPage.getPageURL().contains("blog"), "Redirect to blog page failed");
        
        homePage = blogPage.clickLogoIcon();
        modulesPage = homePage.clickAddOnsModules();
    }

    @Description("TC-MODULE-006: Brand Section Navigation")
    @Test(priority = 7, groups = {"regression", "addons"})
    public void TC_MODULE_006_Brand_Section_Navigation() {
        productCategoryPage = modulesPage.clickFirstBrandLogo();
        assertTrueWithMessage(modulesPage.getPageURL().contains("manufacturer"), "Brand listing page not loaded");
        
        homePage = productCategoryPage.clickLogoIcon();
        modulesPage = homePage.clickAddOnsModules();
    }

    @Description("TC-MODULE-007: Category Wall - Icon Redirection")
    @Test(priority = 8, groups = {"regression", "addons"})
    public void TC_MODULE_007_Category_Wall_Icon_Redirection() {
        productCategoryPage = modulesPage.clickCategoryWallIcon(AddOnsModuleUI.CATEGORY_LAPTOPS);
        assertTrueWithMessage(productCategoryPage.getPageURL().contains("category"), "Category page for Laptops not loaded");
        
        homePage = productCategoryPage.clickLogoIcon();
        modulesPage = homePage.clickAddOnsModules();
    }

    @Description("TC-MODULE-008: Slider Banner Pagination")
    @Test(priority = 9, groups = {"regression", "addons"})
    public void TC_MODULE_008_Slider_Banner_Pagination() {
        modulesPage.clickBannerPaginationDot("2");
    }

    @Description("TC-MODULE-009: Global Header Links (Compare/Wishlist/Cart)")
    @Test(priority = 10, groups = {"regression", "addons"})
    public void TC_MODULE_009_Global_Header_Links() {
        accountPage = modulesPage.clickHeaderWishlist();
        assertTrueWithMessage(modulesPage.getPageURL().contains("wishlist") || modulesPage.getPageURL().contains("login") || modulesPage.getPageURL().contains("account"), "Wishlist link navigation failed");
        
        modulesPage.backToPage();
        cartPage = modulesPage.clickHeaderCart();
        assertTrueWithMessage(modulesPage.getPageURL().contains("cart") || modulesPage.isShoppingCartDrawerDisplayed(), "Cart link navigation failed");
        modulesPage.refreshPage();
    }

    @Description("TC-MODULE-010: Keyboard Accessibility - Focus & Controls")
    @Test(priority = 11, groups = {"regression", "addons"})
    public void TC_MODULE_010_Keyboard_Accessibility_Focus_Controls() {
        modulesPage.pressTabKey(5);
        modulesPage.pressEnterKey();
        assertTrueWithMessage(modulesPage.getPageURL().contains("product") || modulesPage.getPageURL().contains("category") || modulesPage.getPageURL().contains("common/home"), "Keyboard Enter action failed to navigate");
        
        modulesPage.refreshPage();
        modulesPage = PageGenerator.getAddOnsModulesPage(driver);
    }

    @Description("TC-MODULE-011: Loading State - Image Lazy Loading")
    @Test(priority = 12, groups = {"regression", "addons"})
    public void TC_MODULE_011_Loading_State_Image_Lazy_Loading() {
        modulesPage.scrollToBottom();
        assertTrueWithMessage(modulesPage.areLazyLoadImagesDisplayed(), "Lazy loading images not found or failed to trigger");
    }

    @Description("TC-MODULE-012: Search Input Sanitization (Security)")
    @Test(priority = 13, groups = {"regression", "addons"})
    public void TC_MODULE_012_Search_Input_Sanitization() {
        modulesPage.enterSearchKeyword(AddOnsModuleUI.XSS_PAYLOAD);
        searchPage = modulesPage.clickSearchButton();
        assertFalseWithMessage(searchPage.getPageSource().contains(AddOnsModuleUI.XSS_PAYLOAD), "Input script was not sanitized");
        
        modulesPage = searchPage.clickAddOnsModules();
    }

    @Description("TC-MODULE-013: Hover Interaction on Product Card")
    @Test(priority = 14, groups = {"regression", "addons"})
    public void TC_MODULE_013_Hover_Interaction_On_Product_Card() {
        modulesPage.hoverFirstProduct();
        modulesPage.clickAddToCartFirstProduct();
        assertFalseWithMessage(modulesPage.isAddToCartSuccessPopupUndisplayed(), "Success popup did not appear after add to cart");
        
        modulesPage.clickToCartCompareWishlistClosePopup();
        modulesPage.waitForAllPopupsDisappeared();
    }

    @Description("TC-MODULE-014: Currency Switcher Impact on Module")
    @Test(priority = 15, groups = {"regression", "addons"})
    public void TC_MODULE_014_Currency_Switcher_Impact_On_Module() {
        if (modulesPage.isElementDisplayed(AddOnsModuleUI.CURRENCY_DROPDOWN)) {
            String oldPrice = modulesPage.getFirstProductPrice();
            modulesPage.selectEuroCurrency();
            modulesPage.sleepInSecond(2);
            String newPrice = modulesPage.getFirstProductPrice();
            assertNotEqualsWithMessage(oldPrice, newPrice, "Price did not update after currency change");
            assertTrueWithMessage(newPrice.contains(AddOnsModuleUI.EURO_SYMBOL), "Euro symbol not found in new price");
        } else {
            log.info("Currency dropdown not found in this environment. Skipping price update check.");
        }
    }

    @Description("TC-MODULE-015: Mobile View - Slider Touch Interaction")
    @Test(priority = 16, groups = {"regression", "addons"})
    public void TC_MODULE_015_Mobile_View_Slider_Touch_Interaction() {
        modulesPage.performSwipeOnSlider();
    }

    @Description("TC-MODULE-016: Page Refresh Persistence")
    @Test(priority = 17, groups = {"regression", "addons"})
    public void TC_MODULE_016_Page_Refresh_Persistence() {
        modulesPage.refreshPage();
        assertTrueWithMessage(modulesPage.isAddOnsModulesTitleDisplayed(), "Modules page title missing after refresh");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
