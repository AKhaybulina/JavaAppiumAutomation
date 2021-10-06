package lib.UI.iOS;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        TEXT_IN_SEARCH_LINE = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_ELEMENT_TITLE = "xpath://XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
        CLEAN_SEARCH_LINE_BUTTON = "id:Clear text";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
