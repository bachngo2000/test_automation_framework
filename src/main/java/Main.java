import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.FrameworkProperties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton = DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://bitheap.tech/");

        HomePage homePage = new HomePage();
        SignInPage signInPage = new SignInPage();
        ShopPage shopPage = new ShopPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        String username = frameworkProperties.getProperty("username");

        String password = frameworkProperties.getProperty("password");


        homePage.clickSignIn();
        signInPage.logIn(username, password);

        if (homePage.getUserName().equals("Hello, Bach")) {
            System.out.println("Test Passed");
            System.out.println(homePage.getUserName());
        }
        else {
            System.out.println("Test Failed");
            System.out.println(homePage.getUserName());
        }

        homePage.clickShopButton();
        shopPage.sortByPopularity();
        shopPage.addProductToCart();
        shopPage.proceedToCheckOut();
        cartPage.proceedToCheckOut();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();

        if (checkoutPage.getOrderStatus().equals("Order received")) {
            System.out.println("Order successfully placed");
        }
        else {
            System.out.println("Something's wrong with order placement!");
            System.out.println(checkoutPage.getOrderStatus());
        }

        homePage.clickLogOut();

        DriverSingleton.closeObjectInstance();
    }
}