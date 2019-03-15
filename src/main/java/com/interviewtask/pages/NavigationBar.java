package com.interviewtask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar extends Page {

    @FindBy(xpath = "//div[@class='pm-menu']//a[@href='/messages/inbox/']")
    public WebElement mailsLink;

    @FindBy(xpath = "//div[@id='b-nav_folders']//a[@href='/messages/inbox/']")
    public WebElement inboxFolder;

    @FindBy(xpath = "//div[contains(@class, 'b-nav__item_settings')]")
    public WebElement hideText;

    @FindBy(xpath = "//a[@data-mnemo='settings-folders']")
    public WebElement setupFolders;

    @FindBy(xpath = "//div[@data-group='moveTo'  and not(ancestor::div[contains(@style, 'display: none;') " +
            "or contains(@style, 'visibility: hidden;')])]")
    public WebElement moveBtn;

    public NavigationBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement folderLink(String label) {
        return driver.findElement(By.xpath(String.format("//span[@class='b-nav__item__text' and text()='%s']", label)));
    }

    public void moveToFolder(String label) {
        moveBtn.click();
        driver.findElement(By.xpath(String.format("//a[@data-text='%s']", label))).click();
    }
}
