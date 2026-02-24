package tests;

import utils.FilterHelper;
import commons.BaseTest;
import components.FilterComponent;
import components.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.List;

public class TC_Filter_Special extends BaseTest {
    protected WebDriver driver;
    protected ProductDetailPO productDetailPage;
    protected ProductCategoryPO productCategoryPage;
    protected MenuCategoryPO menuCategoryPage;
    protected SpecialPO specialPage;

    @Parameters({"browserName", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {

        driver = getBrowserDriver(browser, url);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(driver);
    }


    //-- Viet tc vay thoi chu special page khong co product nao ca :)
    //---UI---//
    @Test
    public void TC_Verify_Filter_UI_Elements_Displayed() {

    }

    //---Function---//
    @Test
    public void TC_Verify_Default_Filter_Price() {
        // Access to category page
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";

        specialPage = menuCategoryPage.clickSpecialMenuItem();

        FilterComponent filter = specialPage.getFilterComponent();
        // Apply default filter price
        Assert.assertEquals(filter.getMinPriceFilterValues(), defaultMinPrice);
        Assert.assertEquals(filter.getMaxPriceFilterValues(), defaultMaxPrice);

        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, defaultMaxPrice, productListInPage);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            specialPage.clickNextPageButton();
        }
    }


    @Test(dataProvider = "priceRanges")
    public void TC_Verify_Filter_Price_Success() {
        String minPrice = "130";
        String maxPrice = "1500";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(minPrice, maxPrice, productListInPage);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            specialPage.clickNextPageButton();
        }
        filter.clearPriceFilter();
    }

    @Test
    public void TC_Verify_Price_Filter_Reset() {
        //After clear filter
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange("130", "1500");
        specialPage.waitForLoadingIconUndisplayed();
        filter.clearPriceFilter();
        specialPage.waitForLoadingIconUndisplayed();
        Assert.assertEquals(filter.getMinPriceFilterValues(), defaultMinPrice);
        Assert.assertEquals(filter.getMaxPriceFilterValues(), defaultMaxPrice);
    }

    //----Negative cases---//
    @Test
    public void TC_Verify_Price_Filter_Min_Less_Than_Default_Min() {
        // Result = product displayed from default min to selected max
        String defaultMinPrice = "122";
        String minPrice = "100";
        String maxPrice = "1500";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, maxPrice, productListInPage);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            specialPage.clickNextPageButton();
        }
        filter.clearPriceFilter();
    }

    @Test
    public void TC_Verify_Price_Filter_Max_Over_Default_Max() {
        // Result = product displayed from selected min to default max
        String defaultMaxPrice = "2000";
        String minPrice = "130";
        String maxPrice = "2500";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(minPrice, defaultMaxPrice, productListInPage);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            specialPage.clickNextPageButton();
        }
        filter.clearPriceFilter();
    }

    @Test
    public void TC_Verify_Price_Filter_Below_Default_Range() {
        // Result = No products are displayed "There are no products to list."
        String minPrice = "100";
        String maxPrice = "120";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        Assert.assertEquals(specialPage.getNoProductMessage(), "There are no products to list.");
        filter.clearPriceFilter();
    }

    @Test
    public void TC_Verify_Price_Filter_Min_Greater_Than_Default_Max() {
        // Result = No products are displayed "There are no products to list."
        String minPrice = "2100";
        String maxPrice = "2500";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        Assert.assertEquals(specialPage.getNoProductMessage(), "There are no products to list.");
        filter.clearPriceFilter();

    }

//    ---Format---
    @Test
    public void TC_Verify_Price_Filter_Blank_Price() {
        // Result = All products are displayed
        String minPrice = "";
        String maxPrice = "";
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, defaultMaxPrice, productListInPage);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.goToNextPage();
        }

    }

    @Test
    public void TC_Verify_Price_Filter_Invalid_Format() {
        // Cannot input special characters, letters, spaces
        String minPrice = "!@#$%";
        String maxPrice = "abcde";
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        specialPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, defaultMaxPrice, productListInPage);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            specialPage.clickNextPageButton();
        }

    }

    @Test
    public void TC_Verify_Price_Filter_Persistence_After_Navigation(){
        String minPrice = "130";
        String maxPrice = "2000";
        // Access to category page
        specialPage = menuCategoryPage.clickSpecialMenuItem();
        FilterComponent filter = specialPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);

        while (true) {
            System.out.println("----- Verify products in page -----");
            specialPage.waitForLoadingIconUndisplayed();
            List<ProductComponent> productListInPage = specialPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(minPrice, maxPrice, productListInPage);
            System.out.println(filter.getMinPriceFilterValues());
            System.out.println(filter.getMaxPriceFilterValues());
            Assert.assertEquals(filter.getMinPriceFilterValues(), minPrice);
            Assert.assertEquals(filter.getMaxPriceFilterValues(), maxPrice);
            if (specialPage.isNextPageButtonUndisplayed()) {
                break;
            }
            specialPage.clickNextPageButton();
        }

        filter.clearPriceFilter();
    }



    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

}
