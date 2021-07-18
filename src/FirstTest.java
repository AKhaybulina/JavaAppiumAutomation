import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver<WebElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/akhaybulina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testTextOfSearchField() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Невозможно найти поле  'Search Wikipedia'",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Неверный текст элемента",
                5
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Невозможно найти поле  'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Невозможно найти поле ввода поиска",
                5
        );

        assertQuantityOfElements(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Элемент(ы) не найден(ы) на странице",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Невозможно найти 'Х' в поле ввода поиска",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Элемент(ы) обнаружен(ы) на странице",
                5
        );
    }

    @Test
    public void testCheckWordsAtSearch() {
        String searchWord = "Java";
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Невозможно найти поле  'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchWord,
                "Невозможно найти поле ввода поиска",
                5
        );

        assertQuantityOfElements(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Элемент(ы) не найден(ы) на странице",
                5
        );

        assertWordsAtSearch(
                By.id("org.wikipedia:id/page_list_item_title"),
                searchWord,
                "Нужного слова нет в результатах поиска",
                5
        );
    }

    @Test
    public void saveTwoArticlesToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String first_search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                first_search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + first_search_line,
                5
        );

        String name_of_first_article = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to find more article options",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.widget.ListView']"),
                "Cannot find options menu",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String nameOfFolder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("text_input"),
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String second_search_line = "Python";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                second_search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='General-purpose programming language']"),
                "Cannot find 'General-purpose programming language' topic searching by " + second_search_line,
                5
        );

        String name_of_second_article = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to find more article options",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.widget.ListView']"),
                "Cannot find options menu",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.FrameLayout']//*[@text='" + nameOfFolder + "']"),
                "Cannot find folder with saved article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button 'My lists'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + nameOfFolder + "']"),
                "Cannot find list with articles",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + name_of_first_article + "']"),
                "Cannot find list with articles",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='" + name_of_first_article + "']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + name_of_first_article + "']"),
                "Cannot delete saved article",
                5
        );

        String name_of_article_at_folder = waitForElementAndGetAttribute(
                By.id("page_list_item_title"),
                "text",
                "Cannot find articles at folder",
                15
        );

        Assert.assertEquals("Cannot find article '" + name_of_second_article + "' at folder", name_of_second_article, name_of_article_at_folder);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement assertElementHasText(By by, String expectedText, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        String textAtElement = element.getAttribute("text");
        Assert.assertEquals("Неверный текст!", expectedText, textAtElement);
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private List<WebElement> findAllElementsOnPage(By by, String error_message, long timeoutInSeconds) {
        waitForElementPresent(by, error_message, timeoutInSeconds);
        return driver.findElements(by);
    }

    private void assertQuantityOfElements(By by, String error_message, long timeoutInSeconds) {
        Assert.assertTrue("Количество элементов ", findAllElementsOnPage(by, error_message, timeoutInSeconds).size() > 1);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private void assertWordsAtSearch(By by, String searchWord, String error_message, long timeoutInSeconds) {
        int i = 0;
        do {
            Assert.assertTrue("Нужного слова нет в заголовке", findAllElementsOnPage(by, error_message, timeoutInSeconds).get(i).getAttribute("text").contains(searchWord));
            i++;
        } while (i < findAllElementsOnPage(by, error_message, timeoutInSeconds).size());
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
