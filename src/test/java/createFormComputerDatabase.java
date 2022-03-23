import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createFormComputerDatabase {
    public static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    // URL Environment
    String computerDatabaseEnv = "http://computer-database.herokuapp.com/computers";

    // Locators
    String addNewComputerButton = "add";
    String computerNameField = "name";
    String introducedDateField = "introduced";
    String createNewButton = "input.btn.primary";
    String successfulMessageForCreateNewComputer = "div.alert-message.warning";

    // Test data
    String computerNameTestData = "TestAutom2";

    @Before
    public void openCreateForm() {
        driver.get(computerDatabaseEnv);
        System.out.println("01. I will open computer database.");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(addNewComputerButton)));
        System.out.println("02. I will click on add new computer button.");
        driver.findElement(By.id(addNewComputerButton)).click();
    }

    // 01. TC Verify that we can add new computer
    @Test
    public void Test01() throws Exception {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(computerNameField)));
            driver.findElement(By.id(computerNameField)).sendKeys(computerNameTestData);
            driver.findElement(By.cssSelector(createNewButton)).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(successfulMessageForCreateNewComputer)));
            String actualResult = driver.findElement(By.cssSelector(successfulMessageForCreateNewComputer)).getText();
            String expectedResult = "Done! Computer " + computerNameTestData + " has been created";
            Assert.assertEquals(expectedResult, actualResult);
        } catch(TimeoutException ex) {
            throw new Exception("ERROR: We cannot add new computer.");
        }
    }
    // 02. TC Verify that we cannot add new computer if we submit all empty fields
    @Test
    public void Test02() throws Exception {
        driver.findElement(By.cssSelector(createNewButton)).click();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(computerNameField)));
            driver.findElement(By.cssSelector(createNewButton)).click();
            wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(successfulMessageForCreateNewComputer))));
        }
        catch(TimeoutException ex) {
            throw new Exception("ERROR: We can add computer if we submit all empty fields.");
        }
        }
    // 03. TC Verify that we cannot add new computer if we submit wrong format introduced date
    @Test
    public void Test03() throws Exception {
        driver.findElement(By.cssSelector(createNewButton)).click();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(computerNameField)));
            driver.findElement(By.id(computerNameField)).sendKeys(computerNameTestData);
            driver.findElement(By.id(introducedDateField)).sendKeys("2021239400");
            driver.findElement(By.cssSelector(createNewButton)).click();
            wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(successfulMessageForCreateNewComputer))));
        }
        catch(TimeoutException ex) {
            throw new Exception("ERROR: We can add computer if we submit introduced date in wrong format.");
        }
    }
}
