package org.propovednik.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminMenu {
    WebDriver driver;
    public AdminMenu (WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdminMenuItem(String adminMenuItem) {
        driver.findElement(By.xpath("//ul[@id='adminSideMenu']//img[@title='" + adminMenuItem + "']")).click();

    }
}
