package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotePage {

    @FindBy(id="logout-button")
    private WebElement logoutButton;

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


}
