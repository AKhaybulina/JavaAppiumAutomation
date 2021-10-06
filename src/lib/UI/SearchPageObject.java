package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_RESULT_ELEMENT,
    SEARCH_EMPTY_RESULT_ELEMENT,
    TEXT_IN_SEARCH_LINE,
    SEARCH_RESULT_ELEMENT_TITLE,
    CLEAN_SEARCH_LINE_BUTTON;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 15);
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

    public void clearSearchLine() {
        this.waitForElementAndClick(CLEAN_SEARCH_LINE_BUTTON, "errro", 10);
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
        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementAndGetAttribute(TEXT_IN_SEARCH_LINE, "text", "Cannot find text at search line", 5);
        } else
            return this.waitForElementAndGetAttribute(TEXT_IN_SEARCH_LINE, "value", "Cannot find text at search line", 5);

    }

    public void assertWordsAtSearch(String searchWord) {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT_TITLE, "error", 15);
        int i = 1;
        do {
            if (Platform.getInstance().isAndroid()) {
                Assert.assertTrue("Нужного слова нет в результатах поиска", findAllElementsOnPage(SEARCH_RESULT_ELEMENT_TITLE).get(i).getAttribute("text").contains(searchWord));
            } else
                Assert.assertTrue("Нужного слова нет в результатах поиска" + findAllElementsOnPage(SEARCH_RESULT_ELEMENT_TITLE).get(i).getAttribute("value"), findAllElementsOnPage(SEARCH_RESULT_ELEMENT_TITLE).get(i).getAttribute("value").contains(searchWord));

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
