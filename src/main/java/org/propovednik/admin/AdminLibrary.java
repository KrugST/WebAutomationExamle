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

}
