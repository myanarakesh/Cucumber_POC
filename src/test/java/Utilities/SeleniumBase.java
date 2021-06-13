package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
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

    static WebDriver driver;

    public SeleniumBase(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    public static boolean isElementPresent(final WebElement element) {
        try {
            element.getTagName();
        } catch (final NoSuchElementException ignored) {
            return false;
        } catch (final StaleElementReferenceException ignored) {
            return false;
        }
        return true;
    }

    public static boolean isElementPresent(final By by) {
        return driver.findElements(by).size() > 0;
    }

    public static boolean isElementVisible(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementVisible(final By by) {
        return driver.findElement(by).isDisplayed();
    }

    public static boolean isAnyTextPresent(final WebElement element) {
        final String text = element.getText();
        //return StringUtils.isNotBlank(text);
        return true;
    }

    public static boolean isAnyTextPresent(final By by) {
        return isAnyTextPresent(driver.findElement(by));
    }

    public static void waitForElement(final WebElement element) {
        waitForElement(element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
    }

    public static void waitForElement(final WebElement element, final int timeoutInSeconds) {
        final WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElement(final By by) {
        waitForElement(by, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
    }

    public static void waitForElement(final By by, final int timeoutInSeconds) {
        final WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementIsInvisible(final By by) {
        final WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitForPageLoad() {
        waitForPageLoad(PAGE_WAIT_TIMEOUT_IN_SECONDS);
    }

    public static void waitForPageLoad(final int timeoutInSeconds) {
        waitForElement(By.tagName("html"), timeoutInSeconds);
    }

    public static String xpathFinder(final String... xpathList) {
        for (final String xpath : xpathList) {
            if (isElementPresent(By.xpath(xpath))) {
                return xpath;
            }
        }
        return null;
    }

    public static void mouseover(final WebElement element) {
        final Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
    }

    public static void mouseover(final By by) {
        mouseover(driver.findElement(by));
    }

    public static void dragAndDrop(final WebElement element, final int xOffset, final int yOffset) {
        final Actions builder = new Actions(driver);
        final Action dragAndDrop = builder.clickAndHold(element).moveByOffset(xOffset, yOffset).release().build();
        dragAndDrop.perform();
    }

    public static void dragAndDrop(final By by, final int xOffset, final int yOffset) {
        dragAndDrop(driver.findElement(by), xOffset, yOffset);
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

    public static boolean isReadonly(final WebElement element) {
        return Boolean.valueOf(element.getAttribute("readonly")).booleanValue();
    }

    public static boolean isEnabled(final WebElement element) {
        return Boolean.valueOf(element.isEnabled()).booleanValue();
    }

    public static boolean isReadonly(final By by) {
        return isReadonly(driver.findElement(by));
    }

    public static boolean isActive(final WebElement element) {
        return Boolean.valueOf(element.getAttribute("class").contains("active")
                || element.getAttribute("class").contains("label-highlight")).booleanValue();
    }

    public static boolean isActive(final By by) {
        return isActive(driver.findElement(by));
    }

    public static Point getElementPosition(final WebElement element) {
        return element.getLocation();
    }

    public static int getElementPositionX(final WebElement element) {
        final Point pos = getElementPosition(element);
        return pos.getX();
    }

    public static int getElementPositionY(final WebElement element) {
        final Point pos = getElementPosition(element);
        return pos.getY();
    }

    public static void backspaceInputClear(final WebElement element) {
        final int numberOfCharacters = element.getAttribute("value").length();
        for (int i = 0; i <= numberOfCharacters; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public static void backspaceInputClear(final WebElement element, final int numberOfCharacters) {
        for (int i = 0; i <= numberOfCharacters; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
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

    static JavascriptExecutor highlightElementPermanently(final WebElement element) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        return js;
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

    public static void takeScreenshot(final String path) {
//        try {
//            final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(scrFile, new File(path));
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
    }

    public static boolean isAlertPresent() {
        try {
            final Alert alert = driver.switchTo().alert();
            alert.getText();
        } catch (final NoAlertPresentException nape) {
            return false;
        }
        return true;
    }

    public static String getAlertText() {
        final Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static void acceptAlert() {
        final Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void dismissAlert() {
        final Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}

