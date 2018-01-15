package com.company.stories;

import com.company.page.pages.SimpleFormPage;
import com.thoughtworks.selenium.Selenium;
import org.jbehave.core.annotations.*;
import org.jbehave.web.selenium.SeleniumSteps;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleFormPageSteps extends SeleniumSteps {
    private WebDriver webDriver = new ChromeDriver();
    private SimpleFormPage webPage;


    @Given("I want to open page")
    public void openPage() {
        webPage.openPage();
    }

    @When("I provide first number $number1")
    public void enterFirstNumber(@Named("number1") String number1) {
        System.out.println(number1);
        webPage.clickOnFirstValueInput();
        webPage.provideFirstNumber(number1);
    }

    @When("I provide second number $number2")
    public void enterSecondNumber(String number2) {
        System.out.println(number2);
        webPage.clickOnSecondValueInput();
        webPage.provideSecondNumber(number2);
    }

    @Then("I get the result as $result")
    public void checkResult(String result) {
        System.out.println(result);
        Assert.assertEquals(result, webPage.showResult());
    }

    @AfterScenario
    public void closePage() {
        webPage.closePage();
    }
}
