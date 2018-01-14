package firstTest;

import com.company.page.TableSearchFilterPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestTableSearchFilter {
    private static TableSearchFilterPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new TableSearchFilterPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void tasksFilter() {
        webPage.clickOnFirstFilter();
        webPage.provideFilterText("SEO");
        Assert.assertEquals("3 SEO tags Loblab Dan failed qa", webPage.getText());
    }

    @Test
    public void listedUserFilter() {
        webPage.unlockFilter();
        webPage.clickOnIdFilter();
        webPage.enterID("3");
        Assert.assertEquals("3 larrypt Brigade Swarroon", webPage.getSecondText());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
