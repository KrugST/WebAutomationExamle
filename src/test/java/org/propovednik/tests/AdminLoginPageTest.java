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
    private By adminLoginError = By.xpath("//div[@id='msgDiv']/div[contains(text(),'The username/email or password you entered is incorrect.')]");


    @Test
    public void testingAdminLoginPage() {
        WebDriver driver = getDriverInstance();

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();
        adminLoginPage.loginWithAdminAccount();

    }

    @Test
    public void testingAdminLoginPageWithRandomCredentials() {
        String login = "abrakadaabra";
        String password = "azazashechka";

        WebDriver driver = getDriverInstance();

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.goToAdminLogin();

        adminLoginPage.loginWithProvidedCredentials(login,password);

        assertTrue(DriverUtility.isElementPresent(driver,adminLoginError));

    }

    @Test
    public void testingNavigationOnAdminPanel() throws InterruptedException {
        WebDriver driver = getDriverInstance();

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
