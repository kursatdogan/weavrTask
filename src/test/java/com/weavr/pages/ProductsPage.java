package com.weavr.pages;

import com.weavr.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage {



    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    public WebElement addBackpackBtn;

    @FindBy(id="add-to-cart-sauce-labs-bike-light")
    public WebElement addTshirtBtn;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement marketTrolleyIcon;

    @FindBy(xpath = "react-burger-menu-btn")
    public WebElement hiddenBtn;


}
