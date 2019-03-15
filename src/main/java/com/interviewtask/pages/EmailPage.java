package com.interviewtask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EmailPage extends Page {

    @FindBy(xpath = "//a[@data-name='link' and not(ancestor::div[contains(@style, 'display: none;')])]")
    public List<WebElement> messages;

    @FindBy(xpath = "//span[@class='b-paginator__page b-paginator__page_active']")
    public WebElement currentTab;

    @FindBy(xpath = "//div[@data-name='next']")
    public WebElement nextTabBtn;

    @FindBy(xpath = "//a[@id='PH_logoutLink']")
    public WebElement logoutLink;

    public EmailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getCheckbox(WebElement message) {
        return message.findElement(By.xpath(".//div[@class='js-item-checkbox b-datalist__item__cbx']"));
    }

    public String getSubject(WebElement message) {
        WebElement subjectWithText = message.findElement(By.xpath(".//div[contains(@class, 'subj')]"));
        return ((JavascriptExecutor) driver).executeScript(
             "var element = arguments[0];\n" +
                "    var text = \"\";\n" +
                "    for (var i = 0; i < element.childNodes.length; i++) {\n" +
                "            var curNode = element.childNodes[i];\n" +
                "            if (curNode.nodeName === \"#text\") {\n" +
                "                text = curNode.nodeValue;\n" +
                "                break;\n" +
                "            }\n" +
                "        }" +
                "    return text", subjectWithText).toString();
    }

    public int getCurrentTabNumber() {
        return Integer.valueOf(currentTab.getText());
    }

    public void waitForRefreshData(WebElement message) {
        wait.until(ExpectedConditions.stalenessOf(message));
    }

    public void waitOnePageDisplay() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='b-paginator__page " +
                "b-paginator__page_active']")));
    }
}
