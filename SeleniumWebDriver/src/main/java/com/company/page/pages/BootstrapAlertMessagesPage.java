package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BootstrapAlertMessagesPage extends PageObjectFactory<BootstrapAlertMessagesPage> {

    private final String URL = "http://www.seleniumeasy.com/test/bootstrap-alert-messages-demo.html";
    private By autoSuccessMessageButton = By.xpath("//*[@id=\"autoclosable-btn-success\"]");
    private By normalSuccessMessageButton = By.xpath("//*[@id=\"normal-btn-success\"]");
    private By autoWarningMessageButton = By.xpath("//*[@id=\"autoclosable-btn-warning\"]");
    private By normalWarningMessageButton = By.xpath("//*[@id=\"normal-btn-warning\"]");
    private By autoDangerMessageButton = By.xpath("//*[@id=\"autoclosable-btn-danger\"]");
    private By normalDangerMessageButton = By.xpath("//*[@id=\"normal-btn-danger\"]");
    private By autoInfoMessageButton = By.xpath("//*[@id=\"autoclosable-btn-info\"]");
    private By normalInfoMessageButton = By.xpath("//*[@id=\"normal-btn-info\"]");
    private By showAutoSuccessMessage = By.xpath("//*[@class=\"alert alert-success alert-autocloseable-success\"]");
    private By showNormalSuccessMessage = By.xpath("//*[@class=\"alert alert-success alert-normal-success\"]");
    private By showAutoWarningMessage = By.xpath("//*[@class=\"alert alert-warning alert-autocloseable-warning\"]");
    private By showNormalWarningMessage = By.xpath("//*[@class=\"alert alert-warning alert-normal-warning\"]");
    private By showAutoDangerMessage = By.xpath("//*[@class=\"alert alert-danger alert-autocloseable-danger\"]");
    private By showNormalDangerMessage = By.xpath("//*[@class=\"alert alert-danger alert-normal-danger\"]");
    private By showAutoInfoMessage = By.xpath("//*[@class=\"alert alert-info alert-autocloseable-info\"]");
    private By showNormalInfoMessage = By.xpath("//*[@class=\"alert alert-info alert-normal-info\"]");
    private By buttonSuccesCloser = By.xpath("//*[@class=\"alert alert-success alert-normal-success\"]//button[@class=\"close\"]");
    private By buttonWarningCloser = By.xpath("//*[@class=\"alert alert-warning alert-normal-warning\"]//button[@class=\"close\"]");
    private By buttonDangerCloser = By.xpath("//*[@class=\"alert alert-danger alert-normal-danger\"]//button[@class=\"close\"]");
    private By buttonInfoCloser = By.xpath("//*[@class=\"alert alert-info alert-normal-info\"]//button[@class=\"close\"]");

    public BootstrapAlertMessagesPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void clickOnAutoSuccessMessageButton() {
        click(autoSuccessMessageButton);
    }

    public void clickOnNormalSuccessMessageButton() {
        click(normalSuccessMessageButton);
    }

    public void clickOnAutoWarningMessageButton() {
        click(autoWarningMessageButton);
    }

    public void clickOnNormalWarningMessageButton() {
        click(normalWarningMessageButton);
    }

    public void clickOnAutoDangerMessageButton() {
        click(autoDangerMessageButton);
    }

    public void clickOnNormalDangerMessageButton() {
        click(normalDangerMessageButton);
    }

    public void clickOnAutoInfoMessageButton() {
        click(autoInfoMessageButton);
    }

    public void clickOnNormalInfoMessageButton() {
        click(normalInfoMessageButton);
    }

    public void closeNormalMessage() {
        click(buttonSuccesCloser);
    }

    public void closeWarningMessage() {
        click(buttonWarningCloser);
    }

    public void closeDangerMessage() {
        click(buttonDangerCloser);
    }

    public void closeInfoMessage() {
        click(buttonInfoCloser);
    }

    public By getShowAutoSuccessMessage() {
        return showAutoSuccessMessage;
    }

    public By getShowNormalSuccessMessage() {
        return showNormalSuccessMessage;
    }

    public By getShowAutoWarningMessage() {
        return showAutoWarningMessage;
    }

    public By getShowNormalWarningMessage() {
        return showNormalWarningMessage;
    }

    public By getShowAutoDangerMessage() {
        return showAutoDangerMessage;
    }

    public By getShowNormalDangerMessage() {
        return showNormalDangerMessage;
    }

    public By getShowAutoInfoMessage() {
        return showAutoInfoMessage;
    }

    public By getShowNormalInfoMessage() {
        return showNormalInfoMessage;
    }

    public boolean isActive(By container) {
        return isDisplayed(container);
    }

    public void closePage() {
        close();
    }
}
