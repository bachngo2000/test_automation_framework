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

        homePage.clickSignIn();
        signInPage.logIn("bach", "123456");

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

        homePage.clickLogOut();

        DriverSingleton.closeObjectInstance();
    }
}