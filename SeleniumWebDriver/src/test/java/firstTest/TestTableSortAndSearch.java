package firstTest;

import com.company.page.pages.TableSortAndSearchPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestTableSortAndSearch {
    private static TableSortAndSearchPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new TableSortAndSearchPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void tasksFilter() {
        webPage.clickOnEntires();
        webPage.enterEntires("50");
        webPage.sortByAge();
        webPage.clickOnSearchInput();
        webPage.enterSearch("London");

        Assert.assertEquals("Showing 1 to 7 of 7 entries (filtered from 32 total entries)", webPage.getInformation());
        Assert.assertEquals("J. Gaines Office Manager London 30 Fri 19th Dec 08 $90,560/y", webPage.showElement());

    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
