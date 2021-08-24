package lib.UI.Android;

import io.appium.java_client.AppiumDriver;
import lib.UI.MainPageObject;
import org.junit.Assert;

public class SearchPageObject extends MainPageObject {

    private static final String
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
    SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Clear query\"]",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
    TEXT_IN_SEARCH_LINE = "id:org.wikipedia:id/search_src_text",
    SEARCH_RESULT_ELEMENT_TITLE = "id:page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubString(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultForSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public String getSearchLineText() {
        return this.waitForElementAndGetAttribute(TEXT_IN_SEARCH_LINE, "text", "Cannot find text at search line", 5);
    }

    public void assertWordsAtSearch(String searchWord) {
        int i = 0;
        do {
            Assert.assertTrue("Нужного слова нет в результатах поиска", this.findAllElementsOnPage(SEARCH_RESULT_ELEMENT_TITLE).get(i).getAttribute("text").contains(searchWord));
            i++;
        } while (i < getAmountOfFoundArticles());
    }

    public void assertQuantityOfSearchResultsMoreThanOne(int minQuantity) {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find search results", 10);
        Assert.assertTrue("Quantity of elements ", findAllElementsOnPage(SEARCH_RESULT_ELEMENT).size() >= minQuantity);
    }

    public void assertQuantityOfSearchResultsIsZero() {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Cannot find search results", 10);
    }
}
