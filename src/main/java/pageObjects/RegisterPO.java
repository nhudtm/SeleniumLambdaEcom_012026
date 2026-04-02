package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.RegisterUI;

public class RegisterPO extends MenuCategoryPO {

    public RegisterPO(WebDriver driver) {
        super(driver);

    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(RegisterUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(RegisterUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(RegisterUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(RegisterUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisible(RegisterUI.EMAIL_TEXTBOX);
        sendKeyToElement(RegisterUI.EMAIL_TEXTBOX, email);
    }

    public void inputToPhoneTextbox(String phone) {
        waitForElementVisible(RegisterUI.PHONE_TEXTBOX);
        sendKeyToElement(RegisterUI.PHONE_TEXTBOX, phone);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(RegisterUI.PASSWORD_TEXTBOX);
        sendKeyToElement(RegisterUI.PASSWORD_TEXTBOX, password);
    }

    public void inputToConfirmPasswordTextbox(String password) {
        waitForElementVisible(RegisterUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(RegisterUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickToNewsletterYesCheckbox() {
        scrollToBottomByJS();
        waitForElementClickable(RegisterUI.NEWSLETTER_CHECKBOX_YES);
        checkToCheckBoxRadio(RegisterUI.NEWSLETTER_CHECKBOX_YES);
    }

    public void clickToPrivacyPolicyCheckbox() {
        waitForElementClickable(RegisterUI.PRIVACY_POLICY_CHECKBOX);
        clickToElement(RegisterUI.PRIVACY_POLICY_CHECKBOX);
    }

    public MyAccountPO clickToContinueButtonAtSuccessPage() {
        waitForElementClickable(RegisterUI.ACCOUNT_CREATED_CONTINUE_BUTTON);
        clickToElement(RegisterUI.ACCOUNT_CREATED_CONTINUE_BUTTON);
        return PageGenerator.getMyAccountPage(driver);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(RegisterUI.REGISTER_SUCCESS_MESSAGE);
        getElementText(RegisterUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(RegisterUI.REGISTER_SUCCESS_MESSAGE);
    }

    public void clickToContinueButtonAtResigterPage() {
        waitForElementClickable(RegisterUI.CONTINUE_BUTTON);
        clickToElement(RegisterUI.CONTINUE_BUTTON);
    }

    public String getErrorMessage(String fieldName) {
        waitForElementVisible(RegisterUI.DYNAMIC_ERROR_MESSAGE_BY_FIELD_NAME, fieldName);
        return getElementText(RegisterUI.DYNAMIC_ERROR_MESSAGE_BY_FIELD_NAME, fieldName);
    }

    public String getValidationMessageAtEmailTextbox() {
        waitForElementVisible(RegisterUI.EMAIL_TEXTBOX);
        return getAttributeValidationMessageByJS(RegisterUI.EMAIL_TEXTBOX);
    }

    public String getPrivacyPolicyErrorMessage() {
        waitForElementVisible(RegisterUI.PRIVACY_POLICY_WARNING_MESSAGE);
        return getElementText(RegisterUI.PRIVACY_POLICY_WARNING_MESSAGE);
    }

    public void clickToNewsletterNoCheckbox() {
        scrollToBottomByJS();
        waitForElementClickable(RegisterUI.NEWSLETTER_CHECKBOX_NO);
        checkToCheckBoxRadio(RegisterUI.NEWSLETTER_CHECKBOX_NO);
    }

    public boolean isRegisterFormDisplayed() {
        waitForElementVisible(RegisterUI.REGISTER_FORM_TITLE);
        return isElementDisplayed(RegisterUI.REGISTER_FORM_TITLE);
    }

    public MyAccountPO register(String firstName, String lastName, String email, String phone, String password,
            boolean isNewsletterYes) {
        inputToFirstNameTextbox(firstName);
        inputToLastNameTextbox(lastName);
        inputToEmailTextbox(email);
        inputToPhoneTextbox(phone);
        inputToPasswordTextbox(password);
        inputToConfirmPasswordTextbox(password);
        if (isNewsletterYes) {
            clickToNewsletterYesCheckbox();
        } else {
            clickToNewsletterNoCheckbox();
        }
        clickToPrivacyPolicyCheckbox();
        clickToContinueButtonAtResigterPage();
        clickToContinueButtonAtSuccessPage();
        return PageGenerator.getMyAccountPage(driver);
    }
}