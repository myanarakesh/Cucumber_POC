package Pages;

import Utilities.DriverFactory;
import Utilities.SeleniumBase;
import Utilities.UrbanConst;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class trackOrderPage {

    static Logger logger = Logger.getLogger(trackOrderPage.class.getName());
    SeleniumBase base;

    public trackOrderPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        base = new SeleniumBase(webDriver);
    }

    @FindBy(xpath = ".//a[contains(text(),'Track Order')]")
    WebElement trackOrderHyperLink;

    @FindBy(name = "phoneNumber")
    WebElement phoneNumberEle;

    @FindBy(name = "orderNumber")
    WebElement orderNumberEle;

    @FindBy(xpath = ".//button[text()='Login To Track All Your Orders']")
    WebElement loginToTrackBtn;

    @FindBy(id = "facebook_login")
    WebElement facebookLoginBtn;

    @FindBy(id = "google_login")
    WebElement googleLoginBtn;

    @FindBy(xpath = ".//a[text()='Sign-up']")
    WebElement signUpLink;

    DriverFactory obj = new DriverFactory();

    public void clickOnTrackOrderLink() {
        SeleniumBase.Click(trackOrderHyperLink);
    }

    public void validatePhoneNumberVisible() {
        SeleniumBase.isElementEnable(phoneNumberEle);
    }

    public void validateOrderNumberVisible() {
        SeleniumBase.isElementEnable(orderNumberEle);
    }

    public void verifyTrackOrderPage() {
        Assert.assertTrue("Unable to load track order page",
                SeleniumBase.driver.getTitle().trim().equals(UrbanConst.trackOrderPageTitle));
    }

    public void clicksOnTrackYourOrder() {
        SeleniumBase.submit(loginToTrackBtn);
    }

    public void validateSocialMediaLoginToTrack() {
        Assert.assertTrue("Facebook or Google login option is missing",
                SeleniumBase.isElementEnable(facebookLoginBtn) && SeleniumBase.isElementEnable(googleLoginBtn));
    }

    public void validateSingOffAvailable() {
        Assert.assertTrue("Sign up option is available in track order page",
                SeleniumBase.isElementEnable(signUpLink));
        logger.info("Sign Up option is available");
    }
}