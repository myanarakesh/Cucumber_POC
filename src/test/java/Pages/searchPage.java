package Pages;

import Utilities.SeleniumBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.logging.Logger;

public class searchPage {
    static Logger logger = Logger.getLogger(searchPage.class.getName());
    SeleniumBase base;

    public searchPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        base = new SeleniumBase(webDriver);
    }

    @FindBy(xpath = " .//a[contains(text(),'Close')]")
    WebElement loginCloseIcon;

    @FindBy(id = "search")
    WebElement searchInput;

    @FindBy(id = "search_button")
    WebElement seachIcon;

    @FindBy(xpath = ".//span[@class='name']")
    List<WebElement> searchResultName1;

    @FindBy(className = "small")
    List<WebElement> searchResultName2;

    @FindBy(xpath = ".//strong[@class='tt-highlight']")
    List<WebElement> advanceSearchOptionsEle;

    @FindBy(className = "withsubtext")
    WebElement searchResultHeaderEle;

    @FindBy(tagName = "title")
    WebElement titleOfPage;

    public void closeLoginPopup() {
        SeleniumBase.waitForClick(loginCloseIcon);
        SeleniumBase.Click(loginCloseIcon);
    }

    public void searchAProduct(String search) {
        SeleniumBase.Clear(searchInput);
        SeleniumBase.sendKeys(searchInput,search);
    }

    public void clickOnSearchIcon() {
        SeleniumBase.Click(seachIcon);
    }

    public void validateTitleOfPage(String titleOfPage){
        SeleniumBase.validateTitle(titleOfPage);
    }

    public void validateSearchPage(String lookingFor){
        Assert.assertEquals("Search Results For '"+lookingFor+"'",searchResultHeaderEle.getText().trim());
    }

    public void verifySearchResult(String verifySearchResult) {

        System.out.println("List Of Search result of " + verifySearchResult);
        for (WebElement result : searchResultName1) {
            SeleniumBase.getElementText(result);
        }

        for (int i = 0; i < searchResultName1.size(); i++) {
            if (!searchResultName1.get(i).getText().toLowerCase().contains(verifySearchResult.toLowerCase())) {
                if (!searchResultName2.get(i).getText().toLowerCase().contains(verifySearchResult.toLowerCase())) {
                    Assert.assertTrue(searchResultName1.get(i).getText() + " is not matched with " + verifySearchResult
                            , false);
                }
            }
        }
    }

    public void searchAProductInSearchOptions(String product) {
        for (WebElement ele : advanceSearchOptionsEle) {
            Assert.assertTrue("Advance search is not matching",
                    ele.getText().toLowerCase().contains(product.toLowerCase()));
        }
    }
}