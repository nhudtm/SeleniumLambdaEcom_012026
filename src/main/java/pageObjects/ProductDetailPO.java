package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.Product;
import pageUIs.ProductDetailUI;
import utils.DBUtils;

public class ProductDetailPO extends MenuCategoryPO {


    public ProductDetailPO(WebDriver driver) {
        super(driver);

    }

    public String getProductPageTitleText() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_PAGE_TITLE);
        return getElementText(  ProductDetailUI.PRODUCT_PAGE_TITLE);
    }


    public Product getProductInfoFromDB(int productId) {
        return DBUtils.getProductInfoById(productId);
    }

    public String getProductPriceFromUI() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_PRICE);
        return getElementText(  ProductDetailUI.PRODUCT_PRICE);
    }

    public String getProductAvailabilityFromUI() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_AVAILABILITY);
        return getElementText(  ProductDetailUI.PRODUCT_AVAILABILITY);
    }

    public String getProductDescriptionFromUI() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_DESCRIPTION);
        return getElementText(  ProductDetailUI.PRODUCT_DESCRIPTION);
    }

    public String getMainImageSrc() {
        waitForElementVisible(  ProductDetailUI.MAIN_PRODUCT_IMAGE);
        return getElementDOMAttribute(  ProductDetailUI.MAIN_PRODUCT_IMAGE, "src");
    }

    public List<WebElement> getAllMediaSrc() {
        waitForElementVisible(  ProductDetailUI.ALL_PRODUCT_MEDIA);
        return getElementList(  ProductDetailUI.ALL_PRODUCT_MEDIA);
    }

    public String getProductVideoHref() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_THUMBNAIL_VIDEO);
        return getElementDOMAttribute(  ProductDetailUI.PRODUCT_THUMBNAIL_VIDEO, "href");
    }

    public boolean isProductVideoNotAvailable() {
        return isElementUndisplayed(  ProductDetailUI.PRODUCT_THUMBNAIL_VIDEO);
    }

    public boolean isMainImageDisplayed() {
        waitForElementVisible(  ProductDetailUI.MAIN_PRODUCT_IMAGE);
        return isElementDisplayed(  ProductDetailUI.MAIN_PRODUCT_IMAGE);
    }

    public String getImageAltByIndex(int i) {
        waitForElementVisible(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(i));
        return getElementDOMAttribute(  ProductDetailUI.DYNAMIC_IMAGE, "alt", String.valueOf(i));
    }

    public String getImageTitleByIndex(int i) {
        waitForElementVisible(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(i));
        return getElementDOMAttribute(  ProductDetailUI.DYNAMIC_IMAGE, "title", String.valueOf(i));
    }

    public String getImageSrcByIndex(int i) {
        waitForElementVisible(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(i));
        return getElementDOMAttribute(  ProductDetailUI.DYNAMIC_IMAGE, "src", String.valueOf(i));
    }

    public List<String> getProductMediaSrcFromDB(int productId) {
        return DBUtils.getProductMediaSrc(productId);
    }

    public String getImageSrc(String element) {
        waitForElementVisible(  element + "/img");
        return getElementDOMAttribute(  element + "/img", "src");
    }

    public int getProductMediaNumberFromDB(int productId) {
        //SELECT p.product_id, name, count(*) as numberOfMedia FROM product p join `product_media` pm on p.product_id = pm.product_id group by p.product_id
        return DBUtils.getProductMediaNumber(productId);
    }

    public int getProductIdFromURL() {
        String url = getPageURL();
        return Integer.parseInt(url.split("product_id=")[1].split("&")[0]);
    }

    public String getMainImageSrcFromDB(int productId) {
        return DBUtils.getMainImageSrc(productId);

    }

    public String getProductName() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_PAGE_TITLE);
        return getElementText(  ProductDetailUI.PRODUCT_PAGE_TITLE);
    }

    public boolean isMainImageLoadedSuccess() {
        waitForElementVisible(  ProductDetailUI.MAIN_PRODUCT_IMAGE);
        return isImageLoadedByJS(  ProductDetailUI.MAIN_PRODUCT_IMAGE);
    }


    public boolean isImageLoadedSuccess(int k) {
        waitForElementVisible(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(k));
        return isImageLoadedByJS(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(k));
    }

    public boolean isImageDisplayed(int k) {
        waitForElementVisible(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(k));
        return isElementDisplayed(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(k));
    }

    public List<String> getAllProductImage() {
        waitForElementVisible(  ProductDetailUI.ALL_PRODUCT_IMAGE);
        return getElementAttributeList(  ProductDetailUI.ALL_PRODUCT_IMAGE, "src");
    }

    public String getProductVideoHrefFromDB(int productId) {
        return DBUtils.getProductVideoHref(productId);
    }

    public void clickVideoThumbnail() {
        waitForElementVisible(  ProductDetailUI.PRODUCT_THUMBNAIL_VIDEO);
        clickToElement(  ProductDetailUI.PRODUCT_THUMBNAIL_VIDEO);
    }

    public boolean isVideoPlayerDisplayed() {
        waitForElementVisible(  ProductDetailUI.VIDEO_IFRAME);
        switchToFrameIframe(  ProductDetailUI.VIDEO_IFRAME);
        boolean isVideoDisplayed = isElementDisplayed(  ProductDetailUI.VIDEO_PLAYER);
//        switchToDefaultContent(driver);
        return isVideoDisplayed;
    }

    public boolean isVideoErrorNotDisplayed() {
        return isElementUndisplayed(  ProductDetailUI.VIDEO_ERROR);
    }

    public void clickPlayButtonInVideoPlayer() {
        // Switch to the YouTube iframe
//        waitForElementVisible(  ProductDetailUI.VIDEO_IFRAME);
//        switchToFrameIframe(  ProductDetailUI.VIDEO_IFRAME);
        // Wait for and click the YouTube play button
        waitForElementVisible(  ProductDetailUI.VIDEO_PLAYER);
        hoverToElement(  ProductDetailUI.VIDEO_PLAY_BUTTON);
        highligthElementByJS(  ProductDetailUI.VIDEO_PLAY_BUTTON);
        clickToElement(  ProductDetailUI.VIDEO_PLAY_BUTTON);
        sleepInSecond(2);

//        switchToDefaultContent(driver);
    }

    public boolean isVideoPlaying() {
        // Switch to the YouTube iframe
//        waitForElementVisible(  ProductDetailUI.VIDEO_IFRAME);
//        switchToFrameIframe(  ProductDetailUI.VIDEO_IFRAME);
        boolean isVideoPlaying = isVideoPlayingByJS();
//        switchToDefaultContent(driver);
        return isVideoPlaying;
    }

    public void clickPauseVideo() {
//        waitForElementVisible(  ProductDetailUI.VIDEO_IFRAME);
//        switchToFrameIframe(  ProductDetailUI.VIDEO_IFRAME);
        waitForElementVisible(  ProductDetailUI.VIDEO_PAUSE_BUTTON);
        hoverToElement(  ProductDetailUI.VIDEO_PLAYER);
        hoverToElement(  ProductDetailUI.VIDEO_PAUSE_BUTTON);
        highligthElementByJS(  ProductDetailUI.VIDEO_PAUSE_BUTTON);
        clickToElement(  ProductDetailUI.VIDEO_PAUSE_BUTTON);
    }

    public boolean isVideoPaused() {
//        waitForElementVisible(  ProductDetailUI.VIDEO_IFRAME);
//        switchToFrameIframe(  ProductDetailUI.VIDEO_IFRAME);
//        waitForElementVisible(  ProductDetailUI.VIDEO_PLAYER);
        boolean isVideoPaused = isVideoPausedByJS();
        switchToDefaultContent();
        return isVideoPaused;
    }

    public void clickNextButtonInZoomedImage() {
        waitForElementClickable(  ProductDetailUI.MEDIA_ZOOM_NEXT_BUTTON);
        hoverToElement(  ProductDetailUI.MEDIA_ZOOM_NEXT_BUTTON);
        highligthElementByJS(  ProductDetailUI.MEDIA_ZOOM_NEXT_BUTTON);
        clickToElement(  ProductDetailUI.MEDIA_ZOOM_NEXT_BUTTON);
    }


    public void clickPrevButtonInZoomedImage() {
        waitForElementClickable(  ProductDetailUI.MEDIA_ZOOM_PREV_BUTTON);
        hoverToElement(  ProductDetailUI.MEDIA_ZOOM_PREV_BUTTON);
        highligthElementByJS(  ProductDetailUI.MEDIA_ZOOM_PREV_BUTTON);
        clickToElement(  ProductDetailUI.MEDIA_ZOOM_PREV_BUTTON);
    }

    public void clickCloseMediaZoom() {
        switchToDefaultContent();
        waitForElementPresence(  ProductDetailUI.MEDIA_ZOOM_CLOSE_BUTTON);
        clickToElementByJS(  ProductDetailUI.MEDIA_ZOOM_CLOSE_BUTTON);
    }



    public String getNewImageSrc() {
        waitForElementVisible(  ProductDetailUI.ZOOM_IMAGE);
        return getElementDOMAttribute(  ProductDetailUI.ZOOM_IMAGE, "src");
    }

    public void clickImageByIndex(int k) {
        waitForElementClickable(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(k));
        clickToElement(  ProductDetailUI.DYNAMIC_IMAGE, String.valueOf(k));
    }

    public boolean isImageZoomedDisplayed() {
        waitForElementVisible(  ProductDetailUI.ZOOM_IMAGE);
        return isElementDisplayed(  ProductDetailUI.ZOOM_IMAGE);
    }


    public void selectStarInRating(int star) {
        scrollElementToLocation(  300);
        waitForElementPresence(  ProductDetailUI.DYNAMIC_REVIEW_STAR_RATING, String.valueOf(star));
        hoverToElement(  ProductDetailUI.DYNAMIC_REVIEW_STAR_RATING, String.valueOf(star));
        highligthElementByJS(  ProductDetailUI.DYNAMIC_REVIEW_STAR_RATING, String.valueOf(star));
        clickToElementByJS(  ProductDetailUI.DYNAMIC_REVIEW_STAR_RATING, String.valueOf(star));
    }

    public void enterNameToReview(String name) {
        waitForElementVisible(  ProductDetailUI.REVIEW_NAME_TEXTBOX);
        sendKeyToElement(  ProductDetailUI.REVIEW_NAME_TEXTBOX, name);
    }

    public void enterReviewText(String reviewText) {
        waitForElementVisible(  ProductDetailUI.REVIEW_TEXT_TEXTAREA);
        sendKeyToElement(  ProductDetailUI.REVIEW_TEXT_TEXTAREA, reviewText);
    }

    public void clickWriteReviewButton() {
        waitForElementClickable(  ProductDetailUI.REVIEW_SUBMIT_BUTTON);
        clickToElement(  ProductDetailUI.REVIEW_SUBMIT_BUTTON);
    }

    public String getSuccessMessageText() {
        waitForElementVisible(  ProductDetailUI.REVIEW_SUCCESS_MESSAGE);
        return getElementText(  ProductDetailUI.REVIEW_SUCCESS_MESSAGE);

    }

    public String getWarningMessageText() {
        waitForElementVisible(  ProductDetailUI.REVIEW_WARNING_MESSAGE);
        return getElementText(  ProductDetailUI.REVIEW_WARNING_MESSAGE);
    }

    
}