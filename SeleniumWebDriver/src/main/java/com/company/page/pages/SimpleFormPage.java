package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimpleFormPage extends PageObjectFactory<SimpleFormPage> {
    private static final String URL = "http://www.seleniumeasy.com/test/basic-first-form-demo.html";
    private By messageInput = By.xpath("//*[@id=\"user-message\"]");
    private By messageClick = By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/form/button");
    private By messageSumInput = By.xpath("//*[@id=\"sum1\"]");
    private By messageSumInput2 = By.xpath("//*[@id=\"sum2\"]");
    private By messageSumClick = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/form/button");
    private By showMessage = By.xpath("//*[@id=\"display\"]");
    private By showSumMessage = By.xpath("//*[@id=\"displayvalue\"]");

    public SimpleFormPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void clickOnTextBox() {
        click(messageInput);
    }

    public void enterElementBox(String text) {
        sendKeys(messageInput, text);
    }

    public void submit() {
        click(messageClick);
    }

    public void clickOnFirstValueInput() {
        click(messageSumInput);
    }

    public void enterFirstValue(String text) {
        sendKeys(messageSumInput, text);
    }

    public void clickOnSecondValueInput() {
        click(messageSumInput2);
    }

    public void enterSecondValue(String text) {
        sendKeys(messageSumInput2, text);
    }

    public void getTotalSubmit() {
        click(messageSumClick);
    }

    public String showMessage() {
        return show(showMessage);
    }

    public String showSumMessage() {
        return show(showSumMessage);
    }

    public void closePage() {
        close();
    }
}
