package com.weavr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourChartPage extends BasePage{
    @FindBy(xpath = "//span[@class='title']")
    public WebElement title;

    @FindBy(id="checkout")
    public WebElement checkOutBtn;
}
