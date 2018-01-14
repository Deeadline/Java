package firstTest;

import com.company.page.SimpleFormPage;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSimpleForm {
    private static SimpleFormPage webPage;
    private static WebDriver webDriver = new ChromeDriver();
    @BeforeClass
    public static void runPage() {
        webPage = new SimpleFormPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void testSingleInputForm() {
        webPage.clickOnTextBox();
        webPage.enterElementBox("My message");
        webPage.submit();
        Assert.assertEquals("My message", webPage.showMessage());
    }

    @Test
    public void testTwoInputForm() {
        webPage.clickOnFirstValueInput();
        webPage.enterFirstValue("2");
        webPage.clickOnSecondValueInput();
        webPage.enterSecondValue("2");
        webPage.getTotalSubmit();
        Assert.assertEquals("4", webPage.showSumMessage());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
