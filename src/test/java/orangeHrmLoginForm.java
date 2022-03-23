import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orangeHrmLoginForm {
    public static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    // URL Environment
    String loginPage = "https://opensource-demo.orangehrmlive.com/";

    // Locators
    String usernameField = "txtUsername";
    String passwordField = "txtPassword";
    String loginButton = "btnLogin";
    String errorMessage = "spanMessage";
    String welcomeMessage = "welcome";

    // Test data
    String nonExistingUsername = "dffddffd";
    String existingUsername = "Admin";
    String existingPassword = "admin123";


    @Before
    public void openLoginForm() {
        driver.get(loginPage);
        System.out.println("01. I will open the login form");
        driver.manage().window().maximize();
    }

    // 04. TC Verify that user cannot access with submitting all fields empty
    @Test
    public void Test04() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(loginButton)));
        System.out.println("02. I will leave username field empty");
        System.out.println("03. I will leave password field empty");
        System.out.println("04. I will click on login button");
        driver.findElement(By.id(loginButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(errorMessage)));

        if(driver.findElement(By.id(errorMessage)) != null) {

            String actualResult = driver.findElement(By.id(errorMessage)).getText();
            String expectedResult = "Username cannot be empty";

            Assert.assertEquals(expectedResult,actualResult);
            System.out.println("Verify that user cannot access with submitting all fields empty and username error message is shown");

        }else {

            throw new Exception("ERROR - Username error message is not displayed");

        }

    }

    // 06. TC Verify that user cannot access if he submits empty password field
    @Test
    public void Test06() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(loginButton)));
        System.out.println("02. I will enter username");
        driver.findElement(By.id(usernameField)).sendKeys(nonExistingUsername);
        System.out.println("03. I will leave password field empty");
        System.out.println("04. I will click on login button");
        driver.findElement(By.id(loginButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(errorMessage)));

        if(driver.findElement(By.id(errorMessage)) != null) {

            String actualResult = driver.findElement(By.id(errorMessage)).getText();
            String expectedResult = "Password cannot be empty";

            Assert.assertEquals(expectedResult,actualResult);
            System.out.println("Verify that user cannot access if he submits empty password field and password error message is shown");

        }else {

            throw new Exception("ERROR - Password error message is not displayed");

        }

    }

    // 10. TC Verify that user can log in
    @Test
    public void Test10() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(loginButton)));
        System.out.println("02. I will enter valid username");
        driver.findElement(By.id(usernameField)).sendKeys(existingUsername);
        System.out.println("03. I will enter valid password");
        driver.findElement(By.id(passwordField)).sendKeys(existingPassword);
        System.out.println("04. I will click on login button");
        driver.findElement(By.id(loginButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(welcomeMessage)));

        if(driver.findElement(By.id(welcomeMessage)) != null) {

            System.out.println("Verify that user can log in with valid credentials");

        }else {

            throw new Exception("ERROR - User cannot login with valid credentials");

        }

        driver.close();

    }
}
