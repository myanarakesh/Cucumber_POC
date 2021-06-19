package Steps;

import Pages.searchPage;
import Utilities.Utility;
import io.cucumber.java.en.*;

public class SearchSteps {
    searchPage search = new searchPage(hooks.driver);

    @Given("^user navigates to the urbanLadder application$")
    public void navigateToHomePage() {
        hooks.driver.get(Utility.getProperty("url"));
    }

    @And("^I close the Sign or Sign up pop-up$")
    public void iCloseTheSignOrSignUpPopUp() {
        search.closeLoginPopup();
    }

    @Then("^I verify (.+) search result$")
    public void iVerifySearchResult(String searchAProduct) {
        search.verifySearchResult(searchAProduct);
    }

    @When("^i enter (.+) in search bar$")
    public void iEnterSearchAProductInSearchBar(String searchAProduct) {
        search.searchAProduct(searchAProduct);
    }

    @Then("I verify (.+) options under search bar")
    public void iVerifySearchAProductOptionsUnderSearchBar(String searchAProduct) {
        search.searchAProductInSearchOptions(searchAProduct);
    }

    @And("i click on Search Icon in search bar")
    public void iClickOnSearchIconInSearchBar() {
        search.clickOnSearchIcon();
    }
}
