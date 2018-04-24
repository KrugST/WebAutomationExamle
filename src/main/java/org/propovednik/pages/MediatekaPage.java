package org.propovednik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.propovednik.base.DriverUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MediatekaPage {

    WebDriver driver;
    private By downloadIconLocator = By.xpath(".//img[@src='/modules/common/images/download.png']");

    public MediatekaPage(WebDriver driver){
        this.driver = driver;
    }

    // метод который проверяет и находит название папки на сайте и проверяет если он там точно есть, в него передаётсо название папки и возврашяетсо true or false
    public boolean isFolderExist(String folderItemName) {
       return DriverUtility.isElementPresent(driver, By.linkText(folderItemName));
    }

    //метод чтобы провериить если у фолдера есть кнопочка скачать, в него передаётсо название папки и возвращяетсо true or false
    public boolean checkIfFolderHaveDownloadIcon(String folderName) {
        WebElement folderSectionInformation = driver.findElement(By.xpath("//div[@title='" + folderName + "']/parent::*"));
        return DriverUtility.isElementPresentWithinAnotherElement(driver, folderSectionInformation,downloadIconLocator );

    }

    //Метод для нажатия папки на странице мидиотека, в него передаю название папки которую нужно нажать, и это название вставляю прямо в xpath
    public void clickFolderNameOnMediotekaPage(String folderNameToClick) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement folderToClick = driver.findElement(By.xpath("//div[@title='" + folderNameToClick + "']/parent::*"));
        folderToClick.click();
    }

    public List<String> getBreadcrumbs(){
        WebElement breadcrumbsDiv = driver.findElement(By.xpath("//div[@class='breadcrumb-wrapper']//div[contains(@class, 'breadcrumb ')]"));
        List<WebElement> breadcrumbsItemsList = breadcrumbsDiv.findElements(By.tagName("a"));
        List<String> actualBreadcrumbsList = new ArrayList<String>();

        for (WebElement breadcrumbItem : breadcrumbsItemsList) {
            String itemText = breadcrumbItem.getText().trim();
            // вот тут добавляем в нашу новую созданную переменную текст из каждой А элементины
           actualBreadcrumbsList.add(itemText);
        }
        return actualBreadcrumbsList;
    }

    public void addAudioToPlayList() {

    }


}
