package steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.*;

public class Activity5 extends BaseClass {

    @Given("Activity5 user is on the login page")
    public void openPage() {
        driver.get("https://training-support.net/webelements/login-form");
        Assertions.assertEquals("Selenium: Login Form", driver.getTitle());
    }

    @When("Activity5 user enters the invalid username and password")
    public void enterCredentials() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
    }

    @When("Activity5 user enters credentials {string} and {string}")
    public void enterCredentialsFromInputs(String username, String password) {

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.clear();
        passwordField.clear();

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @And("Activity5 user clicks on the submit button")
    public void clickSubmit() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("Activity5 gets the confirmation of the message and verifies it")
    public void confirmMessage() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h2.mt-5"), "Welcome"));
        String message = driver.findElement(By.cssSelector("h2.mt-5")).getText();
        Assertions.assertEquals("Welcome Back, Admin!", message);
    }

    @Then("Activity5 gets the confirmation text and verifies message as {string}")
    public void confirmMessageAsInput(String expectedMessage) {

        String message = "NOT FOUND";

        if (expectedMessage.contains("Invalid")) {
            message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2#subheading"))
            ).getText();
        } else {
            message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.mt-5"))
            ).getText();
        }

        Assertions.assertEquals(expectedMessage, message);
    }
}