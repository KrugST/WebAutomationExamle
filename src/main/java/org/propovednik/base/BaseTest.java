package org.propovednik.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

    public WebDriver getDriverInstance() {
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

}
