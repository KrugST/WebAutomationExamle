package org.propovednik.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    String browser = "Chrome";
    // TODO: get system variable from MVN command
    // TODO: it should work on linux

    public WebDriver getDriverInstance() {
        WebDriver driver = null;
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
            driver = new FirefoxDriver();

        } else {
        }
        return driver;

    }

}
