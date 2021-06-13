//package Runner;
//
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.junit.runner.RunWith;
//import org.testng.annotations.DataProvider;
//
//@CucumberOptions(
//        features = "src/test/resources/Features",
//        glue= {"Steps"},
////		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
//        monochrome=true
//)
//class RunnerTest extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
//}