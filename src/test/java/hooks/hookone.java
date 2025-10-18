package hooks;

import driverfactory.factorymain;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class hookone {

    WebDriver driver;
    @Before
    public void setup(){
        factorymain.initializeBrowser("chrome");
        driver = factorymain.getDriver();
        driver.manage().deleteAllCookies();
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
