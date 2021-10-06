package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

public class MyListsPageObject extends MainPageObject {

    public MyListsPageObject(AppiumDriver driver) {
            super(driver);
        }
    private static final String
    FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']",
    LOG_IN_POP_UP = "id:Sync your saved articles?",
    CLOSE_LOG_IN_POP_UP_BUTTON = "id:Close",
    DELETE_ARTICLE_BUTTON = "id:swipe action delete";

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(folder_name_xpath, "Cannot find folder by name '" + name_of_folder + "'");
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name '" + name_of_folder + "'",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article;
        if (Platform.getInstance().isAndroid()) {
            article = getSavedArticleXpathByTitle(article_title);
        } else article = "id:" + article_title;

            this.waitForElementPresent(
                    article,
                    "Cannot find saved article by title '" + article_title + "'",
                    15
            );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article;
        if (Platform.getInstance().isAndroid()) {
           article = getSavedArticleXpathByTitle(article_title);
        } else article = "id:" + article_title;
        this.waitForElementNotPresent(
                article,
                "Saved article still present with title '" + article_title + "'",
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article;
        if (Platform.getInstance().isAndroid()) {
            article = getSavedArticleXpathByTitle(article_title);
        } else article = "id:" + article_title;
        this.swipeElementToLeft(
                article,
                "Cannot find saved article"
        );
    }

    public void clickOnArticle(String articleName) {
        this.waitForElementAndClick(
                "xpath://*[@text='" + articleName + "']",
                "Cannot find list with articles",
                5);
    }

    public void clickCloseOnLogInPopUp() {
        this.waitForElementPresent(
                LOG_IN_POP_UP,
                "Cannot log-in popup"
        );
        this.waitForElementAndClick(
                CLOSE_LOG_IN_POP_UP_BUTTON,
                "Cannot screen name 'SAVED'",
                5
        );
    }

    public void clickDeleteArticleButton() {
        this.waitForElementAndClick(
                DELETE_ARTICLE_BUTTON,
                "Cannot find button 'DELETE'",
                5
        );
    }
}
