package com.weavr.UI.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends BasePage{

    @FindBy(xpath = "//span[@class='title']")
    public WebElement checkoutPageTitle;

    @FindBy(xpath = "//button[@name='finish']")
    public WebElement finishBtn;

    @FindBy(xpath = "//button[@name='remove-sauce-labs-bike-light']")
    public WebElement removeLightBtn;



}
