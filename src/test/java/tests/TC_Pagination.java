package tests;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class TC_Pagination extends BaseTest {
    protected WebDriver driver;
    protected ProductDetailPO productDetailPage;
    protected ProductCategoryPO productCategoryPage;
    protected MenuCategoryPO menuCategoryPage;
    protected SpecialPO specialPage;

    @Parameters({"browserName", "url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(driver);
    }


    
    //---UI---//
    @Test
    public void TC_Verify_Pagination_Invilibility_When_No_Product() {
        
    }

    @Test
    public void TC_Verify_Pagination_Invisiblity_When_Have_One_Page() {
        //Product Category page co 1 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("HTC");
        productCategoryPage.isProductCategoryTitleDisplayed("HTC");

        Assert.assertTrue(productCategoryPage.isFirstPageButtonUndisplayed());
        Assert.assertTrue(productCategoryPage.isPreviousPageButtonUndisplayed());
        Assert.assertTrue(productCategoryPage.isNextPageButtonUndisplayed());
        Assert.assertTrue(productCategoryPage.isLastPageButtonUndisplayed());
    }

    @Test
    public void TC_Verify_Pagination_First_Page() {
        //Product Category page co 3 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        Assert.assertTrue(productCategoryPage.isActivePageNumberHighlighted());
        Assert.assertTrue(productCategoryPage.isFirstPageButtonUndisplayed());
        Assert.assertTrue(productCategoryPage.isPreviousPageButtonUndisplayed());
        Assert.assertTrue(productCategoryPage.isNextPageButtonDisplayed());
        Assert.assertTrue(productCategoryPage.isLastPageButtonDisplayed());

        productCategoryPage.getAllProductsInPage();

    }

    @Test
    public void TC_Verify_Pagination_Select_Page_Number() {
        //Product Category page co 3 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.goToPageNumber("2");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");
        productCategoryPage.goToPageNumber("3");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "3");
        productCategoryPage.goToPageNumber("1");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");

    }

    @Test
    public void TC_Verify_Pagination_All_Button_Displayed() {
        //Product Category page co 3 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.clickNextPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");

        Assert.assertTrue(productCategoryPage.isFirstPageButtonDisplayed());
        Assert.assertTrue(productCategoryPage.isPreviousPageButtonDisplayed());
        Assert.assertTrue(productCategoryPage.isNextPageButtonDisplayed());
        Assert.assertTrue(productCategoryPage.isLastPageButtonDisplayed());
    }

    @Test
    public void TC_Verify_Pagination_Last_Page() {
        //Product Category page co 3 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.goToPageNumber("3");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "3");
        Assert.assertTrue(productCategoryPage.isFirstPageButtonDisplayed());
        Assert.assertTrue(productCategoryPage.isPreviousPageButtonDisplayed());
        Assert.assertTrue(productCategoryPage.isNextPageButtonUndisplayed());
        Assert.assertTrue(productCategoryPage.isLastPageButtonUndisplayed());
    }

    @Test
    public void TC_Verify_Pagination_Next_Button() {
        //Product Category page co 3 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.clickNextPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");

    }

    @Test
    public void TC_Verify_Pagination_Previous_Button() {
        //Product Category page co 3 trang
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.goToPageNumber("2");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");
        productCategoryPage.clickPreviousPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
    }

    @Test
    public void TC_Verify_Pagination_Last_Button() {
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.clickLastPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "3");
    }

    @Test
    public void TC_Verify_Pagination_First_Button() {
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.goToPageNumber("3");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "3");
        productCategoryPage.clickFirstPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
    }

    @Test
    public void TC_Verify_Pagination_Sequential_Navigation() {
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.clickNextPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");
        productCategoryPage.clickNextPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "3");
        productCategoryPage.clickPreviousPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");
        productCategoryPage.clickPreviousPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.clickNextPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "2");
        productCategoryPage.clickPreviousPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        productCategoryPage.clickLastPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "3");
        productCategoryPage.clickFirstPageButton();
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
    }


    @Test
    public void TC_Verify_Pagination_Total_Page_0_Pages() {

    }


    // Need to start db before run test 
    // @Test
    public void TC_Verify_Pagination_Total_Page_1_Pages() {
        //Product Category page co 1 trang
        // Cần test các TH 1 trang - 1 product, 1 trang - 14 product
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("HTC");
        productCategoryPage.isProductCategoryTitleDisplayed("HTC");
        int totalProductsFromDB = productCategoryPage.getAllProductByCategoryFromDBByCategoryName("HTC");
        System.out.println("Total products from DB: " + totalProductsFromDB);
        int totalPages = (int) Math.ceil((double) totalProductsFromDB / 15); // Lam tron len vdu 34/15 = 2.26 -> 3
        System.out.println("Total pages: " + totalPages);
        for (int i = 1; i < totalPages; i++) {
            int totalProductInPage = productCategoryPage.getProductQuantityInPage();
            int from = (15 * (i - 1)) + 1;
            int to = (15 * (i - 1)) + totalProductInPage;
            String expectedText = "Showing " + from + " to " + to + " of " + totalProductsFromDB + " (" + totalPages + " Pages)";
            System.out.println("Expected text: " + expectedText);
            Assert.assertEquals(productCategoryPage.getPaginationText(), expectedText);

            if (productCategoryPage.isNextPageNumberUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
        }
    }

    // @Test
    public void TC_Verify_Pagination_Total_Page_3_Pages() {
        //Product Category page co 3 trang
        // Cần test các TH 3 trang - 31 product, 3 trang - 34 product, 3 trang - 45 product
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        productCategoryPage.isProductCategoryTitleDisplayed("Apple");
        Assert.assertEquals(productCategoryPage.getActivePageNumber(), "1");
        int totalProductsFromDB = productCategoryPage.getAllProductByCategoryFromDBByCategoryName("Apple");
        System.out.println("Total products from DB: " + totalProductsFromDB);
        int totalPages = (int) Math.ceil((double) totalProductsFromDB / 15); // Lam tron len vdu 34/15 = 2.26 -> 3

        System.out.println("Total pages: " + totalPages);
        for (int i = 1; i < totalPages; i++) {
            int totalProductInPage = productCategoryPage.getProductQuantityInPage();
            int from = (15 * (i - 1)) + 1;
            int to = (15 * (i - 1)) + totalProductInPage;
            String expectedText = "Showing " + from + " to " + to + " of " + totalProductsFromDB + " (" + totalPages + " Pages)";
            System.out.println("Expected text: " + expectedText);
            Assert.assertEquals(productCategoryPage.getPaginationText(), expectedText);

            if (productCategoryPage.isNextPageNumberUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
        }
    }


    @Test
    public void TC_Verify_Pagination_Total_Page_n_Pages() {
        //Product Category page co n trang (nhiều trang)
        // Cần test các TH n trang - (n-1)*15 + 1 product, 5 trang - n*15 + 5 product, 5 trang - n*15  product

    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

}
