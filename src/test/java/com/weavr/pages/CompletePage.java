package com.weavr.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompletePage extends BasePage{

    @FindBy(xpath = "//h2[@class='complete-header']")
    public WebElement completeText;

}
