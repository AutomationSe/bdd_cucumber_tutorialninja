package stepsdefinitions;

import driverfactory.factorymain;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class Register {

    WebDriver driver;
    WebDriverWait wait;

    @Given("User navigates to Register Account page")
    public void user_navigates_to_register_account_page() {
        driver = factorymain.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'My Account')]")));
            myAccount.click();

            WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(),'Register')]")));
            registerLink.click();

            // Verify we're on registration page
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(),'Register Account')]")));

        } catch (Exception e) {
            System.out.println("Error navigating to register page: " + e.getMessage());
            throw e;
        }
    }

    @When("User enters the details into below fields")
    public void user_enters_the_details_into_below_fields(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")))
                    .sendKeys(dataMap.get("firstName"));

            driver.findElement(By.id("input-lastname")).sendKeys(dataMap.get("lastName"));

            String email = dataMap.get("email");
            // If email contains a specific marker for unique, generate unique email
            if (email.contains("unique")) {
                email = "test" + System.currentTimeMillis() + "@gmail.com";
            }
            driver.findElement(By.id("input-email")).sendKeys(email);

            driver.findElement(By.id("input-telephone")).sendKeys(dataMap.get("telephone"));
            driver.findElement(By.id("input-password")).sendKeys(dataMap.get("password"));
            driver.findElement(By.id("input-confirm")).sendKeys(dataMap.get("password"));

        } catch (Exception e) {
            System.out.println("Error entering registration details: " + e.getMessage());
            throw e;
        }
    }

    @When("User enters the details into below fields with duplicate email")
    public void user_enters_the_details_into_below_fields_with_duplicate_email(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")))
                    .sendKeys(dataMap.get("firstName"));
            driver.findElement(By.id("input-lastname")).sendKeys(dataMap.get("lastName"));

            // Use the exact duplicate email provided
            driver.findElement(By.id("input-email")).sendKeys(dataMap.get("email"));

            driver.findElement(By.id("input-telephone")).sendKeys(dataMap.get("telephone"));
            driver.findElement(By.id("input-password")).sendKeys(dataMap.get("password"));
            driver.findElement(By.id("input-confirm")).sendKeys(dataMap.get("password"));

        } catch (Exception e) {
            System.out.println("Error entering duplicate email details: " + e.getMessage());
            throw e;
        }
    }

    @And("User selects Privacy Policy")
    public void user_selects_privacy_policy() {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@name='agree']")));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (Exception e) {
            System.out.println("Error selecting privacy policy: " + e.getMessage());
            throw e;
        }
    }

    @And("User clicks on Continue button")
    public void user_clicks_on_continue_button() {
        try {
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@value='Continue']")));
            continueButton.click();
        } catch (Exception e) {
            System.out.println("Error clicking continue button: " + e.getMessage());
            throw e;
        }
    }

    @Then("User account should get created successfully")
    public void user_account_should_get_created_successfully() {
        try {
            WebElement successHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")));
            Assert.assertEquals("Your Account Has Been Created!", successHeading.getText());

        } catch (Exception e) {
            System.out.println("Error verifying account creation: " + e.getMessage());
            throw e;
        }
    }

    @And("User selects Yes for Newsletter")
    public void user_selects_yes_for_newsletter() {
        try {
            WebElement yesRadio = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@name='newsletter'][@value='1']")));
            if (!yesRadio.isSelected()) {
                yesRadio.click();
            }
        } catch (Exception e) {
            System.out.println("Error selecting newsletter option: " + e.getMessage());
            throw e;
        }
    }

    @Then("User should get a proper warning about duplicate email")
    public void user_should_get_a_proper_warning_about_duplicate_email() {
        try {
            WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'alert-danger')]")));
            String warningText = warning.getText();
            Assert.assertTrue("Warning should contain duplicate email message",
                    warningText.contains("E-Mail Address is already registered!") ||
                            warningText.contains("already registered"));
        } catch (Exception e) {
            System.out.println("Error verifying duplicate email warning: " + e.getMessage());
            throw e;
        }
    }

    @When("User dont enter any details into fields")
    public void user_dont_enter_any_details_into_fields() {
        try {
            // Clear all fields to ensure no data is present
            driver.findElement(By.id("input-firstname")).clear();
            driver.findElement(By.id("input-lastname")).clear();
            driver.findElement(By.id("input-email")).clear();
            driver.findElement(By.id("input-telephone")).clear();
            driver.findElement(By.id("input-password")).clear();
            driver.findElement(By.id("input-confirm")).clear();

            // Ensure privacy policy is not selected
            WebElement privacyCheckbox = driver.findElement(By.name("agree"));
            if (privacyCheckbox.isSelected()) {
                privacyCheckbox.click();
            }
        } catch (Exception e) {
            System.out.println("Error clearing registration fields: " + e.getMessage());
            throw e;
        }
    }

    @Then("User should get proper warning messages for every mandatory field")
    public void user_should_get_proper_warning_messages_for_every_mandatory_field() {
        try {
            // Wait for main warning to appear
            WebElement mainWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'alert-danger')]")));
            Assert.assertTrue("Main warning should be displayed", mainWarning.isDisplayed());

            // Check for individual field warnings - using more flexible approach
            boolean foundFieldWarnings = false;

            // Check for field-level error messages (they might be near each field)
            try {
                // Look for any text-danger elements which typically show field validation errors
                java.util.List<WebElement> fieldErrors = driver.findElements(
                        By.xpath("//div[contains(@class,'text-danger')]"));
                if (fieldErrors.size() > 0) {
                    foundFieldWarnings = true;
                    System.out.println("Found " + fieldErrors.size() + " field validation errors");
                }
            } catch (Exception e) {
                System.out.println("No field-level validation errors found with text-danger class");
            }

            // Alternative: check if there are any visible error messages near input fields
            if (!foundFieldWarnings) {
                try {
                    java.util.List<WebElement> inputGroups = driver.findElements(
                            By.xpath("//div[contains(@class,'form-group')]"));
                    for (WebElement group : inputGroups) {
                        try {
                            WebElement error = group.findElement(By.xpath(".//div[contains(@class,'text-danger')]"));
                            if (error.isDisplayed()) {
                                foundFieldWarnings = true;
                                break;
                            }
                        } catch (Exception e) {
                            // Continue checking other groups
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Alternative field error check also failed");
                }
            }

            Assert.assertTrue("Should find field validation warnings", foundFieldWarnings);

        } catch (Exception e) {
            System.out.println("Error verifying mandatory field warnings: " + e.getMessage());
            throw e;
        }
    }
}