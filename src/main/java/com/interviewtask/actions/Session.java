package com.interviewtask.actions;

import com.interviewtask.pages.EmailPage;
import com.interviewtask.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;

public class Session {
    private WebDriver driver;
    private Properties config;
    private MainPage mainPage;
    private EmailPage emailPage;

    public Session(WebDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        mainPage = new MainPage(driver);
        emailPage = new EmailPage(driver);
    }

    public void logIn() {
        goToMainPage(config.getProperty("url"));
        enterCredentials(config.getProperty("username"), config.getProperty("domain"), config.getProperty("password"));
    }

    public void logOut() {
        emailPage.logoutLink.click();
    }

    private void goToMainPage(String url) {
        mainPage.open(url);
    }

    private void enterCredentials(String email, String domain, String password) {
        mainPage.emailInput.sendKeys(email);
        new Select(mainPage.domainSelect).selectByVisibleText(domain);
        mainPage.passwordInput.sendKeys(password);
        mainPage.logInButton.click();
    }

    public boolean isLoggedIn() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("messages") || currentUrl.contains("addressbook") ||
                currentUrl.contains("filesearch") || currentUrl.contains("settings") || currentUrl.contains("send");
    }
}
