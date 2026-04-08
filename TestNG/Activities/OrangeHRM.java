package Testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class OrangeHRM{

    WebDriver driver;
    String empID;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/orangehrm/");
    }

    @Test(priority = 1)
    public void verifyTitleAndImage() {
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        WebElement headerImage = driver.findElement(By.xpath("//img"));
        String imageURL = headerImage.getAttribute("src");
        System.out.println("Header Image URL: " + imageURL);
    }

    @Test(priority = 2)
    public void loginTest() throws InterruptedException {
        driver.findElement(By.id("txtUsername")).sendKeys("orange");
        driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
        driver.findElement(By.id("btnLogin")).click();

        Thread.sleep(3000);

        String dashboardHeading = driver.findElement(By.tagName("h1")).getText();
        if (dashboardHeading.equals("Dashboard")) {
            System.out.println("Login successful");
        }
    }

    @Test(priority = 3)
    public void addEmployee() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("btnAdd")).click();

        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Smith");

        empID = driver.findElement(By.id("employeeId")).getAttribute("value");

        driver.findElement(By.id("btnSave")).click();
        System.out.println("Employee ID: " + empID);
    }

    @Test(priority = 4)
    public void verifyEmployee() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("empsearch_id")).sendKeys(empID);
        driver.findElement(By.id("searchBtn")).click();

        String result = driver.findElement(By.xpath("//table/tbody/tr/td[2]")).getText();
        System.out.println("Employee Found: " + result);
    }

    @Test(priority = 5)
    public void editUserInfo() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("btnSave")).click();

        WebElement firstName = driver.findElement(By.id("personal_txtEmpFirstName"));
        firstName.clear();
        firstName.sendKeys("John");

        driver.findElement(By.id("personal_optGender_1")).click();

        Select nationality = new Select(driver.findElement(By.id("personal_cmbNation")));
        nationality.selectByVisibleText("Indian");

        WebElement dob = driver.findElement(By.id("personal_DOB"));
        dob.clear();
        dob.sendKeys("1995-05-10");

        driver.findElement(By.id("btnSave")).click();
        System.out.println("User info updated");
    }

    @Test(priority = 6)
    public void verifyDirectory() throws InterruptedException {
        WebElement directoryMenu = driver.findElement(By.id("menu_directory_viewDirectory"));

        if (directoryMenu.isDisplayed()) {
            directoryMenu.click();
        }

        Thread.sleep(2000);

        String heading = driver.findElement(By.tagName("h1")).getText();
        System.out.println("Directory Page: " + heading);
    }

    @Test(priority = 7)
    public void addQualification() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Qualifications")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("addWorkExperience")).click();

        driver.findElement(By.id("experience_employer")).sendKeys("ABC Company");
        driver.findElement(By.id("experience_jobtitle")).sendKeys("Automation Tester");

        driver.findElement(By.id("btnWorkExpSave")).click();
        System.out.println("Qualification added");
    }

    @Test(priority = 8)
    public void applyLeave() throws InterruptedException {
        driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("menu_leave_applyLeave")).click();

        Select leaveType = new Select(driver.findElement(By.id("applyleave_txtLeaveType")));
        leaveType.selectByIndex(1);

        driver.findElement(By.id("applyleave_txtFromDate")).sendKeys("2026-04-10");
        driver.findElement(By.id("applyleave_txtToDate")).sendKeys("2026-04-12");

        driver.findElement(By.id("applyBtn")).click();
        System.out.println("Leave applied");
    }

    @Test(priority = 9)
    public void emergencyContacts() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Emergency Contacts")).click();

        List<WebElement> contacts = driver.findElements(By.xpath("//table/tbody/tr"));

        for (WebElement contact : contacts) {
            System.out.println(contact.getText());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}