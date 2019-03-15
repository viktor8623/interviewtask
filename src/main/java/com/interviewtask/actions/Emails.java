package com.interviewtask.actions;

import com.interviewtask.pages.EmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Emails {
    private Navigation navigation;
    private EmailPage emailPage;


    public Emails(WebDriver driver) {
        emailPage = new EmailPage(driver);
        navigation = new Navigation(driver);
    }

    public List<WebElement> getMessages() {
        return emailPage.messages;
    }

    public List<String> getSubjects(List<WebElement> messages) {
        List<String> subjects = new ArrayList<>();
        for (WebElement message : messages) {
            subjects.add(emailPage.getSubject(message));
        }
        return subjects;
    }

    public List<String> getTopSubjects(List<String> list, int amount, Comparator<String> comparator) {
        return list.stream().sorted(comparator).limit(amount).collect(Collectors.toList());
    }

    public List<String> getSubjectsFromFirst(int tabs) {
        List<String> listOfSubjects = new ArrayList<>();
        for (int currentTab = 0; currentTab < tabs; currentTab++) {
            listOfSubjects.addAll(getSubjects(getMessages()));
            if(emailPage.getCurrentTabNumber() < tabs) {
                navigateToNextTab();
            }
        }
        return listOfSubjects;
    }

    private void navigateToNextTab() {
        WebElement firstEmail = emailPage.messages.get(0);
        emailPage.nextTabBtn.click();
        emailPage.waitForRefreshData(firstEmail);
    }

    public void moveEmailsWithSubjectsTo(List<String> list, String folderLabel) {
        List<String> listCopy = new ArrayList<>(list);
        navigation.goToInboxFolder();
        while(!listCopy.isEmpty()) {
            if(!selectEmailsFromList(listCopy, folderLabel)) {
                emailPage.nextTabBtn.click();
            }
        }
    }

    private boolean selectEmailsFromList(List<String> list, String folderLabel) {
        for (WebElement email : emailPage.messages) {
            String currentSubject = emailPage.getSubject(email);
            if (list.contains(currentSubject)) {
                emailPage.getCheckbox(email).click();
                WebElement firstMessage = emailPage.messages.get(0);
                navigation.moveEmailToFolder(folderLabel);
                emailPage.waitForRefreshData(firstMessage);
                list.remove(currentSubject);
                return true;
            }
        }
        return false;
    }
}
