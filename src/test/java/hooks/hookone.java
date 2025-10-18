package hooks;

import driverfactory.factorymain;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class hookone {

    private WebDriver driver;

    @Before(order = 0)
    public void setup() {
        // Initialize browser
        factorymain.initializeBrowser("chrome");
        driver = factorymain.getDriver();

        // Configure browser settings
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot if scenario fails
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }
}