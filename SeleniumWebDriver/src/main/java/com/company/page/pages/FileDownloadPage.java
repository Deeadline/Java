package com.company.page.pages;

import com.company.page.factory.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileDownloadPage extends PageObjectFactory<FileDownloadPage> {
    private final String URL = "http://www.seleniumeasy.com/test/generate-file-to-download-demo.html";
    private By inputData = By.xpath("//*[@id=\"textbox\"]");
    private By charactersRemaining = By.xpath("//*[@id=\"textarea_feedback\"]");
    private By generateFileButton = By.xpath("//*[@id=\"create\"]");
    private By downloader = By.xpath("//*[@id=\"link-to-download\"]");

    public FileDownloadPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDataProvider() {
        click(inputData);
    }

    public void enterData(String text) {
        sendKeys(inputData, text);
    }

    public String showCharactersRemaining() {
        return show(charactersRemaining);
    }

    public void clickOnGenerateFileButton() {
        click(generateFileButton);
    }

    public void clickOnDownloader() {
        click(downloader);
    }

    public boolean isActiveGenerator() {
        return isEnabled(generateFileButton);
    }

    public boolean isActiveDownloader() {
        return isEnabled(downloader);
    }

    public boolean isContaining() {
        return isContain("Download");
    }

    public void openPage() {
        getPage(URL);
    }

    public void closePage() {
        close();
    }
}
