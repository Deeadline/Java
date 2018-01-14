package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxPage extends PageObjectFactory<CheckBoxPage> {
    private final String URL = "http://www.seleniumeasy.com/test/basic-checkbox-demo.html";
    private By singleCheckBox = By.xpath("//*[@id=\"isAgeSelected\"]");
    private By checkBoxText = By.xpath("//*[@id=\"txtAge\"]");
    private By checkBoxAll = By.xpath("//*[@id=\"check1\"]");

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

    public String showCheckBoxText() {
        return show(checkBoxText);
    }

    public void clickOnCheckAllButton() {
        click(checkBoxAll);
    }

    public String checkBoxValue() {
        return showAttribute(checkBoxAll);
    }
}
