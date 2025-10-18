package stepsdefinitions;

import driverfactory.factorymain;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I navigate to login page")
    public void i_navigate_to_login_page() {
        driver = factorymain.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'My Account')]")));
            myAccount.click();

            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(),'Login')]")));
            loginLink.click();

            // Verify we're on login page
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(),'Returning Customer')]")));

        } catch (Exception e) {
            System.out.println("Error navigating to login page: " + e.getMessage());
            throw e;
        }
    }

    @When("User has entered valid email address {string} into the email field")
    public void user_has_entered_valid_email_address_into_the_email_field(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @When("User has entered invalid email address {string} into the email field")
    public void user_has_entered_invalid_email_address_into_the_email_field(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @And("User has entered valid password {string} into password field")
    public void user_has_entered_valid_password_into_password_field(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @And("User has entered invalid password {string} into password field")
    public void user_has_entered_invalid_password_into_password_field(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @And("User clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Login']")));
        loginButton.click();
    }

    @Then("User should get successfully logged in")
    public void user_should_get_successfully_logged_in() {
        WebElement myAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'My Account')]")));
        Assert.assertTrue("My Account section should be visible after login",
                myAccount.isDisplayed());
    }

    @Then("User should get a proper warning message about credentials mismatch")
    public void user_should_get_a_proper_warning_message_about_credentials_mismatch() {
        WebElement alertBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-danger')]")));
        String alertText = alertBox.getText();
        Assert.assertTrue("Warning message should contain appropriate text",
                alertText.contains("Warning: No match for E-Mail Address and/or Password.") ||
                        alertText.contains("Warning"));
    }
}