package firstTest;

import com.company.page.PageObjectFactory;
import org.junit.*;
import org.openqa.selenium.By;

public class TestBasicRadioButton {
    static private PageObjectFactory webPage;
    private By maleRadioInput = By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/label[1]/input");
    private By maleRadioButtonClick = By.xpath("//*[@id=\"buttoncheck\"]");
    private By maleRadioInput2 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[1]/label[1]/input");
    private By ageRadioInput = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/label[1]/input");
    private By checkGenderAndAgeButtonClick = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/button");

    @BeforeClass
    public static void openPage(){
        webPage = new PageObjectFactory("http://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
        webPage.openPage();
    }
    @Test
    public void checkGender(){
        webPage.getWebdriver().findElement(maleRadioInput).click();
        webPage.getWebdriver().findElement(maleRadioButtonClick).click();
        String check =webPage.getWebdriver().findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/p[3]")).getText();
        Assert.assertEquals("Radio button 'Male' is checked", check);
    }
    @Test
    public void checkGenderAndAgeGroup(){
        webPage.getWebdriver().findElement(maleRadioInput2).click();
        webPage.getWebdriver().findElement(ageRadioInput).click();
        webPage.getWebdriver().findElement(checkGenderAndAgeButtonClick).click();
        String check =webPage.getWebdriver().findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/p[2]")).getText();
        Assert.assertEquals("Sex : Male\nAge group: 0 - 5", check);
    }
    @AfterClass
    public static void closePage(){
        webPage.closePage();
    }
}
