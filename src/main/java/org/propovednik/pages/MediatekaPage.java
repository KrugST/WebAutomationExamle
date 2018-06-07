package org.propovednik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.propovednik.base.DriverUtility;
import org.propovednik.base.JSWaiter;

import java.util.ArrayList;
import java.util.List;

public class MediatekaPage {

    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 10);
    private By downloadIconLocator = By.xpath(".//img[@src='/modules/common/images/download.png']");
    private By breadcrumbs = By.xpath("//div[@class='breadcrumb-wrapper']//div[contains(@class, 'breadcrumb ')]");

    public MediatekaPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFolderPresent(String folderItem) {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();

        return DriverUtility.isElementPresent(driver, By.linkText(folderItem));
    }

    //метод чтобы провериить если у фолдера есть кнопочка скачать, в него передаётсо название папки и возвращяетсо true or false
    public boolean isDownloadIconPresentForFolder(String folderName) {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();

        return DriverUtility.isElementPresentWithinAnotherElement(driver, getFolderElement(folderName), downloadIconLocator);
    }

    //Метод для нажатия папки на странице мидиотека, в него передаю название папки которую нужно нажать, и это название вставляю прямо в xpath
    public void clickFolder(String folderName) {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();

        getFolderElement(folderName).click();
    }

    private WebElement getFolderElement(String folderName) {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();

        WebElement folderElement = driver.findElement(By.xpath("//div[@title='" + folderName + "']/parent::*"));
        return folderElement;
    }

    public List<String> getBreadcrumbs() {
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();

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
        JSWaiter jsWaiter = new JSWaiter(driver);
        jsWaiter.waitForJQueryLoad();

       // WebElement audioListAlbumItem = driver.findElement(By.xpath("//div[@title='" + audioListAlbumText + "']/parent::*"));
        // TODO:  conflict with xpath, not looking inside element, it looks inside whole page
        driver.findElement(By.xpath("//a[contains(text(), '" + audioListAlbumText + "')]/parent::div/following-sibling::div/a[2]")).click();

    }


}
