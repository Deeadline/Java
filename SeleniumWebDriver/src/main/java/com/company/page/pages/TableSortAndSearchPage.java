package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableSortAndSearchPage extends PageObjectFactory<TableSortAndSearchPage> {
    private final String URL = "http://www.seleniumeasy.com/test/table-sort-search-demo.html";
    private By showEntires = By.xpath("//*[@id=\"example_length\"]");
    private By selectEntires = By.xpath("/html/body/div[2]/div/div[2]/div/div/div[1]/label/select");
    private By sortByAge = By.xpath("/html/body/div[2]/div/div[2]/div/div/table/thead/tr/th[4]");
    private By searchInput = By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/label/input");
    private By information = By.xpath("//*[@id=\"example_info\"]");
    private By tableBody = By.xpath("/html/body/div[2]/div/div[2]/div/div/table/tbody");
    private By searchedValue = By.xpath("/html/body/div[2]/div/div[2]/div/div/table/tbody/tr[2]");

    public TableSortAndSearchPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void clickOnEntires() {
        click(showEntires);
    }

    public void enterEntires(String text) {
        sendKeys(selectEntires, text);
    }

    public void sortByAge() {
        click(sortByAge);
    }

    public void clickOnSearchInput() {
        click(searchInput);
    }

    public void enterSearch(String text) {
        sendKeys(searchInput, text);
    }

    public String getInformation() {
        return show(information);
    }

    public void openPage() {
        getPage(URL);
    }

    List<WebElement> findElements() {
        return find(tableBody);
    }


    public String showElement() {
        return findElements().get(0).findElement(searchedValue).getText();
    }

    public void closePage() {
        close();
    }
}
