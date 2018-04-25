package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    WebDriver driver;

    private By playlistItemLocator = By.xpath("//div[@id='playList']/ul[@id='playlist-ul']//a[contains(@class, 'jp-playlist-item') and not (contains(@class, 'jp-playlist-item-remove'))]");
    private By resetButtonLocator = By.xpath("//button[@onclick='clearPlaylist();']");

    ////div[@id="playList"]/ul[@id="playlist-ul"]//a[contains(@class, 'jp-playlist-item') and not (contains(@class, 'jp-playlist-item-remove'))]
    public Playlist(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getPlaylistItems() {
        List<WebElement> playListItemsWebElement = driver.findElements(playlistItemLocator);
        List<String> playListItemsText = new ArrayList<String>();
        for (WebElement playListItem : playListItemsWebElement) {
            playListItemsText.add(playListItem.getText().trim());
        }
        return playListItemsText;
    }

    public boolean isPlaylistEmpty() {
        // ну фиг знает, если метод getPlaylistItems невыполнитсо то всё итак сломаетсо
        List<String> playListItems = getPlaylistItems();
        if (playListItems.size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isResetButtonPresent() {
        return DriverUtility.isElementPresent(driver, resetButtonLocator);
    }

    public void clickResetButton() {
        driver.findElement(resetButtonLocator).click();
    }
}
