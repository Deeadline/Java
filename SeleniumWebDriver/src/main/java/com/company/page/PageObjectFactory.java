package com.company.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObjectFactory{
    private WebDriver webdriver;
    private String url;
    final private String chromeDriverPath = "D:\\Java\\SeleniumWebDriver\\chromedriver.exe";
    public PageObjectFactory(String url){
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        webdriver = new ChromeDriver();
        this.url = url;
    }
    public void openPage(){
        webdriver.get(url);
        webdriver.getPageSource();
        webdriver.manage().window().maximize();
    }
    public void closePage(){
        webdriver.close();
    }

    public WebDriver getWebdriver() {
        return webdriver;
    }
}
