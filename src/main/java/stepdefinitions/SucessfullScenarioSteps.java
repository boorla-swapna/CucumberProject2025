package stepdefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import runner.TestRunner;
import utils.DataUtils;

public class SucessfullScenarioSteps extends BaseSteps {
	static WebDriver driver;
	 static LoginPage lp; 
   static ExtentTest test;
  // static String browser;
   

   @Before
	public static void getDriverFromBaseSteps() throws MalformedURLException {
	   System.out.println("getting the driver from successful login");
		
		driver=BaseSteps.getDriver();
		System.out.println(driver.getCurrentUrl());
		lp = new LoginPage(driver);
	}

	@Before 
	public void beforeScenario(Scenario scenario) { 
		test = TestRunner.extent.createTest(scenario.getName()); 
		} 
	
	@When("user entered valid username,user entered valid password,user clicks the login button")
	public void user_entered_valid_username_user_entered_valid_password_user_clicks_the_login_button() throws IOException {
	   
		
		lp.enterUserName(driver, DataUtils.readLoginTestData("valid.username"))
	    .enterPassword(DataUtils.readLoginTestData("valid.password"))
	    .clickLoginButton(driver);
	    test.log(Status.INFO, "User Entered valid Username,password and clicked on login Button");
	}


	@Then("app home page should be displayed")
	public void app_home_page_should_be_displayed() {
	    lp.verifyHomePageIsDisplayed(driver, "Home Page ~ Salesforce - Developer Edition");
	    test.log(Status.INFO,"Home Page is Displayed");
	   
	}
}
