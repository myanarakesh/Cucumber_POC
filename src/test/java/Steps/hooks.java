package Steps;

import Utilities.DriverFactory;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class hooks {

    public static WebDriver driver;
    static Logger logger = Logger.getLogger(hooks.class.getName());

    public hooks(){
    }

    @Before
    public void setUp() {
        logger.info("Instantiating chrome driver@#@$@@@");
        driver = DriverFactory.get_driver_instance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = System.getProperty("user.dir") + "\\target\\screenshots\\"+scenario.getName()+"\\";
            File file = new File(screenshotPath);
            file.mkdir();
            try {
                final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(screenshotPath+"scr1.png"));
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void shutDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
