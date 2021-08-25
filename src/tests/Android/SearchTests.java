package tests.Android;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.SearchPageObject;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testTextOfSearchField() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String searchLineText = searchPageObject.getSearchLineText();

        if (Platform.getInstance().isAndroid()) {
            assertEquals("Неверный текст элемента", searchLineText, "Search…");
        } else
            assertEquals("Неверный текст элемента", searchLineText, "Search Wikipedia");
    }

    @Test
    public void testCheckWordsAtSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String searchWord = "Python";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.assertWordsAtSearch(searchWord);
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String searchWord = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchWord);

        SearchPageObject.assertQuantityOfSearchResultsMoreThanOne(2);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertQuantityOfSearchResultsIsZero();
    }
}
