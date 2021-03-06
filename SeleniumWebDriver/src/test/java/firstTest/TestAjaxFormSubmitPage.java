package firstTest;

import com.company.page.pages.AjaxFormSubmitPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestAjaxFormSubmitPage {
    static private AjaxFormSubmitPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new AjaxFormSubmitPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void ajaxFormSubmitTest() throws InterruptedException {
        webPage.clickOnTitle();
        webPage.enterTitle("Title");
        webPage.clickOnDescription();
        webPage.enterDescription("Description");
        webPage.submit();
        Assert.assertEquals("Ajax Request is Processing!", webPage.showMessage());
        Thread.sleep(2000);
        Assert.assertEquals("Form submited Successfully!", webPage.showMessage());
    }

    @AfterClass
    static public void closePage() {
        webPage.closePage();
    }
}
