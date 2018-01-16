package firstTest;

import com.company.page.pages.FileDownloadPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFileDownload {
    private static FileDownloadPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new FileDownloadPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void testFileDownload(){
        webPage.clickOnDataProvider();
        webPage.enterData("Text");
        Assert.assertTrue(webPage.showCharactersRemaining().equals("496 characters remaining"));
        Assert.assertTrue(webPage.isActiveGenerator());
        webPage.clickOnGenerateFileButton();
        Assert.assertTrue(webPage.isActiveDownloader());
        webPage.clickOnDownloader();
        Assert.assertTrue(webPage.isContaining());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
