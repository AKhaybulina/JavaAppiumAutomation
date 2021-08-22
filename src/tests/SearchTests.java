package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testTextOfSearchField() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String searchLineText = SearchPageObject.getSearchLineText();

        assertEquals("Неверный текст элемента", searchLineText, "Search…");
    }

    @Test
    public void testCheckWordsAtSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String searchWord = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.assertWordsAtSearch(searchWord);
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String searchWord = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchWord);

        SearchPageObject.assertQuantityOfSearchResultsMoreThanOne(2);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertQuantityOfSearchResultsIsZero();
    }
}
