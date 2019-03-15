package com.interviewtask.appmanager;

import com.interviewtask.actions.Emails;
import com.interviewtask.actions.Folders;
import com.interviewtask.actions.Navigation;
import com.interviewtask.actions.Session;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    private static volatile ApplicationManager instanceOfApp;
    private static volatile WebDriver driver;
    public Session session;
    public Navigation navigation;
    public Folders folders;
    public Emails emails;

    private ApplicationManager() {
        Properties config = getTestConfig();
        driver = DriverFactory.getDriver(DriverType.valueOf(config.getProperty("driver")));
        driver.manage().window().maximize();
        long implicitlyWaitTimeout = Long.parseLong(config.getProperty("implicitlyWait"));
        long pageLoadTimeout = Long.parseLong(config.getProperty("pageLoadTimeout"));
        driver.manage().timeouts().implicitlyWait(implicitlyWaitTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        session = new Session(driver, config);
        navigation = new Navigation(driver);
        folders = new Folders(driver);
        emails = new Emails(driver);
    }

    public static ApplicationManager getInstance() {
        if (instanceOfApp == null || driverIsInvalid()) {
            instanceOfApp = new ApplicationManager();
        }
        return instanceOfApp;
    }

    private static Properties getTestConfig() {
        Properties config = new Properties();
        try (InputStream in = ApplicationManager.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (in != null) {
                config.load(in);
            } else {
                throw new FileNotFoundException("test.properties are not found");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return config;
    }

    private static boolean driverIsInvalid() {
        try {
            driver.getCurrentUrl();
            return false;
        } catch (WebDriverException e) {
            return true;
        }
    }

    public void quit() {
        session.logOut();
        driver.quit();
    }
}
