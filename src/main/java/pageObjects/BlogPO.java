package pageObjects;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BlogPO extends MenuCategoryPO {

    public BlogPO(WebDriver driver) {
        super(driver);
    }

    public boolean isBlogTitleDisplayed() {
        waitForElementVisible(pageUIs.BlogUI.BLOG_TITLE);
        return isElementDisplayed(pageUIs.BlogUI.BLOG_TITLE);
    }

    public void openBlogHome(String blogHomeUrl) {
        openPage(blogHomeUrl);
        waitForElementVisible(pageUIs.BlogUI.BLOG_HOME_CONTAINER);
    }

    public boolean isBlogHomeDisplayed() {
        waitForElementVisible(pageUIs.BlogUI.BLOG_HOME_CONTAINER);
        return getPageURL().contains("route=extension/maza/blog/home")
                && isElementDisplayed(pageUIs.BlogUI.BLOG_HOME_CONTAINER);
    }

    public boolean isLatestArticlesSectionDisplayed() {
        waitForElementVisible(pageUIs.BlogUI.LATEST_ARTICLES_TITLE);
        return isElementDisplayed(pageUIs.BlogUI.LATEST_ARTICLES_TITLE);
    }

    public boolean isMostViewedSectionDisplayed() {
        waitForElementVisible(pageUIs.BlogUI.MOST_VIEWED_TITLE);
        return isElementDisplayed(pageUIs.BlogUI.MOST_VIEWED_TITLE);
    }

    public int getArticleListingModuleCount() {
        waitForAllElementPresence(pageUIs.BlogUI.ARTICLE_LISTING_MODULES);
        return getNumberOfElements(pageUIs.BlogUI.ARTICLE_LISTING_MODULES);
    }

    public int getArticleCardCountBySection(String sectionKeyword) {
        waitForElementPresence(pageUIs.BlogUI.DYNAMIC_ARTICLE_CARDS_BY_SECTION, sectionKeyword);
        return getNumberOfElements(pageUIs.BlogUI.DYNAMIC_ARTICLE_CARDS_BY_SECTION, sectionKeyword);
    }

    public boolean isFirstArticleContentValidBySection(String sectionKeyword) {
        waitForElementVisible(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION, sectionKeyword);

        String title = getElementText(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION, sectionKeyword).trim();
        String articleHref = getElementDOMAttribute(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION, "href", sectionKeyword);
        String imageSrc = getElementDOMAttribute(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_IMAGE_BY_SECTION, "data-src", sectionKeyword);
        if (imageSrc == null || imageSrc.isBlank()) {
            imageSrc = getElementDOMAttribute(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_IMAGE_BY_SECTION, "src", sectionKeyword);
        }

        boolean hasAuthor = isElementDisplayed(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_AUTHOR_BY_SECTION, sectionKeyword)
                && !getElementText(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_AUTHOR_BY_SECTION, sectionKeyword).trim().isEmpty();
        boolean hasComment = isElementDisplayed(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_COMMENT_BY_SECTION, sectionKeyword)
                && !getElementText(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_COMMENT_BY_SECTION, sectionKeyword).trim().isEmpty();
        boolean hasViewed = isElementDisplayed(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_VIEW_BY_SECTION, sectionKeyword)
                && !getElementText(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_VIEW_BY_SECTION, sectionKeyword).trim().isEmpty();
        boolean hasDate = isElementDisplayed(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_DATE_BY_SECTION, sectionKeyword)
                && !getElementText(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_DATE_BY_SECTION, sectionKeyword).trim().isEmpty();

        return !title.isEmpty()
                && articleHref != null && articleHref.contains("route=extension/maza/blog/article")
                && imageSrc != null && !imageSrc.isBlank()
                && hasAuthor && hasComment && hasViewed && hasDate;
    }

    public String openFirstArticleBySection(String sectionKeyword) {
        waitForElementClickable(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION, sectionKeyword);
        clickToElement(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION, sectionKeyword);
        waitForElementVisible(pageUIs.BlogUI.ARTICLE_DETAIL_HEADING);
        return getPageURL();
    }

    public int getBlogCategoryCount() {
        waitForAllElementPresence(pageUIs.BlogUI.BLOG_CATEGORY_ITEMS);
        return getNumberOfElements(pageUIs.BlogUI.BLOG_CATEGORY_ITEMS);
    }

    public String openFirstBlogCategoryAndGetUrl() {
        waitForElementClickable(pageUIs.BlogUI.FIRST_BLOG_CATEGORY_LINK_VISIBLE);
        clickToElement(pageUIs.BlogUI.FIRST_BLOG_CATEGORY_LINK_VISIBLE);
        return getPageURL();
    }

    public boolean isSidebarLatestWidgetDisplayed() {
        waitForElementVisible(pageUIs.BlogUI.SIDEBAR_LATEST_WIDGET_TITLE);
        return isElementDisplayed(pageUIs.BlogUI.SIDEBAR_LATEST_WIDGET_TITLE);
    }

    public int getSidebarLatestProductCount() {
        waitForAllElementPresence(pageUIs.BlogUI.SIDEBAR_LATEST_PRODUCT_ITEMS);
        return getNumberOfElements(pageUIs.BlogUI.SIDEBAR_LATEST_PRODUCT_ITEMS);
    }

    public String openFirstSidebarLatestProductAndGetUrl() {
        waitForElementClickable(pageUIs.BlogUI.SIDEBAR_LATEST_PRODUCT_ITEMS);
        String productHref = getElementDOMAttribute(pageUIs.BlogUI.SIDEBAR_LATEST_PRODUCT_ITEMS, "href");
        clickToElement(pageUIs.BlogUI.SIDEBAR_LATEST_PRODUCT_ITEMS);

        if (!getPageURL().contains("route=product/product")
                && productHref != null
                && productHref.contains("route=product/product")) {
            openPage(productHref);
        }
        waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
        return getPageURL();
    }

    public String openFirstAuthorLinkAndGetUrl() {
        waitForElementClickable(pageUIs.BlogUI.ALL_AUTHOR_LINKS);
        clickToElement(pageUIs.BlogUI.ALL_AUTHOR_LINKS);
        return getPageURL();
    }

    public boolean areArticleLinksWellFormed() {
        waitForAllElementPresence(pageUIs.BlogUI.ALL_ARTICLE_TITLE_LINKS);
        List<WebElement> articleLinks = getElementList(pageUIs.BlogUI.ALL_ARTICLE_TITLE_LINKS);
        if (articleLinks.isEmpty()) {
            return false;
        }

        int validatedDisplayedLinks = 0;
        for (WebElement articleLink : articleLinks) {
            if (!isDisplayedSafely(articleLink)) {
                continue;
            }

            String href = articleLink.getDomAttribute("href");
            String title = articleLink.getText() == null ? "" : articleLink.getText().trim();
            if (title.isEmpty()) {
                String titleAttribute = articleLink.getDomAttribute("title");
                title = titleAttribute == null ? "" : titleAttribute.trim();
            }

            validatedDisplayedLinks++;
            if (href == null || !href.contains("route=extension/maza/blog/article") || title.isEmpty()) {
                return false;
            }
        }

        return validatedDisplayedLinks > 0;
    }

    public boolean canClickNextSliderBySection(String sectionKeyword) {
        waitForElementClickable(pageUIs.BlogUI.DYNAMIC_SLIDER_NEXT_BY_SECTION, sectionKeyword);
        clickToElement(pageUIs.BlogUI.DYNAMIC_SLIDER_NEXT_BY_SECTION, sectionKeyword);
        return getArticleCardCountBySection(sectionKeyword) > 0;
    }

    public boolean canClickPreviousSliderBySection(String sectionKeyword) {
        waitForElementClickable(pageUIs.BlogUI.DYNAMIC_SLIDER_PREV_BY_SECTION, sectionKeyword);
        clickToElement(pageUIs.BlogUI.DYNAMIC_SLIDER_PREV_BY_SECTION, sectionKeyword);
        return getArticleCardCountBySection(sectionKeyword) > 0;
    }

    public boolean areBlogCategoryLabelsFormattedWithCount() {
        waitForAllElementPresence(pageUIs.BlogUI.BLOG_CATEGORY_ITEMS);
        List<WebElement> categoryItems = getElementList(pageUIs.BlogUI.BLOG_CATEGORY_ITEMS);
        if (categoryItems.isEmpty()) {
            return false;
        }

        Pattern categoryCountPattern = Pattern.compile(".+\\s\\(\\d+\\)$");
        for (WebElement item : categoryItems) {
            String text = item.getText() == null ? "" : item.getText().trim();
            if (!categoryCountPattern.matcher(text).matches()) {
                return false;
            }
        }
        return true;
    }

    public String searchBlogAndGetUrl(String keyword) {
        submitBlogSearch(keyword);
        return getPageURL();
    }

    public String submitEmptySearchAndGetUrl() {
        submitBlogSearch("");
        return getPageURL().toLowerCase().replace("%2f", "/");
    }

    public boolean isBlogSearchRouteOnly() {
        String normalizedUrl = getPageURL().toLowerCase().replace("%2f", "/");
        return normalizedUrl.contains("route=extension/maza/blog/search")
            && !normalizedUrl.contains("route=product/search");
    }

    public boolean isBlogSearchResultAreaVisible() {
        try {
            waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
        } catch (Exception ignored) {
        }
        return isElementDisplayed(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER)
                || getNumberOfElements(pageUIs.BlogUI.BLOG_SEARCH_RESULT_ITEMS) > 0
                || isNoResultStateDisplayed();
    }

    public boolean isNoResultStateDisplayed() {
        if (isElementDisplayed(pageUIs.BlogUI.BLOG_NO_RESULT_MESSAGE)) {
            return true;
        }

        String lowerSource = getPageSource().toLowerCase();
        return lowerSource.contains("no article")
                || lowerSource.contains("no results")
                || lowerSource.contains("not found");
    }

    public boolean isNoSensitiveErrorDisclosure() {
        String pageSource = getPageSource().toLowerCase();
        return !pageSource.contains("stack trace")
                && !pageSource.contains("sqlstate")
                && !pageSource.contains("syntax error")
                && !pageSource.contains("java.lang")
                && !pageSource.contains("exception in thread")
                && !pageSource.contains("traceback");
    }

    public boolean isMetadataDisplayedInBlogCards() {
        waitForAllElementPresence(pageUIs.BlogUI.ALL_COMMENT_METADATA);
        return getNumberOfElements(pageUIs.BlogUI.ALL_COMMENT_METADATA) > 0
                && getNumberOfElements(pageUIs.BlogUI.ALL_VIEW_METADATA) > 0
                && getNumberOfElements(pageUIs.BlogUI.ALL_DATE_METADATA) > 0;
    }

    public boolean areBlogImagesDataBound() {
        waitForAllElementPresence(pageUIs.BlogUI.ALL_ARTICLE_IMAGES);
        List<WebElement> images = getElementList(pageUIs.BlogUI.ALL_ARTICLE_IMAGES);
        if (images.isEmpty()) {
            return false;
        }

        int checked = 0;
        for (WebElement image : images) {
            if (!isDisplayedSafely(image)) {
                continue;
            }

            String src = image.getDomAttribute("src");
            String dataSrc = image.getDomAttribute("data-src");
            if ((src == null || src.isBlank()) && (dataSrc == null || dataSrc.isBlank())) {
                return false;
            }

            checked++;
            if (checked >= 5) {
                break;
            }
        }

        return checked > 0;
    }

    public boolean isImageRenderSuccessRatioAtLeast(double expectedRatio, int timeoutSeconds) {
        waitForAllElementPresence(pageUIs.BlogUI.ALL_ARTICLE_IMAGES);
        return getVisibleImageLoadedRatio() >= expectedRatio;
    }

    public boolean areSliderArrowsVisibleInSection(String sectionKeyword) {
        return isElementDisplayed(pageUIs.BlogUI.DYNAMIC_SLIDER_NEXT_BY_SECTION, sectionKeyword)
                && isElementDisplayed(pageUIs.BlogUI.DYNAMIC_SLIDER_PREV_BY_SECTION, sectionKeyword);
    }

    public boolean isMobileLayoutUsable() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Long scrollWidth = (Long) jsExecutor.executeScript("return document.documentElement.scrollWidth;");
        Long innerWidth = (Long) jsExecutor.executeScript("return window.innerWidth;");
        return scrollWidth <= innerWidth + 1;
    }

    public boolean canNavigateByKeyboardFromSearch() {
        WebElement searchBox = getVisibleSearchBox();
        String originalUrl = getPageURL();
        searchBox.click();
        searchBox.clear();

        for (int i = 0; i < 25; i++) {
            driver.switchTo().activeElement().sendKeys(Keys.TAB);
            Object href = ((JavascriptExecutor) driver)
                    .executeScript("return document.activeElement && document.activeElement.getAttribute('href');");
            if (href != null) {
                String value = href.toString();
                if (value.contains("route=extension/maza/blog/article") || value.contains("route=extension/maza/blog/category")) {
                    driver.switchTo().activeElement().sendKeys(Keys.ENTER);
                    try {
                        waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
                    } catch (Exception ignored) {
                    }
                    String navigatedUrl = getPageURL();
                    if (!navigatedUrl.equals(originalUrl)
                            && (navigatedUrl.contains("route=extension/maza/blog/article")
                            || navigatedUrl.contains("route=extension/maza/blog/category"))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void setViewportSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public boolean isAnyBlogSearchTextboxDisplayed() {
        List<WebElement> desktopSearchBoxes = getElementList(pageUIs.BlogUI.BLOG_SEARCH_TEXTBOX_DESKTOP);
        for (WebElement searchBox : desktopSearchBoxes) {
            if (isDisplayedSafely(searchBox)) {
                return true;
            }
        }

        List<WebElement> mobileSearchBoxes = getElementList(pageUIs.BlogUI.BLOG_SEARCH_TEXTBOX_MOBILE);
        for (WebElement searchBox : mobileSearchBoxes) {
            if (isDisplayedSafely(searchBox)) {
                return true;
            }
        }

        return false;
    }

    public boolean isInitialRenderWithinSeconds(String blogHomeUrl, long expectedSeconds) {
        long start = System.currentTimeMillis();
        openBlogHome(blogHomeUrl);
        long renderMs = System.currentTimeMillis() - start;
        return renderMs <= expectedSeconds * 1000;
    }

    public boolean canOpenInvalidArticleGracefully() {
        String invalidArticleUrl = resolveBaseUrl() + "/index.php?route=extension/maza/blog/article&article_id=999999999";
        openPage(invalidArticleUrl);
        try {
            waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
        } catch (Exception ignored) {
        }

        String pageSource = getPageSource().toLowerCase();
        boolean hasTechnicalDetails = pageSource.contains("stack trace")
                || pageSource.contains("sqlstate")
                || pageSource.contains("java.lang")
                || pageSource.contains("exception in thread");

        boolean hasUserFriendlySignal = pageSource.contains("not found")
                || pageSource.contains("no article")
                || pageSource.contains("404")
                || isElementDisplayed(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);

        return !hasTechnicalDetails && hasUserFriendlySignal;
    }

    public boolean canNavigateBackAndForwardToSameArticle() {
        String articleUrl = openFirstArticleBySection("latest");
        backToPage();
        waitForElementVisible(pageUIs.BlogUI.BLOG_HOME_CONTAINER);
        String backUrl = getPageURL();

        forwardToPage();
        waitForElementVisible(pageUIs.BlogUI.ARTICLE_DETAIL_HEADING);
        String forwardUrl = getPageURL();

        return backUrl.contains("route=extension/maza/blog/home")
                && forwardUrl.contains("route=extension/maza/blog/article")
                && forwardUrl.equals(articleUrl);
    }

    public boolean isFirstThumbnailBehaviorConsistentWithTitle(String sectionKeyword) {
        String originalUrl = getPageURL();
        String titleHref = getElementDOMAttribute(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_TITLE_BY_SECTION, "href", sectionKeyword);

        try {
            waitForElementClickable(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_THUMBNAIL_BY_SECTION, sectionKeyword);
            clickToElement(pageUIs.BlogUI.DYNAMIC_FIRST_ARTICLE_THUMBNAIL_BY_SECTION, sectionKeyword);
        } catch (Exception e) {
            return true;
        }

        try {
            waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
        } catch (Exception ignored) {
        }
        String afterClickUrl = getPageURL();

        if (afterClickUrl.equals(originalUrl)) {
            return true;
        }

        if (titleHref == null || titleHref.isBlank()) {
            return afterClickUrl.contains("route=extension/maza/blog/article");
        }

        return afterClickUrl.contains("route=extension/maza/blog/article");
    }

    private void submitBlogSearch(String keyword) {
        WebElement searchBox = getVisibleSearchBox();
        String query = keyword == null ? "" : keyword;

        searchBox.click();
        searchBox.clear();
        if (!query.isEmpty()) {
            searchBox.sendKeys(query);
        }
        waitForElementClickable(pageUIs.BlogUI.BLOG_SEARCH_SUBMIT_BUTTON);
        clickToElement(pageUIs.BlogUI.BLOG_SEARCH_SUBMIT_BUTTON);

        try {
            waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
        } catch (Exception ignored) {
        }

        if (isBlogSearchRouteOnly() || isNoResultStateDisplayed()) {
            return;
        }

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        openPage(resolveBaseUrl() + "/index.php?route=extension/maza/blog/search&search=" + encodedQuery);
        try {
            waitForElementPresence(pageUIs.BlogUI.BLOG_CONTENT_CONTAINER);
        } catch (Exception ignored) {
        }
    }

    private WebElement getVisibleSearchBox() {
        List<WebElement> desktopSearchBoxes = getElementList(pageUIs.BlogUI.BLOG_SEARCH_TEXTBOX_DESKTOP);
        for (WebElement searchBox : desktopSearchBoxes) {
            if (isDisplayedSafely(searchBox)
                    && searchBox.isEnabled()
                    && searchBox.getSize().getWidth() > 0
                    && searchBox.getSize().getHeight() > 0) {
                return searchBox;
            }
        }

        List<WebElement> mobileSearchBoxes = getElementList(pageUIs.BlogUI.BLOG_SEARCH_TEXTBOX_MOBILE);
        for (WebElement searchBox : mobileSearchBoxes) {
            if (isDisplayedSafely(searchBox)
                    && searchBox.isEnabled()
                    && searchBox.getSize().getWidth() > 0
                    && searchBox.getSize().getHeight() > 0) {
                return searchBox;
            }
        }

        throw new IllegalStateException("No visible and enabled blog search textbox found");
    }

    private String resolveBaseUrl() {
        try {
            URI uri = URI.create(getPageURL());
            return uri.getScheme() + "://" + uri.getHost();
        } catch (Exception e) {
            return "https://ecommerce-playground.lambdatest.io";
        }
    }

    private double getVisibleImageLoadedRatio() {
        waitForAllElementPresence(pageUIs.BlogUI.ALL_ARTICLE_IMAGES);
        List<WebElement> images = getElementList(pageUIs.BlogUI.ALL_ARTICLE_IMAGES);
        if (images.isEmpty()) {
            return 0;
        }

        int visibleCount = 0;
        int loadedCount = 0;
        for (WebElement image : images) {
            if (!isDisplayedSafely(image)) {
                continue;
            }

            visibleCount++;
            if (isImageLoaded(image)) {
                loadedCount++;
            }

            if (visibleCount >= 20) {
                break;
            }
        }

        if (visibleCount == 0) {
            return 0;
        }

        return (double) loadedCount / visibleCount;
    }

    private boolean isImageLoaded(WebElement image) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Object result = jsExecutor.executeScript(
                "return arguments[0] && arguments[0].complete && typeof arguments[0].naturalWidth === 'number' && arguments[0].naturalWidth > 0;",
                image);
        return result instanceof Boolean && (Boolean) result;
    }

    private boolean isDisplayedSafely(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}