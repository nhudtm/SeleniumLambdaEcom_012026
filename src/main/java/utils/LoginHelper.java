package utils;

import pageObjects.HomePO;
import pageObjects.MyAccountPO;

public class LoginHelper {
    public static void login(HomePO homePage, String email, String password) {
        MyAccountPO myAccount = homePage.clickMyAccountMenuItem();
        myAccount.inputToEmailTextbox(email);
        myAccount.inputToPasswordTextbox(password);
        myAccount.clickLoginButton();

    }
}
