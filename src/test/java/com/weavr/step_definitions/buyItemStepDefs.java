package com.weavr.step_definitions;

import com.weavr.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import com.weavr.utilities.Driver;

import java.util.Locale;


public class buyItemStepDefs {
    ProductsPage productsPage = new ProductsPage();
    YourChartPage yourChartPage = new YourChartPage();
    InfoPage infoPage = new InfoPage();
    CheckOutPage checkOutPage = new CheckOutPage();
    CompletePage completePage = new CompletePage();

//    private WebDriver driver;
//    WebDriverWait wait = new WebDriverWait(driver,20);

    @When("the user clicks the addToCart button")
    public void the_user_clicks_the_addToCart_button() throws InterruptedException {
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")));

        productsPage.addBackpackBtn.click();
        //productsPage.addTshirtBtn.click();
      //  System.out.println("user click to add btn");
    }

    @When("the user clicks to shopping trolley icon")
    public void the_user_clicks_to_shopping_trolley_icon() {
        productsPage.marketTrolleyIcon.click();
    }

    @Then("the user should able to navigate your cart page")
    public void the_user_should_able_to_navigate_your_cart_page() {
        assertEquals("YOUR CART",yourChartPage.title.getText());
    }


    @Given("the user on the your chart page")
    public void the_user_on_the_your_chart_page() {
        Driver.get().get("https://www.saucedemo.com/cart.html");
    }

    @When("the user clicks checkout button")
    public void the_user_clicks_checkout_button() {
        yourChartPage.checkOutBtn.click();
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
        infoPage.firstNameInputBox.sendKeys("fakeName");
        infoPage.lastNameInputBox.sendKeys("fakeLastName");
        infoPage.postalCodeInputBox.sendKeys("fakeNumber");
    }

    @When("the user clicks to continue button")
    public void the_user_clicks_to_continue_button() {
        infoPage.continueBtn.click();
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
    }

    @Then("the user shoul able to see {string}")
    public void the_user_shoul_able_to_see(String message) {
        assertNotEquals(infoPage.errorBtn.getText(),message);
    }





}
