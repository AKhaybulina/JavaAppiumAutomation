package tests.Android;

import lib.CoreTestCase;
import lib.UI.Android.AndroidSearchPageObject;
import lib.UI.ArticlePageObject;
import lib.UI.MyListsPageObject;
import lib.UI.NavigationUI;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    @Test
    public void testSaveTwoArticlesToMyList() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        String first_search_line = "Java";
        String second_search_line = "Python";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(first_search_line);
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String first_article_title = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";

        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(second_search_line);
        searchPageObject.clickByArticleWithSubString("General-purpose programming language");
        articlePageObject.waitForTitleElement();
        String second_article_title = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToExistingList(nameOfFolder);
        articlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(nameOfFolder);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.clickOnArticle(second_article_title);

        String name_of_article_at_folder = articlePageObject.getArticleTitle();

        Assert.assertEquals("Article at folder not  '" + second_article_title + "'", second_article_title, name_of_article_at_folder);
    }

}
