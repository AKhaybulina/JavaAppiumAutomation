import io.appium.java_client.AppiumDriver;
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
}
