package firstTest;

import com.company.page.pages.BootstrapAlertMessagesPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestBootstrapAlertMessages {
    private static BootstrapAlertMessagesPage webPage;
    private static WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new BootstrapAlertMessagesPage(webDriver);
        webPage.openPage();
    }
    @Before
    public void waitForNext() throws InterruptedException {
        Thread.sleep(2000);
    }
    @Test
    public void autoSuccessMessage() throws InterruptedException {
        webPage.clickOnAutoSuccessMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowAutoSuccessMessage()));
        Thread.sleep(6000);
        Assert.assertFalse(webPage.isActive(webPage.getShowAutoSuccessMessage()));
    }

    @Test
    public void normalSuccessMessage() {
        webPage.clickOnNormalSuccessMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowNormalSuccessMessage()));
        webPage.closeNormalMessage(webPage.getShowNormalSuccessMessage());
    }

    @Test
    public void autoWarningMessage() throws InterruptedException {
        webPage.clickOnAutoWarningMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowAutoWarningMessage()));
        Thread.sleep(6000);
        Assert.assertFalse(webPage.isActive(webPage.getShowAutoWarningMessage()));
    }

    @Test
    public void normalWarningMessage() {
        webPage.clickOnNormalWarningMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowNormalWarningMessage()));
        webPage.closeNormalMessage(webPage.getShowNormalWarningMessage());
    }

    @Test
    public void autoDangerMessage() throws InterruptedException {
        webPage.clickOnAutoDangerMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowAutoDangerMessage()));
        Thread.sleep(6000);
        Assert.assertFalse(webPage.isActive(webPage.getShowAutoDangerMessage()));
    }

    @Test
    public void normalDangerMessage() {
        webPage.clickOnNormalDangerMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowNormalDangerMessage()));
        webPage.closeNormalMessage(webPage.getShowNormalDangerMessage());
    }

    @Test
    public void autoInfoMessage() throws InterruptedException {
        webPage.clickOnAutoInfoMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowAutoInfoMessage()));
        Thread.sleep(6000);
        Assert.assertFalse(webPage.isActive(webPage.getShowAutoInfoMessage()));
    }

    @Test
    public void normalInfoMessage() throws InterruptedException {
        webPage.clickOnNormalInfoMessageButton();
        Assert.assertTrue(webPage.isActive(webPage.getShowNormalInfoMessage()));
        Thread.sleep(1000);
        webPage.closeNormalMessage(webPage.getShowNormalInfoMessage());
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
