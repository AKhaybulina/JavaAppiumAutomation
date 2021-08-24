package lib.UI.Android;

import io.appium.java_client.AppiumDriver;
import lib.UI.MainPageObject;

public class NavigationUI extends MainPageObject {

    private static final String
    MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button 'My lists'",
                5
        );
    }
}
