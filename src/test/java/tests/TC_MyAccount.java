package tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AuthMessages;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.HomePO;
import pageObjects.MyAccountPO;
import pageObjects.PageGenerator;
import utils.PropertiesConfig;

@Epic("Login - Register")
@Feature("Login Tests")
public class TC_MyAccount extends BaseTest {
//    WebDriver driver;
    protected HomePO homePage;
    protected MyAccountPO myAccount;

    String email = PropertiesConfig.getRequiredProp("test.email");
    String password = PropertiesConfig.getRequiredProp("test.password");

    @Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass( String env, @Optional("chrome") String browserName, @Optional String browserVersion, @Optional String os, @Optional String osVersion, String url, ITestContext context) {
        getBrowserDriverWithContext(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        log.info("Thread id beforeClass TC_Login: " + Thread.currentThread().getId() + " - " + getDriver().toString());
    }

    @Description("User 05 - Login with valid information")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_05_Login_With_Valid_Info() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(email);
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.isMyAccountTitleDisplayed(), "Expected my account title not found");
        myAccount.clickAccountRightMenu("Logout");
    }

    @Description("User 01 - Login with email has space")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Login_With_Email_Has_Space() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox("  " + email + "  ");
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_NO_MATCH_WARNING), "Expected warning message not found");
    }

    @Description("User 03 - Login with email case sensitive")
    @Story("Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void TC_03_Login_With_Email_Case_Sensitive() {
    /*
        Theo RFC 5321 — chuẩn kỹ thuật cho email:
         Phần local (trước dấu @): có thể phân biệt chữ hoa/thường
         → User@example.com và user@example.com có thể là hai địa chỉ khác nhau
         Phần domain (sau dấu @): không phân biệt chữ hoa/thường
        → user@Example.com và user@example.com là giống nhau
     */
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(email.toUpperCase());
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.isMyAccountTitleDisplayed(), "Expected my account title not found");
        myAccount.clickAccountRightMenu("Logout");
    }

    @Description("User 02 - Login with blank information")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login_With_Blank_Info() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_NO_MATCH_WARNING), "Expected warning message not found");
    }

    @Description("User 04 - Login with blank email")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_04_Login_With_Blank_Email() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_ACCOUNT_LOCKED_WARNING), "Expected warning message not found");
    }

    @Description("User 06 - Login with blank password")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Login_With_Blank_Password() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(password);
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_NO_MATCH_WARNING), "Expected warning message not found");
    }

    @Description("User 07 - Login with invalid email")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_07_Login_With_Invalid_Email() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox("abc@abc");
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_NO_MATCH_WARNING), "Expected warning message not found");
    }


    @Description
            ("User 08 - Login with not existed email")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_08_Login_With_Not_Existed_Email() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox("abc@gmail.com");
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_NO_MATCH_WARNING), "Expected warning message not found");

    }

    @Description("User 09 - Login with invalid password")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_09_Login_With_Invalid_Password() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(email);
        myAccount.inputToPasswordTextbox("123");
        myAccount.clickLoginButton();
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_NO_MATCH_WARNING), "Expected warning message not found");
    }

    @Description("User 10 - Login with exceed attempt")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_10_Login_With_Exceed_Attempt() {
        myAccount = homePage.clickMyAccountMenuItem();
    
        for (int i = 0; i < 5; i++) {
            myAccount.inputToEmailTextbox(email);
            myAccount.inputToPasswordTextbox("123");
            myAccount.clickLoginButton();
        }
       
        assertTrueWithMessage(myAccount.getWarningMessageText().contains(AuthMessages.LOGIN_ACCOUNT_LOCKED_WARNING), "Expected warning message not found");
    }

    @Description("User 01 - Forgot Password with valid email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"regression", "login"})
    public void TC_01_Forgot_Password_Success() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        myAccount.enterEmailToResetPassword(email);
        myAccount.clickContinueButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.getSuccessMessageTextInForgotPasswordPage().contains(AuthMessages.LOGIN_CONFIRMATION_LINK_SENT), "Expected success message not found");

        //Verify email is sent

        //Verify login with new password
    }

    @Description("User 02 - Forgot Password with blank email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Forgot_Password_Blank_Email() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        myAccount.clickContinueButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.getWarningMessageTextInForgotPasswordPage().contains(AuthMessages.FORGOT_PASSWORD_EMAIL_NOT_FOUND_WARNING), "Expected warning message not found");
    }

    @Description("User 03 - Forgot Password with email has space")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Forgot_Password_Email_Has_Space() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        myAccount.enterEmailToResetPassword("  " + email + "  ");
        myAccount.clickContinueButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.getWarningMessageTextInForgotPasswordPage().contains(AuthMessages.FORGOT_PASSWORD_EMAIL_NOT_FOUND_WARNING), "Expected warning message not found");
    }

    @Description("User 04 - Forgot Password with email case sensitive")
    @Story("Forgot Password")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void TC_04_Forgot_Password_Email_Case_Sensitive() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        System.out.println("Sau khi test failed");
        myAccount.enterEmailToResetPassword(email.toUpperCase());
        myAccount.clickContinueButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.getSuccessMessageTextInForgotPasswordPage().contains(AuthMessages.LOGIN_CONFIRMATION_LINK_SENT), "Expected success message not found");
    }

    @Description("User 05 - Forgot Password with invalid email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_05_Forgot_Password_Invalid_Email() {
        // Email ko đúng định dạng
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        System.out.println("Sau khi test failed");
        myAccount.enterEmailToResetPassword("abc@abc");
        myAccount.clickContinueButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.getWarningMessageTextInForgotPasswordPage().contains(AuthMessages.FORGOT_PASSWORD_EMAIL_NOT_FOUND_WARNING), "Expected warning message not found");
    }

    @Description("User 06 - Forgot Password with not existed email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Forgot_Password_Not_Existed_Email() {
        // Đúng email format nhưng không tồn tại trong hệ thống
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        myAccount.enterEmailToResetPassword("abcsdfsd@gmail.com");
        myAccount.clickContinueButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.getWarningMessageTextInForgotPasswordPage().contains(AuthMessages.FORGOT_PASSWORD_EMAIL_NOT_FOUND_WARNING), "Expected warning message not found");
    }

    @Description("User 07 - Forgot Password Cancel")
    @Story("Forgot Password")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void TC_07_Forgot_Password_Cancel() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        assertTrueWithMessage(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"), "Expected forgot password page title not found");
        myAccount.enterEmailToResetPassword(email);
        myAccount.clickBackButtonInForgotPasswordPage();
        assertTrueWithMessage(myAccount.isLoginFormDisplayed(), "Expected login form not found");
    }


}
