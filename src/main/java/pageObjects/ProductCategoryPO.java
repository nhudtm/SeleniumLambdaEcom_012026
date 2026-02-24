package pageObjects;

import utils.CategoryAPIHelper;
import utils.DBUtils;
import components.ProductComponent;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.ProductCategoryUI;

import java.util.List;
import java.util.Map;

public class ProductCategoryPO extends MenuCategoryPO {


    public ProductCategoryPO(WebDriver driver) {
        super(driver);

    }

    public boolean isProductCategoryTitleDisplayed(String productDetailTitle) {
        waitForElementVisible(  ProductCategoryUI.DYNAMIC_CATEGORY_TITLE, productDetailTitle);
        sleepInSecond(2);
        return isElementDisplayed(  ProductCategoryUI.DYNAMIC_CATEGORY_TITLE, productDetailTitle);
    }

    public int getProductQuantityInCategory() {
        int totalProductInCategory = 0;
        while(true) {
            waitForElementVisible(  ProductCategoryUI.PRODUCT_ITEM);
            List<WebElement> listOfProduct = getElementList(  ProductCategoryUI.PRODUCT_ITEM);
            totalProductInCategory += listOfProduct.size();
            System.out.println("Total product in this page = " + listOfProduct.size());
            if (isElementUndisplayed(  ProductCategoryUI.NEXT_PAGE)) {
                break;
            }
            scrollToElement(  ProductCategoryUI.NEXT_PAGE);
            clickToElement(  ProductCategoryUI.NEXT_PAGE);
        }
        System.out.println("Total product in category = " + totalProductInCategory);
        return totalProductInCategory;
    }

    public List<String> getAllProductNameInCategory() {
        List<String> allProductNames = new java.util.ArrayList<>();
        while(true) {
            waitForElementVisible(  ProductCategoryUI.ALL_PRODUCT_NAME);
            List<WebElement> listOfProductName = getElementList(  ProductCategoryUI.ALL_PRODUCT_NAME);
            for (WebElement product : listOfProductName) {
                String productName = product.getText();
                allProductNames.add(productName);
            }
            if (isElementUndisplayed(  ProductCategoryUI.NEXT_PAGE)) {
                break;
            }
            scrollToElement(  ProductCategoryUI.NEXT_PAGE);
            clickToElement(  ProductCategoryUI.NEXT_PAGE);
        }
        return allProductNames;
    }


    public int getAllProductByCategoryFromDBByCategoryName(String categoryName) {
        return DBUtils.getCategoryProductQuantityFromDBByCategoryName(categoryName);

    }

    public int getAllProductByCategoryFromAPIByCategoryName(String categoryName) {
        Map<String, Integer> categories = CategoryAPIHelper.getCategoryQuantityMap();
        if(categories.containsKey(categoryName)){
            return categories.get(categoryName);
        }
        return 0;
    }

    public int getProductQuantityInPage() {
        waitForElementVisible(  ProductCategoryUI.PRODUCT_ITEM);
        List<WebElement> listOfProduct = getElementList(  ProductCategoryUI.PRODUCT_ITEM);
        return listOfProduct.size();
    }

    public Product getProductFromDB(int productId) {
        return DBUtils.getProductInfoById(productId);
    }


    public String getProductNameFromUI(int index) {
        waitForElementVisible(  ProductCategoryUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(index));
        return getElementText(  ProductCategoryUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(index));
    }

    public boolean isNextPageNumberUndisplayed() {
        return isElementUndisplayed(  ProductCategoryUI.NEXT_PAGE);
    }


    public void goToNextPage() {
        scrollToElement(  ProductCategoryUI.NEXT_PAGE);
        clickToElement(  ProductCategoryUI.NEXT_PAGE);
    }

    public String getProductPriceFromUI(int index) {
        waitForElementVisible(  ProductCategoryUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(index));
        return getElementText(  ProductCategoryUI.DYNAMIC_PRODUCT_PRICE_BY_INDEX, String.valueOf(index));
    }

    public ProductDetailPO clickProductToViewDetail(int index) {
        waitForElementVisible(  ProductCategoryUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(index));
        clickToElement(  ProductCategoryUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(index));
        return PageGenerator.getProductDetailPage(driver);
    }

    public String getProductUrlFromUI(int j) {
        waitForElementVisible(  ProductCategoryUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, String.valueOf(j));
        return getElementDOMAttribute(  ProductCategoryUI.DYNAMIC_PRODUCT_NAME_BY_INDEX, "href", String.valueOf(j));
    }

    public List<ProductComponent> getAllProductsInPage() {
        waitForElementVisible(  ProductCategoryUI.PRODUCT_ITEM);
        List<WebElement> productElements = getElementList(  ProductCategoryUI.PRODUCT_ITEM);
        return productElements.stream().map(productElement -> new ProductComponent( driver, productElement)).toList();
    }


    public String getNoProductMessage() {
        waitForElementVisible(  ProductCategoryUI.NO_PRODUCT_MESSAGE);
        return getElementText(  ProductCategoryUI.NO_PRODUCT_MESSAGE);
    }


    public String getActivePageNumber() {
        waitForElementVisible(  ProductCategoryUI.ACTIVE_PAGE);
        return getElementText(  ProductCategoryUI.ACTIVE_PAGE);
    }

    public void clickNextPageButton() {
        scrollToElement(  ProductCategoryUI.NEXT_PAGE_BUTTON);
        clickToElement(  ProductCategoryUI.NEXT_PAGE_BUTTON);
    }

    public boolean isFirstPageButtonDisplayed() {
        waitForElementVisible(  ProductCategoryUI.FIRST_PAGE_BUTTON);
        return isElementDisplayed(  ProductCategoryUI.FIRST_PAGE_BUTTON);
    }

    public boolean isFirstPageButtonUndisplayed() {
        return isElementUndisplayed(  ProductCategoryUI.FIRST_PAGE_BUTTON);
    }

    public boolean isPreviousPageButtonDisplayed() {
        return isElementDisplayed(  ProductCategoryUI.PREVIOUS_PAGE_BUTTON);
    }

    public boolean isPreviousPageButtonUndisplayed() {
        return isElementUndisplayed(  ProductCategoryUI.PREVIOUS_PAGE_BUTTON);
    }

    public boolean isNextPageButtonDisplayed() {
        waitForElementVisible(  ProductCategoryUI.NEXT_PAGE_BUTTON);
        return isElementDisplayed(  ProductCategoryUI.NEXT_PAGE_BUTTON);
    }

    public boolean isNextPageButtonUndisplayed() {
        return isElementUndisplayed(  ProductCategoryUI.NEXT_PAGE_BUTTON);
    }

    public boolean isLastPageButtonDisplayed() {
        waitForElementVisible(  ProductCategoryUI.LAST_PAGE_BUTTON);
        return isElementDisplayed(  ProductCategoryUI.LAST_PAGE_BUTTON);
    }

    public boolean isLastPageButtonUndisplayed() {
        return isElementUndisplayed(  ProductCategoryUI.LAST_PAGE_BUTTON);
    }

    public void goToPageNumber(String number) {
        waitForElementVisible( ProductCategoryUI.DYNAMIC_PAGE_NUMBER_BUTTON, number);
        clickToElement( ProductCategoryUI.DYNAMIC_PAGE_NUMBER_BUTTON, number);
    }

    public void clickPreviousPageButton() {
        scrollToElement(  ProductCategoryUI.PREVIOUS_PAGE_BUTTON);
        clickToElement(  ProductCategoryUI.PREVIOUS_PAGE_BUTTON);
    }

    public void clickLastPageButton() {
        scrollToElement(  ProductCategoryUI.LAST_PAGE_BUTTON);
        clickToElement(  ProductCategoryUI.LAST_PAGE_BUTTON);
    }

    public void clickFirstPageButton() {
        scrollToElement(  ProductCategoryUI.FIRST_PAGE_BUTTON);
        clickToElement(  ProductCategoryUI.FIRST_PAGE_BUTTON);
    }

    public boolean isActivePageNumberHighlighted() {
        String backgroundColor = getElementCssValue(  ProductCategoryUI.ACTIVE_PAGE, "background-color");
        System.out.println("Background color of active page number: " + backgroundColor);
        return backgroundColor.equals("rgba(14, 186, 197, 1)");
    }

    public String getPaginationText() {
        waitForElementVisible(  ProductCategoryUI.PAGINATION_TEXT);
        return getElementText(  ProductCategoryUI.PAGINATION_TEXT);
    }

    public boolean isSortByDisplayed(String defaultOption) {
        waitForElementVisible(  ProductCategoryUI.SORT_BY_DROPDOWN);
        String selectedOption = getSelectedItemInDropdown(  ProductCategoryUI.SORT_BY_DROPDOWN);
        return selectedOption.equals(defaultOption);
    }

    public void selectSortBy(String bestSellers) {
        waitForElementVisible(  ProductCategoryUI.SORT_BY_DROPDOWN);
        selectItemInDropdown(  ProductCategoryUI.SORT_BY_DROPDOWN, bestSellers);
    }
}
