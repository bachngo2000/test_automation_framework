package automation.pages;

import automation.drivers.DriverSingleton;
import automation.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;

    public CartPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#post-206 > content > div > div.woocommerce > div.cart-collaterals > div > div > a")
    private WebElement proceedToCheckoutButton;

    @FindBy(id = "coupon_code")
    private WebElement couponCodeTextBox;

    @FindBy(css ="#post-206 > content > div > div.woocommerce > form > table > tbody > tr:nth-child(2) > td > div > button")
    private WebElement applyCouponButton;

    public void proceedToCheckOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));

        proceedToCheckoutButton.click();
    }

    public void applyCoupon() {
        couponCodeTextBox.sendKeys(Constants.COUPON_CODE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponButton));

        applyCouponButton.click();



    }


}
