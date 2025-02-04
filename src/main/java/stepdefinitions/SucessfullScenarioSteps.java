package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import runner.TestRunner;
import utils.DataUtils;

public class SucessfullScenarioSteps extends BaseSteps {
	static WebDriver driver;
	 static LoginPage lp; 
   static ExtentTest test;
   
   @BeforeAll
   public static void getDriverFromBaseSteps() {
	   driver=getDriver();
	   lp = new LoginPage(driver);
	   System.out.println("chrome driver initialized from successfull login steps");
		
	}
	

//	@AfterAll
//	public static void tearDownConfigReport() {
//		if(driver!=null) {
//			driver.close();
//			System.out.println("closing the driver from successfull login steps");
//		}
//		
//	}
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
