package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

    @FindBy(id="return-home")
    private WebElement returnHomeButton;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickReturnHomeButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try{
            wait.until(ExpectedConditions.visibilityOf(returnHomeButton)).click();
        }catch (TimeoutException ex){
            System.out.println("clickReturnHomeButton Error : " + ex);
        }
    }
}
