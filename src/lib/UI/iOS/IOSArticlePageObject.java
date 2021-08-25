package lib.UI.iOS;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:/View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
