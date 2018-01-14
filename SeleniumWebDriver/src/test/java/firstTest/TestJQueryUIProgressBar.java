package firstTest;

import com.company.page.pages.JQueryUIProgressBarPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestJQueryUIProgressBar {
    private static JQueryUIProgressBarPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new JQueryUIProgressBarPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void checkProgressBar() {
        webPage.startDownload();
        while (!webPage.getProgress().equals("Complete!")) {
        }
        Assert.assertEquals("Complete!", webPage.getProgress());
        webPage.closeButton();
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
