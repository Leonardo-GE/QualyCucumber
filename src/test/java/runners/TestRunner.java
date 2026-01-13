package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/java/features",   // Path to your .feature files
    glue = {"stepdefinitions"},            // Package where your step definitions live
    plugin = {"pretty", "html:target/cucumber-report.html"}, // Optional reports
    monochrome = true                       // Optional, makes console output cleaner
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
