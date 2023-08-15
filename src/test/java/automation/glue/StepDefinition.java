package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
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
    }

    @Given("^I go to the Website")
    public void i_go_to_the_Website() {
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
    }

    @When("^I click on Sign In button")
    public void i_click_on_sign_in_button() {
        homePage.clickSignIn();
    }


    @When("^I add one product to the cart")
    public void i_add_one_element_to_the_cart() {
        homePage.clickShopButton();
        shopPage.sortByPopularity();
        shopPage.addProductToCart();
    }

    @And("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_Login() {
        signInPage.logIn(configurationProperties.getEmail(), configurationProperties.getPassword());
    }

    @And("^I proceed to checkout")
    public void i_proceed_to_checkout(){
        shopPage.proceedToCheckOut();
        cartPage.proceedToCheckOut();
    }

    @And("^I confirm address, shipping, payment, and final order")
    public void i_confirm_address_shipping_payment_and_final_order(){
//        checkoutPage.providePersonalInfo();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
    }

    @Then("^I can log into the website")
    public void i_can_log_into_the_website() {
        System.out.println(configurationProperties.getUsername());
        assertEquals(configurationProperties.getUsername(), homePage.getUserName());
    }

    @Then("^The products are bought")
    public void the_products_are_bought(){
        assertEquals("Order received", checkoutPage.getOrderStatus());
    }



}
