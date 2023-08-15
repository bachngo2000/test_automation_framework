package automation.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJs implements DriverStrategy {
    @Override
    public WebDriver setStrategy() {
        System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        WebDriver driver = new PhantomJSDriver(desiredCapabilities);

        return driver;
    }
}
