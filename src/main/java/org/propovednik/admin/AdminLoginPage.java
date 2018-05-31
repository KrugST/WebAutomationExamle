package org.propovednik.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage {
    WebDriver driver;
    String login = "rsentsov";
    String password = "Sacramento#2018";
    private By adminLoginError = By.xpath("//div[@id='msgDiv']/div[contains(text(),'The username/email or password you entered is incorrect.')]");
    private By loginButtonLocator = By.xpath("//div[@id='login-container-form']//input[@value='Log in']");
    private By usernameFieldLocator = By.xpath("//div[@id='login-container-form']//input[@name='username']");
    private By passwordFieldLocator = By.xpath("//div[@id='login-container-form']//input[@name='password']");

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAdminLogin() {
        driver.get("https://dev.propovednik.com/admin/");
    }

    public void loginWithAdminAccount() {
        // TODO: refactor locators and similar lines
        goToAdminLogin();
        driver.findElement(usernameFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(By.xpath("//div[@id='title-div-left']/h1[contains(text(),'Dashboard')]"));
    }

    public void loginWithProvidedCredentials(String loginToInput, String passwordToInput) {
        // TODO: refactor locators and similar lines
        goToAdminLogin();
        driver.findElement(usernameFieldLocator).sendKeys(loginToInput);
        driver.findElement(passwordFieldLocator).sendKeys(passwordToInput);
        driver.findElement(loginButtonLocator).click();
    }

    public void isLoginErrorPresent() {

    }

    public void getErrorMsgText() {

    }
}
