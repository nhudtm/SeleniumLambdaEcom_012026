package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.SearchUI;

public class SearchPO extends MenuCategoryPO {
   

    public SearchPO(WebDriver driver) {
        super(driver);
    }

    public String getSearchPageTitleText() {
        waitForElementVisible(  SearchUI.SEARCH_PAGE_TITLE);
        return getElementText(  SearchUI.SEARCH_PAGE_TITLE);
    }

    public String getTotalProductAtPagination() {
        waitForElementVisible(  SearchUI.PAGINATION_TOTAL_PRODUCT);
        return getElementText(  SearchUI.PAGINATION_TOTAL_PRODUCT);

    }

    public String getNoProductMessage() {
        waitForElementVisible(  SearchUI.NO_PRODUCT_MESSAGE);
        return getElementText(  SearchUI.NO_PRODUCT_MESSAGE);
    }

    public boolean isListView() {
        waitForElementVisible(  SearchUI.PRODUCT_ITEM);
        System.out.println(getElementDOMAttribute(  SearchUI.PRODUCT_ITEM, "class"));
        return getElementDOMAttribute(  SearchUI.PRODUCT_ITEM, "class").contains("product-list");
    }

    public boolean isGridView(){

        waitForElementVisible(  SearchUI.PRODUCT_ITEM);
        System.out.println(getElementDOMAttribute(  SearchUI.PRODUCT_ITEM, "class"));
        return getElementDOMAttribute(  SearchUI.PRODUCT_ITEM, "class").contains("product-grid");
    }

    public void clickToGridView() {
        waitForElementClickable(  SearchUI.GRID_VIEW_ICON);
        clickToElement(  SearchUI.GRID_VIEW_ICON);
    }

    public void clickToListView() {
        waitForElementClickable(  SearchUI.LIST_VIEW_ICON);
        clickToElement(  SearchUI.LIST_VIEW_ICON);
    }
}
