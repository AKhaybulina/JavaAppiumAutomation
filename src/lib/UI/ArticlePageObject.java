package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            FIRST_TITLE,
            SECOND_TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON ,
            OPTIONS_FULL_MENU,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            WIKI_MAIN_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON_ENABLED;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(FIRST_TITLE, "Cannot find article title on page", 25);
    }

    public WebElement waitForSecondTitleElementForIOS() {
        return this.waitForElementPresent(SECOND_TITLE, "Cannot find article title on page", 25);
    }

    public String getArticleTitle() {
        this.waitForElementPresent(FIRST_TITLE, "Cannot find article title on page", 25);
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else
            return title_element.getAttribute("name");
    }

    public String getSecondArticleTitleForIOS() {
        this.waitForElementPresent(SECOND_TITLE, "Cannot find article title on page", 25);
        WebElement title_element = waitForSecondTitleElementForIOS();
        return title_element.getAttribute("name");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder) {

            this.waitForElementAndClick(
                    OPTIONS_BUTTON,
                    "Cannot find button to find more article options",
                    5
            );

            this.waitForElementPresent(
                    OPTIONS_FULL_MENU,
                    "Cannot find options menu",
                    5
            );

            this.waitForElementAndClick(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find option to add article to reading list",
                    5
            );

            this.waitForElementAndClick(
                    ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find 'Got it' tip overlay",
                    5
            );

            this.waitForElementAndClear(
                    MY_LIST_NAME_INPUT,
                    "Cannot find input to set name of articles folder",
                    5
            );

            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    name_of_folder,
                    "Cannot put text into articles folder input",
                    5
            );

            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot press OK button",
                    5
            );
    }

    public void addArticleToSaved() {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void addArticleToExistingList(String folderName) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to find more article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_FULL_MENU,
                "Cannot find options menu",
                10
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                10
        );

        String FOLDER_NAME = "xpath://*[@text='" + folderName +"']";

        this.waitForElementAndClick(
                FOLDER_NAME,
                "Cannot find button to find more article options",
                5
        );
    }

    public void assertArticleTitlePresentWithoutWait() {
        Assert.assertTrue("Cannot find element(s) on page", driver.findElements(By.id(FIRST_TITLE)).size() > 0);
    }

    public void clickWikiMainButton() {
        this.waitForElementAndClick(
                WIKI_MAIN_BUTTON,
                "Cannot find button 'W' to go to main screen",
                5
        );
    }

    public void assertThatArticleIsSaved() {
        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_ENABLED,
                "Cannot find enabled 'Save' button",
                5
        );
    }
}
