package tests;

import utils.FilterHelper;
import commons.BaseTest;
import commons.FilterColor;
import components.FilterComponent;
import components.ProductComponent;
import dataProviders.FilterColorDataProvider;
import dataProviders.FilterDataProviders;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.MenuCategoryPO;
import pageObjects.PageGenerator;
import pageObjects.ProductCategoryPO;
import pageObjects.ProductDetailPO;

import java.util.List;

public class TC_Filter_ProductCategory extends BaseTest {
    protected WebDriver driver;
    protected ProductDetailPO productDetailPage;
    protected ProductCategoryPO productCategoryPage;
    protected MenuCategoryPO menuCategoryPage;

    @Parameters({ "env", "browserName", "browserVersion", "os", "osVersion", "url" })
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion,
            @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
         menuCategoryPage = PageGenerator.getMenuCategoryPage(getDriver(context));
        log.info(
                "Thread id beforeClass : " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }



    //---UI---//
    @Test
    public void TC_Verify_Filter_UI_Elements_Displayed() {

    }

    //---Function---//
    @Test(groups = {"regression", "filter"})
    public void TC_Verify_Default_Filter_Price() {
        // Access to category page
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply default filter price
        Assert.assertEquals(filter.getMinPriceFilterValues(), defaultMinPrice);
        Assert.assertEquals(filter.getMaxPriceFilterValues(), defaultMaxPrice);

        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, defaultMaxPrice, productListInPage);
            if (productCategoryPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
        }
    }


    @Test(dataProvider = "priceRanges", dataProviderClass = FilterDataProviders.class)
    public void TC_Verify_Filter_Price_Success(String minPrice, String maxPrice) {
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        productCategoryPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(minPrice, maxPrice, productListInPage);
            if (productCategoryPage.isNextPageNumberUndisplayed()) {
                break;
            }
            productCategoryPage.goToNextPage();
        }
        filter.clearPriceFilter();
    }


    @Test(groups = {"regression", "filter","debug"})
    public void TC_Verify_Price_Filter_Reset() {
        //After clear filter
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange("130", "1500");
        productCategoryPage.waitForLoadingIconUndisplayed();
        filter.clearPriceFilter();
        productCategoryPage.waitForLoadingIconUndisplayed();
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
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        productCategoryPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, maxPrice, productListInPage);
            if (productCategoryPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
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
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        productCategoryPage.waitForLoadingIconUndisplayed();
        while (true) {
            System.out.println("----- Verify products in page -----");
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(minPrice, defaultMaxPrice, productListInPage);
            if (productCategoryPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
        }
        filter.clearPriceFilter();
    }

    @Test
    public void TC_Verify_Price_Filter_Below_Default_Range() {
        // Result = No products are displayed "There are no products to list."
        String minPrice = "100";
        String maxPrice = "120";
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        productCategoryPage.waitForLoadingIconUndisplayed();
        Assert.assertEquals(productCategoryPage.getNoProductMessage(), "There are no products to list.");
        filter.clearPriceFilter();
    }

    @Test
    public void TC_Verify_Price_Filter_Min_Greater_Than_Default_Max() {
        // Result = No products are displayed "There are no products to list."
        String minPrice = "2100";
        String maxPrice = "2500";
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        productCategoryPage.waitForLoadingIconUndisplayed();
        Assert.assertEquals(productCategoryPage.getNoProductMessage(), "There are no products to list.");
        filter.clearPriceFilter();

    }
//
//    ---Format---
    @Test
    public void TC_Verify_Price_Filter_Blank_Price() {
        // Result = All products are displayed
        String minPrice = "";
        String maxPrice = "";
        String defaultMinPrice = "122";
        String defaultMaxPrice = "2000";
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);
        productCategoryPage.waitForLoadingIconUndisplayed();
        while (true) {
            
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, defaultMaxPrice, productListInPage);
            if (productCategoryPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
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
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);

        while (true) {
            
            productCategoryPage.waitForLoadingIconUndisplayed();
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(defaultMinPrice, defaultMaxPrice, productListInPage);
            if (productCategoryPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
        }

    }

    @Test
    public void TC_Verify_Price_Filter_Persistence_After_Navigation(){
        String minPrice = "130";
        String maxPrice = "2000";
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter price
        filter.filterByInputPriceRange(minPrice, maxPrice);

        while (true) {
            
            productCategoryPage.waitForLoadingIconUndisplayed();
            List<ProductComponent> productListInPage = productCategoryPage.getAllProductsInPage();
            FilterHelper.verifyFilterByPrice(minPrice, maxPrice, productListInPage);
            System.out.println(filter.getMinPriceFilterValues());
            System.out.println(filter.getMaxPriceFilterValues());
            Assert.assertEquals(filter.getMinPriceFilterValues(), minPrice);
            Assert.assertEquals(filter.getMaxPriceFilterValues(), maxPrice);
            if (productCategoryPage.isNextPageButtonUndisplayed()) {
                break;
            }
            productCategoryPage.clickNextPageButton();
        }

        filter.clearPriceFilter();
    }

//    @Test
//    public void TC_Verify_Filter_Search() {
//
//    }
//
//    @Test
//    public void TC_Verify_Filter_Availability() {
//
//    }
//
//    @Test
//    public void TC_Verify_Filter_Size() {
//
//    }


    //Sử dụng enum - chỉ test 1 màu
    @Test
    public void TC_Verify_Filter_Color_Enum() {
        // Access to category page
        String color = FilterColor.Blue.toString();
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter color
        filter.filterByColor(color);
        productCategoryPage.waitForLoadingIconUndisplayed();
        // Verify products in page
    }

    //Sử dụng Data Provider - test nhiều màu
   @Test(dataProvider="filterColorData", dataProviderClass = FilterColorDataProvider.class)
    public void TC_Verify_Filter_Color_DataProvider(String color) {
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter color
        filter.filterByColor(color);
        productCategoryPage.waitForLoadingIconUndisplayed();
        // Verify products in page
    }

    @Test(dataProvider="filterSizeData", dataProviderClass = FilterColorDataProvider.class)
    public void TC_Verify_Filter_Size_DataProvider(String id) {
        // Access to category page
        menuCategoryPage.hoverToMegaMenu();
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu("Apple");
        FilterComponent filter = productCategoryPage.getFilterComponent();
        // Apply filter size
        filter.filterBySize(id);
        productCategoryPage.waitForLoadingIconUndisplayed();
        // Verify products in page - if have expected data.
    }

 

}
