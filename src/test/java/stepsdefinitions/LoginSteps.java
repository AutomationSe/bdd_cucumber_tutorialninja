package stepsdefinitions;

import driverfactory.factorymain;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;

    @Given("User navigates to login page")
    public void user_navigates_to_login_page() {
        driver = factorymain.getDriver();
        WebElement myAccount = driver.findElement(By.xpath("(//span[normalize-space()='My Account'])[1]"));
        myAccount.click();
        driver.findElement(By.linkText("Login")).click();
    }

    // Enter valid email
    @When("User enters valid email address {string} into email field")
    public void user_enters_valid_email_address_into_email_field(String emailText) {
        driver.findElement(By.id("input-email")).sendKeys(emailText);
    }

    // Enter invalid email
    @When("User enters invalid email address {string} into email field")
    public void user_enters_invalid_email_address_into_email_field(String emailText) {
        driver.findElement(By.id("input-email")).sendKeys(emailText);
    }

    // Enter valid password
    @And("User enters valid password {string} into password field")
    public void user_enters_valid_password_into_password_field(String passwordText) {
        driver.findElement(By.id("input-password")).sendKeys(passwordText);
    }

    // Enter invalid password
    @When("User enters invalid password {string} into password field")
    public void user_enters_invalid_password_into_password_field(String passwordText) {
        driver.findElement(By.id("input-password")).sendKeys(passwordText);
    }

    // Do not enter email
    @When("User dont enter email address into email field")
    public void user_dont_enter_email_address_into_email_field() {
        driver.findElement(By.id("input-email")).sendKeys("");
    }

    // Do not enter password
    @When("User dont enter password into password field")
    public void user_dont_enter_password_into_password_field() {
        driver.findElement(By.id("input-password")).sendKeys("");
    }

    // Click login button
    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.xpath("(//input[@value='Login'])[1]")).click();
    }

    // Successful login check
    @Then("User should get successfully logged in")
    public void user_should_get_successfully_logged_in() {
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
    }

    // Warning message check for invalid credentials or empty fields
    @Then("User should get a proper warning message about credentials mismatch")
    public void user_should_get_a_proper_warning_message_about_credentials_mismatch() {
        String alertText = driver.findElement(By.xpath("(//div[@class='alert alert-danger alert-dismissible'])[1]")).getText();
        Assert.assertTrue(alertText.contains("Warning: No match for E-Mail Address and/or Password."));
    }
}
