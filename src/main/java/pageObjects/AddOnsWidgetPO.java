package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.AddOnsModuleUI;

public class AddOnsWidgetPO extends MenuCategoryPO {

    public AddOnsWidgetPO(WebDriver driver) {
        super(driver);
       }

    public boolean isAddOnsWidgetsDisplayed() {
        waitForElementVisible(AddOnsModuleUI.ADD_ONS_WIDGETS_TITLE);
        return isElementDisplayed(AddOnsModuleUI.ADD_ONS_WIDGETS_TITLE);
    }
}
