import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class FirstTest {
    // создаём верейбел драйвер внутри класса чтобы можно было использовать в любых метадах
    WebDriver driver;

    // метод указывает путь к драйверу, открывает браузер и заходит на страницу
    public void goToPage() {
        // Путь к драйверу
        System.setProperty("webdriver.gecko.driver", "C:\\repos\\popovednik-automation\\drivers\\geckodriver.exe");
        // Использует драйвер фаирфокс
        driver = new FirefoxDriver();
        // Открывает браузер и заходит на страницу
        driver.get("https://propovednik.com/");
    }

    public void goToPageModified(String pageUrlToGo) {
        // Путь к драйверу
        System.setProperty("webdriver.gecko.driver", "C:\\repos\\popovednik-automation\\drivers\\geckodriver.exe");
        // Использует драйвер фаирфокс
        driver = new FirefoxDriver();
        // Открывает браузер и заходит на страницу
        driver.get("" + pageUrlToGo + "");
    }

    // метод для поиска кнопки меню и нажатие на него, в него передаётсо текст со ссылки <a>
    public void clickMenuItem(String menuItemName) {
        // Создаём переменную в которой находитсо веб элемент и запихиваем туда элемент с сайта
        WebElement menuItemElement = driver.findElement(By.linkText(menuItemName));
        // берём переменную с веб элементом и кликаем на него
        menuItemElement.click();
    }

    // метод который проверяет и находит название папки на сайте и проверяет если он там точно есть, в него передаётсо название папки и возврашяетсо true or false
    public boolean isFolderExist(String folderItemName) {
        // Штука которая может поймать ошибку если вдруг чегото непроизойдёт того что мы ожидаем
        try {
            // Ищим элемент по тексту ссылки <a> если он нашолся то возвращяем true
            WebElement folderItemElement = driver.findElement(By.linkText(folderItemName));
            return true;
            //ожидаем если вдруг вылезет ошибка и ненайдётсо этого элемента то возвращяем false
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    //метод чтобы провериить если у фолдера есть кнопочка скачать, в него передаётсо название папки и возвращяетсо true or false
    public boolean checkIfFolderHaveDownloadIcon(String folderName) {
        // выбераю перент веб элемент li в котором находитсо 2 дива, все папки одинаковые
        WebElement folderSectionInformation = driver.findElement(By.xpath("//div[@title='" + folderName + "']/parent::*"));
        //ищю уже внутри li элимента если есть картинка для скачивания, иконка везде одинаковая так что ищю по ссылки на иконку
        try {
            folderSectionInformation.findElement(By.xpath(".//img[@src='/modules/common/images/download.png']"));
            return true;
        } catch (NoSuchElementException no) {
            return false;
        }
    }

    //Метод для нажатия папки на странице мидиотека, в него передаю название папки которую нужно нажать, и это название вставляю прямо в xpath
    public void clickFolderNameOnMediotekaPage(String folderNameToClick) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement folderToClick = driver.findElement(By.xpath("//div[@title='" + folderNameToClick + "']/parent::*"));
        folderToClick.click();

    }

    // тесткейс для проверки меню, если оно соответствует реквайраментам
    @Test
    public void testone() throws InterruptedException {
        // используем зарание созданый метод чтобы зайти на страницу
        goToPage();
        // создаём переменную лист и запихиваем туда лист с названиями нашего меню как в реквайраментах
        List<String> expectedMenuItems = Arrays.asList("Медиатека", "Новости", "О нас", "Вещание", "Подписка", "Церкви и миссии");
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
        goToPage();
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem("Медиатека");
        // принимаем и записываем результат из другого метода где используетсо трай катчь, true или false :)
        boolean result = isFolderExist("Благовестие");
        // ожидаем что наша переменная с результатом содержит true, если нет то всё плохо и мы видим ошибку
        assertTrue(result);
    }

    //тест кейс для проверки если папка имеет довнлоад иконку
    @Test
    public void checkAudioBibleForDownloadIcon() {
        // используем зарание созданый метод чтобы зайти на страницу
        goToPage();
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem("Медиатека");
        // используем зарание созданный метод чтобы проверить если папка аудиобиблии имеет иконку скачать
        // метод вернёт true или false, засовываем результат в переменную
        boolean result = checkIfFolderHaveDownloadIcon("Аудио Библия");
        // ожидаем что наша переменная с результатом содержит true, если нет то всё плохо и мы видим ошибку
        assertTrue(result);
    }

    //Проверяю если папка благовестие имеет иконку скачки
    @Test
    public void checkIfBlagovestieDoesNotHaveDownloadIcon() {
        // используем зарание созданый метод чтобы зайти на страницу
        goToPage();
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem("Медиатека");
        //используем рание созданный метод, но проверяем если иконки нету, !!!!!!!!!!
        boolean result = checkIfFolderHaveDownloadIcon("Благовестие");
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
        goToPage();
        // используем зарание созданый метод чтобы нажать на ссылку в меню
        clickMenuItem("Медиатека");
        //в поезде медленный интернет, похоже кликает быстрее чем грузятсо папки, надо какнибудь зделать получше, имплисити вейт чтото тоже неработает, надо наверное чтобы джейквейри загрузилось!!!!!!!!!!!
        Thread.sleep(1000);
        // исользуем зарание созданный метод чтобы нажать зайти в папку на страницу медиотека, передаём туда текст в переменную
        clickFolderNameOnMediotekaPage("Благовестие");
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
        String websiteActualName = new String();
        // используем зарание созданый метод чтобы зайти на страницу
        goToPage();
        websiteActualName = driver.findElement(By.xpath("//div[@id='header-top']/div[@class='header-logo']/h1")).getText().trim();
        assertTrue(websiteActualName.equals(websiteExpectedName));
    }

    //тесткейс для проверки если на сайте сть кнопки play, previous track and next track
    @Test
    public void checkPlayerButtonsOnWebsite() throws InterruptedException {
        goToPageModified("https://propovednik.com/");
    //тут бы надо метод чтобы подождать пока загрузитсо джейквейри, спрошу в воскрисение, в гугле чтото большой какойто, мож потом зделаю
        // а пока втыкну эту говняшку
        Thread.sleep(1000);
        // ну такое себе, надо проверяетсо одно за другим
       driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-play')]"));
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-next')]"));
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-previous')]"));
        driver.findElement(By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'playlist-clear-list')]"));
    }

    // закрываем браузер после теста
    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
