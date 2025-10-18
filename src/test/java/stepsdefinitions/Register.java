package stepsdefinitions;

import driverfactory.factorymain;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class Register {

    WebDriver driver;


    @Given("User navigates to Register Account page")
    public void user_navigates_to_register_account_page() {
        driver = factorymain.getDriver();
        WebElement myAccount = driver.findElement(By.xpath("(//span[normalize-space()='My Account'])[1]"));
        myAccount.click();
        driver.findElement(By.linkText("Register")).click();
    }

    @When("User enters the details into below fields")
    public void user_enters_the_details_into_below_fields(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> dataMap = dataTable.asMap(String.class, String.class);
        driver.findElement(By.id("input-firstname")).sendKeys(dataMap.get("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataMap.get("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(dataMap.get("email"));
        driver.findElement(By.id("input-telephone")).sendKeys(dataMap.get("telephone"));
        driver.findElement(By.id("input-password")).sendKeys(dataMap.get("password"));
        driver.findElement(By.id("input-confirm")).sendKeys(dataMap.get("password"));
    }

    @And("User selects Privacy Policy")
    public void user_selects_privacy_policy() {
        driver.findElement(By.name("agree")).click();
    }

    @And("User clicks on Continue button")
    public void user_clicks_on_continue_button() {
        driver.findElement(By.xpath("(//input[@value='Continue'])[1]")).click();
    }


    @Then("User account should get created successfully")
    public void user_account_should_get_created_successfully() {
        Assert.assertEquals("Your Account Has Been Created!", driver);
    }

    @And("User selects Yes for Newsletter")
    public void user_selects_yes_for_newsletter() {
        driver.findElement(By.xpath("(//input[@name='newsletter'])[1]")).click();
    }

    @Then("User should get a proper warning about duplicate email")
    public void user_should_get_a_proper_warning_about_duplicate_email() {
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='alert alert-danger alert-dismissible'])[1]")).getText().contains("Warning: E-Mail Address is already registered!"));
    }

    @When("User dont enter any details into fields")
    public void user_dont_enter_any_details_into_fields() {

    }

    @Then("User should get proper warning messages for every mandatory field")
    public void user_should_get_proper_warning_messages_for_every_mandatory_field() {
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='alert alert-danger alert-dismissible'])[1]")).getText().contains("Warning: You must agree to the Privacy Policy!\n"));
        Assert.assertTrue(driver.findElement(By.xpath("(//div[contains(text(),'First Name must be between 1 and 32 characters!')])[1]")).getText().contains("First Name must be between 1 and 32 characters!"));
        //Assert.assertTrue(driver.findElement(By.xpath("(//div[contains(text(),'Last Name must be between 1 and 32 characters!')])[1]")).getText().contains("Last Name must be between 1 and 32 characters!"));
        String lastNameWarning = driver.findElement(By.xpath("(//div[contains(text(),'Last Name must be between 1 and 32 characters!')])[1]")).getText();
        Assert.assertEquals(lastNameWarning, "Last Name must be between 1 and 32 characters!", "Last Name warning mismatch");
    }



}
