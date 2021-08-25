package lib.UI.Android;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_FULL_MENU = "xpath://*[@class = 'android.widget.ListView']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:onboarding_button";
        MY_LIST_NAME_INPUT = "id:text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
