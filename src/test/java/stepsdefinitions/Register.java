package stepsdefinitions;

import driverfactory.factorymain;
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

    @Given("User navigates to Regesiter Account page")
    public void user_navigates_to_regesiter_account_page() {

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
    @When("User Selects privcay Policy")
    public void user_selects_privcay_policy() {
        driver.findElement(By.name("agree")).click();
    }
    @When("User Clicks on Continue button")
    public void user_clicks_on_continue_button() {
        driver.findElement(By.xpath("(//input[@value='Continue'])[1]")).click();
    }
    @Then("User account should get created sucessfully")
    public void user_account_should_get_created_sucessfully() {
        Assert.assertEquals("Your Account Has Been Created!", driver);
    }

    @When("User selescts Yes for Newsletter")
    public void user_selects_yes_for_newsletter(){
        driver.findElement(By.xpath("(//input[@name='newsletter'])[1]")).click();
    }



}
