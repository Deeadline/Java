package firstTest;

import com.company.page.pages.BasicRadioButtonPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBasicRadioButton {
    static private BasicRadioButtonPage webPage;
    private static final WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void openPage() {
        webPage = new BasicRadioButtonPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void checkGender() {
        webPage.clickOnMaleRadioInput();
        webPage.submit();
        Assert.assertEquals("Radio button 'Male' is checked", webPage.checkMessage());
    }

    @Test
    public void checkGenderAndAgeGroup() {
        webPage.clickOnFemaleRadioInput();
        webPage.clickOnFirstGroupOfAge();
        webPage.submitFemaleAndAgeGroup();
        Assert.assertEquals("Sex : Female\nAge group: 0 - 5", webPage.showGroupMessage());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}