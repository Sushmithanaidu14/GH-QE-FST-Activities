package Appium;

import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4 {

    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;

    // Setup Method
    @BeforeClass
    public void setUp() throws MalformedURLException {

        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.contacts");
        options.setAppActivity("com.android.contacts.activities.PeopleActivity");
        options.noReset();

        // Appium Server URL
        URL serverURL = new URL("http://127.0.0.1:4723");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);

        // Explicit Wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Test Case
    @Test
    public void contactsTest() {

        // Click "Create new contact"
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Create new contact"))).click();

        // Enter First Name
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.EditText[@text='First name']")))
                .sendKeys("Aaditya");

        // Enter Last Name
        driver.findElement(AppiumBy.xpath(
                "//android.widget.EditText[@text='Last name']"))
                .sendKeys("Varma");

        // Enter Phone Number
        driver.findElement(AppiumBy.xpath(
                "//android.widget.EditText[@text='Phone']"))
                .sendKeys("999148292");

        // Click Save Button
        driver.findElement(AppiumBy.id(
                "com.google.android.contacts:id/editor_menu_save_button"))
                .click();

        // Wait until contact page loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("com.google.android.contacts:id/large_title")));

        // Get Contact Name
        String contactName = driver.findElement(
                AppiumBy.id("com.google.android.contacts:id/large_title"))
                .getText();

        System.out.println("Saved Contact: " + contactName);

        // Assertion
        Assert.assertEquals(contactName, "Aaditya Varma");
    }

    // Tear Down
    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}