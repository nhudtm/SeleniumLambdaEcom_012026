package tests;

import commons.BaseTest;
import models.Product;
import models.ProductMedia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.MenuCategoryPO;
import pageObjects.PageGenerator;
import pageObjects.ProductCategoryPO;
import pageObjects.ProductDetailPO;

import java.util.ArrayList;
import java.util.List;

public class CrawData extends BaseTest {
    protected WebDriver driver;
    protected ProductDetailPO productDetailPage;
    protected ProductCategoryPO productCategoryPage;
    protected MenuCategoryPO menuCategoryPage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {

        driver = getBrowserDriver(browser, url);
        menuCategoryPage = PageGenerator.getMenuCategoryPage(driver);
    }

    @Test
    public void Craw_data() {
        // Truy cập vào Mega Menu
        menuCategoryPage.hoverToMegaMenu();
        List<String> allChildItemNames = menuCategoryPage.getAllChildItemsInMegaMenu();
        List<Product> productList = new ArrayList<Product>();
        List<ProductMedia> productMediaList = new ArrayList<ProductMedia>();
        // Duyệt qua từng category đầu tiên để lấy dữ liệu
        for (int i = 0; i < 2; i++) {
            menuCategoryPage.hoverToMegaMenu();
            String childItem = allChildItemNames.get(i); // Apple, HTC
            productCategoryPage = menuCategoryPage.clickChildItemInMegaMenu(childItem);
            Assert.assertTrue(productCategoryPage.isProductCategoryTitleDisplayed(childItem));
            while (true) {
                int productsInPage = productCategoryPage.getProductQuantityInPage();
                System.out.println("Number of products in this page: " + productsInPage);
                // Duyệt qua từng sản phẩm trong trang để lấy dữ liệu
                for (int j = 1; j <= productsInPage; j++) {
                    System.out.println("-------------------");
                    System.out.println("Product thứ " + j);
                    String productName = productCategoryPage.getProductNameFromUI(j);
                    productDetailPage = productCategoryPage.clickProductToViewDetail(j);
                    String productUrl = driver.getCurrentUrl();
                    String productId = productUrl.split("product_id=")[1].split("&")[0];
                    String productPrice = productDetailPage.getProductPriceFromUI();
                    String productAvailability = productDetailPage.getProductAvailabilityFromUI();
                    String productDescription = productDetailPage.getProductDescriptionFromUI();
                    String productMainImageSrc = productDetailPage.getMainImageSrc();
                    boolean productVideoHrefNotAvalable = productDetailPage.isProductVideoNotAvailable();
                    System.out.println(productVideoHrefNotAvalable);
                    List<WebElement> productMediaElement = productDetailPage.getAllMediaSrc();
                    Product product = new Product();
                    product.setProductId(Integer.parseInt(productId));
                    product.setName(productName);
                    product.setPrice(Float.parseFloat(productPrice.replaceAll("[^\\d.]", "")));
                    product.setActive(productAvailability.contains("In Stock"));
                    product.setDescription(productDescription);
                    System.out.println("Product: " + product.getName() + " - " + product.getPrice() + " - " + product.isActive() + " - " + product.getDescription());
                    productList.add(product);

                    // Lấy dữ liệu Product Media
                    for (WebElement mediaElement : productMediaElement) {
                        ProductMedia productMedia = new ProductMedia();
                        productMedia.setProductId(Integer.parseInt(productId));
                        String elementHref = mediaElement.getDomAttribute("href");
                        if (elementHref != null && elementHref.contains("youtube.com")) {
                            productMedia.setMediaUrl(elementHref);
                            productMedia.setMediaType("video");
                        } else {
                            String imageSrc = mediaElement.findElement(By.tagName("img")).getDomAttribute("src");
                            productMedia.setMediaUrl(imageSrc);
                            productMedia.setMediaType("image");
                            // Kiểm tra main image
                            if (imageSrc.equals(productMainImageSrc)) {
                                productMedia.setMain(true);
                            } else {
                                productMedia.setMain(false);
                            }
                        }
                        productMediaList.add(productMedia);
                        System.out.println("Product Media: " + productMedia.getProductId() + " - " + productMedia.getMediaType() + " - " + productMedia.getMediaUrl() + " - " + productMedia.isMain());
                    }
                    driver.navigate().back();
                    productCategoryPage = PageGenerator.getProductCategoryPage(driver);
                }
                if (productCategoryPage.isNextPageNumberUndisplayed()) {
                    break;
                }
                productCategoryPage.goToNextPage();
            }
        }

// Ghi dữ liệu sản phẩm vào file CSV
        writeProductToCSV(productList);

        writeProductMediaToCSV(productMediaList);
    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

}
