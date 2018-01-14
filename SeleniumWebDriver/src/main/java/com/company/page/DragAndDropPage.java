package com.company.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends PageObjectFactory<DragAndDropPage> {
    private final String URL = "http://www.seleniumeasy.com/test/drag-and-drop-demo.html";
    private By firstDraggable = By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/span[1]");
    private By secondDraggable = By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/span[2]");
    private By thirdDraggable = By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/span[3]");
    private By fourthDraggable = By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/span[4]");
    private By dropZone = By.xpath("//*[@id=\"mydropzone\"]");
    private By droppedList = By.xpath("//*[@id=\"droppedlist\"]");
    public DragAndDropPage(WebDriver driver){
        super(driver);
    }

    public void openPage(){
        getPage(URL);
    }
    public WebElement getFirstElement(){
        return webdriver.findElement(firstDraggable);
    }
    public WebElement getSecondElement(){
        return webdriver.findElement(secondDraggable);
    }
    public WebElement getThirdElement(){
        return webdriver.findElement(thirdDraggable);
    }
    public WebElement getFourthElement(){
        return webdriver.findElement(fourthDraggable);
    }
    public WebElement getDropZone(){
        return webdriver.findElement(dropZone);
    }
    public String getText(){
        return show(droppedList);
    }
    public void dragAndDrop(WebElement elementDrag, WebElement dropZone){
       new Actions(webdriver).dragAndDrop(elementDrag, dropZone).perform();
    }

    public void closePage(){
        close();
    }
}
