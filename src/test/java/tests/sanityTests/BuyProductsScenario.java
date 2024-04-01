package tests.sanityTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BuyProductsScenario {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
            System.out.println("The Url is correct: " + currentUrl);
        }else{
            System.out.println("The Url is not correct");
        }
        WebElement pageTitle = driver.findElement(By.className("title"));
        String expectedTitle = "Products";
        if (pageTitle.getText().equals(expectedTitle)) {
            System.out.println("Page title is correct: " + pageTitle.getText());
        }else{
            System.out.println("Page title is not correct");
        }
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket")).click();

        String cartItemNumber = driver.findElement(By.cssSelector("#shopping_cart_container > a")).getText();
        int actualItemNumber = 0;
        if (!cartItemNumber.isEmpty()) {
            actualItemNumber = Integer.parseInt(cartItemNumber);
        }
        if (actualItemNumber == 2) {
            System.out.println("Cart item number is correct");
        }else{
            System.out.println("Cart item number is not correct. Expected: 2 but found " + actualItemNumber);
        }
        driver.findElement(By.cssSelector("#shopping_cart_container > a")).click();

        String currentURLCart = driver.getCurrentUrl();
        if ("https://www.saucedemo.com/cart.html".equals(currentURLCart)) {
            System.out.println("URL is correct.");
        }else{
            System.out.println("URL does not match. Expected 'https://www.saucedemo.com/cart.html' but got '" + currentURLCart + "'");
        }

        WebElement cartTitleElement = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
        String actualCartText = cartTitleElement.getText();
        if ("Your Cart".equals(actualCartText)) {
            System.out.println("Your Cart title text is correct.");
        }else{
            System.out.println("Your Cart title text is not correct. Expected: 'Your Cart' but got '" + actualCartText + "'");
        }
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));
        int expectedNumberOfItems = 2;
        if (cartItems.size() == expectedNumberOfItems) {
            System.out.println("Number of items in cart is correct.");
        }else{
            System.out.println("Number of items in cart is not correct. Expected: " + expectedNumberOfItems + ", but got: " + cartItems.size());
        }
        driver.findElement(By.cssSelector("#checkout")).click();

        String currentURLCheckout = driver.getCurrentUrl();
        if ("https://www.saucedemo.com/checkout-step-one.html".equals(currentURLCheckout)) {
            System.out.println("URL is correct.");
        }else{
            System.out.println("URL is not correct. Expected: 'https://www.saucedemo.com/checkout-step-one.html', got '" + currentURLCheckout + "'");
        }

        WebElement checkoutTitle = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
        String actualCheckoutText = checkoutTitle.getText();
        if ("Checkout: Your Information".equals(actualCheckoutText)) {
            System.out.println("Checkout page title text is correct.");
        }else{
            System.out.println("Checkout: Your Information title text is not correct. Expected: 'Checkout: Your Information', got '" + actualCheckoutText + "'");
        }
        driver.findElement(By.cssSelector("#first-name")).sendKeys("Anna");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("Segal");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("543285");
        driver.findElement(By.cssSelector("#continue")).click();

        String currentURLCheckout2 = driver.getCurrentUrl();
        if ("https://www.saucedemo.com/checkout-step-two.html".equals(currentURLCheckout2)) {
            System.out.println("URL for checkout step two is correct.");
        }else{
            System.out.println("URL for checkout step two is not correct. Expected: 'https://www.saucedemo.com/checkout-step-two.html', got '" + currentURLCheckout2 + "'");
        }
        WebElement CheckoutOverviewTitle = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
        String actualCheckoutOverviewText = CheckoutOverviewTitle.getText();
        if ("Checkout: Overview".equals(actualCheckoutOverviewText)) {
            System.out.println("Checkout overview title text is correct.");
        }else{
            System.out.println("Checkout overview title text is not correct. Expected: 'Checkout: Overview', got '" + actualCheckoutOverviewText + "'");
        }
        driver.findElement(By.cssSelector("#finish")).click();

        String currentURLComplete = driver.getCurrentUrl();
        if ("https://www.saucedemo.com/checkout-complete.html".equals(currentURLComplete)) {
            System.out.println("URL for checkout complete is correct.");
        }else{
            System.out.println("URL for checkout complete is not correct. Expected: 'https://www.saucedemo.com/checkout-complete.html', got '" + currentURLComplete + "'");
        }

        WebElement CheckoutCompleteTitle = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span"));
        String actualCheckoutCompleteText = CheckoutCompleteTitle.getText();
        if ("Checkout: Complete!".equals(actualCheckoutCompleteText)) {
            System.out.println("Checkout complete title text is correct.");
        }else{
            System.out.println("Checkout complete title text is not correct. Expected: 'Checkout: Complete!', got '" + actualCheckoutCompleteText + "'");
        }

        WebElement orderTitle = driver.findElement(By.cssSelector("#checkout_complete_container > h2"));
        String yourOrderText = orderTitle.getText();
        if ("Thank you for your order!".equals(yourOrderText)) {
            System.out.println("Thank you for your order title text is correct.");
        }else{
            System.out.println("Thank you for your order title text is not correct. Expected: 'Thank you for your order!', got '" + yourOrderText + "'");
        }

        WebElement dispatchedTitle = driver.findElement(By.cssSelector("#checkout_complete_container > div"));
        String orderDispatchedText = dispatchedTitle.getText();
        String expectedDispatchedText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        if (expectedDispatchedText.equals(orderDispatchedText)) {
            System.out.println("Dispatched title text is correct.");
        }else{
            System.out.println("Dispatched title text is not correct. Expected: '" + expectedDispatchedText + "', got '" + orderDispatchedText + "'");
        }
    }
}