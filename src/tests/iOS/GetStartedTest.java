package tests.iOS;

import lib.CoreTestCase;
import lib.UI.iOS.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

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
