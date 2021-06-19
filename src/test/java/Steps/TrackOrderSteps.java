package Steps;

import Pages.trackOrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackOrderSteps {
    trackOrderPage trackOrderPageObj = new trackOrderPage(hooks.driver);

    @When("user clicks on Track Order option")
    public void userClicksOnTrackOrderOption() {
        trackOrderPageObj.clickOnTrackOrderLink();
    }

    @Then("user navigate to Track Order page")
    public void userNavigateToTrackOrderPage() {
        trackOrderPageObj.verifyTrackOrderPage();
    }

    @And("Verify Phone Number present on Track Order Page")
    public void verifyPhoneNumberPresentOnTrackOrderPage() {
        trackOrderPageObj.validatePhoneNumberVisible();
    }

    @And("Order Number present on Track Order Page")
    public void orderNumberPresentOnTrackOrderPage() {
        trackOrderPageObj.validateOrderNumberVisible();
    }

    @When("user clicks on Login to Track your orders button")
    public void userClicksOnLoginToTrackYourOrdersButton() {
        trackOrderPageObj.clicksOnTrackYourOrder();
    }

    @Then("user sees social media option for login")
    public void userSeesSocialMediaOptionForLogin() {
        trackOrderPageObj.validateSocialMediaLoginToTrack();
    }

    @Then("Verify Sign Off link available")
    public void verifySignOffLinkAvailable() {
        trackOrderPageObj.validateSingOffAvailable();
    }
}
