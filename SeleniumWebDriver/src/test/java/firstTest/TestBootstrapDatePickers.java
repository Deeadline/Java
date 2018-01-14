package firstTest;

import com.company.page.pages.BootstrapDatePickersPage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.time.LocalDate;

public class TestBootstrapDatePickers {
    private static BootstrapDatePickersPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage(){
        webPage = new BootstrapDatePickersPage(webDriver);
        webPage.openPage();
    }
    @Before
    public void setDateFormat(){
        webPage.setDateFormat();
    }
    @Test
    public void testTodayDate() throws InterruptedException {
        webPage.clickOnDateInputPlaceholder();
        webPage.clickOnToday();
        Assert.assertEquals(webPage.getDateFormatOutcome().format(webPage.getTodayDate()), webPage.showDate());
    }
    @Test
    public void testDateRange() throws ParseException {
        webPage.clickOnStartDateInput();
        Assert.assertTrue(webPage.isActive());
        webPage.clickOnStartDate();
        webPage.clickOnEndDateInput();
        Assert.assertTrue(webPage.isActive());
        webPage.clickOnEndDate();
        LocalDate startDate = LocalDate.parse("18/01/2018",webPage.getDateFormatOutcome());
        LocalDate endDate = LocalDate.parse("20/01/2018",webPage.getDateFormatOutcome());
        Assert.assertEquals(startDate.format(webPage.getDateFormatOutcome()),webPage.showStartDate());
        Assert.assertEquals(endDate.format(webPage.getDateFormatOutcome()),webPage.showEndDate());
    }
    @AfterClass
    public static void closePage(){
        webPage.closePage();
    }
}
