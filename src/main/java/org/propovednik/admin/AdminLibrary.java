package org.propovednik.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.propovednik.base.DragAndDropFile;
import org.propovednik.base.JSWaiter;

public class AdminLibrary {
    WebDriver driver;


    public AdminLibrary (WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdminLibraryFolder(String folderNameText) {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();
        driver.findElement(By.xpath("//div[@id='angularTools']//a[text()='" + folderNameText + "']")).click();
    }

    public void createNewFolder(String newFolderNameToInput) throws InterruptedException {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();
        driver.findElement(By.xpath("//div[@id='newFolderForm']/button")).click();
        jsWaiter.waitForJQueryLoad();
        driver.findElement(By.xpath("//input[@id='newFolderName']")).sendKeys(newFolderNameToInput);
        jsWaiter.waitForJQueryLoad();
        driver.findElement(By.xpath("//div[@id='newFolderForm']/div[@class='editBtns']/button[@class='editBtn']")).click();
    }

    public void makeFolderPublic(String newFolderNameToSearch) {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();
        driver.findElement(By.xpath("//a[contains(text(), '"+newFolderNameToSearch+"')]/../../following-sibling::div/a[4]")).click();

    }

    public void uploadFile() {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();
        //WebElement uploadFileDropArea = driver.findElement(By.xpath("//div[@uploader='uploader' and @class='well my-drop-zone']"));
        WebElement uploadFileDropArea = driver.findElement(By.xpath("//*[@id='uploadForm']/div/div[2]"));
        DragAndDropFile dragAndDropFile = new DragAndDropFile(driver);
        dragAndDropFile.dropFile(new String[]{"C:/Users/roman.sentsov/Desktop/test111.txt"},uploadFileDropArea);
    }

}
