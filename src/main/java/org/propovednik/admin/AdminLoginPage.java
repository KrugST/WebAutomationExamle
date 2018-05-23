package org.propovednik.admin;

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
    private By adminLoginError = By.xpath("//div[@id='msgDiv']/div[contains(text(),'The username/email or password you entered is incorrect.')]");

    public void loginWithAdminAccount() {
        // TODO: refactor locators and similar lines
        goToAdminLogin();
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='username']")).sendKeys(login);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@value='Log in']")).click();
        driver.findElement(By.xpath("//div[@id='title-div-left']/h1[contains(text(),'Dashboard')]"));
    }

    public void loginWithProvidedCredentials(String loginToInput, String passwordToInput) {
        // TODO: refactor locators and similar lines
        goToAdminLogin();
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='username']")).sendKeys(loginToInput);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@name='password']")).sendKeys(passwordToInput);
        driver.findElement(By.xpath("//div[@id='login-container-form']//input[@value='Log in']")).click();
    }

    public void isLoginErrorPresent() {

    }

    public void getErrorMsgText() {

    }
}
