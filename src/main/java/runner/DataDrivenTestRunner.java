package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		plugin= {"pretty","html:target/cucumber-reports/report.html"},
		features="src\\main\\java\\features\\DataDriven.feature", glue={"stepdefinitions.datadriven"},
		monochrome=true)

public class DataDrivenTestRunner {

}
