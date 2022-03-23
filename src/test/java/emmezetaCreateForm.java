import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class emmezetaCreateForm {
    public static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    // URL Environment
    String registerPage = "https://www.emmezeta.rs/customer/account/create";

    // Locators
    String acceptCookiesButton = "CybotCookiebotDialogBodyButtonAccept";
    String emailAddressField = "email_address";
    String passwordField = "password";
    String passwordConfirmationField = "password-confirmation";
    String firstNameField = "firstname";
    String lastNameField = "lastname";
    String submitButton = "button.action.submit.primary.account-button";

    String emailErrorMessage = "email_address-error";
    String passwordErrorMessage = "password-error";
    String passwordConfirmationErrorMessage = "password-confirmation-error";
    String firstNameErrorMessage = "firstname-error";
    String lastNameErrorMessage = "lastname-error";
    String dashboardInfo = "div.block.block-dashboard-info";

    // Test data
    String email = "marko@markoghh.com";
    String shortPassword = "fdfd";
    String longPassword = "fdfd77kjkjkj";
    String firstname = "Marko";
    String lastname = "Petrovic";

    @Before
    public void openCreateForm() {
        driver.get(registerPage);
        System.out.println("01. I will open registration form.");
        driver.manage().window().maximize();
    }

    // 01. TC Verify that we cannot register if we submit all empty fields
    @Test
    public void Test01() throws Exception {
        // Cookie pop-up
        wait.until(ExpectedConditions.elementToBeClickable(By.id(acceptCookiesButton)));
        driver.findElement(By.id(acceptCookiesButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will click on create new user button");
        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailErrorMessage)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passwordErrorMessage)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passwordConfirmationErrorMessage)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(firstNameErrorMessage)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(lastNameErrorMessage)));

        if(driver.findElement(By.id(emailErrorMessage)) != null
                && driver.findElement(By.id(passwordErrorMessage)) != null
                && driver.findElement(By.id(passwordConfirmationErrorMessage)) != null
                && driver.findElement(By.id(firstNameErrorMessage)) != null
                && driver.findElement(By.id(lastNameErrorMessage)) != null) {

            String actualResult = driver.findElement(By.id(emailErrorMessage)).getText();
            String expectedResult = "Ovo je neophodno polje.";

            Assert.assertEquals(expectedResult,actualResult);
            System.out.println("Verify that error messages are present");

        }else {

            throw new Exception("ERROR - We can register with all fields left blank");

        }

    }

    // 02. TC Verify that we cannot register if we submit empty email field
    @Test
    public void Test02() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter password");
        driver.findElement(By.id(passwordField)).sendKeys(shortPassword);
        driver.findElement(By.id(passwordConfirmationField)).sendKeys(longPassword);
        System.out.println("03. I will enter text in confirm password");
        driver.findElement(By.id(firstNameField)).sendKeys(firstname);
        System.out.println("04. I will enter first name");
        driver.findElement(By.id(lastNameField)).sendKeys(lastname);
        System.out.println("05. I will enter last name");


        driver.findElement(By.cssSelector(submitButton)).click();
        System.out.println("06. I will click on create new user button");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailErrorMessage)));
        System.out.println("Verify that error messages is present on Email field");

        if(driver.findElement(By.id(emailErrorMessage)) != null ) {

            String actualResult = driver.findElement(By.id(emailErrorMessage)).getText();
            String expectedResult = "Ovo je neophodno polje.";

            Assert.assertEquals(expectedResult,actualResult);

        }else {

            throw new Exception("ERROR - We can register with blank email field");

        }

    }

    // 03. TC Verify that we cannot register if we submit empty password field
    @Test
    public void Test03() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter email");
        driver.findElement(By.id(emailAddressField)).sendKeys(email);
        driver.findElement(By.id(passwordConfirmationField)).sendKeys(longPassword);
        System.out.println("03. I will enter text in confirm password");
        driver.findElement(By.id(firstNameField)).sendKeys(firstname);
        System.out.println("04. I will enter first name");
        driver.findElement(By.id(lastNameField)).sendKeys(lastname);
        System.out.println("05. I will enter last name");


        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passwordErrorMessage)));
        System.out.println("Verify that error messages is present on Password field");

        if(driver.findElement(By.id(passwordErrorMessage)) != null ) {

            String actualResult = driver.findElement(By.id(passwordErrorMessage)).getText();
            String expectedResult = "Ovo je neophodno polje.";

            Assert.assertEquals(expectedResult,actualResult);

        }else {

            throw new Exception("ERROR - We can register with empty password field");

        }

    }

    // 04. TC Verify that we cannot register if we submit empty confirm password field
    @Test
    public void Test04() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter email");
        driver.findElement(By.id(emailAddressField)).sendKeys(email);
        System.out.println("03. I will enter password");
        driver.findElement(By.id(passwordField)).sendKeys(longPassword);
        System.out.println("04. I will enter first name");
        driver.findElement(By.id(firstNameField)).sendKeys(firstname);
        System.out.println("05. I will enter last name");
        driver.findElement(By.id(lastNameField)).sendKeys(lastname);


        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passwordConfirmationErrorMessage)));
        System.out.println("Verify that error messages is present on Confirm password field");

        if(driver.findElement(By.id(passwordConfirmationErrorMessage)) != null ) {

            String actualResult = driver.findElement(By.id(passwordConfirmationErrorMessage)).getText();
            String expectedResult = "Ovo je neophodno polje.";

            Assert.assertEquals(expectedResult,actualResult);

        }else {

            throw new Exception("ERROR - We can register with empty confirm password field");

        }

    }

    // 05. TC Verify that we cannot register if we submit empty first name field
    @Test
    public void Test05() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter email");
        driver.findElement(By.id(emailAddressField)).sendKeys(email);
        System.out.println("03. I will enter password");
        driver.findElement(By.id(passwordField)).sendKeys(longPassword);
        System.out.println("04. I will enter text in confirm password");
        driver.findElement(By.id(passwordConfirmationField)).sendKeys(longPassword);
        System.out.println("05. I will enter last name");
        driver.findElement(By.id(lastNameField)).sendKeys(lastname);


        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(firstNameErrorMessage)));
        System.out.println("Verify that error messages is present on First name field");

        if(driver.findElement(By.id(firstNameErrorMessage)) != null ) {

            String actualResult = driver.findElement(By.id(firstNameErrorMessage)).getText();
            String expectedResult = "Ovo je neophodno polje.";

            Assert.assertEquals(expectedResult,actualResult);

        }else {

            throw new Exception("ERROR - We can register with empty first name field");

        }

    }

    // 06. TC Verify that we cannot register if we submit empty last name field
    @Test
    public void Test06() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter email");
        driver.findElement(By.id(emailAddressField)).sendKeys(email);
        System.out.println("03. I will enter password");
        driver.findElement(By.id(passwordField)).sendKeys(longPassword);
        System.out.println("04. I will enter text in confirm password");
        driver.findElement(By.id(passwordConfirmationField)).sendKeys(longPassword);
        System.out.println("05. I will enter first name");
        driver.findElement(By.id(firstNameField)).sendKeys(firstname);


        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(lastNameErrorMessage)));
        System.out.println("Verify that error messages is present on Last name field");

        if(driver.findElement(By.id(lastNameErrorMessage)) != null ) {

            String actualResult = driver.findElement(By.id(lastNameErrorMessage)).getText();
            String expectedResult = "Ovo je neophodno polje.";

            Assert.assertEquals(expectedResult,actualResult);

        }else {

            throw new Exception("ERROR - We can register with empty last name field");

        }

    }

    // 07. TC Verify that we cannot register if we submit password shorter than eight characters
    @Test
    public void Test07() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter email");
        driver.findElement(By.id(emailAddressField)).sendKeys(email);
        System.out.println("03. I will enter password");
        driver.findElement(By.id(passwordField)).sendKeys(shortPassword);
        System.out.println("04. I will enter text in confirm password");
        driver.findElement(By.id(passwordConfirmationField)).sendKeys(shortPassword);
        System.out.println("05. I will enter first name");
        driver.findElement(By.id(firstNameField)).sendKeys(firstname);
        System.out.println("06. I will enter last name");
        driver.findElement(By.id(lastNameField)).sendKeys(lastname);


        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passwordErrorMessage)));
        System.out.println("Verify that error messages is present on Password field");

        if(driver.findElement(By.id(passwordErrorMessage)) != null ) {

            String actualResult = driver.findElement(By.id(passwordErrorMessage)).getText();
            String expectedResult = "Minimalna dužina ovog polja mora biti jednaka ili veća od 8 simbola. Prazni prostori biti će zanemareni.";

            Assert.assertEquals(expectedResult,actualResult);

        }else {

            throw new Exception("ERROR - We can register with entered password shorter than eight characters.");

        }

    }

    // 08. TC Verify that we can register if we submit all fields with valid data
    @Test
    public void Test08() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(submitButton)));
        System.out.println("02. I will enter email");
        driver.findElement(By.id(emailAddressField)).sendKeys(email);
        System.out.println("03. I will enter password");
        driver.findElement(By.id(passwordField)).sendKeys(longPassword);
        System.out.println("04. I will enter text in confirm password");
        driver.findElement(By.id(passwordConfirmationField)).sendKeys(longPassword);
        System.out.println("05. I will enter first name");
        driver.findElement(By.id(firstNameField)).sendKeys(firstname);
        System.out.println("06. I will enter last name");
        driver.findElement(By.id(lastNameField)).sendKeys(lastname);


        driver.findElement(By.cssSelector(submitButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(dashboardInfo)));
        System.out.println("Verify that I can register new user account");

        if(driver.findElement(By.cssSelector(dashboardInfo)) == null ) {

            throw new Exception("ERROR - We cannot register with all fields filled with valid data.");

        }

        driver.close();

    }

}
