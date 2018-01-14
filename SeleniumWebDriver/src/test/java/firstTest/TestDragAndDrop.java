package firstTest;

import com.company.page.DragAndDropPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestDragAndDrop {
    private static DragAndDropPage webPage;
    private static WebDriver webDriver = new ChromeDriver();

    @BeforeClass
    public static void runPage() {
        webPage = new DragAndDropPage(webDriver);
        webPage.openPage();
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        webPage.dragAndDrop(webPage.getFirstElement(),webPage.getDropZone());
        Thread.sleep(2000);
        webPage.dragAndDrop(webPage.getSecondElement(),webPage.getDropZone());
        Thread.sleep(2000);
        webPage.dragAndDrop(webPage.getThirdElement(),webPage.getDropZone());
        Thread.sleep(2000);
        webPage.dragAndDrop(webPage.getFourthElement(),webPage.getDropZone());
        Thread.sleep(2000);

        Assert.assertEquals("Draggable 1", webPage.getText());
        //Assert.assertTrue(droppedElementslist.get(0).getText().equals(firstDragElement.getText()));
    }

    @AfterClass
    public static void closePage() {
        webPage.closePage();
    }
}
