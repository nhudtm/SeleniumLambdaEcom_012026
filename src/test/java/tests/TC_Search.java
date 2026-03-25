package tests;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.MenuCategoryPO;
import pageObjects.PageGenerator;
import pageObjects.ProductDetailPO;
import pageObjects.SearchPO;

import java.util.List;

public class TC_Search extends BaseTest {
    protected WebDriver driver;
    protected MenuCategoryPO menuCategoryPage;
    protected SearchPO searchPage;
    protected ProductDetailPO productDetailPage;

      @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(getDriver(context));
        log.info(
                "Thread id beforeClass: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    @Test(groups = {"regression", "search"})
    public void TC_01_Search_Empty_Data(){
        menuCategoryPage.enterTextInSearchBox("");
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search -" );
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 15 of 75 (5 Pages)");

    }

    @Test(groups = {"regression", "search"})
    public void TC_02_Search_Long_Keywords(){
        String longSearchText = "This is an extremely long search keyword that exceeds the maximum limit";
        menuCategoryPage.enterTextInSearchBox(longSearchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + longSearchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getNoProductMessage(),"There is no product that matches the search criteria.");

    }

    @Test(groups = {"regression", "search"})
    public void TC_03_Search_Valid_Keywords(){
        String searchText = "iPhone";
        menuCategoryPage.enterTextInSearchBox(searchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + searchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 4 of 4 (1 Pages)");

    }

    @Test
    public void TC_04_Search_Special_Characters(){
        String specialCharSearchText = "@#$%&*!";
        menuCategoryPage.enterTextInSearchBox(specialCharSearchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + specialCharSearchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getNoProductMessage(),"There is no product that matches the search criteria.");

    }

    @Test
    public void TC_05_Search_Invalid_Keywords(){
        String invalidSearchText = "xyz123";
        menuCategoryPage.enterTextInSearchBox(invalidSearchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + invalidSearchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getNoProductMessage(),"There is no product that matches the search criteria.");

    }

    @Test
    public void TC_06_Search_Uppercase_Keywords(){
        String uppercaseSearchText = "IPHONE";
        menuCategoryPage.enterTextInSearchBox(uppercaseSearchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + uppercaseSearchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 4 of 4 (1 Pages)");


    }

    @Test
    public void TC_07_Search_Lowercase_Keywords(){
        String lowercaseSearchText = "iphone";
        menuCategoryPage.enterTextInSearchBox(lowercaseSearchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + lowercaseSearchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 4 of 4 (1 Pages)");
    }

    @Test
    public void TC_08_Search_Space_Before_And_After_Keywords(){
        String spacedSearchText = "  iPhone  ";
        menuCategoryPage.enterTextInSearchBox(spacedSearchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + spacedSearchText.trim() );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 4 of 4 (1 Pages)");
    }

    @Test
    public void TC_08_Search_Auto_Suggest() throws InterruptedException {
        //Kiểm tra tính năng gợi ý từ khóa khi nhập vào ô tìm kiếm
        //Nhập từ khóa vào ô tìm kiếm vd "ip
        //Verify danh sách gợi ý xuất hiện
        //Chọn một từ khóa gợi ý và thực hiện tìm kiếm. vd ipodtouch
        //Verify kết quả tìm kiếm đúng với từ khóa đã chọn
        String partialSearchText = "ip";
        String toBeSelectedProduct = "iPod Touch";
        menuCategoryPage.enterTextInSearchBox(partialSearchText);
        Thread.sleep(3);
        Assert.assertTrue(menuCategoryPage.isAutoSuggestListDisplayed());
        System.out.println("Before selecting suggested product");
        productDetailPage = menuCategoryPage.selectSuggestedProduct(toBeSelectedProduct);
        System.out.println("After selecting suggested product");

        Assert.assertEquals(productDetailPage.getProductPageTitleText(), toBeSelectedProduct);
    }

//    @Test
//    public void TC_09_Search_By_Category(){
//        //Kiểm tra số lượng sản phẩm trả về trong category
//        //Chọn category
//        //Search
//        //Verify số lượng sản phẩm khi search theo category.
//        //Có thể là đã có expected number of product thông qua API hoặc database
//    }

    @Test
    public void TC_11_Verify_Search_Category_Selection(){
        //Verify có 9 categories
        menuCategoryPage.clickAllCategoriesParentDropdown();
        List<String> allCategories = menuCategoryPage.getAllCategoriesInSearch();
        menuCategoryPage.clickAllCategoriesParentDropdown();
        Assert.assertEquals(allCategories.size(),9);

        // Verify chọn từng category trong dropdown
        for(String category : allCategories){
            menuCategoryPage.clickCategoryInDropdown(category);
            Assert.assertEquals(menuCategoryPage.getSelectedCategory(),category);
        }
    }

//    @Test
//    public void TC_Search_Results_Sorted(){
//    }

    @Test
    public void TC_Search_Verify_Default_List_View(){
        String searchText = "iPhone";
        menuCategoryPage.enterTextInSearchBox(searchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + searchText );
        // Kiểm tra số lượng sản phẩm trả về
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 4 of 4 (1 Pages)");
        // Verify default view is List View
        Assert.assertTrue(searchPage.isListView());
    }

    @Test
    public void TC_12_Search_Verify_List_Grid_View(){
        String searchText = "iPhone";
        menuCategoryPage.enterTextInSearchBox(searchText);
        searchPage = menuCategoryPage.clickSearchButton();
        Assert.assertEquals(searchPage.getSearchPageTitleText(),  "Search - " + searchText );
        Assert.assertEquals(searchPage.getTotalProductAtPagination(), "Showing 1 to 4 of 4 (1 Pages)");
        Assert.assertTrue(searchPage.isGridView());
        searchPage.clickToListView();
        Assert.assertTrue(searchPage.isListView());
    }

    // Kiểm tra chức năng tìm kiếm với các từ khóa khác nhau, bao gồm cả từ khóa phổ biến và từ khóa không phổ biến.
    // Kiểm tra search với các category khác nhau (nếu có).

}
