package pageObjects;

import org.openqa.selenium.WebDriver;

public class BlogPO extends MenuCategoryPO {


    public BlogPO(WebDriver driver) {
        super(driver);
    }

    public boolean isBlogTitleDisplayed() {
        waitForElementVisible(pageUIs.BlogUI.BLOG_TITLE);
        return isElementDisplayed(pageUIs.BlogUI.BLOG_TITLE);
    }
}
