package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BootstrapDatePickersPage extends PageObjectFactory<BootstrapDatePickersPage> {
    private static final String URL = "http://www.seleniumeasy.com/test/bootstrap-date-picker-demo.html";
    private By dateInputPlaceholder = By.xpath("//input[contains(@class,'form-control') and @placeholder='dd/mm/yyyy']");
    private By todayInput = By.xpath("/html/body/div[3]/div[1]/table/tfoot/tr[1]/th");
    private By startDateInput = By.xpath("//input[contains(@class,'form-control') and @placeholder='Start date']");
    private By dateCalendar = By.xpath("/html/body/div[3]/div[1]/table");
    private By startDate = By.xpath("/html/body/div[3]/div[1]/table/tbody/tr[3]/td[6]");
    private By endDate = By.xpath("/html/body/div[3]/div[1]/table/tbody/tr[3]/td[7]");
    private By endDateInput = By.xpath("//input[contains(@class,'form-control') and @placeholder='End date']");
    private DateTimeFormatter dateFormatOutcome;
    private LocalDate todayDate;

    public BootstrapDatePickersPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void clickOnDateInputPlaceholder() {
        click(dateInputPlaceholder);
    }

    public void clickOnToday() {
        click(todayInput);
    }

    public DateTimeFormatter getDateFormatOutcome() {
        return dateFormatOutcome;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public String showDate() {
        return showAttribute(dateInputPlaceholder);
    }

    public void clickOnStartDateInput() {
        click(startDateInput);
    }

    public void clickOnStartDate() {
        click(startDate);
    }

    public void clickOnEndDateInput() {
        click(endDateInput);
    }

    public void clickOnEndDate() {
        click(endDate);
    }

    public String showStartDate() {
        return showAttribute(startDateInput);
    }

    public String showEndDate() {
        return showAttribute(endDateInput);
    }

    public boolean isActive() {
        return isDisplayed(dateCalendar);
    }

    public void setDateFormat() {
        //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d");
        dateFormatOutcome = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        todayDate = LocalDate.now();
    }

    public void closePage() {
        close();
    }
}
