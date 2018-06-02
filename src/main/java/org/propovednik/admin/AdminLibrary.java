package org.propovednik.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLibrary {
    WebDriver driver;
    public AdminLibrary (WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdminLibraryFolder(String folderNameText) {
        driver.findElement(By.xpath("//div[@id='angularTools']//a[text()='" + folderNameText + "']")).click();
    }

    public void createNewFolder(String newFolderNameToInput) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='newFolderForm']/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='newFolderName']")).sendKeys(newFolderNameToInput);
    }

}
