package firstTest;

import com.company.page.PageObjectFactory;
import org.junit.*;
import org.openqa.selenium.By;

public class TestSimpleForm {
    private static PageObjectFactory webPage;
    private By messageInput = By.xpath("//*[@id=\"user-message\"]");
    private By messageClick = By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/form/button");
    private By messageSumInput = By.xpath("//*[@id=\"sum1\"]");
    private By messageSumInput2 = By.xpath("//*[@id=\"sum2\"]");
    private By messageSumClick = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/form/button");
    @BeforeClass
    public static void runPage(){
        webPage = new PageObjectFactory("http://www.seleniumeasy.com/test/basic-first-form-demo.html");
        webPage.openPage();
    }
    @Test
    public void testSingleInputForm(){
        webPage.getWebdriver().findElement(messageInput).sendKeys("My message");
        webPage.getWebdriver().findElement(messageClick).click();
        Assert.assertEquals("My message", webPage.getWebdriver().findElement(By.xpath("//*[@id=\"display\"]")).getText());
    }
    @Test
    public void testTwoInputForm(){
        webPage.getWebdriver().findElement(messageSumInput).sendKeys("2");
        webPage.getWebdriver().findElement(messageSumInput2).sendKeys("2");
        webPage.getWebdriver().findElement(messageSumClick).click();
        Assert.assertEquals("4", webPage.getWebdriver().findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText());
    }
    @AfterClass
    public static void closePage(){
        webPage.closePage();
    }
}
