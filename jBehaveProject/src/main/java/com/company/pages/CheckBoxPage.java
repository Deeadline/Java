package main.java.com.company.pages;

import main.java.com.company.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxPage extends PageObjectFactory<CheckBoxPage> {
    private final String URL = "http://www.seleniumeasy.com/test/basic-checkbox-demo.html";
    private By singleCheckBox = By.xpath("//*[@id=\"isAgeSelected\"]");
    private By checkBoxText = By.xpath("//*[@id=\"txtAge\"]");

    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void closePage() {
        close();
    }

    public void clickSingleCheckBox() {
        click(singleCheckBox);
    }

    public boolean contain(String text) {
        return isContain(text);
    }

}
