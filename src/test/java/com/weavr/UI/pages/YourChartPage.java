package com.weavr.UI.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourChartPage extends BasePage{
    @FindBy(xpath = "//span[@class='title']")
    public WebElement title;

    @FindBy(id="checkout")
    public WebElement checkOutBtn;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> productPricesOnCart;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> productsNameYourCart;
}
