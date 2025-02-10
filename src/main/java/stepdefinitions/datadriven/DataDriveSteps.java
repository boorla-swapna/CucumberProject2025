package stepdefinitions.datadriven;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import stepdefinitions.BaseSteps;
import stepdefinitions.BaseStepsDataDriven;
import utils.DataUtils;




public class DataDriveSteps extends BaseStepsDataDriven{
	
	static WebDriver driver;
	 static LoginPage lp; 


	@BeforeAll
	public static void before_or_after_all() throws MalformedURLException {
		System.out.println("getting the driver from data driven Steps");
		BaseStepsDataDriven.setDriver("chrome", false);
		driver=BaseStepsDataDriven.getDriver();
		System.out.println(driver.getCurrentUrl());
		lp = new LoginPage(driver);
		
	}
	

	@AfterAll
	public static void tearDownConfigReport() {
		if(driver!=null) {
			driver.close();
		}
		
	}
	
//	@Before 
//	public void beforeScenario(Scenario scenario) { 
//		test = TestRunner.extent.createTest(scenario.getName()); 
//		} 

	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() throws IOException {
		
		driver.get(DataUtils.readLoginTestData("app.url"));
	    driver.manage().window().maximize();

	}

	@When("I enter {string} and {string}")
	public void i_enter_and(String username, String password) throws IOException {
		
		 lp.enterUserName(driver, DataUtils.readLoginTestData(username)).enterPassword(DataUtils.readLoginTestData(password));    
	}

	

	@When("I click on the login button")
	public void i_click_on_the_login_button() {
		 lp.clickLoginButton(driver);
	}

	@Then("I should see the message {string}")
	public void i_should_see_the_message(String expectedMessage) throws IOException {
		switch(expectedMessage) {
		case "Login successful":
			if(lp.verifyHomePageIsDisplayed(driver, "Home Page ~ Salesforce - Developer Edition")) {
//	    		System.out.println(expectedMessage);
			}
	    		break;
			
	    case "Invalid password":
	    	if(lp.verifyWrongPasswordEntered(driver,DataUtils.readLoginTestData("wrong.password"))){	
	  //  		System.out.println(expectedMessage);
	    	}
	    	break;
		}
		
    	
	
	}


}
