package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getMenuItems() {
        //создаём переменную с веб элементом и запихиваем туда весь веб элемент который мы нашли по айди
        WebElement menuDiv = driver.findElement(By.id("header-links"));
        // создаём переменную лист и вычлиняем запихиваем из другой переменной все елементы которые относятсо к ссылке <a>
        List<WebElement> menuItemsList = menuDiv.findElements(By.tagName("a"));
        //создаём новую пустую переменную для результатов с сайта, туда мы засуним все названия кнопок которые найдём
        List<String> actualMenuItems = new ArrayList<String>();
        // лууп, берёт нашу переменную лист где нахотсо <a> елементы из меню, и достаём из них текст
        for (WebElement menuItem : menuItemsList) {
            String itemText = menuItem.getText().trim();
            // вот тут добавляем в нашу новую созданную переменную текст из каждой кнопки меню
            actualMenuItems.add(itemText);
        }
        return actualMenuItems;
    }
    public String getWebsiteTitle(){
        String websiteActualName = driver.findElement(By.xpath("//div[@id='header-top']/div[@class='header-logo']/h1")).getText().trim();
        return websiteActualName;
    }

}
