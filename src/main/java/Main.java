import org.openqa.selenium.WebDriver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DriverSingleton driverSingleton = DriverSingleton.getInstance();
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://bitheap.tech/");
    }
}