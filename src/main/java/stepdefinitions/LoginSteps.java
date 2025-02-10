package stepdefinitions;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import listeners.TestListener;
import pages.LoginPage;
import runner.TestRunner;

import utils.DataUtils;


public class LoginSteps extends BaseSteps {
	
	//private static final Logger logg = LogManager.getLogger("LoginSteps");
	 static WebDriver driver;
	 static LoginPage lp; 
    static ExtentTest test;
    

	@Before
	public static void before_or_after_all() throws MalformedURLException {
		System.out.println("getting the driver");
		BaseSteps.setDriver("chrome", false);
		driver=BaseSteps.getDriver();
		System.out.println(driver.getCurrentUrl());
		lp = new LoginPage(driver);
	}
  
		
	@After
	public static void tearDownConfigReport() {
		if(driver!=null) {
			driver.quit();
		}
		
	}
	
	@Before 
	public void beforeScenario(Scenario scenario) { 
		
		test = TestRunner.extent.createTest(scenario.getName()); 
		} 
	
	
	
    
	@Given("user launched login Page")
	public static void user_launched_login_page() throws IOException {
		
		driver.get(DataUtils.readLoginTestData("app.url"));
	    driver.manage().window().maximize();
	    test.log(Status.INFO, "User Launched Login Page");

	}

	@Then("verify applicationPageOpened")
	public void verify_application_page_opened() {
		Assert.assertTrue("Application Page Should be Displayed",lp.verifyApplicationPageIsDisplayed(driver));
		logger.info("PASS: SFDC Application login page is opened");
	}

	@When("user entered valid username")
	public void user_entered_valid_username() throws IOException {
		lp.username.sendKeys(DataUtils.readLoginTestData("valid.username"));
	}
	
	@Then("verify userenteredUsername")
	public void verifyUserEnteredUsername() throws IOException {
		Assert.assertTrue("Username should be displayed in User name field",lp.verifyUserNameIsDisplayedInUserNameField(driver));
		logger.info("PASS: Username is Displayed in Username Field");
		test.log(Status.PASS, "Username Displayed in USername Field");
}


	@When("the password field is empty")
	public void the_password_field_is_empty() throws IOException {
		lp.enterPassword(DataUtils.readLoginTestData("empty.password"));
	}
	
	@Then ("verifyPasswordFieldisEmpty")
	public void verifyPasswordFieldisEmpty() throws IOException {
		Assert.assertTrue(lp.verifyEmptyPasswordFieldDisplayed(driver));
	    logger.info("PASS: Password field is Empty ");
	    test.log(Status.PASS,"Password Field is Empty");
}


	@When("user clicks login button")
	public void user_clicks_login_button() {
		lp.clickLoginButton(driver);
	    test.log(Status.INFO,"Login Button is Clickable and Clicked");

	}

	@Then("password error message Is Displayed")
	public void password_error_message_is_displayed() {
		  Assert.assertTrue("Error message should be displayed as Expected",lp.verfiyPasswordErrorMessageDisplayed(driver,"Please enter your password."));
		  test.log(Status.PASS,"Password Error Message Displayed ");

	}
	
	
	

}
