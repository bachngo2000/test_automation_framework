package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {

    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;
    private ShopPage shopPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    ExtentTest test;
    static ExtentReports report = new ExtentReports("report/TestReport.html");
    private String testName = "test name";

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects() {
        DriverSingleton.getInstance(Constants.CHROME);
        homePage = new HomePage();
        signInPage = new SignInPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        TestCases[] tests = TestCases.values();
        test = report.startTest(tests[Utils.testCount].getTestName());
        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());
        testName = tests[Utils.testCount].getTestName();
        Utils.testCount++;
    }

    @Given("^I go to the Website")
    public void i_go_to_the_Website() {
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        Log.info("Navigating to " + Constants.URL);
        test.log(LogStatus.PASS, "Navigating to " + Constants.URL);
    }

    @When("^I click on Sign In button")
    public void i_click_on_sign_in_button() {
        homePage.clickSignIn();
        Log.info("Sign-In button has been clicked.");
        test.log(LogStatus.PASS, "Sign-In button has been clicked.");
    }


    @When("^I add one product to the cart")
    public void i_add_one_element_to_the_cart() {
        homePage.clickShopButton();
        shopPage.sortByPopularity();
        shopPage.addProductToCart();
        Log.info("A product has been added to the cart");
        test.log(LogStatus.PASS, "A product has been added to the cart");

    }

    @And("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_Login() {
        signInPage.logIn(configurationProperties.getEmail(), configurationProperties.getPassword());
        Log.info("Login has been clicked");
        test.log(LogStatus.PASS, "Login has been clicked");

    }

    @And("^I proceed to checkout")
    public void i_proceed_to_checkout(){
        shopPage.proceedToCheckOut();
        cartPage.proceedToCheckOut();
        Log.info("We proceeded to checkout");
        test.log(LogStatus.PASS, "We proceeded to checkout");

    }

    @And("^I confirm address, shipping, payment, and final order")
    public void i_confirm_address_shipping_payment_and_final_order(){
        checkoutPage.providePersonalInfo();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
        Log.info("We confirmed the final order");
        test.log(LogStatus.PASS, "We confirmed the final order");

    }

    @Then("^I can log into the website")
    public void i_can_log_into_the_website() {
//        System.out.println(configurationProperties.getUsername());
        if (configurationProperties.getUsername().equals(homePage.getUserName())) {
            Log.info("The authentication was successful!");
            test.log(LogStatus.PASS, "The authentication was successful!");
        }
        else {
            Log.info("The authentication has failed!");
            test.log(LogStatus.FAIL, "The authentication has failed!");
        }
        assertEquals(configurationProperties.getUsername(), homePage.getUserName());
    }

    @Then("^The products are bought")
    public void the_products_are_bought(){
        if (checkoutPage.getOrderStatus().equals("Order received")) {
            Log.info("We bought the product!");
            test.log(LogStatus.PASS, "We bought the product!");
        }
        else {
            Log.info("We did not buy the product!");
            test.log(LogStatus.FAIL, "We did not buy the product!");

        }
        assertEquals("Order received", checkoutPage.getOrderStatus());
    }

    @After
    public void closeObjects() {
        report.endTest(test);
        Log.endTest(testName);
        report.flush();
        DriverSingleton.closeObjectInstance();
        Log.info("Driver closed");
    }

}
