package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class challenge {

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
       

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.amazon.in");

        // Wait for search box
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

        searchBox.sendKeys("iphone 17 pro");
        searchBox.submit();

        // Wait for products to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-component-type='s-search-result']")));

        // Get product titles
        List<WebElement> products = driver.findElements(
                By.xpath("//div[@data-component-type='s-search-result']//h2"));

        // Get product prices
        List<WebElement> prices = driver.findElements(
                By.xpath("//span[@class='a-price-whole']"));

        if (products.size() >= 3 && prices.size() >= 3) {

            WebElement thirdProduct = products.get(2);

            String productName = thirdProduct.getText();
            String productPrice = prices.get(2).getText();

            System.out.println("Product Name: " + productName);
            System.out.println("Product Price: ₹" + productPrice);

            String mainWindow = driver.getWindowHandle();

            // Click product
            thirdProduct.click();

            // Switch tab
            for (String tab : driver.getWindowHandles()) {
                if (!tab.equals(mainWindow)) {
                    driver.switchTo().window(tab);
                }
            }

            // Wait for delivery message
            WebElement delivery = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("deliveryBlockMessage")));

            System.out.println("Delivery Info: " + delivery.getText());

        } else {
            System.out.println("Products not loaded properly.");
        }

       // driver.quit();
    }
}