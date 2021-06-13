package Steps;

import Utilities.DataHelper;
import Utilities.DriverFactory;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class hooks {

    public static WebDriver driver;
    public List<HashMap<String, String>> datamap;
    static Logger logger = Logger.getLogger(SearchSteps.class.getName());
    DriverFactory obj = new DriverFactory();

    public hooks(){

    }

    @Before
    public void setUp() {
        datamap = DataHelper.data(System.getProperty("user.dir") + "src/test/resources/data.xlsx", "Sheet1");
        logger.info("Instantiating chrome driver");
        driver = DriverFactory.get_driver_instance();
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
    public void shutDown() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
