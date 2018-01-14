package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AjaxFormSubmitPage extends PageObjectFactory<AjaxFormSubmitPage> {
    private static final String URL = "http://www.seleniumeasy.com/test/ajax-form-submit-demo.html";
    private By titleInputForm = By.xpath("//*[@id=\"title\"]");
    private By descriptionInputForm = By.xpath("//*[@id=\"description\"]");
    private By buttonAjaxSubmit = By.xpath("//*[@id=\"btn-submit\"]");
    private By ajaxSubmitText = By.xpath("//*[@id=\"submit-control\"]");

    public AjaxFormSubmitPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void clickOnTitle() {
        click(titleInputForm);
    }

    public void enterTitle(String text) {
        sendKeys(titleInputForm, text);
    }

    public void clickOnDescription() {
        click(descriptionInputForm);
    }

    public void enterDescription(String text) {
        sendKeys(descriptionInputForm, text);
    }

    public void submit() {
        click(buttonAjaxSubmit);
    }

    public String showMessage() {
        return show(ajaxSubmitText);
    }

    public void closePage() {
        close();
    }
}
