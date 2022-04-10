package com.weavr.UI.pages;

import com.weavr.UI.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {



    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    public WebElement addBackpackBtn;

    @FindBy(id="add-to-cart-sauce-labs-bike-light")
    public WebElement addLightBtn;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement marketTrolleyIcon;

    @FindBy(xpath = "react-burger-menu-btn")
    public WebElement hiddenBtn;

    @FindBy(css = ".inventory_item_price")
    public List<WebElement> productPrices;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> productNamesProductPage;


}
