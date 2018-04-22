package org.propovednik.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.propovednik.base.AudioPleer;
import org.propovednik.base.BaseTest;
import org.propovednik.base.DriverUtility;
import org.propovednik.base.Menu;
import org.propovednik.pages.HomePage;
import org.propovednik.pages.MediatekaPage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class FirstTest extends BaseTest {

    List<String> expectedMenuItems = Arrays.asList("Медиатека", "Новости", "О нас", "Вещание", "Подписка", "Церкви и миссии");
    List<String> expectedBreadcrumList = Arrays.asList("Медиатека", "Благовестие");

    // тесткейс для проверки меню, если оно соответствует реквайраментам
    @Test
    public void testone() throws InterruptedException {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        List<String> actualMenuItems = menu.getMenuItems();

        //Ожидаем что наша переменная с реквайраментами = переменной в которой находятсо айтемы с нашего сайта, если они неравны то будет false
        assertTrue(actualMenuItems.equals(expectedMenuItems));

        driver.close();
    }

    // тесткейс для проверки если папка с именем медиотека сушествует
    @Test
    public void isFolderPresent() {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        //boolean result = mediatekaPage.isFolderExist("Благовестие");
        assertTrue(mediatekaPage.isFolderExist("Благовестие"));

        driver.close();
    }

    //тест кейс для проверки если папка имеет довнлоад иконку
    @Test
    public void checkAudioBibleForDownloadIcon() {
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        assertTrue(mediatekaPage.checkIfFolderHaveDownloadIcon("Аудио Библия"));

        driver.close();
    }

    //Проверяю если папка благовестие имеет иконку скачки
    @Test
    public void checkIfBlagovestieDoesNotHaveDownloadIcon() {
        WebDriver driver = getDriverInstance();
        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        assertFalse(mediatekaPage.checkIfFolderHaveDownloadIcon("Благовестие"));

        driver.close();
    }

    //метод чтобы проверять бредкрамбс на сайте, тесткеейс чтобы проверить на страницы медиатека в папке благовести
    @Test
    public void checkBreadcrumbIfItSaysPageFolder() throws InterruptedException {
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        Thread.sleep(1000);

        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        mediatekaPage.clickFolderNameOnMediotekaPage("Благовестие");

        List<String> actualBreadcrumbsList = mediatekaPage.getBreadcrumbs();

        assertTrue(actualBreadcrumbsList.equals(expectedBreadcrumList));

        driver.close();
    }

    // тест кейс для проверки названия хедара сайта, нечего особенного
    @Test
    public void checkWebsiteHeaderLogoName() {
        String websiteExpectedName = "Propovednik.com";

        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        String websiteActualName = menu.getWebsiteTitle();
        assertTrue(websiteActualName.equals(websiteExpectedName));

        driver.close();
    }

    //тесткейс для проверки если на сайте сть кнопки play, previous track and next track
    @Test
    public void checkPlayerButtonsOnWebsite() throws InterruptedException {
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        //тут бы надо метод чтобы подождать пока загрузитсо джейквейри, спрошу в воскрисение, в гугле чтото большой какойто, мож потом зделаю
        // а пока втыкну эту говняшку
        Thread.sleep(1000);
        // ну такое себе, надо проверяетсо одно за другим
        AudioPleer audioPleer = new AudioPleer(driver);

        assertTrue(audioPleer.isPlayButtonPresent());
        assertTrue(audioPleer.isNextTrackButtonPresent());
        assertTrue(audioPleer.isPreviousTrackButtonPresent());

        driver.close();
    }


}
