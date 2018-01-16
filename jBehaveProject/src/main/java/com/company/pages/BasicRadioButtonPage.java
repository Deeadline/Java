package main.java.com.company.pages;

import main.java.com.company.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicRadioButtonPage extends PageObjectFactory<BasicRadioButtonPage> {
    private final String URL = "http://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
    private By maleRadioInput = By.xpath("//input[@value='Male'][@name='optradio']");
    private By maleRadioButtonClick = By.xpath("//button[@id='buttoncheck']");
    private By maleShowMessage = By.xpath("//p[@class='radiobutton']");

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

    public void closePage() {
        close();
    }
}
