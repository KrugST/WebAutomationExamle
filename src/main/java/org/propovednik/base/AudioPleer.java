package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AudioPleer {

    WebDriver driver;
    private By playButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-play')]");
    private By nextButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-next')]");
    private By previousButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'playlist-clear-list')]");

    public AudioPleer(WebDriver driver){
        this.driver = driver;
    }

    public void isPuaseButtonPresent(){

    }

    public boolean isPlayButtonPresent(){
        return DriverUtility.isElementPresent(driver,playButton);

    }

    public void clickPlayButton(){
        driver.findElement(playButton).click();
    }

    public boolean isNextTrackButtonPresent(){
        return DriverUtility.isElementPresent(driver,nextButton);

    }

    public void clickNextTrackButton(){
        driver.findElement(nextButton).click();

    }

    public boolean isPreviousTrackButtonPresent(){
        return DriverUtility.isElementPresent(driver, previousButton);

    }

    public void clickPreviousTrackButton(){
        driver.findElement(previousButton).click();

    }

    public void isMuteButtonShown(){

    }

    public void clickMuteButton(){

    }

    public void isUnmuteButtonShown(){

    }

    public void clickUnmuteButton(){

    }
}
