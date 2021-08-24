package tests.iOS;

import lib.UI.iOS.WelcomePageObject;
import lib.iOSTestCase;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThroughWelcome() {

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
