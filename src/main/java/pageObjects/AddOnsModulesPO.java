package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.AddOnsModuleUI;

public class AddOnsModulesPO extends MenuCategoryPO {

    public AddOnsModulesPO(WebDriver driver) {

        super(driver);

    }

    public boolean isAddOnsModulesTitleDisplayed() {
        waitForElementVisible( AddOnsModuleUI.ADD_ONS_MODULES_TITLE);
        return isElementDisplayed( AddOnsModuleUI.ADD_ONS_MODULES_TITLE);
    }



}
