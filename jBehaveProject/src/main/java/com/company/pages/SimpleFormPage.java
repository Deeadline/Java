package main.java.com.company.pages;

import main.java.com.company.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimpleFormPage extends PageObjectFactory<SimpleFormPage> {
    private static final String URL = "http://www.seleniumeasy.com/test/basic-first-form-demo.html";
    private By messageInput = By.xpath("//*[@id=\"user-message\"]");
    private By messageClick = By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/form/button");
    private By showMessage = By.xpath("//*[@id=\"display\"]");

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

    public String showMessage() {
        return show(showMessage);
    }


    public void closePage() {
        close();
    }

}
