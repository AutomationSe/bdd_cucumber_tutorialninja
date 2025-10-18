package stepsdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class search {


    @Given("the User opens the Application")
    public void the_user_opens_the_application() {

    }
    @When("the User enters valid product {string} into the Search box field")
    public void the_user_enters_valid_product_into_the_search_box_field(String vaidproductText) {

    }
    @When("the User clicks on the Search button")
    public void the_user_clicks_on_the_search_button() {

    }
    @Then("the User should get valid product displayed in search results")
    public void the_user_should_get_valid_product_displayed_in_search_results() {

    }

    @When("User enters invalid product {string} into Search box field")
    public void user_enters_invalid_product_into_search_box_field(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("User should get a message about no product matching")
    public void user_should_get_a_message_about_no_product_matching() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("User dont enter any product name into Search box field")
    public void user_dont_enter_any_product_name_into_search_box_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
