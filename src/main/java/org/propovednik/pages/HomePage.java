package org.propovednik.pages;

import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // метод указывает путь к драйверу, открывает браузер и заходит на страницу
    public void goToHomePage() {
        driver.get("https://propovednik.com/");
    }

    public void goToDevHomePage() {
        driver.get("http://dev.propovednik.com/");
    }
}
