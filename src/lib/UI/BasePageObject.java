package lib.UI;

import io.appium.java_client.AppiumDriver;

public class BasePageObject extends MainPageObject{

    protected static String
            SAVED_BUTTON = "id:Saved",
            EXPLORE_BUTTON = "id:Explore";

    public BasePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickSavedButton() {
        this.waitForElementAndClick(
                SAVED_BUTTON,
                "Cannot find button 'SAVED' to go to saved articles",
                25
        );
    }

    public void clickExploreButton() {
        this.waitForElementAndClick(
                EXPLORE_BUTTON,
                "Cannot find button 'EXPLORE' to go to main screen",
                25
        );
    }
}
