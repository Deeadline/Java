package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JQueryUIProgressBarPage extends PageObjectFactory<JQueryUIProgressBarPage> {
    private final String URL = "http://www.seleniumeasy.com/test/jquery-download-progress-bar-demo.html";
    private By startDownloadButton = By.xpath("//*[@id=\"downloadButton\"]");
    private By progressLabel = By.xpath("/html/body/div[3]/div[2]/div[1]");
    private By closeButton = By.xpath("//div[@class='ui-dialog-buttonset']");

    public JQueryUIProgressBarPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        getPage(URL);
    }

    public void closePage() {
        close();
    }

    public void startDownload() {
        click(startDownloadButton);
    }

    public String getProgress() {
        return show(progressLabel);
    }

    public void closeButton() {
        click(closeButton);
    }

}
