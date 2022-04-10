package com.weavr.UI.step_definitions;

import com.github.javafaker.Faker;
import com.weavr.UI.pages.*;
import com.weavr.UI.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import com.weavr.UI.utilities.Driver;

import java.util.Locale;


public class buyItemStepDefs {
    ProductsPage productsPage = new ProductsPage();
    YourChartPage yourChartPage = new YourChartPage();
    InfoPage infoPage = new InfoPage();
    CheckOutPage checkOutPage = new CheckOutPage();
    CompletePage completePage = new CompletePage();
    Faker faker = new Faker();

    String firstProduct = null;
    String firstProductnameProductPage = null;
    String secondProductnameProductPage = null;


    @When("the user clicks the addToCart button")
    public void the_user_clicks_the_addToCart_button() {
        productsPage.addBackpackBtn.click();
        BrowserUtils.waitFor(1);

    }

    @When("the user clicks to shopping trolley icon")
    public void the_user_clicks_to_shopping_trolley_icon() throws InterruptedException {
        productsPage.marketTrolleyIcon.click();
        BrowserUtils.waitFor(1);

    }

    @Given("the user get product prices for verification")
    public void the_user_get_product_prices_for_verification() {
        firstProduct = productsPage.productPrices.get(0).getText();
    }

    @Then("the product prices should be same with cart prices")
    public void the_product_prices_should_be_same_with_cart_prices() {
        assertEquals(firstProduct,yourChartPage.productPricesOnCart.get(0).getText());
    }


    @Then("the user should able to navigate your cart page")
    public void the_user_should_able_to_navigate_your_cart_page() {
        assertEquals("YOUR CART",yourChartPage.title.getText());
    }


    @When("the user on the your chart page")
    public void the_user_on_the_your_chart_page() {
        Driver.get().get("https://www.saucedemo.com/cart.html");
    }

    @When("the user clicks checkout button")
    public void the_user_clicks_checkout_button() {
        yourChartPage.checkOutBtn.click();
        BrowserUtils.waitFor(1);
    }

    @Then("the user should able to navigate info page")
    public void the_user_should_able_to_navigate_info_page() {
        assertEquals(infoPage.title.getText().toLowerCase(Locale.ROOT),"checkout: your information");
    }


    @Given("the user on the info page")
    public void the_user_on_the_info_page() {
        Driver.get().get("https://www.saucedemo.com/checkout-step-one.html");
    }

    @When("the user enters the valid user information")
    public void the_user_enters_the_valid_user_information() {
        infoPage.firstNameInputBox.sendKeys(faker.name().firstName());
        infoPage.lastNameInputBox.sendKeys(faker.name().lastName());
        infoPage.postalCodeInputBox.sendKeys(faker.address().zipCode());
        BrowserUtils.waitFor(1);
    }

    @When("the user clicks to continue button")
    public void the_user_clicks_to_continue_button() {
        infoPage.continueBtn.click();
        BrowserUtils.waitFor(1);
    }

    @Then("the user should able to checkout overview page")
    public void the_user_should_able_to_checkout_overview_page() {
       assertEquals(Driver.get().getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
    }

    @Given("the user on the checkout page")
    public void the_user_on_the_checkout_page() {
        Driver.get().get("https://www.saucedemo.com/checkout-step-two.html");
    }

    @When("the user clicks to finish button")
    public void the_user_clicks_to_finish_button() {
        checkOutPage.finishBtn.click();
        BrowserUtils.waitFor(1);
    }

    @Then("the user should able to see thanks you for your order text")
    public void the_user_should_able_to_see_thanks_you_for_your_order_text() {
        assertEquals(completePage.completeText.getText(),"THANK YOU FOR YOUR ORDER");
    }

    @When("the user enters the {string}, {string} and {string}")
    public void the_user_enters_the_and(String firstName, String lastName, String postalCode) {
        infoPage.firstNameInputBox.sendKeys(firstName);
        infoPage.lastNameInputBox.sendKeys(lastName);
        infoPage.postalCodeInputBox.sendKeys(postalCode);
        BrowserUtils.waitFor(1);
    }

    @Then("the user should able to see {string}")
    public void the_user_should_able_to_see(String message) {
        assertNotEquals(infoPage.errorBtn.getText(),message);
    }

    @Given("the user choose the products from products page")
    public void the_user_choose_the_products_from_products_page() {
        productsPage.addBackpackBtn.click();
        BrowserUtils.waitFor(1);
        productsPage.addLightBtn.click();
        BrowserUtils.waitFor(1);
        firstProductnameProductPage = productsPage.productNamesProductPage.get(0).getText();
        secondProductnameProductPage = productsPage.productNamesProductPage.get(1).getText();
    }

    @Then("the name of the products should able to same with the products chosen from products page")
    public void the_name_of_the_products_should_able_to_same_with_the_products_chosen_from_products_page() {
        String firstProductActual = productsPage.productNamesProductPage.get(0).getText();
        String secondProductActual = productsPage.productNamesProductPage.get(1).getText();
        assertEquals(firstProductnameProductPage,firstProductActual);
        assertEquals(secondProductnameProductPage,secondProductActual);
    }






}
