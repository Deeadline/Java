package firstTest;

import com.company.page.PageObjectFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestDropdownSearch {
    static private PageObjectFactory webPage;
    private By countrySearch = By.id("country");
    private By multiSearch = By.xpath("//select[contains(@class, 'js-example-basic-multiple')]");
    private By disabledSelect = By.xpath("//select[contains(@class,'js-example-disabled-results')]");
    private By multiValues = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div[2]/span/span[1]/span/ul");
    private By selectCategory = By.id("files");
    private By dropdownCategories = By.xpath("//optgroup[contains(@label,'Programming languages')]/option");
    @BeforeClass
    public static void runPage(){
        webPage = new PageObjectFactory("http://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        webPage.openPage();
    }
    @Test
    public void testSearchCountryBox(){
        webPage.getWebdriver().findElement(countrySearch).sendKeys("New Ze");
        Assert.assertEquals("New Zealand",webPage.getWebdriver().findElement(By.xpath("//*[@id=\"select2-country-container\"]")).getText());
    }
    @Test
    public void testMultipleValues(){
        WebElement element = webPage.getWebdriver().findElement(multiSearch);
        element.sendKeys("Alabama");
        element.sendKeys("Alaska");
        element.sendKeys("Arizona");
        List<WebElement> elements = element.findElements(By.tagName("option"));
        Assert.assertEquals("Alabama",elements.get(0).getText());
        Assert.assertEquals("Alaska",elements.get(1).getText());
        Assert.assertEquals("Arizona",elements.get(2).getText());
    }
    @Test
    public void testDisabledValues(){
        WebElement element = webPage.getWebdriver().findElement(disabledSelect);
        element.sendKeys("American Samoa");
        Assert.assertEquals("American Samoa", webPage.getWebdriver().findElement(By.xpath("//*[@id=\"select2-th1z-container\"]")).getText());
    }
    @AfterClass
    static public void closePage(){
        webPage.closePage();
    }
}
