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
    WebDriver driver;

    public void goToPage() {
        System.setProperty("webdriver.gecko.driver", "C:\\repos\\popovednik-automation\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://propovednik.com/");
    }

    public void clickMenuItem(String menuItemName) {
        WebElement menuItemElement = driver.findElement(By.linkText(menuItemName));
        menuItemElement.click();
    }

    public boolean isFolderExist(String folderItemName) {

        try {
            WebElement folderItemElement = driver.findElement(By.linkText(folderItemName));
            return true;
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    @Test
    public void testone() throws InterruptedException {
        goToPage();
        List<String> expectedMenuItems = Arrays.asList("Медиатека", "Новости", "О нас", "Вещание", "Подписка", "Церкви и миссии");

        WebElement menuDiv = driver.findElement(By.id("header-links"));
        List<WebElement> menuItemsList = menuDiv.findElements(By.tagName("a"));

        List<String> actualMenuItems = new ArrayList<String>();
        for (WebElement menuItem : menuItemsList) {
            String itemText = menuItem.getText().trim();
            actualMenuItems.add(itemText);
        }
        System.out.println(expectedMenuItems.toString());
        System.out.println(actualMenuItems.toString());

        assertTrue(actualMenuItems.equals(expectedMenuItems));

    }

    @Test
    public void isFolderPresent() {
        goToPage();
        clickMenuItem("Медиатека");
        boolean result = isFolderExist("Благовестие");
        assertTrue(result);
    }

    @Test
    public void qweqweqweqweqwe(){

    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
