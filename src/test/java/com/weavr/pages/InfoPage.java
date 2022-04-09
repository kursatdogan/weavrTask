package com.weavr.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfoPage extends BasePage {

    @FindBy(xpath = "//input[@id='first-name']")
    public WebElement firstNameInputBox;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement lastNameInputBox;

    @FindBy(xpath = "//input[@id='postal-code']")
    public WebElement postalCodeInputBox;

    @FindBy(xpath = "//span[@class='title']")
    public WebElement title;

    @FindBy(xpath = "//input[@id='continue']")
    public WebElement continueBtn;

    @FindBy(xpath = "//button[@class='error-button']")
    public WebElement errorBtn;

}
