package tests.Android;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
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
        String second_article_title;

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(first_search_line);
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String first_article_title = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else
            articlePageObject.addArticleToSaved();
        articlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clearSearchLine();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(second_search_line);
        if (Platform.getInstance().isAndroid()) {
            searchPageObject.clickByArticleWithSubString("General-purpose programming language");
        } else
            searchPageObject.clickByArticleWithSubString("General-purpose, high-level programming language");
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            second_article_title = articlePageObject.getArticleTitle();
        } else {
            articlePageObject.waitForSecondTitleElementForIOS();
            second_article_title = articlePageObject.getSecondArticleTitleForIOS();
        }

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingList(nameOfFolder);
            articlePageObject.closeArticle();
        } else
            articlePageObject.addArticleToSaved();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        BasePageObject basePageObject = new BasePageObject(driver);

        if (Platform.getInstance().isAndroid()) {
            NavigationUI NavigationUI = new NavigationUI(driver);
            NavigationUI.clickMyLists();

            myListsPageObject.openFolderByName(nameOfFolder);
        }
        else {
            articlePageObject.clickWikiMainButton();
            basePageObject.clickSavedButton();
            myListsPageObject.clickCloseOnLogInPopUp();
        }
        myListsPageObject.swipeByArticleToDelete(first_article_title);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.waitForArticleToDisappearByTitle(first_article_title);
            myListsPageObject.clickOnArticle(second_article_title);

            String name_of_article_at_folder = articlePageObject.getArticleTitle();

            Assert.assertEquals("Article at folder not  '" + second_article_title + "'", second_article_title, name_of_article_at_folder);
        }
        else {
            myListsPageObject.clickDeleteArticleButton();
            myListsPageObject.waitForArticleToDisappearByTitle(first_article_title);
            basePageObject.clickExploreButton();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine(second_search_line);
            searchPageObject.clickByArticleWithSubString("General-purpose, high-level programming language");
            articlePageObject.waitForSecondTitleElementForIOS();
            articlePageObject.assertThatArticleIsSaved();
        }
    }

}
