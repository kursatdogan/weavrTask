package com.weavr.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends BasePage{

    @FindBy(xpath = "//span[@class='title']")
    public WebElement checkoutPageTitle;

    @FindBy(xpath = "//button[@name='finish']")
    public WebElement finishBtn;



}
