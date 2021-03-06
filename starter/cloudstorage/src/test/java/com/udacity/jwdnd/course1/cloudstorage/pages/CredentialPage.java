package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredentialPage {

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialstab;

    @FindBy(id="addcredential-button")
    private WebElement addcredentialbutton;

    @FindBy(id="editcredential-button")
    private WebElement editcredentialbutton;

    @FindBy(id="deletecredential-button")
    private WebElement deletecredentialbutton;

    @FindBy(id="credentialurl-display")
    private WebElement credentialurldisplay;

    @FindBy(id="credentialusername-display")
    private WebElement credentialusernamedisplay;

    @FindBy(id="credentialpassword-display")
    private WebElement credentialpassworddisplay;

    @FindBy(id="credential-url")
    private WebElement credentialurlInput;

    @FindBy(id="credential-username")
    private WebElement credentialusernameInput;

    @FindBy(id="credential-password")
    private WebElement credentialpasswordInput;

    @FindBy(id="credentialsubmit-button")
    private WebElement credentialsubmitbutton;

    @FindBy(id="logout-button")
    private WebElement logoutbutton;

    @FindBy(id = "close-button")
    private WebElement closeButton;

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addCredential(WebDriver driver, String url, String username, String password){
        clickCredentialTab(driver);
        clickAddCredential(driver);
        sendUrlToInputAdd(driver,url);
        sendUserNameToInputAdd(driver,username);
        sendPasswordToInputAdd(driver,password);
        clickSubmitCredential(driver);
    }

    public void editCredential(WebDriver driver, String url, String username, String password){
        clickCredentialTab(driver);
        clickEditCredential(driver);
        sendUrlToInputEdit(driver,url);
        sendUserNameToInputEdit(driver,username);
        sendPasswordToInputEdit(driver,password);
        clickSubmitCredential(driver);
    }

    public void deleteCredential(WebDriver driver){
        clickCredentialTab(driver);
        clickDeleteCredential(driver);
    }

    public void clickCredentialTab(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialstab)).click();
    }

    public void clickAddCredential(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addcredentialbutton)).click();
    }

    public void clickEditCredential(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(editcredentialbutton)).click();
    }

    public void clickSubmitCredential(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialsubmitbutton)).click();
    }

    public void clickDeleteCredential(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(deletecredentialbutton)).click();
    }

    public void sendUrlToInputAdd(WebDriver driver,String url) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialurlInput)).sendKeys(url);
    }

    public void sendUserNameToInputAdd(WebDriver driver,String url) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialusernameInput)).sendKeys(url);
    }

    public void sendPasswordToInputAdd(WebDriver driver,String url) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialpasswordInput)).sendKeys(url);
    }

    public void sendUrlToInputEdit(WebDriver driver,String url) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialurlInput));
        credentialurlInput.clear();
        credentialurlInput.sendKeys(url);
    }

    public void sendUserNameToInputEdit(WebDriver driver,String url) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialusernameInput));
        credentialusernameInput.clear();
        credentialusernameInput.sendKeys(url);
    }

    public void sendPasswordToInputEdit(WebDriver driver,String url) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialpasswordInput));
        credentialpasswordInput.clear();
        credentialpasswordInput.sendKeys(url);
    }

    public void closeButton(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(closeButton)).click();
    }


    public void userLogout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(logoutbutton)).click();
    }

    public String getCredentialUrl() { return credentialurldisplay.getText(); }

    public String getCredentialUsername() { return credentialusernamedisplay.getText(); }

    public String getCredentialPassword() { return credentialpassworddisplay.getText(); }

    public String getEditPassword() { return credentialpasswordInput.getAttribute("value"); }
}
