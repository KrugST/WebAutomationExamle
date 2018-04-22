package org.propovednik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MediatekaPage {

    WebDriver driver;

    public MediatekaPage(WebDriver driver){
        this.driver = driver;
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

}
