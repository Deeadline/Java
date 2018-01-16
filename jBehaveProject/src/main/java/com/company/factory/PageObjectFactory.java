package main.java.com.company.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageObjectFactory<T> {
    protected WebDriver webdriver;
    protected WebDriverWait webWait;

    public PageObjectFactory(WebDriver webdriver) {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        this.webdriver = webdriver;
        webWait = new WebDriverWait(webdriver, 30);
    }

    protected T getPage(String url) {
        webdriver.get(url);
        webdriver.manage().window().maximize();
        return (T) this;
    }

    protected T click(By clicker) {
        webdriver.findElement(clicker).click();
        return (T) this;
    }

    protected T sendKeys(By textInput, String text) {
        webdriver.findElement(textInput).sendKeys(text);
        return (T) this;
    }

    protected String show(By showMessage) {
        return webdriver.findElement(showMessage).getText();
    }

    protected String showAttribute(By showMessage) {
        return webdriver.findElement(showMessage).getAttribute("value");
    }

    protected boolean isDisplayed(By container) {
        return webdriver.findElement(container).isDisplayed();
    }

    protected boolean isEnabled(By container) {
        return webdriver.findElement(container).isEnabled();
    }

    protected boolean isContain(String text) {
        return webdriver.getPageSource().contains(text);
    }


    protected List<WebElement> find(By container) {
        return webdriver.findElements(container);
    }

    protected T close() {
        webdriver.close();
        return (T) this;
    }

}
