package org.propovednik.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver getDriverInstance() {
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    // метод указывает путь к драйверу, открывает браузер и заходит на страницу
    public void goToHomePage(WebDriver driver) {
        driver.get("https://propovednik.com/");
    }

    @BeforeTest
    public void beforeTest(){

    }

}
