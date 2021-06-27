package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
//        tags = {"@temp"},
        glue = {"Steps"},
        plugin = ("json:target/cucumber-reports/CucumberTestReport.json"),
        monochrome = true
)
public class UnitRunnerTest {
}