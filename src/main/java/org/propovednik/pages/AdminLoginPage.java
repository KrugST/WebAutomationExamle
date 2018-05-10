package org.propovednik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage {
    WebDriver driver;
    String login = "rsentsov";
    String password = "Sacramento#2018";

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAdminLogin() {
        driver.get("https://dev.propovednik.com/admin/");
    }

    public void loginWithAdminAccount() {
        goToAdminLogin();
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='username']")).sendKeys(login);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@value='Log in']")).click();
        driver.findElement(By.xpath("//div[@id='title-div-left']/h1[contains(text(),'Dashboard')]"));
    }

    public void loginWithProvidedCredentials(String loginToInput, String passwordToInput) {
        goToAdminLogin();
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='username']")).sendKeys(loginToInput);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='password']")).sendKeys(passwordToInput);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@value='Log in']")).click();
    }
}
