package com.weavr.step_definitions;

import com.weavr.pages.LoginPage;
import com.weavr.pages.ProductsPage;
import com.weavr.utilities.ConfigurationReader;
import com.weavr.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();

    @Given("the user login with valid credentials")
    public void the_user_login_with_valid_credentials() {
        Driver.get().get(ConfigurationReader.get("url"));
        loginPage.login();
    }

    @Then("the user should be able to see products page")
    public void the_user_should_be_able_to_see_products_page() {
        assertEquals(Driver.get().getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Given("the user should be on login page")
    public void the_user_should_be_on_login_page() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("the user enters invalid {string} and {string}")
    public void the_user_enters_invalid_and(String username, String password){
        loginPage.login(username, password);
    }

    @Then("the user should not able to login and see {string}")
    public void the_user_should_not_able_to_login_and_see(String warningMessage) {
        assertEquals(warningMessage,loginPage.loginErrorMessage.getText());
    }



}
