package com.company.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectFactory<T> {
    protected WebDriver webdriver;

    public PageObjectFactory(WebDriver webdriver) {
        String chromeDriverPath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        this.webdriver = webdriver;
    }

    T getPage(String url) {
        webdriver.get(url);
        webdriver.getPageSource();
        webdriver.manage().window().maximize();
        return (T) this;
    }

    protected T click(By clicker) {
        webdriver.findElement(clicker).click();
        return (T) this;
    }

    T sendKeys(By textInput, String text) {
        webdriver.findElement(textInput).sendKeys(text);
        return (T) this;
    }

    String show(By showMessage) {
        return webdriver.findElement(showMessage).getText();
    }

    String showAttribute(By showMessage) {
        return webdriver.findElement(showMessage).getAttribute("value");
    }

    boolean isDisplayed(By container) {
        return webdriver.findElement(container).isDisplayed();
    }

    boolean isEnabled(By container) {
        return webdriver.findElement(container).isEnabled();
    }

    boolean isContain(String text) {
        return webdriver.getPageSource().contains(text);
    }

    List<WebElement> find(By container) {
        return webdriver.findElements(container);
    }

    T close() {
        webdriver.close();
        return (T) this;
    }

}
