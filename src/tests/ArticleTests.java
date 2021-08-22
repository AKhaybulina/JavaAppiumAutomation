package tests;

import Azaza.SearchPageObjectAzaza;
import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testAssertTitleWithoutWait() {
        SearchPageObjectAzaza SearchPageObjectAzaza = new SearchPageObjectAzaza(driver);

        SearchPageObjectAzaza.initSearchInput();
        SearchPageObjectAzaza.typeSearchLine("Java");
        SearchPageObjectAzaza.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.assertArticleTitlePresentWithoutWait();
    }
}
