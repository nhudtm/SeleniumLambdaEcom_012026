package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static HomePO getHomepage(WebDriver driver) {
        return new HomePO(driver);
    }

    public static LoginPO getLoginPage(WebDriver driver) {
        return new LoginPO(driver);
    }

    public static RegisterPO getRegisterPage(WebDriver driver) {
        return new RegisterPO(driver);
    }

    public static CartPO getCartPage(WebDriver driver) {
        return new CartPO(driver);
    }

    public static ProductDetailPO getProductDetailPage(WebDriver driver) {
        return new ProductDetailPO(driver);
    }

    public static ComparePO getComparePage(WebDriver driver) {
        return new ComparePO(driver);
    }

    public static BlogPO getBlogPage(WebDriver driver) {
        return new BlogPO(driver);
    }

    public static SearchPO getSearchPage(WebDriver driver) {
        return new SearchPO(driver);
    }

  public static AddOnsDesignsPO getAddOnsDesignsPage(WebDriver driver) {
        return new AddOnsDesignsPO(driver);
  }
    public static AddOnsModulesPO getAddOnsModulesPage(WebDriver driver) {
        return new AddOnsModulesPO(driver);
    }

    public static AddOnsWidgetPO getAddOnsWidgetPage(WebDriver driver) {
        return new AddOnsWidgetPO(driver);
    }
    public static MyAccountPO getMyAccountPage(WebDriver driver) {
        return new MyAccountPO(driver);
    }

    public static WishListPO getWishListPage(WebDriver driver) {
        return new WishListPO(driver);
    }

    public static SpecialPO getSpecialPage(WebDriver driver) {
        return new SpecialPO(driver);
    }

    public static MenuCategoryPO getMenuCategoryPage(WebDriver driver) {
        return new MenuCategoryPO(driver);
    }

    public static ProductCategoryPO getProductCategoryPage(WebDriver driver) {
        return new ProductCategoryPO(driver);
    }

}
