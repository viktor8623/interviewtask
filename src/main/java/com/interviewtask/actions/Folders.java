package com.interviewtask.actions;

import com.interviewtask.pages.SetUpFoldersPage;
import org.openqa.selenium.WebDriver;

public class Folders {
    private WebDriver driver;
    private Navigation navigation;
    private SetUpFoldersPage settings;

    public Folders(WebDriver driver) {
        this.driver = driver;
        this.navigation = new Navigation(driver);
        this.settings = new SetUpFoldersPage(driver);
    }

    public void createFolder(String label) {
        settings.addNewFolderBtn.click();
        settings.newFolderNameInput.sendKeys(label);
        settings.confirmFolderCreation.click();
    }
}
