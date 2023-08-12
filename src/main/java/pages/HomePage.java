package pages;

import drivers.DriverSingleton;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    public HomePage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#menu-item-1311 > a")
    private WebElement signInButton;

    @FindBy(id = "menu-item-312")
    private WebElement shopButton;

    @FindBy(id = "menu-item-1314")
    private WebElement username;

    @FindBy(css = "#menu-item-1313 > a")
    private WebElement signOutButton;

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void clickShopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        shopButton.click();
    }

    public String getUserName() {
        return username.getText();
    }

    public void clickLogOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
        signOutButton.click();
    }

}
