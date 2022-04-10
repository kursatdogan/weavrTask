package com.weavr.UI.pages;

import com.weavr.UI.utilities.BrowserUtils;
import com.weavr.UI.utilities.ConfigurationReader;
import com.weavr.UI.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(id = "login-button")
    public WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test]")
    public WebElement loginErrorMessage;

    public void login() {
        usernameInputBox.sendKeys(ConfigurationReader.get("username"));
        passwordInputBox.sendKeys(ConfigurationReader.get("password"));
        loginBtn.click();
        BrowserUtils.waitForPageToLoad(5);
    }

    public void login(String username,String password){
        usernameInputBox.sendKeys(username);

        passwordInputBox.sendKeys(password);

        loginBtn.click();
    }

}
