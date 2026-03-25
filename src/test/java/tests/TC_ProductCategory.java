package tests;

import commons.BaseTest;
import dataProviders.APIDataProviders;
import dataProviders.DatabaseDataProviders;
import dataProviders.JsonDataProviders;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.MenuCategoryPO;
import pageObjects.PageGenerator;
import pageObjects.ProductCategoryPO;
import pageObjects.ProductDetailPO;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TC_ProductCategory extends BaseTest {
    protected WebDriver driver;
    protected ProductDetailPO productDetailPage;
    protected ProductCategoryPO productCategoryPage;
    protected MenuCategoryPO menuCategoryPage;

    @Parameters({"browserName", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {

        driver = getBrowserDriver(browser, url);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(driver);
    }


    // Kiểm tra danh sách product trong category
    // Kiểm tra hiển thị đúng 75 product trong category, mỗi product hiển thị các thông tin (chi tiết product)
    // Kiểm tra chức năng trong 1 category gồm có : filter (Price, Manufacturer, Search, Color, Availability, size, Discount, Rating), sort, paging, grid/list view
    // -----Kiểm tra chi tiết product: image, Name, price, description, add to cart, add to wishlist,quickview, add to compare
    // Kiểm tra chức năng add to cart, add to wishlist, add to compare của sản phẩm
    // câu hỏi:  làm sao kiểm tra toàn bộ 75 product
    // Kiểm tra info chi tiết sản phẩm -> TC product detail


    @Test
    public void TC_Verify_Product_List_Displayed_Map() {
        // Kiểm tra số lượng sản phẩm hiển thị đúng 75 sản phẩm - Số 75 có thể dựa vào FS hoặc API
        // Vào home -> Mega Menu -> Select 1 category
        // verify Category Title
        // TC này ở bên com.lambda.TC_MegaMenu rồi ạ
        menuCategoryPage.hoverToMegaMenu();
        // Get list of all child items and verify
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu(); // ie Apple, HTC, ...
        Assert.assertEquals(allChildItemNames.size(), 28);

        //Verify each child item page - demo web chỉ đúng 2 cái đầu tiên (Apple = 42, HTC = 8)
        Map<String, Integer> expectedProductQuantity = new java.util.HashMap<>();
        expectedProductQuantity.put("Apple", 42);
        expectedProductQuantity.put("HTC", 8);

        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            int totalProductInCategory = productCategoryPage.getProductQuantityInCategory();
            Assert.assertEquals(totalProductInCategory, expectedProductQuantity.get(childItem));
        }
    }
//
    @Test(dataProvider = "categoryProductQuantityFromJson", dataProviderClass = JsonDataProviders.class)
    public void TC_Verify_Product_List_Displayed_JSON(Map<String, Integer> data) {
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu(); // ie Apple, HTC, ...
        Assert.assertEquals(allChildItemNames.size(), 28);
        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            int totalProductInCategory = productCategoryPage.getProductQuantityInCategory();
            int expectedQuantity = data.get(childItem);
            Assert.assertEquals(totalProductInCategory, expectedQuantity);
        }
    }

    //        get expected quantity from database with data provider
    // @Test(dataProvider = "categoryProductQuantityFromDatabase", dataProviderClass = DatabaseDataProviders.class)
    public void TC_Verify_Product_List_Displayed_DB_DataProvider(Map<String, Integer> data) {
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu(); // ie Apple, HTC, ...
        Assert.assertEquals(allChildItemNames.size(), 28);
        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            int totalProductInCategory = productCategoryPage.getProductQuantityInCategory();
            int expectedQuantity = data.get(childItem);
            System.out.println("data từ db data provider là: " + expectedQuantity);
            Assert.assertEquals(totalProductInCategory, expectedQuantity);
        }
    }

    //get expected quantity from database không dùng data provider
    // @Test
    public void TC_Verify_Product_List_Displayed_DB() {
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu(); // ie Apple, HTC, ...
        Assert.assertEquals(allChildItemNames.size(), 28);
        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            int totalProductInCategory = productCategoryPage.getProductQuantityInCategory();
            int expectedQuantity = productCategoryPage.getAllProductByCategoryFromDBByCategoryName(childItem);
            System.out.println("data từ db là: " + expectedQuantity);
            Assert.assertEquals(totalProductInCategory, expectedQuantity);
        }
    }

    @Test(dataProvider = "categoryQuantityMapFromAPI", dataProviderClass = APIDataProviders.class)
    public void TC_Verify_Product_List_Displayed_API_DataProvider(Map<String, Integer> data) {
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu(); // ie Apple, HTC, ...
        Assert.assertEquals(allChildItemNames.size(), 28);
        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            int totalProductInCategory = productCategoryPage.getProductQuantityInCategory();
            int expectedQuantity = data.get(childItem);
            System.out.println("data từ api data provider là: " + expectedQuantity);
            Assert.assertEquals(totalProductInCategory, expectedQuantity);
        }
    }

    @Test(dataProvider = "categoryQuantityMapFromAPI", dataProviderClass = APIDataProviders.class)
    public void TC_Verify_Product_List_Displayed_API(Map<String, Integer> data) {
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu(); // ie Apple, HTC, ...
        Assert.assertEquals(allChildItemNames.size(), 28);
        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            int totalProductInCategory = productCategoryPage.getProductQuantityInCategory();
            int expectedQuantity = productCategoryPage.getAllProductByCategoryFromAPIByCategoryName(childItem);
            System.out.println("data từ api là: " + expectedQuantity);
            Assert.assertEquals(totalProductInCategory, expectedQuantity);
        }
    }

    // @Test
    public void TC_Verify_Product_Info() {
        //Kiểm tra hiện thị thông tin của all sản phẩm
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu();
        for(int i = 0; i < 2; i++){
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            while(true){
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                System.out.println("Number of products in this page: " + productsInPage);
                for(int j=1; j <= productsInPage; j++){
                    String productUrl = productCategoryPage.getProductUrlFromUI(j);
                    int productId = Integer.parseInt(productUrl.split("product_id=")[1].split("&")[0]);
                    String productName = productCategoryPage.getProductNameFromUI(j);
                    String productPrice = productCategoryPage.getProductPriceFromUI(j);
                    Product product = productCategoryPage.getProductFromDB(productId);
                    String productNameFromDB = product.getName();
                    String productPriceFromDB = String.valueOf(product.getPrice());
                    System.out.println("Product from UI: " + productName + " - " + productPrice);
                    System.out.println("Product from DB: " + productNameFromDB + " - " + productPriceFromDB);
                    Assert.assertEquals(productName,productNameFromDB);


                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }

    @Test
    public void TC_Verify_Navigation_To_Product_Detail_Page() {
        // Kiểm tra điều hướng đến trang chi tiết sản phẩm khi click vào tên sản phẩm hoặc hình ảnh sản phẩm
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu();
        for(int i = 0; i < 2; i++){
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            while(true){
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                System.out.println("Number of products in this page: " + productsInPage);
                for(int j=1; j <= productsInPage; j++){
                    String productName = productCategoryPage.getProductNameFromUI(j);
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    Assert.assertEquals(productDetailPage.getProductPageTitleText(), productName);
                     driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);

                }
                if (productCategoryPage.isNextPageButtonUndisplayed()) {
                    break;
                }
                productCategoryPage.clickNextPageButton();
            }
        }
    }





    @Test
    public void TC_Verify_Sort_Default() {
        menuCategoryPage.hoverToMegaMenu();
        String childItem = "Apple"; // Apple, HTC
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
        Assert.assertTrue(productCategoryPage.isSortByDisplayed("Default"));
    }

    @Test
    public void TC_Verify_Sort_Bestseller() {
        menuCategoryPage.hoverToMegaMenu();
        String childItem = "Apple"; // Apple, HTC
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
        Assert.assertTrue(productCategoryPage.isSortByDisplayed("Default"));
        productCategoryPage.selectSortBy("Best sellers");
        Assert.assertTrue(productCategoryPage.isSortByDisplayed("Best sellers"));
    }

    @Test
    public void TC_Verify_Sort_AZ() {
        menuCategoryPage.hoverToMegaMenu();
        String childItem = "Apple"; // Apple, HTC
        productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
        Assert.assertTrue(productCategoryPage.isSortByDisplayed("Default"));
        // Lấy tất cả product name trong category và tự sắp xếp
        List<String> allProductNames = productCategoryPage.getAllProductNameInCategory();
        Collections.sort(allProductNames,String.CASE_INSENSITIVE_ORDER);

        // Chọn sắp xếp theo Name (A - Z) và kiểm tra kết quả bằng cách so sánh với list đã sắp xếp ở bước trên - lưu ý case sensitive
        productCategoryPage.selectSortBy("Name (A - Z)");
        Assert.assertTrue(productCategoryPage.isSortByDisplayed("Name (A - Z)"));
        List<String> allProductNamesAfterSelectSort = productCategoryPage.getAllProductNameInCategory();
        Assert.assertEquals(allProductNamesAfterSelectSort, allProductNames);
    }

    @Test
    public void TC_Verify_Show_Function() {

    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

}
