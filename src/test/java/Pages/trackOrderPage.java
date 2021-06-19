package Pages;

import Utilities.DriverFactory;
import Utilities.Utility;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class trackOrderPage {
    private WebDriverWait wait;
    static Logger logger = Logger.getLogger(trackOrderPage.class.getName());

    public trackOrderPage(WebDriver webDriver) {

        wait = new WebDriverWait(webDriver, Integer.parseInt(Utility.getProperty("explicitTime")));
        PageFactory.initElements(webDriver, this);
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

    String trackOrderTitile = "Track Order | Urbanladder";

    public void clickOnTrackOrderLink() {
        trackOrderHyperLink.click();
    }

    public void validatePhoneNumberVisible() {
        phoneNumberEle.isEnabled();
    }

    public void validateOrderNumberVisible() {
        orderNumberEle.isEnabled();
    }

    public void verifyTrackOrderPage() {
        Assert.assertTrue("Unable to load track order page",
                obj.driver.getTitle().equals(trackOrderTitile));
    }

    public void clicksOnTrackYourOrder() {
        loginToTrackBtn.submit();
    }

    public void validateSocialMediaLoginToTrack() {
        Assert.assertTrue("Facebook or Google login option is missing",
                facebookLoginBtn.isEnabled() && googleLoginBtn.isEnabled());
    }

    public void validateSingOffAvailable() {
        Assert.assertTrue("Sign up option is available in track order page",
                signUpLink.isEnabled());
        logger.info("Sign Up option is available");
    }
}