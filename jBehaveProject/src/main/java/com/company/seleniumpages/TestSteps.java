package main.java.com.company.seleniumpages;


import main.java.com.company.pages.BasicRadioButtonPage;
import main.java.com.company.pages.FileDownloadPage;
import main.java.com.company.pages.CheckBoxPage;
import main.java.com.company.pages.SimpleFormPage;
import main.java.com.company.pages.TableSortAndSearchPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class TestSteps {

    private WebDriver driver;
    private BasicRadioButtonPage radioPage;
    private FileDownloadPage fileDownloadPage;
    private CheckBoxPage checkBoxPage;
    private SimpleFormPage simpleFormPage;
    private TableSortAndSearchPage tableFilterPage;

    @Given("I wanna set my driver")
    public void setUp() {
        driver = new FirefoxDriver();
    }

    //First page
    @Given("I wanna go to simpleform page")
    public void turnOnSimpleFormPage() {
        simpleFormPage = new SimpleFormPage(driver);
        simpleFormPage.openPage();
    }

    @When("I click on textbox")
    public void clickMessage() {
        simpleFormPage.clickOnTextBox();
    }

    @Then("I want to provide $message")
    public void inputMessage(@Named("message") String message) {
        simpleFormPage.enterElementBox(message);
    }

    @When("I wanna look on textbox")
    public void clickOnTextBoxButton() {
        simpleFormPage.submit();
    }

    @Then("I want to see $inputedMessage")
    public void checkSimpleFormPageAssertion(@Named("inputedMessage") String inputedMessage) {
        Assert.assertTrue(simpleFormPage.showMessage().contains(inputedMessage));
    }

    @Given("Finally I want to close simpleform page")
    public void closeSimpleFormPage() {
        simpleFormPage.closePage();
    }


    //Second page
    @Given("I wanna go to checkbox page")
    public void openCheckBoxPage() {
        checkBoxPage = new CheckBoxPage(driver);
        checkBoxPage.openPage();
    }

    @When("I click on checkbox")
    public void clickCheckbox() {
        checkBoxPage.clickSingleCheckBox();
    }

    @Then("I wanna see $success")
    public void checkCheckBoxPageAssertion(@Named("success") String successInformation) {
        Assert.assertTrue(checkBoxPage.contain(successInformation));
    }

    @Given("Finally I want to close checkbox page")
    public void closeCheckBoxPage() {
        checkBoxPage.closePage();
    }


    //Third page
    @Given("I wanna go to radio button page")
    public void openRadioButtonPage() {
        radioPage = new BasicRadioButtonPage(driver);
        radioPage.openPage();
    }

    @When("I click on Male button")
    public void clickOnRadioButton() {
        radioPage.clickOnMaleRadioInput();
    }

    @When("I click on submit button")
    public void clickOnSubmitButton() {
        radioPage.submit();
    }

    @Then("I wanna see $maleText")
    public void checkRadioButtonPageAssertion(@Named("maleText") String maleText) {
        Assert.assertTrue(radioPage.checkMessage().contains(maleText));
    }

    @Given("Finally I want to close radio button page")
    public void closeRadioButtonPage() {
        radioPage.closePage();
    }


    //Fourth page

    @Given("I wanna go to file download page")
    public void openFileDownloadPage() {
        fileDownloadPage = new FileDownloadPage(driver);
        fileDownloadPage.openPage();
    }

    @Given("I want to click on dataProvider")
    public void clickOnFileDataProvider() {
        fileDownloadPage.clickOnDataProvider();
    }

    @When("I enter $sampleText")
    public void inputDate(@Named("sampleText") String sampleText) {
        fileDownloadPage.enterData(sampleText);
    }

    @Then("I wanna see $result")
    public void checkDateAssertion(@Named("result") String result) {
        Assert.assertTrue(fileDownloadPage.showCharactersRemaining().equals(result));
    }

    @When("I see active generator")
    public void checkGeneratorAssertion() {
        Assert.assertTrue(fileDownloadPage.isActiveGenerator());
    }

    @Then("I want to generate file")
    public void clickOnGenerate() {
        fileDownloadPage.clickOnGenerateFileButton();
    }

    @When("I see active downloader")
    public void checkActiveAssertion() {
        Assert.assertTrue(fileDownloadPage.isActiveDownloader());
    }

    @Then("I want to download a file")
    public void clickOnDownload() {
        fileDownloadPage.clickOnDownloader();
    }

    @Given("And I want to see a file save panel")
    public void checkPage() {
        Assert.assertTrue(fileDownloadPage.isContaining());
    }

    @Given("Finally I want to close file downloader page")
    public void closeFileDownloaderPage() {
        fileDownloadPage.closePage();
    }

    //Fifth page
    @Given("I wanna go to table filter page")
    public void openTableFilterPage() {
        tableFilterPage = new TableSortAndSearchPage(driver);
        tableFilterPage.openPage();
    }

    @When("I click on entries filter")
    public void clickOnSEntriesFilter() {
        tableFilterPage.clickOnEntires();
    }

    @Then("I wanna see $number entries")
    public void provideFiftyEntriesToFilter(@Named("number") String number) {
        tableFilterPage.enterEntires(number);
    }

    @When("I sort table by age")
    public void sortByAge() {
        tableFilterPage.sortByAge();
    }

    @When("I click on search box")
    public void clickOnSearchBox() {
        tableFilterPage.clickOnSearchInput();
    }

    @When("I provide $text for filter")
    public void provideFilterText(@Named("text") String text) {
        tableFilterPage.enterSearch(text);
    }

    @Then("I wanna see $firstText and $secondText below the Table")
    public void checkFilteredText(@Named("firstText") String firstText, @Named("secondText") String secondText) {
        Assert.assertTrue(tableFilterPage.getInformation().contains(firstText));
        Assert.assertTrue(tableFilterPage.showElement().contains(secondText));
    }

    @Given("Finally I want to close table filter page")
    public void closeTableFilterPage() {
        tableFilterPage.closePage();
    }
}
