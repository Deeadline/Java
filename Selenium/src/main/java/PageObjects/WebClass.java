package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import javax.xml.xpath.*;
public class WebClass {
    protected WebDriver driver;
    private WebElement q;
    private WebElement btnl;

    public WebClass(WebDriver driver){
        this.driver=driver;
    }
    public void open(String url){
        driver.get(url);
    }
    public void close(){
        driver.quit();
    }
    public String getTitle(){
        return driver.getTitle();
    }
    public void searchFor(String searchTerm){
        q.sendKeys(searchTerm);
        btnl.click();
    }
    public void typeSearchTerm(String searchTerm){
        q.sendKeys(searchTerm);
    }
    public void clickOnSearch(){
        btnl.click();
    }
}
