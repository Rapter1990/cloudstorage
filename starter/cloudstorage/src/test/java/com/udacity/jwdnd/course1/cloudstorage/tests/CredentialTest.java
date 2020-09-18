package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    public CredentialPage credentialPage;

    public ResultPage resultPage;

    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(new Locale("en","US"));
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        credentialPage = new CredentialPage(driver);
        resultPage = new ResultPage(driver);

        driver.get("http://localhost:" + this.port + "/signup");
        SignUpPage signupPage = new SignUpPage(driver);
        String firstname = "Sonny";
        String lastname = "Johnson";
        String username = "sonny_johnson";
        String password = "P4ssword";
        signupPage.signupUser(firstname,lastname,username,password);

        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(username, password);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void addCredential() throws InterruptedException {

        driver.get("http://localhost:" + this.port + "/home");

        String url = "https://www.yahoo.com";
        String username = "yahoo_username";
        String password = "yahoo_password";

        credentialPage.addCredential(driver, url, username, password);

        Thread.sleep(1000);

        resultPage.clickReturnHomeButton(driver);

        Thread.sleep(1000);

        credentialPage.clickCredentialTab(driver);

        Thread.sleep(1000);

        assertEquals(url, credentialPage.getCredentialUrl());
        assertEquals(username, credentialPage.getCredentialUsername());
        Assertions.assertNotEquals(password, credentialPage.getCredentialPassword());

    }

    @Test
    @Order(2)
    public void getActualPassword(){

        driver.get("http://localhost:" + this.port + "/home");
        credentialPage.clickCredentialTab(driver);

        credentialPage.clickEditCredential(driver);

        String password = credentialPage.getEditPassword();
        assertEquals("yahoo_password", password);
        credentialPage.closeButton(driver);
    }

    @Test
    @Order(3)
    public void editCredential() throws InterruptedException {

        driver.get("http://localhost:" + this.port + "/home");

        String url = "https://www.udacity.com";
        String username = "udacity_username";
        String password = "udacity_password";

        credentialPage.editCredential(driver, url, username, password);

        Thread.sleep(1000);

        resultPage.clickReturnHomeButton(driver);

        Thread.sleep(1000);

        credentialPage.clickCredentialTab(driver);

        Thread.sleep(1000);

        assertEquals(url, credentialPage.getCredentialUrl());
        assertEquals(username, credentialPage.getCredentialUsername());
        Assertions.assertNotEquals(password, credentialPage.getCredentialPassword());
    }

    @Test
    @Order(4)
    public void deleteCredential() throws InterruptedException {

        driver.get("http://localhost:" + this.port + "/home");

        credentialPage.deleteCredential(driver);

        Thread.sleep(1000);

        resultPage.clickReturnHomeButton(driver);
    }
}
