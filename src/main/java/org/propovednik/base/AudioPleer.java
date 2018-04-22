package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AudioPleer {

    WebDriver driver;
    private By playButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-play')]");

    public AudioPleer(WebDriver driver){
        this.driver = driver;
    }

    public void isPlayButtonPresent(){

    }

    public void clickPlayButton(){
        driver.findElement(playButton).click();
    }

    public void isNextTrackButtonPresent(){

    }

    public void clickNextTrackButton(){

    }
}
