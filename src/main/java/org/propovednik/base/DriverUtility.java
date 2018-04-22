package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverUtility {

    public static boolean isElementPresent(WebDriver driver, By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException no) {
            return false;
        }
    }


    public static boolean isElementPresentWithinAnotherElement(WebDriver driver, WebElement elementToLookIn, By locator){
        try {
            elementToLookIn.findElement(locator);
            return true;
        } catch (NoSuchElementException no) {
            return false;
        }
    }

}
