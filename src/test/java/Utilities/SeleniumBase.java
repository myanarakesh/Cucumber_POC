package Utilities;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import Pages.searchPage;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBase {

    private static WebDriverWait wait;
    public static WebDriver driver;
    static Logger logger = Logger.getLogger(SeleniumBase.class.getName());

    public SeleniumBase(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(webDriver, Integer.parseInt(Utility.getProperty("explicitTime")));
    }

    public static void Clear(final WebElement element){
        SeleniumBase.isElementEnable(element);
        element.clear();
    }

    public static void sendKeys(WebElement element,String input){
        SeleniumBase.isElementEnable(element);
        element.sendKeys(input);
    }

    public static void submit(WebElement element){
        SeleniumBase.isElementEnable(element);
        element.submit();
    }

    public static void Click(final WebElement element) {
        element.isEnabled();
        element.click();
    }

    public static void JsClick(final WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                new Object[]{element});
    }

    public static boolean isElementVisible(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementEnable(final WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementSelected(final WebElement element) {
        try {
            return element.isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static String getElementText(final WebElement element) {
        SeleniumBase.isElementEnable(element);
        logger.info(element.getText());
        return element.getText();
    }

    public static void waitForClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForAlert(final WebElement element) {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementToBeSelected(final WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public static void waitForElementVisible(final WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElement(final WebElement element, final int timeoutInSeconds) {
        final WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementIsInvisible(final By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void mouseover(final WebElement element) {
        final Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
    }

    public static void dragAndDrop(final WebElement element, final int xOffset, final int yOffset) {
        final Actions builder = new Actions(driver);
        final Action dragAndDrop = builder.clickAndHold(element).moveByOffset(xOffset, yOffset).release().build();
        dragAndDrop.perform();
    }

    public static void doubleClick(final WebElement element) {
        final Actions builder = new Actions(driver);
        builder.doubleClick(element).build().perform();
    }

    public static void rightClick(final WebElement element) {
        final Actions builder = new Actions(driver);
        builder.contextClick(element).build().perform();
    }

    public static void dragAndDrop(final By by, final int xOffset, final int yOffset) {
        dragAndDrop(driver.findElement(by), xOffset, yOffset);
    }


    public static boolean isReadonly(final WebElement element) {
        return Boolean.valueOf(element.getAttribute("readonly")).booleanValue();
    }

    public static void scroll(final int x, final int y) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i <= x; i = i + 50) {
            js.executeScript("scroll(" + i + ",0)");
        }
        for (int j = 0; j <= y; j = j + 50) {
            js.executeScript("scroll(0," + j + ")");
        }
    }

    public static void zoomPlus() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.ADD).perform();
        actions = new Actions(driver);
        actions.keyUp(Keys.CONTROL).perform();
    }

    public static void zoomMinus() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).perform();
        actions = new Actions(driver);
        actions.keyUp(Keys.CONTROL).perform();
    }

    public static boolean isAlertPresent() {
        boolean flag = false;
        try {
            final Alert alert = driver.switchTo().alert();
            if (alert.hashCode() > 0)
                flag = true;

        } catch (final NoAlertPresentException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String getAlertText() {
        Alert alert = null;
        if (isAlertPresent()) {
            alert = driver.switchTo().alert();
        }
        return alert.getText();
    }

    public static void acceptAlert() {
        Alert alert = null;
        if (isAlertPresent()) {
            alert = driver.switchTo().alert();
        }
        alert.accept();
    }

    public static void dismissAlert() {
        Alert alert = null;
        if (isAlertPresent()) {
            alert = driver.switchTo().alert();
        }
        alert.dismiss();
    }

    public static void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void switchWindowByIndex(int index) {
        Set<String> handles = driver.getWindowHandles();
        String handle = (String) (new ArrayList(handles)).get(index);
        driver.switchTo().window(handle);
    }

    public static void switchWindowByName(String windowName){
        driver.switchTo().window(windowName);
    }

    public static void validateTitle(String lookingFor){
        if(lookingFor.equalsIgnoreCase("Home"))
            Assert.assertEquals(UrbanConst.homePageTitle,driver.getTitle());
        else if(lookingFor.equalsIgnoreCase("Track Order"))
            Assert.assertEquals(UrbanConst.trackOrderPageTitle,driver.getTitle());
    }
}