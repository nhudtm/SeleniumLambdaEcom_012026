package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.AddOnsModuleUI;

public class AddOnsDesignsPO extends MenuCategoryPO {


    public AddOnsDesignsPO(WebDriver driver) {
        super(driver);
       }

    public boolean isAddOnsDesignsTitleDisplayed() {
        waitForElementVisible( AddOnsModuleUI.ADD_ONS_DESIGNS_TITLE);
        return isElementDisplayed(AddOnsModuleUI.ADD_ONS_DESIGNS_TITLE);
    }

}
