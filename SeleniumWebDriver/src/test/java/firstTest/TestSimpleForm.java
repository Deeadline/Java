package firstTest;

import com.company.page.pages.SimpleFormPage;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
        webPage.provideFirstNumber("2");
        webPage.clickOnSecondValueInput();
        webPage.provideSecondNumber("2");
        webPage.checkAddition();
        Assert.assertEquals("4", webPage.showResult());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
