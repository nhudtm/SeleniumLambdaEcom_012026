package tests;

import commons.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePO;
import pageObjects.MyAccountPO;
import pageObjects.PageGenerator;

@Epic("Login - Register")
@Feature("Login Tests")
public class TC_Login extends BaseTest {
//    WebDriver driver;
    protected HomePO homePage;
    protected MyAccountPO myAccount;

    String email = "lazy@gmail.com";
    String password = "123456";

    @Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})
    @BeforeClass
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion, @Optional String os, @Optional String osVersion, String url, ITestContext context) {
         getBrowserDriver2(env, browserName, browserVersion, os, osVersion, url, context);
        homePage = PageGenerator.getHomepage(getDriver(context));
        System.out.println("Thread id beforeClass TC_Login: " + Thread.currentThread().getId() + " - " + getDriver().toString());
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
        Assert.assertTrue(myAccount.isMyAccountTitleDisplayed());
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
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
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
        Assert.assertTrue(myAccount.isMyAccountTitleDisplayed());
        myAccount.clickAccountRightMenu("Logout");
    }

    @Description("User 02 - Login with blank information")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login_With_Blank_Info() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickLoginButton();
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
    }

    @Description("User 04 - Login with blank email")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_04_Login_With_Blank_Email() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."));
    }

    @Description("User 06 - Login with blank password")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Login_With_Blank_Password() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(password);
        myAccount.clickLoginButton();
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
    }

    @Description("User 07 - Login with invalid email")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_07_Login_With_Invalid_Email() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox("abc@abc");
        myAccount.clickLoginButton();
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
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
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));

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
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
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
       
        Assert.assertTrue(myAccount.getWarningMessageText().contains("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."));
    }

    @Description("User 01 - Forgot Password with valid email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_01_Forgot_Password_Success() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        myAccount.enterEmailToResetPassword(email);
        myAccount.clickContinueButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.getSuccessMessageTextInForgotPasswordPage().contains("An email with a confirmation link has been sent your email address."));

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
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        myAccount.clickContinueButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.getWarningMessageTextInForgotPasswordPage().contains("Warning: The E-Mail Address was not found in our records, please try again!"));
    }

    @Description("User 03 - Forgot Password with email has space")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Forgot_Password_Email_Has_Space() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        myAccount.enterEmailToResetPassword("  " + email + "  ");
        myAccount.clickContinueButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.getWarningMessageTextInForgotPasswordPage().contains("Warning: The E-Mail Address was not found in our records, please try again!"));
    }

    @Description("User 04 - Forgot Password with email case sensitive")
    @Story("Forgot Password")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void TC_04_Forgot_Password_Email_Case_Sensitive() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        System.out.println("Sau khi test failed");
        myAccount.enterEmailToResetPassword(email.toUpperCase());
        myAccount.clickContinueButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.getSuccessMessageTextInForgotPasswordPage().contains("An email with a confirmation link has been sent your email address."));
    }

    @Description("User 05 - Forgot Password with invalid email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_05_Forgot_Password_Invalid_Email() {
        // Email ko đúng định dạng
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        System.out.println("Sau khi test failed");
        myAccount.enterEmailToResetPassword("abc@abc");
        myAccount.clickContinueButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.getWarningMessageTextInForgotPasswordPage().contains("Warning: The E-Mail Address was not found in our records, please try again!"));
    }

    @Description("User 06 - Forgot Password with not existed email")
    @Story("Forgot Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Forgot_Password_Not_Existed_Email() {
        // Đúng email format nhưng không tồn tại trong hệ thống
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        myAccount.enterEmailToResetPassword("abcsdfsd@gmail.com");
        myAccount.clickContinueButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.getWarningMessageTextInForgotPasswordPage().contains("Warning: The E-Mail Address was not found in our records, please try again!"));
    }

    @Description("User 07 - Forgot Password Cancel")
    @Story("Forgot Password")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void TC_07_Forgot_Password_Cancel() {
        myAccount = homePage.clickMyAccountMenuItem();
        myAccount.clickForgottenPassword();
        Assert.assertTrue(myAccount.getForgotPasswordPageTitleText().contains("Forgot Your Password?"));
        myAccount.enterEmailToResetPassword(email);
        myAccount.clickBackButtonInForgotPasswordPage();
        Assert.assertTrue(myAccount.isLoginFormDisplayed());
    }


}
