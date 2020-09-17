package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.NotePage;
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
public class NoteTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    public SignUpLoginLogoutTest signUpLoginLogoutTest;
    public NotePage notePage;

    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(new Locale("en","US"));
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        notePage = new NotePage(driver);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void addNote(){
        signUpLoginLogoutTest.login();

        String noteTitle = "Note Title";
        String noteDescription = "Note Description";

        notePage.addNote(driver, noteTitle,noteDescription);

        assertEquals(noteTitle, notePage.getNoteTitle());
        assertEquals(noteDescription, notePage.getNoteDescription());

    }

    @Test
    @Order(2)
    public void editNote(){

        String noteTitle = "Note Title Update";
        String noteDescription = "Note Description Update";

        notePage.editNote(driver, noteTitle,noteDescription);

        assertEquals(noteTitle, notePage.getNoteTitle());
        assertEquals(noteDescription, notePage.getNoteDescription());
    }

    @Test
    @Order(3)
    public void deleteNote(){
        notePage.deleteNote(driver);

        Assertions.assertNull(notePage.getNoteTitle());
        Assertions.assertNull(notePage.getNoteDescription());
    }
}
