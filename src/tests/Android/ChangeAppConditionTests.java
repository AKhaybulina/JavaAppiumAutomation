package tests.Android;

import Azaza.ArticlePageObjectAzaza;
import Azaza.SearchPageObjectAzaza;
import lib.CoreTestCase;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationSearchResults() {
        SearchPageObjectAzaza SearchPageObjectAzaza = new SearchPageObjectAzaza(driver);

        SearchPageObjectAzaza.initSearchInput();
        SearchPageObjectAzaza.typeSearchLine("Java");
        SearchPageObjectAzaza.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObjectAzaza ArticlePageObjectAzaza = new ArticlePageObjectAzaza(driver);
        String title_before_rotation = ArticlePageObjectAzaza.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObjectAzaza.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObjectAzaza.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }
}
