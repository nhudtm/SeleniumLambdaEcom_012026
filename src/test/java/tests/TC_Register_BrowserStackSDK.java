package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
import pageObjects.RegisterPO;
import pageUIs.MenuCategoryUI;
import utils.DataFaker;

@Epic("Login - Register")
@Feature("Register Tests")
public class TC_Register_BrowserStackSDK extends BaseTest {
//    WebDriver driver;
    HomePO homepage;
    MenuCategoryUI menuCategory;
    MyAccountPO myAccount;
    RegisterPO registerPO;
    DataFaker dataFaker;
    String firstName, lastName, email, phone, password;
    WebElement element;

    @Description("Register to system with valid information")
    @Story("Register")
    @Severity(SeverityLevel.MINOR)
    @Parameters({"env","browserName","browserVersion","os","osVersion","url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env, @Optional String browserName, @Optional String browserVersion, @Optional String os, @Optional String osVersion, String url) {
        getBrowserDriverAllEnvironment(env, browserName, browserVersion,os, osVersion, url);
        homepage = PageGenerator.getHomepage(getDriver());
        dataFaker = new DataFaker();
        firstName= dataFaker.getFirstName();
        lastName= dataFaker.getLastName();
        email= dataFaker.getEmail();
        phone= dataFaker.getPhone();
        password= dataFaker.getPassword();

        System.out.println("Thread ID TC_Register_BrowserStackSDK= " + Thread.currentThread().getId() + " - " + browserName + " - " + getDriver().toString());
    }

    //Multi field empty
    @Description("Register to system with blank data")
    @Story("Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_01_Register_Blank_Data() {
        System.out.println("Thread ID TC_01_Register_Blank_Data= " + Thread.currentThread().getId() + " - " + getDriver().toString());
        myAccount = homepage.clickMyAccountMenuItem();
        registerPO = myAccount.clickContinueButton();
        registerPO.clickToContinueButtonAtResigterPage();

        Assert.assertEquals(registerPO.getErrorMessage("First Name"),"First Name must be between 1 and 32 characters!");
        Assert.assertEquals(registerPO.getErrorMessage("Last Name"),"Last Name must be between 1 and 32 characters!");
        Assert.assertEquals(registerPO.getErrorMessage("E-Mail"),"E-Mail Address does not appear to be valid!");
        Assert.assertEquals(registerPO.getErrorMessage("Telephone"),"Telephone must be between 3 and 32 characters!");
        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password must be between 4 and 20 characters!");
        Assert.assertEquals(registerPO.getPrivacyPolicyErrorMessage(),"Warning: You must agree to the Privacy Policy!");
    }

    @Description
    ("Register to system with First Name blank/ over max")
    @Story("Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_02_Register_FirstName_Blank(){
        System.out.println("Thread ID TC_02_Register_FirstName_Blank= " + Thread.currentThread().getId() + " - " + getDriver().toString());
        myAccount = homepage.clickMyAccountMenuItem();
        registerPO = myAccount.clickContinueButton();
        registerPO.inputToLastNameTextbox(lastName);
        registerPO.inputToEmailTextbox(email);
        registerPO.inputToPhoneTextbox(phone);
        registerPO.inputToPasswordTextbox(password);
        registerPO.inputToConfirmPasswordTextbox(password);
        registerPO.clickToPrivacyPolicyCheckbox();
        registerPO.clickToContinueButtonAtResigterPage();
        Assert.assertEquals(registerPO.getErrorMessage("First Name"),"First Name must be between 1 and 32 characters!");
    }

//    @Description
//    ("Register to system with First Name over max")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_02_Register_FirstName_Over_Max() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox("asdf asda asdf asdd asda asda asdd");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("First Name"),"First Name must be between 1 and 32 characters!");
//    }
//
//    @Description
//    ("Register to system with Last Name blank")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_03_Register_LastName_Blank() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(firstName);
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPhoneTextbox(phone);
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox(password);
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Last Name"),"Last Name must be between 1 and 32 characters!");
//    }
//
//    @Description("Register to system with Last Name  over max")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_03_Register_LastName_Over_Max() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        //Max = 32 characters
//        registerPO.inputToLastNameTextbox("asdf asda asdf asdd asda asda asdd");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Last Name"),"Last Name must be between 1 and 32 characters!");
//    }
//
//    @Description("Register to system with Email blank")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_04_Register_Email_Blank() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(firstName);
//        registerPO.inputToLastNameTextbox(lastName);
//        registerPO.inputToPhoneTextbox(phone);
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox(password);
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("E-Mail"),"E-Mail Address does not appear to be valid!");
//    }
//
//    @Description
//    ("Register to system with Email invalid")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_04_Register_Email_Invalid_01() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToEmailTextbox("123@456.789@abc");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getValidationMessageAtEmailTextbox(),"A part following '@' should not contain the symbol '@'.");
//    }
//
//
//    @Description("Register to system with Email invalid")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_05_Register_Email_Invalid_02() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToEmailTextbox("123@456");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("E-Mail"),"E-Mail Address does not appear to be valid!");
//    }
//
////    @Test
////    public void TC_06_Register_Email_Existed() {
////        myAccount = homepage.openMyAccountPage();
////        registerPO = myAccount.clickContinueButton();
////        registerPO.inputToFirstNameTextbox(firstName);
////        registerPO.inputToLastNameTextbox(lastName);
////        registerPO.inputToEmailTextbox(email);
////        registerPO.inputToPhoneTextbox(phone);
////        registerPO.inputToPasswordTextbox(password);
////        registerPO.inputToConfirmPasswordTextbox(password);
////
////        registerPO.clickToNewsletterYesCheckbox();
////        registerPO.clickToPrivacyPolicyCheckbox();
////        registerPO.clickToContinueButtonAtResigterPage();
////        Assert.assertEquals(registerPO.getExistedEmailErrorMessage(),"Warning: E-Mail Address is already registered!");
////    }
//
//    @Description
//    ("Register to system with Phone blank")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_05_Register_Phone_Blank() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(firstName);
//        registerPO.inputToLastNameTextbox(lastName);
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox(password);
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Telephone"),"Telephone must be between 3 and 32 characters!");
//    }
//
//    @Description
//    ("Register to system with Phone over max")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_05_Register_Phone_Over_Max() {
//        //Max = 32 characters
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToPhoneTextbox("012345678901234567890123456789012345");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Telephone"),"Telephone must be between 3 and 32 characters!");
//    }
//
//    @Description("Register to system with Phone below min")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_06_Register_Phone_Below_Min() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToPhoneTextbox("01");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Telephone"),"Telephone must be between 3 and 32 characters!");
//        //Min = 3 characters
//    }
//
//    @Description("Register to system with Password blank")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_07_Register_Password_Blank() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(firstName);
//        registerPO.inputToLastNameTextbox(lastName);
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPhoneTextbox(phone);
//        registerPO.inputToConfirmPasswordTextbox(password);
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password must be between 4 and 20 characters!");
//    }
//
//    @Description
//    ("Register to system with Password over max")
//    @Story("Register")
//    @Severity(SeverityLevel.NORMAL)
//    @Test
//    public void TC_07_Register_Password_Over_Max() {
//        //Max = 20 characters
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToPasswordTextbox("123456789012345678901");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password confirmation does not match password!");
//
//    }
//
//    @Description
//    ("Register to system with Password below min")
//    @Story("Register")
//    @Severity(SeverityLevel.NORMAL)
//    @Test
//    public void TC_08_Register_Password_Below_Min() {
//        //Min = 4 characters
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToPasswordTextbox("123");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password must be between 4 and 20 characters!");
//    }
//
//    @Description
//    ("Register to system with Confirm Password blank")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_09_Register_ConfirmPassword_Blank() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password confirmation does not match password!");
//    }
//
//    @Description
//    ("Register to system with Confirm Password not match")
//    @Story("Register")
//    @Severity(SeverityLevel.CRITICAL)
//    @Test
//    public void TC_09_Register_ConfirmPassword_Not_Match() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox("12345678");
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("Password"),"Password confirmation does not match password!");
//    }
//
//    @Description
//    ("Register to system with valid information and NewsLetter No")
//    @Story("Register")
//    @Severity(SeverityLevel.MINOR)
//    @Test
//    public void TC_10_Register_Success_NewsLetter_No() {
//        firstName = dataFaker.getFirstName();
//        lastName = dataFaker.getLastName();
//        email = dataFaker.getEmail();
//        phone = dataFaker.getPhone();
//        password = dataFaker.getPassword();
//
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(firstName);
//        registerPO.inputToLastNameTextbox(lastName);
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPhoneTextbox(phone);
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox(password);
//
//        registerPO.clickToNewsletterNoCheckbox();
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getRegisterSuccessMessage(),"Your Account Has Been Created!");
//        System.out.println("User info: " + firstName + " " + lastName + " - " + email + " - " + phone + " - " + password);
//
//
//        myAccount = registerPO.clickToContinueButtonAtSuccessPage();
//        Assert.assertTrue(myAccount.isMyAccountLinkDisplayed());
//        Assert.assertTrue(myAccount.isMyAccountTitleDisplayed());
//        myAccount.clickAccountRightMenu("Logout");
//    }
//
//    @Description ("Register to system with valid information and NewsLetter Yes")
//    @Story("Register")
//    @Severity(SeverityLevel.NORMAL)
//    @Test
//    public void TC_11_Register_Success_NewsLetter_Yes() {
//        firstName = dataFaker.getFirstName();
//        lastName = dataFaker.getLastName();
//        email = dataFaker.getEmail();
//        phone = dataFaker.getPhone();
//        password = dataFaker.getPassword();
//
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(dataFaker.getFirstName());
//        registerPO.inputToLastNameTextbox(dataFaker.getLastName());
//        registerPO.inputToEmailTextbox(dataFaker.getEmail());
//        registerPO.inputToPhoneTextbox(dataFaker.getPhone());
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox(password);
//
//        registerPO.clickToNewsletterYesCheckbox();
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getRegisterSuccessMessage(),"Your Account Has Been Created!");
//        System.out.println("User info: " + firstName + " " + lastName + " - " + email + " - " + phone + " - " + password);
//
//        myAccount = registerPO.clickToContinueButtonAtSuccessPage();
//        System.out.printf("Email: " + email);
//        System.out.println("Password: " + password);
//        Assert.assertTrue(myAccount.isMyAccountLinkDisplayed());
//        Assert.assertTrue(myAccount.isMyAccountTitleDisplayed());
//        myAccount.clickAccountRightMenu("Logout");
//    }
//
//    @Description("Register to system with valid information and minimum data")
//    @Story("Register")
//    @Severity(SeverityLevel.NORMAL)
//    @Test
//    public void TC_12_Register_Success_MinData() {
//        email = dataFaker.getEmail();
//
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox("A");
//        registerPO.inputToLastNameTextbox("B");
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPhoneTextbox("012");
//        registerPO.inputToPasswordTextbox("1234");
//        registerPO.inputToConfirmPasswordTextbox("1234");
//
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getRegisterSuccessMessage(),"Your Account Has Been Created!");
//
//        myAccount = registerPO.clickToContinueButtonAtSuccessPage();
//        Assert.assertTrue(myAccount.isMyAccountLinkDisplayed());
//        Assert.assertTrue(myAccount.isMyAccountTitleDisplayed());
//        myAccount.clickAccountRightMenu("Logout");
//    }
//
//    @Description("Register to system with valid information and maximum char")
//    @Story("Register")
//    @Severity(SeverityLevel.NORMAL)
//    @Test
//    public void TC_13_Register_Success_MaxData() {
//        email = dataFaker.getEmail();
//
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox("asdf asda asdf asdf asda asdf as");
//        registerPO.inputToLastNameTextbox("asdf asda asdf asdf asda asdf as");
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPhoneTextbox("01234567890123456789012345678901");
//        registerPO.inputToPasswordTextbox("12345678901234567890");
//        registerPO.inputToConfirmPasswordTextbox("12345678901234567890");
//
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getRegisterSuccessMessage(),"Your Account Has Been Created!");
//
//        myAccount = registerPO.clickToContinueButtonAtSuccessPage();
//        Assert.assertTrue(myAccount.isMyAccountLinkDisplayed());
//        Assert.assertTrue(myAccount.isMyAccountTitleDisplayed());
//        myAccount.clickAccountRightMenu("Logout");
//    }
//
//    @Description
//    ("Register to system with SQL Injection in UserName field")
//    @Story("Register")
//    @Severity(SeverityLevel.NORMAL)
//    @Test
//    public void TC_14_Register_SQLInjection_UserName() {
//        myAccount = homepage.clickMyAccountMenuItem();
//        registerPO = myAccount.clickContinueButton();
//        registerPO.inputToFirstNameTextbox(" ' or '1'='1 ");
//        registerPO.inputToLastNameTextbox(lastName);
//        registerPO.inputToEmailTextbox(email);
//        registerPO.inputToPhoneTextbox(phone);
//        registerPO.inputToPasswordTextbox(password);
//        registerPO.inputToConfirmPasswordTextbox(password);
//        registerPO.clickToPrivacyPolicyCheckbox();
//        registerPO.clickToContinueButtonAtResigterPage();
//        Assert.assertEquals(registerPO.getErrorMessage("First Name"),"First Name must be between 1 and 32 characters!");
//    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriverThreadSafe();
    }
}
