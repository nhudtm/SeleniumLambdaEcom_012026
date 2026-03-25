package commons;

public final class AuthMessages {

    private AuthMessages() {
    }

    public static final String LOGIN_NO_MATCH_WARNING = "Warning: No match for E-Mail Address and/or Password.";
    public static final String LOGIN_NO_MATCH_WARNING_WITH_LEADING_SPACE = " Warning: No match for E-Mail Address and/or Password.";
    public static final String LOGIN_ACCOUNT_LOCKED_WARNING = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
    public static final String LOGIN_ACCOUNT_LOCKED_WARNING_WITH_LEADING_SPACE = " Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
    public static final String FORGOT_PASSWORD_EMAIL_NOT_FOUND_WARNING = "Warning: The E-Mail Address was not found in our records, please try again!";
    public static final String LOGIN_CONFIRMATION_LINK_SENT = "An email with a confirmation link has been sent your email address.";
}
