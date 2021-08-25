package tests.iOS;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {

        if (Platform.getInstance().isAndroid()) {
            return;
        }

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForAddOrEditPreferredText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();
    }
}
