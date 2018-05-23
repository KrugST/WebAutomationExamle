package org.propovednik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.propovednik.base.DriverUtility;

import java.util.ArrayList;
import java.util.List;

public class MediatekaPage {

    WebDriver driver;
    private By downloadIconLocator = By.xpath(".//img[@src='/modules/common/images/download.png']");
    private By breadcrumbs = By.xpath("//div[@class='breadcrumb-wrapper']//div[contains(@class, 'breadcrumb ')]");

    public MediatekaPage(WebDriver driver) {
        // TODO: maybe i can put waiter here to wait if whole page is loaded whole folder list
        this.driver = driver;
    }

    // метод который проверяет и находит название папки на сайте и проверяет если он там точно есть, в него передаётсо название папки и возврашяетсо true or false
    public boolean isFolderPresent(String folderItem) {
        return DriverUtility.isElementPresent(driver, By.linkText(folderItem));
    }

    //метод чтобы провериить если у фолдера есть кнопочка скачать, в него передаётсо название папки и возвращяетсо true or false
    public boolean isDownloadIconPresentForFolder(String folderName) {
        return DriverUtility.isElementPresentWithinAnotherElement(driver, getFolderElement(folderName), downloadIconLocator);
    }

    //Метод для нажатия папки на странице мидиотека, в него передаю название папки которую нужно нажать, и это название вставляю прямо в xpath
    public void clickFolder(String folderName) {
        getFolderElement(folderName).click();
    }

    private WebElement getFolderElement(String folderName) {
        WebElement folderElement = driver.findElement(By.xpath("//div[@title='" + folderName + "']/parent::*"));
        return folderElement;
    }

    public List<String> getBreadcrumbs() {
        WebElement breadcrumbsDiv = driver.findElement(breadcrumbs);
        List<WebElement> breadcrumbsItemsList = breadcrumbsDiv.findElements(By.tagName("a"));
        List<String> actualBreadcrumbsList = new ArrayList<String>();

        for (WebElement breadcrumbItem : breadcrumbsItemsList) {
            String itemText = breadcrumbItem.getText().trim();
            // вот тут добавляем в нашу новую созданную переменную текст из каждой А элементины
            actualBreadcrumbsList.add(itemText);
        }
        return actualBreadcrumbsList;
    }

    public void addAudioAlbumToPlayList(String audioListAlbumText) {
        WebElement audioListAlbumItem = driver.findElement(By.xpath("//div[@title='" + audioListAlbumText + "']/parent::*"));
        audioListAlbumItem.findElement(By.xpath("//img[@title='Add to Playlist']")).click();

    }


}
