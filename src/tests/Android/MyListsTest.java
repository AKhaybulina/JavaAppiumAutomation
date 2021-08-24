package tests.Android;

import lib.CoreTestCase;
import lib.UI.Android.ArticlePageObject;
import lib.UI.Android.MyListsPageObject;
import lib.UI.Android.NavigationUI;
import lib.UI.Android.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    @Test
    public void testSaveTwoArticlesToMyList() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        String first_search_line = "Java";
        String second_search_line = "Python";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(first_search_line);
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
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
