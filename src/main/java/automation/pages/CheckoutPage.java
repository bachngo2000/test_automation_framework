package automation.pages;

import automation.drivers.DriverSingleton;
import automation.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(id = "billing_address_1")
    private WebElement address;

    @FindBy(id = "billing_postcode")
    private WebElement zipcode;

    @FindBy(id = "billing_city")
    private WebElement townName;

    @FindBy(id = "billing_state")
    private WebElement state;

    @FindBy(id = "billing_country")
    private WebElement country;

    @FindBy(css = "#order_review > table > tfoot > tr.order-total > td > strong > span > bdi")
    private WebElement totalAmount;

    @FindBy(id = "place_order")
    private WebElement placeOrder;

    @FindBy(css = "#post-207 > header > h1")
    private WebElement orderStatus;

    @FindBy(css = "#post-207 > content > div > div.woocommerce > div > p")
    private WebElement orderMessage;

    @FindBy(id = "billing_email")
    private WebElement email;


    public void provideBillingDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(address));
        Select selectCountry = new Select(country);
        selectCountry.selectByVisibleText("United States (US)");
        if (address.getAttribute("value").isEmpty()) {
            address.sendKeys("1232 Bright Sun Way");
            townName.sendKeys("Oklahoma City");
            Select selectState = new Select(state);
            selectState.selectByVisibleText("Oklahoma");
            zipcode.sendKeys("73049");
        }
    }

    public String getTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(totalAmount));
        return totalAmount.getText();
    }

    public void placeOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        placeOrder.click();
    }

    public String getOrderStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(orderMessage));
        return orderStatus.getText();
    }

    public void providePersonalInfo() {
        if (firstName.getAttribute("value").isEmpty()) {

            firstName.sendKeys("Bach");

            lastName.sendKeys("Ngo");

            email.sendKeys("bach21@outlook.com");
        }
    }

}
