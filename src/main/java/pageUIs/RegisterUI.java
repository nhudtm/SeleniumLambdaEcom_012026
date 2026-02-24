package pageUIs;

public class RegisterUI {
    public static final String FIRST_NAME_TEXTBOX = "id=input-firstname";
    public static final String LAST_NAME_TEXTBOX = "id=input-lastname" ;
    public static final String EMAIL_TEXTBOX = "id=input-email" ;
    public static final String PHONE_TEXTBOX = "id=input-telephone" ;
    public static final String PASSWORD_TEXTBOX = "id=input-password" ;
    public static final String CONFIRM_PASSWORD_TEXTBOX = "id=input-confirm" ;
    public static final String NEWSLETTER_CHECKBOX_YES = "xpath=//input[@id='input-newsletter-yes']/following-sibling::label" ;
    public static final String PRIVACY_POLICY_CHECKBOX= "xpath=//input[@name='agree']/following-sibling::label" ;
    public static final String CONTINUE_BUTTON = "xpath=//input[@type='submit']" ;
    public static final String REGISTER_SUCCESS_MESSAGE = "xpath=//h1[contains(@class,'page-title')]" ;
    public static final String ACCOUNT_CREATED_CONTINUE_BUTTON = "xpath=//a[text()='Continue']";

    public static final String DYNAMIC_ERROR_MESSAGE_BY_FIELD_NAME = "xpath=//div[contains(@class,'text-danger') and contains(text(),'%s')]";
    public static final String PRIVACY_POLICY_WARNING_MESSAGE = "xpath=//div[contains(@class,'alert-dismissible')]";
    public static final String NEWSLETTER_CHECKBOX_NO = "xpath=//input[@id='input-newsletter-no']/following-sibling::label" ;
    public static final String REGISTER_FORM_TITLE = "xpath=//h1[text()='Register Account']";
}
//input[@id='input-newsletter-yes']
//div[contains(@class,'alert-danger')]