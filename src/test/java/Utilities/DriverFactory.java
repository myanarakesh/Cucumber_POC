package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver get_driver_instance() {

            if (Utilities.Utility.getProperty("browser").equalsIgnoreCase("chrome")) {
                System.out.println("Creating driver");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                driver = new ChromeDriver(options);
            } else if (Utilities.Utility.getProperty("browser").equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/Drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        return driver;
    }
}
