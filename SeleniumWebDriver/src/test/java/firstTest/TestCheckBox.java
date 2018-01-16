package firstTest;

import com.company.page.pages.CheckBoxPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCheckBox {
    private static CheckBoxPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new CheckBoxPage(webDriver);
        webPage.openPage();
    }
    @Test
    public void checkSingleCheckBox() {
        webPage.clickSingleCheckBox();
        Assert.assertEquals("Success - Check box is checked", webPage.showCheckBoxText());
    }

    @Test
    public void checkMultipleCheckBox() throws InterruptedException {
        webPage.clickOnCheckAllButton();
        Assert.assertEquals("Uncheck All",webPage.checkBoxValue());
        webPage.clickOnCheckAllButton();
        Assert.assertEquals("Check All",webPage.checkBoxValue());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }

}