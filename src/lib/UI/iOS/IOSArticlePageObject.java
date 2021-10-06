package lib.UI.iOS;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        FIRST_TITLE = "id:Java (programming language)";
        SECOND_TITLE = "id:Python (programming language)";
        FOOTER_ELEMENT = "id:/View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Search";
        WIKI_MAIN_BUTTON = "id:W";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_ENABLED = "id:Saved. Activate to unsave.";

    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
