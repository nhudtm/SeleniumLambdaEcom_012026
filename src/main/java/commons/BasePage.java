package commons;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.BasePageUI;

public class BasePage {
    protected WebDriver driver  ;
    public BasePage(WebDriver driver ) {
        this.driver = driver;
    }
    //-------PAGE-------/
    public void openPage(String pageURL) {
        driver.get(pageURL);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void backToPage( ) {
        driver.navigate().back();
    }

    public void forwardToPage( ) {
        driver.navigate().forward();
    }

    public void refreshPage( ) {
        driver.navigate().refresh();
    }

    //-------ALERT-------/
    public Alert waitForAlertPresence( ) {
        WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert( ) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert( ) {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText( ) {
        return driver.switchTo().alert().getText();
    }

    public void sendKeyToAlert( String textValue) {
        driver.switchTo().alert().sendKeys(textValue);
    }

    //-------BROWSER WINDOW / TABS-------/
    // Switch to window by ID or Name - dung khi co 2 tab/window
    public void switchToWindowByID( String mainWindowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String windowID : allWindowIDs) {
            if (!windowID.equals(mainWindowID)) {
                driver.switchTo().window(windowID);
                break;
            }
        }
    }

    // Switch to window by Title - dung khi co nhieu hon 2 tab/window
    public void switchToWindowByTitle( String expectedWindowTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String windowID : allWindowIDs) {
            driver.switchTo().window(windowID);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals(expectedWindowTitle)) {
                break;
            }
        }
    }

    // Close all window without parent
    public void closeAllWindowExceptParent( String parentWindowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentWindowID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindowID);
    }

    //-------WEB ELEMENT-------/
    public By getByLocator(String locator) {
        By by = null;
        if (locator.startsWith("id") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("class") || locator.startsWith("CLASS=") || locator.startsWith("Class=")) {
            by = By.className(locator.substring(6));
        } else if (locator.startsWith("name") || locator.startsWith("NAME=") || locator.startsWith("Name=")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("css") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("xpath") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
            by = By.xpath(locator.substring(6));
        } else if (locator.startsWith("tagname") || locator.startsWith("TAGNAME=") || locator.startsWith("Tagname=")) {
            by = By.tagName(locator.substring(8));
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;
    }

    public String castParameter(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public WebElement getElement( String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public WebElement getElement(  String locator, String... params) {
        return driver.findElement(getByLocator(castParameter(locator, params)));
    }

    public List<WebElement> getElementList( String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getElementList( String locator, String... params) {
        return driver.findElements(getByLocator(castParameter(locator, params)));
    }

    public void clickToElement(String locator) {
        getElement(locator).click();
    }

    public void clickToElement(  String locator, String... params) {
        getElement(castParameter(locator, params)).click();
    }

    public void sendKeyToElement( String locator, String textValue) {
        if (textValue == null) {
            throw new IllegalArgumentException("Input textValue must not be null. Locator: " + locator);
        }
        WebElement element = getElement( locator);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeyToElement( String locator, String textValue, String... params) {
        if (textValue == null) {
            throw new IllegalArgumentException("Input textValue must not be null. Locator: " + castParameter(locator, params));
        }
        WebElement element = getElement( castParameter(locator, params));
        element.clear();
        element.sendKeys(textValue);
    }

    // Ham dung khi clear text binh thuong khong duoc
    public void sendKeyToElementAfterClearTextByKey( String locator, String textValue, String... params) {
        WebElement element = getElement( castParameter(locator, params));
        Keys key = null;
        if (GlobalConstants.OS_NAME.startsWith("Windows")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }
        // Xoa text bang cach nhan Ctrl + A (Command + A) roi bam Backspace
        element.sendKeys(key, "a", Keys.BACK_SPACE);
        element.sendKeys(textValue);
    }

    public boolean isElementEnabled(  String locator) {
        return getElement(locator).isEnabled();
    }

    public boolean isElementEnabled(  String locator, String... params) {
        return getElement( castParameter(locator, params)).isEnabled();
    }

    public boolean isElementDisplayed(  String locator) {
        return isElementDisplayedWithRetry(locator);
    }

    public boolean isElementDisplayed( String locator, String... params) {
        return isElementDisplayedWithRetry(castParameter(locator, params));
    }

    private boolean isElementDisplayedWithRetry(String locator) {
        final int maxAttempts = 3;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return getElement(locator).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            } catch (StaleElementReferenceException e) {
                if (attempt == maxAttempts) {
                    return false;
                }
                sleepInSecond(1);
            } catch (WebDriverException e) {
                String message = e.getMessage();
                boolean isDetachedNode = message != null && message.contains("does not belong to the document");
                if (!isDetachedNode || attempt == maxAttempts) {
                    throw e;
                }
                sleepInSecond(1);
            }
        }
        return false;
    }

    public boolean isElementSelected( String locator) {
        return getElement(  locator).isSelected();
    }

    public boolean isElementSelected( String locator, String... params) {
        return getElement(  castParameter(locator, params)).isSelected();
    }

    public void overrideGlobalTimeout( long timeOutInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOutInSecond));
    }

    public boolean isElementUndisplayed( String locator) {
        overrideGlobalTimeout(  GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getElementList(  locator);
        overrideGlobalTimeout(  GlobalConstants.LONG_TIMEOUT); // Tra ve timeout mac dinh
        if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
            return true; // Element khong co trong DOM hoac co trong DOM nhung khong hien thi
        } else {
            return false;
        }
    }

    public String getElementDOMAttribute( String locator, String attributeName) {
        return getElement(  locator).getDomAttribute(attributeName);
    }



    public String getElementProperties( String locator, String propertyName) {
        return getElement(  locator).getDomProperty(propertyName);
    }

    public String getElementDOMAttribute( String locator, String attributeName, String... params) {
        return getElement(  castParameter(locator, params)).getDomAttribute(attributeName);
    }

    public String getElementProperties( String locator, String propertyName, String... params) {
        return getElement(  castParameter(locator, params)).getDomProperty(propertyName);
    }

    public List<String> getElementAttributeList( String locator, String attributeName) {
      List<WebElement> elements = getElementList(  locator);
        List<String> attributeValues = new ArrayList<>();
        for (WebElement element : elements) {
            attributeValues.add(element.getDomAttribute(attributeName));
        }
        return attributeValues;

    }

    public String getElementProperty( String locator, String propertyName) {
        return getElement(  locator).getDomProperty(propertyName);
    }

    public String getElementProperty( String locator, String propertyName, String... params) {
        return getElement(  castParameter(locator, params)).getDomProperty(propertyName);
    }

    public String getElementText( String locator) {
        return getElement(  locator).getText();
    }

    public String getElementText( String locator, String... params) {
        return getElement(  castParameter(locator, params)).getText();
    }

    // Return String of Dimension (height, width) - vd (width=120, height=60)
    public String getElementSize( String locator) {
        return String.valueOf(getElement(  locator).getSize());
    }

    public int getNumberOfElements( String locator) {
        return getElementList(  locator).size();
    }

    public int getNumberOfElements( String locator, String... params) {
        return getElementList(  castParameter(locator, params)).size();
    }

    public String getElementCssValue( String locator, String cssPropertyName) {
        return getElement(  locator).getCssValue(cssPropertyName);
    }

    public String getElementCssValue( String locator, String cssPropertyName, String... params) {
        return getElement(  castParameter(locator, params)).getCssValue(cssPropertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    //------- Cookies--------/
    public Set<Cookie> getAllCookies( ) {
        return driver.manage().getCookies();
    }

    public void setCookies( Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
        refreshPage();
        System.out.println("Cookies after set:");
    }

    //--------DROPDOWN-------/
    public void selectItemInDropdown( String locator, String selectItem) {
        new Select(getElement(  locator)).selectByVisibleText(selectItem);
    }


    public void selectItemInDropdown( String locator, String selectItem, String... params) {
        new Select(getElement(  castParameter(locator, params))).selectByVisibleText(selectItem);
    }

    public void selectItemInDropdownByItemIndex( String locator, int itemIdex) {
        new Select(getElement(  locator)).selectByIndex(itemIdex);
    }

    public void selectItemInDropdownByItemIndex( String locator, int itemIdex, String... params) {
        new Select(getElement(  castParameter(locator,params))).selectByIndex(itemIdex);
    }


    public String getSelectedItemInDropdown( String locator) {
        return new Select(getElement(  locator)).getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDropdown( String locator, String... params) {
        return new Select(getElement(  castParameter(locator, params))).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple( String locator) {
        return new Select(getElement(  locator)).isMultiple();
    }

    public boolean isDropdownMultiple( String locator, String... params) {
        return new Select(getElement(  castParameter(locator, params))).isMultiple();
    }

    public void selectItemInCustomDropdown( String parentLocator, String childLocator, String selectItem) {
        // 1 - Click vao the cha de mo ra tat ca cac the con
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator))).click();

        // 2 - Chon the con can select
        List<WebElement> allItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().equals(selectItem)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                item.click();
                break;
            }
        }
    }

//    public void selectItemInCustomDropdown( String parentLocator, String childLocator, String selectItem, String... params) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
//        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(parentLocator, params)))).click();
//
//        List<WebElement> allItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameter(childLocator, params))));
//        for (WebElement item : allItems) {
//            if (item.getText().equals(selectItem)) {
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
//                item.click();
//                break;
//            }
//        }
//    }

    public void selectMultiItemInCustomDropdown( String parentLocator, String childLocator, String[] selectItems) {
        // 1 - Click vao the cha de mo ra tat ca cac the con
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator))).click();

        // 2 - Chon the con can select
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : elements) {
            for (String selectItem : selectItems) {
                if (item.getText().equals(selectItem)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                    item.click();

                    List<WebElement> selectedItem = getElementList(  "xpath=//li[@class='selected']");

                    if (selectItems.length == selectedItem.size()) {
                        break;
                    }
                }
            }
        }
    }

    public void enterItemInDropdownTextbox( String parentLocator, String childLocator, String itemToSelect) {
        // 1 - Input text vao textbox
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        WebElement dropbox = wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(parentLocator)));
        dropbox.clear();
        dropbox.sendKeys(itemToSelect);

        // 2 - Kiem tra cac item trong dropdown, item nao giong voi item can chon thi click vao
        List<WebElement> allItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (!item.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
            }
            if (item.getText().equals(itemToSelect)) {
                item.click();
                break;
            }
        }

    }

    // ------CHECKBOX / RADIO-------/
    public void checkToCheckBoxRadio( String locator) {
        WebElement element = getElement(  locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckBoxRadio( String locator, String... params) {
        WebElement element = getElement(  castParameter(locator, params));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckBoxRadio( String locator) {
        WebElement element = getElement(  locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckBoxRadio( String locator, String... params) {
        WebElement element = getElement(  castParameter(locator, params));
        if (element.isSelected()) {
            element.click();
        }
    }

    // -------IFRAME------/
    public void switchToFrameIframe( String locator) {
        driver.switchTo().frame(getElement(  locator));
    }

    public void switchToFrameIframe( String locator, String... params) {
        driver.switchTo().frame(getElement(  castParameter(locator, params)));
    }

    public void switchToDefaultContent( ) {
        driver.switchTo().defaultContent();
    }

    //-----UPLOAD FILE-------/
    /* filePath: duong dan den file can upload
       VD: C:\Users\Admin\Desktop\Automation Testing\01.jpg
     */
    public void uploadOneFile( String filePath) {
        getElement(  BasePageUI.UPLOAD_FILE_TYPE).sendKeys(filePath);
    }

    public void uploadMultiFiles( String... fileNames) {
        StringBuilder fullFileNames = new StringBuilder();
        try {
            for (String fileName : fileNames) {
                File file = new File(GlobalConstants.UPLOAD_PATH + fileName);
                if (file.exists()) {
                    fullFileNames.append(GlobalConstants.UPLOAD_PATH).append(file).append("\n");
                } else {
                    System.out.printf("File %s does not exist\n", fileName);
                }
            }
            getElement(  BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileNames);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file " + e);
        }
    }


    // ------ACTIONS------/
    // hover
    public void hoverToElement( String locator) {
        hoverToElementInternal(getElement(locator));
    }

    public void hoverToElement( String locator, String... params) {
        hoverToElementInternal(getElement(castParameter(locator, params)));
    }

    private void hoverToElementInternal(WebElement element) {
        try {
            scrollElementIntoViewCenter(element);
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        } catch (MoveTargetOutOfBoundsException e) {
            hoverToElementByJS(element);
        } catch (WebDriverException e) {
            String message = e.getMessage();
            boolean isOutOfBounds = message != null && message.toLowerCase().contains("out of bounds");
            if (isOutOfBounds) {
                hoverToElementByJS(element);
            } else {
                throw e;
            }
        }
    }

    private void hoverToElementByJS(WebElement element) {
        scrollElementIntoViewCenter(element);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles:true, cancelable:true, view:window}));",
                element
        );
    }

    private void scrollElementIntoViewCenter(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                element
        );
    }

    // double click
    public void doubleClickToElement( String locator) {
        Actions action = new Actions(driver);
        action.doubleClick(getElement(  locator)).perform();
    }

    public void doubleClickToElement( String locator, String... params) {
        Actions action = new Actions(driver);
        action.doubleClick(getElement(  castParameter(locator, params))).perform();
    }

    // right click
    public void rightClickToElement( String locator) {
        Actions action = new Actions(driver);
        action.contextClick(getElement(  locator)).perform();
    }

    public void rightClickToElement( String locator, String... params) {
        Actions action = new Actions(driver);
        action.contextClick(getElement(  castParameter(locator, params))).perform();
    }

    // drag and drop
    public void dragAndDropElement( String sourceLocator, String targetLocator) {
        Actions action = new Actions(driver);
        action.dragAndDrop(getElement(  sourceLocator), getElement(  targetLocator)).perform();
    }

    public void dragAndDropElement( String sourceLocator, String targetLocator, String... params) {
        Actions action = new Actions(driver);
        action.dragAndDrop(getElement(  castParameter(sourceLocator, params)), getElement(  castParameter(targetLocator, params))).perform();
    }

    public void dragAndDropElementByClickAndHold( String sourceLocator, String targetLocator) {
        Actions action = new Actions(driver);
        action.clickAndHold(getElement(  sourceLocator))
                .moveToElement(getElement(  targetLocator))
                .release()
                .build()
                .perform();
    }

    // click and hold
    public void clickAndHoldElement( String locator) {
        Actions action = new Actions(driver);
        action.clickAndHold(getElement(  locator)).perform();
    }

    public void clickAndHoldElement( String locator, String... params) {
        Actions action = new Actions(driver);
        action.clickAndHold(getElement(  castParameter(locator, params))).perform();
    }

    // release
    public void releaseElement( String locator) {
        Actions action = new Actions(driver);
        action.release(getElement(  locator)).perform();
    }

    public void releaseElement( String locator, String... params) {
        Actions action = new Actions(driver);
        action.release(getElement(  castParameter(locator, params))).perform();
    }

    // scroll to element
    public void scrollToElement( String locator) {
        scrollElementIntoViewCenter(getElement(locator));
    }

    public void scrollToElement( String locator, String... params) {
        scrollElementIntoViewCenter(getElement(castParameter(locator, params)));
    }

    // press key
    public void pressKeyToElement( String locator, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getElement(  locator), key).perform();
    }

    public void pressKeyToElement( String locator, Keys key, String... params) {
        Actions action = new Actions(driver);
        action.sendKeys(getElement(  castParameter(locator, params)), key).perform();
    }

    // ------JS EXECUTOR------/
    public void scrollToBottomByJS( ) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)"); //scrollHeight: chieu cao cua trang web
    }

    public void scrollToElementOnTopByJS( String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(  locator));
    }

    public void scrollToElementOnTopByJS( String locator, String... params) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(  castParameter(locator, params)));
    }

    public void scrollToElementOnBottomByJS( String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(  locator));
    }

    public void scrollElementToLocation( int position) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + position + ")");
    }

    public void highligthElementByJS( String locator) {
        String originalStyle = getElement(  locator).getDomAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", getElement(  locator), "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", getElement(  locator), originalStyle);
    }

    public void highligthElementByJS( String locator, String... params) {
        String originalStyle = getElement(  castParameter(locator, params)).getDomAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", getElement(  castParameter(locator, params)), "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", getElement(  castParameter(locator, params)), originalStyle);
    }
    public void clickToElementByJS( String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", getElement(  locator));
    }

    public void clickToElementByJS( String locator, String ... params) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", getElement(  castParameter(locator, params)));
    }

    public void clickToElementByJS( WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    public void getAttributeInDOMByJS( String locator, String attributeName) {
        ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "')", getElement(  locator));
    }

    public void setAttributeInDOMByJS( String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "')", getElement(  locator));
    }

    public void sendKeysToElementByJS( String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(  locator));
    }

    public String getAttributeValidationMessageByJS( String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(  locator));
    }

    public String getElementValidationMessageByJS( String locator, String... params) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(  castParameter(locator, params)));
    }

    public boolean isImageLoadedByJS( String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(  locator));
    }
    public boolean isImageLoadedByJS( String locator, String... params) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(  castParameter(locator, params)));
    }

    //Dùng cho testcase video embbeded youtube
    /* getPlayerState() là hàm nội bộ của YouTube player.
    Video state:
        0 – ended
        1 – playing
        2 – paused
        3 – buffering
        5 – video cued
     */
    public boolean isVideoPlayingByJS( ) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementById('movie_player') && document.getElementById('movie_player').getPlayerState() === 1;");
    }

    public boolean isVideoPausedByJS( ) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementById('movie_player') && document.getElementById('movie_player').getPlayerState() === 2;");
    }


    // ------WAIT------/
    public void waitForElementVisible( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible( String locator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, params))));
    }

    public void waitForElementInvisible( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible( String locator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, params))));
    }

    public void waitForAllElementsVisible( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForElementClickable( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable( String locator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, params))));
    }

    public void waitForAttributeValue( String locator, String attributeName, String attributeValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.attributeToBe(getByLocator(locator), attributeName, attributeValue));
    }

    public void waitForAttributeValue( String locator, String attributeName, String attributeValue, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.attributeToBe(getByLocator(castParameter(locator, params)), attributeName, attributeValue));
    }

    public void waitForElementPresence( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence( String locator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, params))));
    }

    public void waitForAllElementPresence( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForElementToBeSelected( String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementToBeSelected( String locator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, params))));
    }


    // ------OTHER METHOD------/
    public boolean isAjaxLoadingInvisible( String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
