package steps;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Fixtures extends BaseClass {
    @BeforeAll
    public static void setUp() {
        // Initialize Firefox Driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @AfterAll
    public static void tearDown() {
        // Close the browser
        driver.quit();
    }
}