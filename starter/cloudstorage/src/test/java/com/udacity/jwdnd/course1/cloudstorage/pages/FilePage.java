package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class FilePage {

    @FindBy(id = "upload-file-button")
    private WebElement uploadFileButton;

    @FindBy(id = "fileUpload")
    private WebElement chooseFile;

    @FindBy(id = "delete-file-button")
    private WebElement deleteFileButton;

    public FilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void uploadFile(WebDriver driver,String filePath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(chooseFile)).sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOf(uploadFileButton)).click();

    }

    public void deleteFile(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(deleteFileButton)).click();
    }

    public File getLatestFileFromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }
}
