import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class FirstTest {
    // создаём верейбел драйвер внутри класса чтобы можно было использовать в любых метадах
    WebDriver driver;

    public void goToPage() {
        // Путь к драйверу
        System.setProperty("webdriver.gecko.driver", "C:\\repos\\popovednik-automation\\drivers\\geckodriver.exe");
        // Использует драйвер фаирфокс
        driver = new FirefoxDriver();
        // Открывает браузер и заходит на страницу
        driver.get("https://propovednik.com/");
    }

    // метод для поиска кнопки меню и нажатие на него, в него передаётсо текст со ссылки <a> и обрабатываетсо внутри
    public void clickMenuItem(String menuItemName) {
        // Создаём переменную в которой находитсо веб элемент и запихиваем туда элемент с сайта
        WebElement menuItemElement = driver.findElement(By.linkText(menuItemName));
        // берём переменную с веб элементом и кликаем на него
        menuItemElement.click();
    }

    // метод который проверяет и находит название папки на сайте и проверяет если он там точно есть
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

    @Test
    public void qweqweqweqweqwe() {

    }

    // закрываем браузер после теста
    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
