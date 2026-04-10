package Appium;
 
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
 
public class Activity2 {
    // Driver Declaration
    AndroidDriver driver;
 
    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
 
        // Set the Appium server URL
        URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
 
        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
 
        // Open the page in Chrome
        driver.get("https://training-support.net");
    }
 
    // Test method
    @Test
    public void chromeTest() {
        // Find heading on the page
        String pageHeading = driver.findElement(AppiumBy.xpath(
                "//android.widget.EditText[@resource-id=\"com.android.chrome:id/url_bar\"]"
        )).getText();
 
        // Print to console
        System.out.println("Heading: - Activity2.java:48" + pageHeading);
 
        // Find and click the About Us link
        driver.findElement(AppiumBy.className("android.widget.TextView")).click();
 
        // Find heading of new page and print to console
        String aboutPageHeading = driver.findElement(AppiumBy.className(
                "android.widget.EditText"
        )).getText();
        System.out.println(aboutPageHeading);
    }
 
 
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
 