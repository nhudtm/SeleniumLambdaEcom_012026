package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddOnsDesignsPO;
import pageObjects.AddOnsModulesPO;
import pageObjects.AddOnsWidgetPO;
import pageObjects.BlogPO;
import pageObjects.CartPO;
import pageObjects.ComparePO;
import pageObjects.HomePO;
import pageObjects.MenuCategoryPO;
import pageObjects.MyAccountPO;
import pageObjects.PageGenerator;
import pageObjects.ProductCategoryPO;
import pageObjects.ProductDetailPO;
import pageObjects.RegisterPO;
import pageObjects.SpecialPO;
import pageObjects.WishListPO;

public class TC_MenuCategory extends BaseTest {
    protected WebDriver driver;
    protected HomePO homePage;
    protected MyAccountPO myAccountPage;
    protected HomePO homeHomePage;
    protected SpecialPO specialPage;
    protected AddOnsDesignsPO addOnsDesignsPage;
    protected AddOnsModulesPO addOnsModulesPage;
    protected AddOnsWidgetPO addOnsWidgetPage;
    protected BlogPO blogPage;
    protected CartPO cartPage;
    protected ComparePO comparePage;
    protected ProductDetailPO productDetailPage;
    protected RegisterPO registerPage;
    protected WishListPO wishListPage;
    protected MenuCategoryPO menuCategoryPage;
    protected ProductCategoryPO productCategoryPO;

      @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional("chrome") String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(getDriver(context));
        log.info(
                "Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    @Test(groups = {"regression", "menu"})
    public void TC_01_Access_Home_Page(){
        homePage = menuCategoryPage.clickHomeMenuItem();
        Assert.assertTrue(homePage.isHomeSliderDisplayed());
    }

    @Test(groups = {"regression", "menu"})
    public void TC_02_Access_Special_Page(){
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        Assert.assertTrue(specialPage.isSpecialPageTitleDisplayed());
    }

    @Test(groups = {"regression", "menu"})  
    public void TC_03_Access_Blog_Page(){
        blogPage = menuCategoryPage.clickBlogMenuItem();
        Assert.assertTrue(blogPage.isBlogTitleDisplayed());
    }

    @Test(groups = {"regression", "menu"})
    public void TC_04_Access_AddOnsModules_Page(){
        menuCategoryPage.hoverToAddOnsMenuItem(); // viet o MenuCategoryPO
        addOnsModulesPage = menuCategoryPage.clickAddOnsModules();
        Assert.assertTrue(addOnsModulesPage.isAddOnsModulesTitleDisplayed());
    }

    @Test
    public void TC_05_Access_AddOnsDesign_Page(){
        menuCategoryPage.hoverToAddOnsMenuItem(); // viet o MenuCategoryPO
        addOnsDesignsPage = menuCategoryPage.clickAddOnsDesigns();
        Assert.assertTrue(addOnsDesignsPage.isAddOnsDesignsTitleDisplayed());
    }

    @Test
    public void TC_06_Access_AddOnsWidget_Page(){
        menuCategoryPage.hoverToAddOnsMenuItem(); // viet o MenuCategoryPO
        addOnsWidgetPage = menuCategoryPage.clickAddOnsWidget();
        Assert.assertTrue(addOnsWidgetPage.isAddOnsWidgetsDisplayed());
    }

    @Test
    public void TC_07_Access_MyAccount_Page(){
        myAccountPage = menuCategoryPage.clickMyAccountMenuItem();
        Assert.assertTrue(myAccountPage.isLoginFormDisplayed());
    }

    @Test
    public void TC_08_Access_Login_Page_By_Hover()  {
        menuCategoryPage.hoverToMyAccount();
        myAccountPage = menuCategoryPage.clickLogin();
        Assert.assertTrue(myAccountPage.isLoginFormDisplayed());

    }

    @Test
    public void TC_09_Access_Register_Page_By_Hover(){
        menuCategoryPage.hoverToMyAccount();
        registerPage = menuCategoryPage.clickRegister();
        Assert.assertTrue(registerPage.isRegisterFormDisplayed());
    }

    @Test
    public void TC_10_Access_WishList_Page_Without_Login(){
        myAccountPage = menuCategoryPage.clickWishListIconWithoutLogin();
        Assert.assertTrue(myAccountPage.isLoginFormDisplayed());
    }

    @Test
    public void TC_14_Access_WishList_Page_With_Login(){
        String email = utils.PropertiesConfig.getProp("test.email");
        String password = utils.PropertiesConfig.getProp("test.password");
        myAccountPage = homePage.clickMyAccountMenuItem();
        myAccountPage.inputToEmailTextbox(email);
        myAccountPage.inputToPasswordTextbox(password);
        myAccountPage.clickLoginButton();
        Assert.assertTrue(myAccountPage.isMyAccountTitleDisplayed());
        wishListPage = menuCategoryPage.clickWishListIconWithLoggedIn();
        Assert.assertTrue(wishListPage.isYourWishListTitleDisplayed());
    }

    @Test
    public void TC_11_Access_Compare_Page(){
        comparePage = menuCategoryPage.clickCompareIcon();
        Assert.assertTrue(comparePage.isCompareTitleDisplayed());
    }

    @Test
    public void TC_12_Access_Cart_Drawer(){
        cartPage = menuCategoryPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDrawerDisplayed());
        cartPage.closeCartPageDrawer();
    }

    @Test
    public void TC_13_Verify_Logo(){
        homePage = menuCategoryPage.clickLogoIcon();
        Assert.assertTrue(homePage.isHomeSliderDisplayed());
    }


    @Test
    public void TC_14_Access_Category_From_ShopByCategory(){
        menuCategoryPage.clickShopByCategoryMenu();
        productCategoryPO = menuCategoryPage.clickCategoryInShopByCategoryMenu("Components");
        Assert.assertTrue(productCategoryPO.isProductCategoryTitleDisplayed("Components"));

        // Đúng ra là duyệt qua từng category -> click -> verify. Nhưng do html bi lỗi, ko get được list of element text nên không làm tiếp được
//        List<String> allCategories = menuCategoryPage.getAllCategoriesInShopByCategoryMenu();
//        System.out.println(allCategories);
//
//        for(String category : allCategories){
//            System.out.printf("category = %s %n", category);
//            menuCategoryPage.clickShopByCategoryMenu();
//            productByCategoryPO = menuCategoryPage.clickCategoryInShopByCategoryMenu(category);
//            Assert.assertTrue(productByCategoryPO.isProductDetailTitleDisplayed(category));
//        }
    }

}
