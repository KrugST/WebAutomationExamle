package org.propovednik.admin.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.propovednik.admin.AdminLibrary;
import org.propovednik.admin.AdminLoginPage;
import org.propovednik.admin.AdminMenu;
import org.propovednik.base.BaseTest;
import org.propovednik.base.JSWaiter;
import org.propovednik.base.Menu;
import org.propovednik.pages.HomePage;
import org.propovednik.pages.MediatekaPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertTrue;


public class ManageMusicPageTests extends BaseTest {

    String newFolderName = "TestFolder-"+UUID.randomUUID().toString(); // maybe i should make that rendom?

    @Test
    public void testWorkingWithFiles() throws InterruptedException {
        JSWaiter jsWaiter = new JSWaiter(driver);

        // Navigate to http://dev.propovednik.com/admin
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        // Login
        jsWaiter.waitForJQueryLoad();
        adminLoginPage.loginWithAdminAccount();

        // Navigate to Manage Music Tracks page
        jsWaiter.waitForJQueryLoad();
        AdminMenu adminMenu = new AdminMenu(driver);
        adminMenu.clickAdminMenuItem("Library");

        // Create new folder
        jsWaiter.waitForJQueryLoad();
        AdminLibrary adminLibrary = new AdminLibrary(driver);
        adminLibrary.createNewFolder(newFolderName);

        //Make folder public
        adminLoginPage.goToAdminLogin();
        adminMenu.clickAdminMenuItem("Library");
        adminLibrary.makeFolderPublic(newFolderName);

        // Verify breadcrumbs says Медиатека » YourFolderName
        List<String> expectedBreadcrumList = Arrays.asList("Медиатека", ""+newFolderName+"");
        HomePage homePage = new HomePage(driver);
        homePage.goToDevHomePage();

        Menu menu = new Menu(driver);
        menu.clickMenuItem("Медиатека");

        MediatekaPage mediatekaPage = new MediatekaPage(driver);
        mediatekaPage.clickFolder(newFolderName);

        List<String> actualBreadcrumbsList = mediatekaPage.getBreadcrumbs();
        System.out.println(actualBreadcrumbsList);
        System.out.println(expectedBreadcrumList);

        assertTrue(actualBreadcrumbsList.equals(expectedBreadcrumList));

        // Add multiple (lets to 3 files) mp3 files to upload queue
        adminLoginPage.goToAdminLogin();
        adminMenu.clickAdminMenuItem("Library");
        adminLibrary.clickAdminLibraryFolder(newFolderName);

        // Verify files added and they have proper size and title shown
        // Verify queue length shows proper number
        // Verify Upload All, Cancel All, Remove All buttons are present
        // Verify Upload, Cancel and Remove buttons are present for each song
        // Click Upload button for one of the files
        // Verify file loaded by checking the status, it should have check mark
        // Click Upload All button
        // Verify Refresh Current Directory button is shown
        // Click Refresh Current Directory button
        // Verify two files are shown in directoty
        // Verify file names
        // Verify files are private
        // Make each file public
        // Delete one of the files
        // Verify file is deleted
        // Edit file name for one of the files
        // Verify file name is edited

        //Delete folder

    }

    @Test
    public void updateTags(){
        WebDriver driver = getDriverInstance();

        // Navigate to http://dev.propovednik.com/admin
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        // Login
        adminLoginPage.loginWithAdminAccount();

        // Navigate to Manage Music Tracks page
        // Create new folder
        // Verify breadcrumbs says Медиатека » YourFolderName
        // Upload multiple files and refresh the folder
        // Verify files got uploaded
        // For first file, update title, artist and album
    }

    @Test
    public void testBatchTagEditor_currentFolder(){

    }

    @Test
    public void testBatchTagEditor_includeSubFolders(){

    }

    @Test
    public void name() {
        JSWaiter jsWaiter = new JSWaiter(driver);

        // Navigate to http://dev.propovednik.com/admin
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        // Login
        jsWaiter.waitForJQueryLoad();
        adminLoginPage.loginWithAdminAccount();

        // Navigate to Manage Music Tracks page
        jsWaiter.waitForJQueryLoad();
        AdminMenu adminMenu = new AdminMenu(driver);
        adminMenu.clickAdminMenuItem("Library");

        // Create new folder
        jsWaiter.waitForJQueryLoad();
        AdminLibrary adminLibrary = new AdminLibrary(driver);
        adminLibrary.clickAdminLibraryFolder("2017 Christmas story");
        jsWaiter.waitForJQueryLoad();
        adminLibrary.uploadFile();
    }
}
