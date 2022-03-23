import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class winwinLoginForm {
    public static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    // URL Environment
    String loginPage = "https://www.winwin.rs/customer/account/login/";

    // Locators
    String emailField = "email";
    String passwordField = "pass";
    String submitButton = "send2";
    String emailErrorMessage = "advice-required-entry-email";
    String passwordErrorMessage = "advice-required-entry-pass";
    String wrongCredentialsMessage = "li.error-msg";


    // Test data
    String nonExistingUserValidEmail = "marko@markoghh.com";
    String existingUserValidEmail = "jedemtimalter@gmail.com";
    String nonExistingUserValidPassword = "fdfdhj656jkjkj";
    String existingUserValidPassword = "dsds65gfgggf";

    @Before
    public void openLoginForm() {
        driver.get(loginPage);
        System.out.println("01. I will open the login page");
        driver.manage().window().maximize();
    }

    // 05. TC Verify that user cannot login if he submits empty email field
    @Test
    public void Test05() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));
        System.out.println("02. I will leave the email field empty");
        System.out.println("03. I will enter password in valid format");
        driver.findElement(By.id(passwordField)).sendKeys(existingUserValidPassword);
        System.out.println("04. I will click on login button");
        driver.findElement(By.id(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailErrorMessage)));

        if(driver.findElement(By.id(emailErrorMessage)) != null) {

            String actualResult = driver.findElement(By.id(emailErrorMessage)).getText();
            String expectedResult = "Ovo je obavezno polje.";

            Assert.assertEquals(expectedResult,actualResult);
            System.out.println("Verify that user cannot login if he submits empty email field and proper error message for email is displayed");

        }else {

            throw new Exception("ERROR - We can login with empty email field submitted");

        }

    }

    // 06. TC Verify that user cannot login if he submits empty password field
    @Test
    public void Test06() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));
        System.out.println("02. I will enter existing email in valid format");
        driver.findElement(By.id(emailField)).sendKeys(existingUserValidEmail);
        System.out.println("03. I will leave the password field empty");
        System.out.println("04. I will click on login button");
        driver.findElement(By.id(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passwordErrorMessage)));

        if(driver.findElement(By.id(passwordErrorMessage)) != null) {

            String actualResult = driver.findElement(By.id(passwordErrorMessage)).getText();
            String expectedResult = "Ovo je obavezno polje.";

            Assert.assertEquals(expectedResult,actualResult);
            System.out.println("Verify that user cannot login if he submits empty password field and proper error message for password is displayed");

        }else {

            throw new Exception("ERROR - We can login with empty password field and there's no proper error message.");

        }

    }

    // 10. TC Verify that user cannot login with wrong password
    @Test
    public void Test10() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(submitButton)));
        System.out.println("02. I will enter email of existing user");
        driver.findElement(By.id(emailField)).sendKeys(existingUserValidEmail);
        System.out.println("03. I will enter wrong password");
        driver.findElement(By.id(passwordField)).sendKeys(nonExistingUserValidPassword);
        System.out.println("04. I will click on login button");
        driver.findElement(By.id(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wrongCredentialsMessage)));

        if(driver.findElement(By.cssSelector(wrongCredentialsMessage)) != null) {

            String actualResult = driver.findElement(By.cssSelector(wrongCredentialsMessage)).getText();
            String expectedResult = "Neispravna CAPTCHA.";

            Assert.assertEquals(expectedResult,actualResult);
            System.out.println("Verify that user cannot login if he submits wrong password and proper error message is displayed.");

        }else {

            throw new Exception("ERROR - We can login with wrong password and there's no proper error message displayed.");

        }

        driver.close();

    }
}
