package org.propovednik.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.propovednik.admin.AdminLibrary;
import org.propovednik.admin.AdminMenu;
import org.propovednik.base.BaseTest;
import org.propovednik.base.DriverUtility;
import org.propovednik.admin.AdminLoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminLoginPageTest extends BaseTest {

    @Test
    public void testingLoginWithWrongCredentials() {
        String login = "abrakadaabra";
        String password = "azazashechka";

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        adminLoginPage.loginWithProvidedCredentials(login, password);

        assertTrue(adminLoginPage.isLoginErrorPresent(login, password));
    }

    @Test
    public void testingNavigationOnAdminPanel() throws InterruptedException {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        adminLoginPage.loginWithAdminAccount();

        AdminMenu adminMenu = new AdminMenu(driver);
        adminMenu.clickAdminMenuItem("Library");
        Thread.sleep(1000);
        AdminLibrary adminLibrary = new AdminLibrary(driver);
        adminLibrary.clickAdminLibraryFolder("Для семейных");
        Thread.sleep(1000);
        adminLibrary.clickAdminLibraryFolder("2004 Семейное Кавказ");
    }


}
