import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;

public class Tests {

    // created static objects we need in our tests
    static FrameworkProperties frameworkProperties;

    static WebDriver driver;

    static HomePage homePage;

    static SignInPage signInPage;

    static CheckoutPage checkoutPage;

    static ShopPage shopPage;

    static CartPage cartPage;

    @BeforeClass
    public static void initializeObjects() {
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        shopPage = new ShopPage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        cartPage = new CartPage();
    }

    @Test
    public void testingAuthentication() {
        driver.get(Constants.URL);
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME), homePage.getUserName());

    }

    @Test
    public void testingAddingProductsToCart() {
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.sortByPopularity();
        shopPage.addProductToCart();
        assertEquals(Constants.CART_QUANTITY, shopPage.getNumberOfProducts());
    }

    @Test
    public void testingTheFullBuyingProcess() {
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.sortByPopularity();
        shopPage.addProductToCart();
        shopPage.proceedToCheckOut();
        cartPage.proceedToCheckOut();
        checkoutPage.providePersonalInfo();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
        assertEquals("Order received", checkoutPage.getOrderStatus());
    }

    @AfterClass
    public static void closeObjects() {
        driver.close();
    }

}
