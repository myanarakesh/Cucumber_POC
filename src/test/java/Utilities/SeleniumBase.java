package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBase {

    private static final int ELEMENT_WAIT_TIMEOUT_IN_SECONDS = 10;
    private static final int PAGE_WAIT_TIMEOUT_IN_SECONDS = 60;
    private static WebDriverWait wait;
    static WebDriver driver;
    final static Actions builder = new Actions(driver);


    public SeleniumBase(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(webDriver, Integer.parseInt(Utility.getProperty("explicitTime")));
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

    public static String isAnyTextPresent(final WebElement element) {
        SeleniumBase.isElementEnable(element);
        final String text = element.getText();
        return text;
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
        final WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void mouseover(final WebElement element) {
        builder.moveToElement(element).build().perform();
    }

    public static void dragAndDrop(final WebElement element, final int xOffset, final int yOffset) {
        final Action dragAndDrop = builder.clickAndHold(element).moveByOffset(xOffset, yOffset).release().build();
        dragAndDrop.perform();
    }

    public static void doubleClick(final WebElement element) {
        builder.doubleClick(element).build().perform();
    }

    public static void rightClick(final WebElement element) {
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

    public static void switchWindow(final String url) throws Throwable {
        sleep(2000);
        String currentHandle = null;
        final Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
            currentHandle = driver.getWindowHandle();
        }
        if (currentHandle != null) {
            for (final String handle : handles) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().contains(url) && currentHandle.equals(handle) == false) {
                    break;
                }
            }
        } else {
            for (final String handle : handles) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().contains(url)) {
                    break;
                }
            }
        }
    }
}

