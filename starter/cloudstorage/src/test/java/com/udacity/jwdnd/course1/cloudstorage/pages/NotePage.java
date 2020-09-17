package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="addnote-button")
    private WebElement addNoteButton;

    @FindBy(id="editnote-button")
    private WebElement editNoteButton;

    @FindBy(id="deletenote-button")
    private WebElement deleteNoteButton;

    @FindBy(id="note-title")
    private WebElement noteTitleInput;

    @FindBy(id="note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id="notesubmit-button")
    private WebElement noteSubmitButton;

    @FindBy(id="notetitle-display")
    private WebElement noteTitleDisplay;

    @FindBy(id="notedescription-display")
    private WebElement noteDescriptionDisplay;

    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addNote(WebDriver driver, String title, String description){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        clickNotesTab(driver);
        clickAddNewNote(driver);

        writeAddInputTitle(driver,title);
        writeAddDescriptionTitle(driver,description);

        clickSubmitNote(driver);

        clickNotesTab(driver);

    }

    public void editNote(WebDriver driver, String title, String description){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        clickNotesTab(driver);
        clickEditNote(driver);

        writeEditInputTitle(driver,title);
        writeEditDescriptionTitle(driver,description);

        clickSubmitNote(driver);
    }

    public void deleteNote(WebDriver driver){
        clickNotesTab(driver);
        clickDeleteNote(driver);
    }

    public void clickNotesTab(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(notesTab)).click();
    }

    public void clickAddNewNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addNoteButton)).click();
    }

    public void clickEditNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(editNoteButton)).click();
    }

    public void clickDeleteNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(deleteNoteButton)).click();
    }

    public void clickSubmitNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noteSubmitButton)).click();
    }

    public void writeAddInputTitle(WebDriver driver,String title){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput));
        noteTitleInput.sendKeys(title);
    }

    public void writeAddDescriptionTitle(WebDriver driver,String title){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noteDescriptionInput));
        noteDescriptionInput.sendKeys(title);
    }

    public void writeEditInputTitle(WebDriver driver,String title){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput));
        noteTitleInput.clear();
        noteTitleInput.sendKeys(title);
    }

    public void writeEditDescriptionTitle(WebDriver driver,String title){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noteDescriptionInput));
        noteDescriptionInput.clear();
        noteDescriptionInput.sendKeys(title);
    }

    public String getNoteTitle() { return noteTitleDisplay.getText(); }

    public String getNoteDescription() { return noteDescriptionDisplay.getText(); }

}
