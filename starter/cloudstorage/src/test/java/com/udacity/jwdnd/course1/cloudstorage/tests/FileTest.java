package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.FilePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String baseUrl;

    public FilePage filePage;

    public ResultPage resultPage;

    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(new Locale("en","US"));
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        baseUrl = "http://localhost:" + this.port;
        filePage = new FilePage(driver);
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
    public void uploadFile() throws InterruptedException {

        driver.get(baseUrl + "/home");

        String filePath = "C:/Users/Noyan/Desktop/a.png";

        filePage.uploadFile(driver,filePath);

        Thread.sleep(1000);

        resultPage.clickReturnHomeButton(driver);

        Thread.sleep(1000);

        Assertions.assertTrue(filePage.getFileName().contains("a.png"));
    }

    @Test
    @Order(2)
    public void deleteFile() throws InterruptedException {

        driver.get(baseUrl + "/home");

        filePage.deleteFile(driver);

        Thread.sleep(1000);

        resultPage.clickReturnHomeButton(driver);

        Thread.sleep(1000);

        Assertions.assertFalse(filePage.getFileName().contains("a.png"));
    }
}
