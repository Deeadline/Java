package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TableSearchFilterPage extends PageObjectFactory<TableSearchFilterPage> {
    private final String URL = "http://www.seleniumeasy.com/test/table-search-filter-demo.html";
    private By firstTablefilter = By.xpath("//input[contains(@class,'form-control')  and @placeholder='Filter by Task / Assignee / Status ']");
    private By tableText = By.xpath("/html/body/div[2]/div/div[2]/div[1]/div/table/tbody");
    private By idFilter = By.xpath("//input[contains(@class,'form-control')  and @placeholder='#']");
    private By filterUnlock = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/button");
    private By secondTableText = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/table/tbody");

    public TableSearchFilterPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void clickOnFirstFilter() {
        click(firstTablefilter);
    }

    public void provideFilterText(String text) {
        sendKeys(firstTablefilter, text);
    }

    public String getText() {
        return show(tableText);
    }

    public void unlockFilter() {
        click(filterUnlock);
    }

    public void clickOnIdFilter() {
        click(idFilter);
    }

    public void enterID(String text) {
        sendKeys(idFilter, text);
    }

    public String getSecondText() {
        return show(secondTableText);
    }

    public void closePage() {
        close();
    }
}
