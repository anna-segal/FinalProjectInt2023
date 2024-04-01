package tests.loginTests.positiveTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidateLogin {
    public static void main(String[] args) {
        validateUser("standard_user");
        validateUser("problem_user");
        validateUser("performance_glitch_user");
        validateUser("error_user");
        validateUser("visual_user");
    }
    public static void validateUser(String username) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        String currentURL = driver.getCurrentUrl();
        if ("https://www.saucedemo.com/inventory.html".equals(currentURL)) {
            System.out.println(username + " - URL correct");

            WebElement productsTitleElement = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
            String actualProductsText = productsTitleElement.getText();
            if ("Products".equals(actualProductsText)) {
                System.out.println(username + " - Products title text correct");
            } else {
                System.out.println(username + " - Products title text incorrect");
            }
        } else {
            System.out.println(username + " - URL incorrect");
        }
        driver.quit();
    }
}