package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AudioPleer {

    WebDriver driver;
    private By playButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-play')]");
    private By nextButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-next')]");
    private By previousButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-previous')]");
    private By pauseButton = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-pause')]");
    private By muteSoundButtonLocator = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-mute')]");
    private By unmuteSoundButtonLocator = By.xpath("//div[@id='jp_container_1']//button[contains(@class, 'jp-unmute')]");

    public AudioPleer(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPauseButtonPresent() {
        return DriverUtility.isElementPresent(driver, pauseButton);
    }

    public void clickPauseButton() {
        driver.findElement(pauseButton).click();
    }

    public boolean isPlayButtonPresent() {
        return DriverUtility.isElementPresent(driver, playButton);

    }

    public void clickPlayButton() {
        driver.findElement(playButton).click();
    }

    public boolean isNextTrackButtonPresent() {
        return DriverUtility.isElementPresent(driver, nextButton);

    }

    public void clickNextTrackButton() {
        driver.findElement(nextButton).click();

    }

    public boolean isPreviousTrackButtonPresent() {
        return DriverUtility.isElementPresent(driver, previousButton);

    }

    public void clickPreviousTrackButton() {
        driver.findElement(previousButton).click();

    }

    public boolean isMuteButtonShown() {
        return DriverUtility.isElementPresent(driver, muteSoundButtonLocator);
    }

    public void clickMuteButton() {
        driver.findElement(muteSoundButtonLocator).click();
    }

    public boolean isUnmuteButtonShown() {
        return DriverUtility.isElementPresent(driver, unmuteSoundButtonLocator);
    }

    public void clickUnmuteButton() {
        driver.findElement(unmuteSoundButtonLocator).click();
    }
}
