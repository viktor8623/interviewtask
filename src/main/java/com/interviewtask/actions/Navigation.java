package com.interviewtask.actions;

import com.interviewtask.pages.EmailPage;
import com.interviewtask.pages.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Navigation {
    private WebDriver driver;
    private NavigationBar navigationBar;
    private EmailPage emailPage;

    public Navigation(WebDriver driver) {
        this.driver = driver;
        navigationBar = new NavigationBar(driver);
        emailPage = new EmailPage(driver);
    }

    public void goToFolderSettings() {
        Actions actions = new Actions(driver);
        actions.moveToElement(navigationBar.hideText);
        actions.moveToElement(navigationBar.setupFolders);
        actions.click().build().perform();
    }

    public void goToEmails() {
        navigationBar.mailsLink.click();
    }

    public void goToInboxFolder() {
        WebElement firstEmail = emailPage.messages.get(0);
        navigationBar.inboxFolder.click();
        emailPage.waitForRefreshData(firstEmail);
    }

    public void goToFolder(String label) {
        emailPage.scrollToTop();
        navigationBar.folderLink(label).click();
        emailPage.waitOnePageDisplay();
    }

    public void moveEmailToFolder(String label) {
        navigationBar.moveToFolder(label);
    }
}
