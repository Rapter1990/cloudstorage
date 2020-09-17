package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignUpLoginLogoutTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    public String baseURL;

    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(new Locale("en","US"));
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void signUp() {
        driver.get("http://localhost:" + this.port + "/signup");
        SignUpPage signupPage = new SignUpPage(driver);
        String firstname = "Sonny";
        String lastname = "Johnson";
        String username = "sonny_johnson";
        String password = "P4ssword";
        signupPage.signupUser(firstname,lastname,username,password);
    }

    @Test
    @Order(2)
    public void login() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        String username = "sonny_johnson";
        String password = "P4ssword";
        loginPage.loginUser(username, password);

        assertEquals(baseURL+"/home", driver.getCurrentUrl());
    }

    @Test
    @Order(3)
    public void logout() {
        driver.get("http://localhost:" + this.port + "/home");
        assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.userLogout(driver);

        assertEquals(baseURL+"/login?logout", driver.getCurrentUrl());
    }

}
