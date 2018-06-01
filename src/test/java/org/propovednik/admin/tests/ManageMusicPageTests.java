package org.propovednik.admin.tests;

import org.openqa.selenium.WebDriver;
import org.propovednik.admin.AdminLoginPage;
import org.propovednik.base.BaseTest;
import org.testng.annotations.Test;

public class ManageMusicPageTests extends BaseTest {

    @Test
    public void testWorkingWithFiles(){
        WebDriver driver = getDriverInstance();

        // Navigate to http://dev.propovednik.com/admin
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        // Login
        adminLoginPage.loginWithAdminAccount();

        // Navigate to Manage Music Tracks page

        // Create new folder
        // Verify breadcrumbs says Медиатека » YourFolderName
        // Add multiple (lets to 3 files) mp3 files to upload queue
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

}
