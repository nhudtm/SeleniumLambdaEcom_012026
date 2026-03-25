package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.MyAccountUI;

public class MyAccountPO extends MenuCategoryPO {
    public MyAccountPO(WebDriver driver) {
        super(driver);
    }

    @Step("Click to Continue button to go to register page")
    public RegisterPO clickContinueButton() {
        waitForElementClickable(  MyAccountUI.CONTINUE_BUTTON);
        clickToElement(  MyAccountUI.CONTINUE_BUTTON);
        System.out.println("Clicked to Continue button to go to register page");
        sleepInSecond(5); // Wait for page load chuyển hướng, không được xóa
        return PageGenerator.getRegisterPage(driver);
    }

    @Step("Check My Account link is displayed")
    public boolean isMyAccountLinkDisplayed() {
        String url = getPageURL();
        return url.contains("account/account");
    }

    @Step("Check My Account title is displayed")
    public boolean isMyAccountTitleDisplayed() {
        waitForElementVisible(  MyAccountUI.MY_ACCOUNT_TITLE);
        return isElementDisplayed(  MyAccountUI.MY_ACCOUNT_TITLE);
    }

    @Step("Click to '{0}' in Account right menu")
    public void clickAccountRightMenu(String menuName) {
        waitForElementClickable(  MyAccountUI.DYNAMIC_MYACCOUNT_MENU_BY_NAME, menuName);
        clickToElement(  MyAccountUI.DYNAMIC_MYACCOUNT_MENU_BY_NAME, menuName);
    }

    @Step("Check Login form is displayed")
    public boolean isLoginFormDisplayed() {
        waitForElementVisible(  MyAccountUI.LOGIN_FORM_TITLE);
        return isElementDisplayed(  MyAccountUI.LOGIN_FORM_TITLE);
    }

    @Step("Input to Email textbox with value is {0}")
    public void inputToEmailTextbox(String email) {
        waitForElementVisible(  MyAccountUI.EMAIL_TEXTBOX);
        sendKeyToElement(  MyAccountUI.EMAIL_TEXTBOX, email);
    }

    @Step("Input to Password textbox with value is {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(  MyAccountUI.PASSWORD_TEXTBOX);
        sendKeyToElement(  MyAccountUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click to Login button")
    public void clickLoginButton() {
        waitForElementClickable(  MyAccountUI.LOGIN_BUTTON);
        clickToElement(  MyAccountUI.LOGIN_BUTTON);
    }

    @Step("Get warning message text")
    public String getWarningMessageText() {
        waitForElementVisible(  MyAccountUI.WARNING_MESSAGE);
        return getElementText(  MyAccountUI.WARNING_MESSAGE);
    }


    @Step("Click to Forgotten Password link")
    public void clickForgottenPassword() {
        waitForElementClickable(  MyAccountUI.FORGOT_PASSWORD);
        clickToElement(  MyAccountUI.FORGOT_PASSWORD);
    }

    @Step("Get Forgot Password page title text")
    public String getForgotPasswordPageTitleText() {
        waitForElementVisible(  MyAccountUI.FORGOT_PASSWORD_PAGE_TITLE);
        return getElementText(  MyAccountUI.FORGOT_PASSWORD_PAGE_TITLE);
    }

    @Step("Enter email to reset password with value is {0}")
    public void enterEmailToResetPassword(String email) {
        waitForElementVisible(  MyAccountUI.EMAIL_TEXTBOX_AT_FORGOT_PASSWORD);
        sendKeyToElement(  MyAccountUI.EMAIL_TEXTBOX_AT_FORGOT_PASSWORD, email);
    }

    @Step("Click to Continue button in Forgot Password page")
    public void clickContinueButtonInForgotPasswordPage() {
        waitForElementClickable(  MyAccountUI.CONTINUE_BUTTON_IN_FORGOT_PASSWORD_PAGE);
        clickToElement(  MyAccountUI.CONTINUE_BUTTON_IN_FORGOT_PASSWORD_PAGE);
    }

    @Step("Click to Cancel button in Forgot Password page")
    public String getSuccessMessageTextInForgotPasswordPage() {
        waitForElementVisible(  MyAccountUI.SUCCESS_MESSAGE_IN_FORGOT_PASSWORD_PAGE);
        return getElementText(  MyAccountUI.SUCCESS_MESSAGE_IN_FORGOT_PASSWORD_PAGE);
    }

    @Step("Click to Back button in Forgot Password page")
    public void clickBackButtonInForgotPasswordPage() {
        waitForElementClickable(  MyAccountUI.BACK_BUTTON_IN_FORGOT_PASSWORD_PAGE);
        clickToElement(  MyAccountUI.BACK_BUTTON_IN_FORGOT_PASSWORD_PAGE);
    }

    @Step("Get warning message text in Forgot Password page")
    public String getWarningMessageTextInForgotPasswordPage() {
        waitForElementVisible(  MyAccountUI.WARNING_MESSAGE_IN_FORGOT_PASSWORD_PAGE);
        return getElementText(  MyAccountUI.WARNING_MESSAGE_IN_FORGOT_PASSWORD_PAGE);
    }
}
