package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

public class AddToCartAndCheckoutStepDefinition extends BaseClass{

    @Given("User is on Homepage")
    public void website() throws IOException {
        setup();
    }

    @When("User adds a product to cart and checkout")
    public void registrationDetails() throws IOException, InterruptedException {
        pageFactory.getAddToCartAndCheckout().addToCart();
    }

    @And("User registers with valid details and checkout the product")
    public void user_is_registered() throws IOException {
        pageFactory.getAddToCartAndCheckout().registerUser();
    }

    @Then("Order is placed successfully")
    public void productOrderPlaced() throws IOException {
        pageFactory.getAddToCartAndCheckout().verifyOrderPlaced();
        close();
    }
}