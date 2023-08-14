import drivers.DriverSingleton;
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

}
