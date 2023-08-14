package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;

public class ShopPage {

    private WebDriver driver;

    public ShopPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#main > div.shop-top-info-wrapper > form > select")
    private WebElement sortingOptions;

    @FindBy(css = "#main > ul > li.product.type-product.post-211.status-publish.first.instock.product_cat-uncategorized" +
            ".purchasable.product-type-simple > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")
    private WebElement addToCartButton;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement numberOfProducts;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private WebElement cartButton;

    @FindBy(css = "#main > nav > ul > li:nth-child(2) > a")
    private WebElement secondPage;


    public void sortByPopularity() {
        Select selectSorting = new Select(sortingOptions);
        selectSorting.selectByVisibleText("Sort by popularity");
    }

    public void addProductToCart() {
//        addToCartButton.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        if (numberOfProducts.getText().contains(Constants.CART_QUANTITY)) {
//            System.out.println("Cart has been updated");
//        }
//        else {
//            System.out.println("Cart has not been updated");
//            System.out.println(numberOfProducts.getText());
//        }
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addToCartButton);

        for (int i = 0; i < 1000; i++) {
            if (numberOfProducts.getText().contains(Constants.CART_QUANTITY)) {
                System.out.println("Cart has been updated");
                return;
            }
            else {
                System.out.println("Cart has not been updated");
            }
        }
    }

    public void proceedToCheckOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        cartButton.click();
    }

    public String getNumberOfProducts() {
        return numberOfProducts.getText();
    }

    public void goToSecondPage() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", secondPage);

    }


}
