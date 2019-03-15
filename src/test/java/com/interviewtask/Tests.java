package com.interviewtask;

import com.interviewtask.appmanager.ApplicationManager;
import com.interviewtask.utils.SortByVowelsPercentage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Tests {
    private ApplicationManager app;

    @BeforeClass
    public void setUp() {
        app = ApplicationManager.getInstance();
        if (!app.session.isLoggedIn()) {
            app.session.logIn();
        }
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }

    @Test
    public void emailTest() {
        String newFolder = "NewFolder";
        app.navigation.goToFolderSettings();
        app.folders.createFolder(newFolder);
        app.navigation.goToEmails();
        List<String> subjects = app.emails.getSubjectsFromFirst(2);
        List<String> top5HighestPercentageVowels = app.emails.getTopSubjects(subjects, 5, new SortByVowelsPercentage());
        app.emails.moveEmailsWithSubjectsTo(top5HighestPercentageVowels, newFolder);
        app.navigation.goToFolder(newFolder);
        List<String> emailsInFolder = app.emails.getSubjects(app.emails.getMessages());
        Assert.assertEquals(emailsInFolder.size(), top5HighestPercentageVowels.size(),
                "Wrong amount of emails has been moved.");
    }
}
