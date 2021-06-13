package Steps;

import Pages.trackOrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackOrderSteps {
    trackOrderPage trackOrderPageobj = new trackOrderPage(hooks.driver);

    @When("user clicks on Track Order option")
    public void userClicksOnTrackOrderOption() {
        trackOrderPageobj.clickOnTrackOrderLink();
    }

    @Then("user navigate to Track Order page")
    public void userNavigateToTrackOrderPage() {
        trackOrderPageobj.verifyTrackOrderPage();
    }

    @And("Verify Phone Number present on Track Order Page")
    public void verifyPhoneNumberPresentOnTrackOrderPage() {
        trackOrderPageobj.validatePhoneNumberVisible();
    }

    @And("Order Number present on Track Order Page")
    public void orderNumberPresentOnTrackOrderPage() {
        trackOrderPageobj.validateOrderNumberVisisble();
    }

    @When("user clicks on Login to Track your orders button")
    public void userClicksOnLoginToTrackYourOrdersButton() {
        trackOrderPageobj.clicksOnTrackYourOrder();
    }

    @Then("user sees social media option for login")
    public void userSeesSocialMediaOptionForLogin() {
        trackOrderPageobj.validateSocialMediaLoginToTrack();
    }

    @Then("Verify Sign Off link available")
    public void verifySignOffLinkAvailable() {
        trackOrderPageobj.validateSingOffAvailable();
    }
}
