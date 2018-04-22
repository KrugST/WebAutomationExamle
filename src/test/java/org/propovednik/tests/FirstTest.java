package org.propovednik.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.propovednik.base.AudioPleer;
import org.propovednik.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class FirstTest extends BaseTest {

    List<String> expectedMenuItems = Arrays.asList("Медиатека", "Новости", "О нас", "Вещание", "Подписка", "Церкви и миссии");

    // тесткейс для проверки меню, если оно соответствует реквайраментам
    @Test
    public void testone() throws InterruptedException {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
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
        System.out.println(expectedMenuItems.toString());
        System.out.println(actualMenuItems.toString());
        //Ожидаем что наша переменная с реквайраментами = переменной в которой находятсо айтемы с нашего сайта, если они неравны то будет false
        assertTrue(actualMenuItems.equals(expectedMenuItems));
    }

    // тесткейс для проверки если папка с именем медиотека сушествует
    @Test
    public void isFolderPresent() {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem(driver,"Медиатека");
        // принимаем и записываем результат из другого метода где используетсо трай катчь, true или false :)
        boolean result = isFolderExist(driver,"Благовестие");
        // ожидаем что наша переменная с результатом содержит true, если нет то всё плохо и мы видим ошибку
        assertTrue(result);
    }

    //тест кейс для проверки если папка имеет довнлоад иконку
    @Test
    public void checkAudioBibleForDownloadIcon() {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem(driver,"Медиатека");
        // используем зарание созданный метод чтобы проверить если папка аудиобиблии имеет иконку скачать
        // метод вернёт true или false, засовываем результат в переменную
        boolean result = checkIfFolderHaveDownloadIcon(driver,"Аудио Библия");
        // ожидаем что наша переменная с результатом содержит true, если нет то всё плохо и мы видим ошибку
        assertTrue(result);
    }

    //Проверяю если папка благовестие имеет иконку скачки
    @Test
    public void checkIfBlagovestieDoesNotHaveDownloadIcon() {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem(driver, "Медиатека");
        //используем рание созданный метод, но проверяем если иконки нету, !!!!!!!!!!
        boolean result = checkIfFolderHaveDownloadIcon(driver,"Благовестие");
        // ожидаем что наша переменная с результатом содержит false
        assertFalse(result);
    }

    //метод чтобы проверять бредкрамбс на сайте, тесткеейс чтобы проверить на страницы медиатека в папке благовести
    @Test
    public void checkBreadcrumbIfItSaysPageFolder() throws InterruptedException {
        // где брать то реквайраменты?
        List<String> expectedBreadcrumList = Arrays.asList("Медиатека", "Благовестие");
        List<String> actualBreadcrumbsList = new ArrayList<String>();
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem(driver,"Медиатека");
        // в поезде медленный интернет, похоже кликает быстрее чем грузятсо папки, надо какнибудь зделать получше, имплисити вейт чтото тоже неработает, надо наверное чтобы джейквейри загрузилось!!!!!!!!!!!
        Thread.sleep(1000);
        // исользуем зарание созданный метод чтобы нажать зайти в папку на страницу медиотека, передаём туда текст в переменную
        clickFolderNameOnMediotekaPage(driver,"Благовестие");
        // находим бредкрамсы по уникальному лакейтору
        WebElement breadcrumbsDiv = driver.findElement(By.xpath("//div[@class='breadcrumb-wrapper']//div[contains(@class, 'breadcrumb ')]"));
        // вычлиняем каждый бредкрамб, они находятсо в А как ссылка
        List<WebElement> breadcrumbsItemsList = breadcrumbsDiv.findElements(By.tagName("a"));
        //достаём текст из веб элементов где находитсо этот бредкрамб
        for (WebElement breadcrumbItem : breadcrumbsItemsList) {
            String itemText = breadcrumbItem.getText().trim();
            // вот тут добавляем в нашу новую созданную переменную текст из каждой А элементины
            actualBreadcrumbsList.add(itemText);
        }
        assertTrue(actualBreadcrumbsList.equals(expectedBreadcrumList));
    }

    // тест кейс для проверки названия хедара сайта, нечего особенного
    @Test
    public void checkWebsiteHeaderLogoName() {
        String websiteExpectedName = "Propovednik.com";
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
        String websiteActualName = driver.findElement(By.xpath("//div[@id='header-top']/div[@class='header-logo']/h1")).getText().trim();
        assertTrue(websiteActualName.equals(websiteExpectedName));
    }

    //тесткейс для проверки если на сайте сть кнопки play, previous track and next track
    @Test
    public void checkPlayerButtonsOnWebsite() throws InterruptedException {
        WebDriver driver = getDriverInstance();
        goToHomePage(driver);
        //тут бы надо метод чтобы подождать пока загрузитсо джейквейри, спрошу в воскрисение, в гугле чтото большой какойто, мож потом зделаю
        // а пока втыкну эту говняшку
        Thread.sleep(1000);
        // ну такое себе, надо проверяетсо одно за другим
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-play')]"));
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-next')]"));
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-previous')]"));
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'playlist-clear-list')]"));
    }

}
