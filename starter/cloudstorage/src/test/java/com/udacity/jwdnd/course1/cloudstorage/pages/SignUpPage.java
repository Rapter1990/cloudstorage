package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(id="inputFirstName")
    private WebElement firstnameInput;

    @FindBy(id="inputLastName")
    private WebElement lastnameInput;

    @FindBy(id="inputUsername")
    private WebElement usernameInput;

    @FindBy(id="inputPassword")
    private WebElement passwordInput;

    @FindBy(id="submit-button")
    private WebElement signupButton;


    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signupUser(String firstname, String lastname, String username, String password) {
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signupButton.click();
    }
}
