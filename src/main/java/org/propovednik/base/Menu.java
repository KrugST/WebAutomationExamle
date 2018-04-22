package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Menu {

    WebDriver driver;

    public Menu(WebDriver driver){
        this.driver = driver;
    }

    // метод для поиска кнопки меню и нажатие на него, в него передаётсо текст со ссылки <a>
    public void clickMenuItem(String menuItemName) {
        // Создаём переменную в которой находитсо веб элемент и запихиваем туда элемент с сайта
        WebElement menuItemElement = driver.findElement(By.linkText(menuItemName));
        // берём переменную с веб элементом и кликаем на него
        menuItemElement.click();
    }

}
