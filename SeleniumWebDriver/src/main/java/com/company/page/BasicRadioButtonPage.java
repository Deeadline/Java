package com.company.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicRadioButtonPage extends PageObjectFactory<BasicRadioButtonPage> {
    private final String URL = "http://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
    private By maleRadioInput = By.xpath("//input[@value='Male'][@name='optradio']");
    private By maleRadioButtonClick = By.xpath("//button[@id='buttoncheck']");
    private By maleShowMessage = By.xpath("//p[@class='radiobutton']");
    private By femaleRadioInput2 = By.xpath("//input[@value='Female'][@name='gender']");
    private By ageRadioInput = By.xpath("//input[@value='0 - 5'][@name='ageGroup']");
    private By checkGenderAndAgeButtonClick = By.xpath("//button[@onclick='getValues();']");
    private By groupShowMessage = By.xpath("//p[@class='groupradiobutton']");

    public BasicRadioButtonPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void clickOnMaleRadioInput() {
        click(maleRadioInput);
    }

    public void submit() {
        click(maleRadioButtonClick);
    }

    public String checkMessage() {
        return show(maleShowMessage);
    }

    public void clickOnFemaleRadioInput() {
        click(femaleRadioInput2);
    }

    public void clickOnFirstGroupOfAge() {
        click(ageRadioInput);
    }

    public void submitFemaleAndAgeGroup() {
        click(checkGenderAndAgeButtonClick);
    }

    public String showGroupMessage() {
        return show(groupShowMessage);
    }

    public void closePage() {
        close();
    }
}
