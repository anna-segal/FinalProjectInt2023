package tests.loginTests.negativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidateError {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        validateLogin(driver, "locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out.");
        validateLogin(driver, "standard_user", "incorrect_password", "Epic sadface: Username and password do not match any user in this service");
        validateLogin(driver, "incorrect_username", "secret_sauce", "Epic sadface: Username and password do not match any user in this service");
        validateLogin(driver, "incorrect_username", "incorrect_password", "Epic sadface: Username and password do not match any user in this service");
        validateLogin(driver, "", "secret_sauce", "Epic sadface: Username is required");
        validateLogin(driver, "standard_user", "", "Epic sadface: Password is required");
        validateLogin(driver, "", "", "Epic sadface: Username is required");
        driver.quit();
    }
    public static void validateLogin(WebDriver driver, String username, String password, String expectedErrorMessage) {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();

        WebElement errorText = driver.findElement(By.cssSelector(".error-message-container.error"));
        String actualErrorMessage = errorText.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test passed for: " + username);
        }else{
            System.out.println("Test failed for: " + username + ". Expected: '" + expectedErrorMessage + "' but got: '" + actualErrorMessage + "'");
        }
    }
}