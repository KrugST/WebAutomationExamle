package org.propovednik.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.propovednik.base.*;
import org.propovednik.pages.HomePage;
import org.propovednik.pages.MediatekaPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class FirstTest extends BaseTest {

    List<String> expectedMenuItems = Arrays.asList("Медиатека", "Новости", "О нас", "Вещание", "Подписка", "Церкви и миссии");
    List<String> expectedBreadcrumList = Arrays.asList("Медиатека", "Благовестие");


    // тесткейс для проверки меню, если оно соответствует реквайраментам
    @Test
    public void testone() {
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
    public void isFolderPresent() throws InterruptedException {
        // используем зарание созданый метод чтобы зайти на страницу
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");
        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        assertTrue(mediatekaPage.isFolderPresent("Благовестие"));

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
        assertTrue(mediatekaPage.isDownloadIconPresentForFolder("Аудио Библия"));

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
        assertFalse(mediatekaPage.isDownloadIconPresentForFolder("Благовестие"));

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

        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        mediatekaPage.clickFolder("Благовестие");

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

    @Test
    public void testingPlayListMetods() throws InterruptedException {
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        Thread.sleep(1000);
        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        mediatekaPage.clickFolder("Благовестие");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//img[@src='/modules/common/images/add_play_button_small.png'])[2]")).click();
        Thread.sleep(1000);

        Playlist playlist = new Playlist(driver);
        Boolean testingIfListNotEmpty = playlist.isPlaylistEmpty();
        System.out.println(testingIfListNotEmpty);
        assertTrue(playlist.isPlaylistEmpty());

        List<String> justTestingAzaza = playlist.getPlaylistItems();
        System.out.println(justTestingAzaza);

        driver.close();
    }

    @Test
    public void testingAddAudioToPlayList() throws InterruptedException {
        List<String> playListItemsTextExpected = Arrays.asList("qwe1 Байкит, Варнавара 06-1997 - В.Фот - Дневник миссионера", "2 Байкит, Варнавара 06-1997 - В.Фот - Дневник миссионера","3 Дальний Восток, Сахалин - В.Фот - Дневник миссионера");
        WebDriver driver = getDriverInstance();

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        Thread.sleep(1000); // TODO: i need to write waiters
        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        mediatekaPage.clickFolder("Благовестие");

        Thread.sleep(3000);
        mediatekaPage.addAudioAlbumToPlayList("2002 Курганинск");
        Thread.sleep(3000); //TODO: ahhh nope i need better waiter =(
        Playlist playlist = new Playlist(driver);
        List<String> playListItemsTextActual = playlist.getPlaylistItems();
        //TODO: create veriable with expected playlist items, and compare it to actual, assert true equal, DONE
        System.out.println(playListItemsTextActual);
        System.out.println(playListItemsTextExpected);
        assertTrue(playListItemsTextActual.equals(playListItemsTextExpected));

    }

    @AfterTest
    public void clozeShit() {
        //not working
        WebDriver driver = getDriverInstance();
        if (driver != null) {
            driver.quit();
        }
    }

// TODO: browser should close after each test
// TODO: logi, log4J
}
