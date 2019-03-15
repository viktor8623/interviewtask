package com.interviewtask.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetUpFoldersPage extends Page {

    @FindBy(xpath = "//button[@data-name='newFolder']")
    public WebElement addNewFolderBtn;

    @FindBy(xpath = "//div[@class='popup__form']//input[@name='name']")
    public WebElement newFolderNameInput;

    @FindBy(xpath = "//div[@class='popup__form']//button[@type='submit']")
    public WebElement confirmFolderCreation;

    public SetUpFoldersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
