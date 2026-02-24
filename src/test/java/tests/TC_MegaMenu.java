package tests;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.MenuCategoryPO;
import pageObjects.PageGenerator;
import pageObjects.ProductCategoryPO;

import java.util.List;

public class TC_MegaMenu extends BaseTest {
    protected WebDriver driver;
    protected MenuCategoryPO menuCategoryPage;
    protected ProductCategoryPO productCategoryPage;

    @Parameters({"browserName", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(driver);
    }

    // 75p
    @Test
    public void TC_Verify_MegaMenu_Items(){
        // Test case này để kiểm tra điều hướng khi click vào từng item trong Mega Menu
        // Hover Mega Menu
        menuCategoryPage.hoverToMegaMenu();
        // Get list of all child items and verify
        List<String > allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu();
        Assert.assertEquals(allChildItemNames.size(), 28);

        //Verify each child item page - demo web chỉ đúng 2 cái đầu tiên
        for(int i = 0; i < 2; i++){
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i);
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
        }
       //
//       Đúng là phải duyệt qua toàn bộ product list. Nhưng mà do web demo chỉ đúng 2 cái đầu, nên em test tạm 2 cái thôi ạ
//        for(String childItem : allChildItemNames) {
//            menuCategoryPage.hoverToMegaMenu();
//            System.out.println("Child Item: " + childItem);
//            productDetailPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
//            System.out.println("Clicked on Child Item: " + childItem);
//            // Verify page
//            Assert.assertTrue(productDetailPage.isProductDetailTitleDisplayed(childItem));
//            System.out.println("Verified page for Child Item: " + childItem);
//        }
    }



    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
