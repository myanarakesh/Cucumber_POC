package Steps;

import Utilities.DriverFactory;
import Utilities.Utility;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class hooks {

    public static WebDriver driver;
    static Logger logger = Logger.getLogger(hooks.class.getName());

    public hooks(){
    }

    @Before
    public void setUp() {
        logger.info("Instantiating chrome driver");
        driver = DriverFactory.get_driver_instance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utility.getProperty("implicitTime")), TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        File file;
        if (scenario.isFailed()) {
            String screenshotPath = System.getProperty("user.dir") + "\\target\\screenshots\\"+scenario.getName()+"\\";
            file = new File(screenshotPath);
            file.mkdir();
            try {
                final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(screenshotPath+"screenshot.png"));

            } catch (final Exception e) {
                e.printStackTrace();
            }
            String failedScreenShot = screenshotPath+"screenshot.png";
            String url = "<img src="+failedScreenShot+" alt='failed screenshot'>";
            scenario.embed(url.getBytes(),"png", "Click Here To See Screenshot");
        }
    }

    @After
    public void shutDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
