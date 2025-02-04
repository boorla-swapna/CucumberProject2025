package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import constants.WaitConstants;
import stepdefinitions.BaseSteps;
import utils.CommonUtils;
import utils.DataUtils;
import utils.WaitUtils;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


//	public  LoginPage(WebDriver driver) {
//		super(driver);
//	}
//	
	@FindBy(id="username")
	public WebElement username;
	
	@FindBy(xpath="//input[@value='stay@learning.com']")
	public WebElement verifyUserNameEntered;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(id="Login")
	public WebElement loginButton;
	
	@FindBy(xpath="//*[@id='error']")
	public WebElement errorMsg;
	
	@FindBy(xpath="//label[contains(text(),'Remember me')]")
	public WebElement rememberMe;
	
	@FindBy(xpath="//div[@id='userNav']")
	public WebElement userMenu;
	
	
	@FindBy(xpath="//a[@title='Logout']")
	public WebElement logOut;
	
//	@FindBy(xpath="//span[@id='idcard-identity']")
//	public WebElement usernameFieldInLoginButton;
	
	@FindBy(xpath="(//span[contains(text(),'stay@learning.com')])[2]")
	public WebElement usernameFieldText;
	
	@FindBy(xpath="//div[@id='idcard']")
	public WebElement usernameAfterLogout;
	
	@FindBy(xpath="//input[@checked='checked']")
	public WebElement rememberMeAfterLogout;
	
	@FindBy(id="forgot_password_link")
	public WebElement forgotYourPassword;
	
	@FindBy(id="un")
	public WebElement enterUsernameInForgotPasswordPage;
	
	@FindBy(id="continue")
	public WebElement continueButton;
	
	@FindBy(xpath="//div[@class='message']")
	public WebElement passwordResetMessage;
	
	@FindBy(xpath="//div[@id='error']")
	public WebElement errorMessageForWrongUsernamePassword;
	
	
	public boolean verifyPasswordError() {
		boolean isErrorMessageDisplayedforWrongUserNPass=false;
		//Please check your username and password. If you still can't log in, contact your Salesforce administrator.
	String actualErrorMessageForWrongUsernamePassword=errorMessageForWrongUsernamePassword.getText();
	String expectedErrorMessageForWrongUsernamePassword="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		if(actualErrorMessageForWrongUsernamePassword.contains(expectedErrorMessageForWrongUsernamePassword)) {
			isErrorMessageDisplayedforWrongUserNPass=true; 
			logger.info("PASS: Error Message Displayed as Expected");
		}else {
			logger.debug("PASS: Error Message is not Displayed as Expected");
		}
	  return isErrorMessageDisplayedforWrongUserNPass;
	}
	
	
	public boolean verifyWrongUsernameEntered(WebDriver driver,String actualEnteredUsername) throws IOException {
		boolean isWrongEnteredUsername=false;
		//String expectedUser=DataUtils.readLoginTestData("valid.username");
		if(WaitUtils.waitForElementToBeVisible(driver, username)) {
			if(!this.username.getAttribute("value").isEmpty()) {
			this.username.clear();
			this.username.sendKeys(DataUtils.readLoginTestData("wrong.username"));
			}
			if(actualEnteredUsername.equals(DataUtils.readLoginTestData("wrong.username"))) {
				isWrongEnteredUsername=true;
			logger.info("PASS: Wrong Username Entered into Username field as Expected");
		}else {
		     logger.error("Enter Valid Username");
			}
		}
		return isWrongEnteredUsername;
	}
	
	public boolean verifyWrongPasswordEntered(WebDriver driver,String actualPasswordEntered) throws IOException {
		boolean isWrongPasswordEntered=false;
		if(WaitUtils.waitForElementToBeVisible(driver, password)) {
			if(!password.getAttribute("value").isEmpty()) {
			this.password.clear();
			this.password.sendKeys(DataUtils.readLoginTestData("wrong.password"));
			}
			if(actualPasswordEntered.equals(DataUtils.readLoginTestData("wrong.password"))) {
				isWrongPasswordEntered=true;
				logger.info("PASS: Wrong password Entered into password field as Expected");
		}else {
		     logger.error("Enter Valid Password");
			}
		}
		return isWrongPasswordEntered;
		}
	
	public boolean verifyPasswordResetMessagePageIsDisplayed(WebDriver driver,String expectedPasswordResetMessage) {
		boolean isPasswordResetMessageDisplayed=false;
		if(WaitUtils.waitForElementToBeVisible(driver, passwordResetMessage)) {
			String actualPasswordResetMessage=passwordResetMessage.getText();
			//String expectedPasswordResetMessage="sent you an email with a link to finish resetting your password.";
		   if(actualPasswordResetMessage.contains(expectedPasswordResetMessage)) {
			   isPasswordResetMessageDisplayed=true;
			   logger.info("PASS: Password Reset Message is Displayed.An email associated with the <username> account is sent.  as Expected");
		}else {
			logger.error("Password Reset Message Page is not Displayed");
		}
		}
		return isPasswordResetMessageDisplayed;
	}
	
	public void clickOnContinueButton(WebDriver driver) {
		if(WaitUtils.elementToBeClickable(driver, continueButton)) {
			continueButton.click();
			logger.info("Continue Button is clicked on the forgot your Password Page");
		}else {
			logger.error("Continue Button is clickable on the forgot your Password Page");
		}
	}
	
	public void  enterUsernameInForgotYourPasswordPage(String username) throws IOException {
		if(enterUsernameInForgotPasswordPage.isDisplayed()) {
			enterUsernameInForgotPasswordPage.sendKeys(username);
		    logger.info("Valid username is entered into Username field");
		}else {
			logger.error("Username field is not Displayed on the page");
		}
	}
	
	public boolean verifyForgotPasswordLink(WebDriver driver,String expectedForgotPasswordPageDisplayed) {
		boolean isForgotPasswordPageDisplayed=false;
		if(WaitUtils.elementToBeClickable(driver, forgotYourPassword)) {
			forgotYourPassword.click();
			String actualForgotPasswordPageDisplayed=driver.getTitle();
			//String expectedForgotPasswordPageDisplayed="Forgot Your Password | Salesforce";
			if(actualForgotPasswordPageDisplayed.contains(expectedForgotPasswordPageDisplayed)) {
				isForgotPasswordPageDisplayed=true;
				logger.info("PASS: Salesforce forgot password page is displayed");
			}else {
				logger.error("forgotYourPassword Link is not visible on Page to Click" + forgotYourPassword);
			}
		}
		return isForgotPasswordPageDisplayed;
	}
	
	public boolean verifyRememberMeAfterLogout(WebDriver driver) {
		boolean isRemeberMeChecked=false;
		if(WaitUtils.waitForElementToBeVisible(driver, rememberMeAfterLogout)) {
			String check=rememberMeAfterLogout.getAttribute("checked");
			if(check.contains("true")) {
				isRemeberMeChecked=true;
				logger.info("PASS: RememberMe checkbox is checked as Expected");
			}else {
				logger.error("RememberMe check Box is not checked after Logout");
			}
			}
			return isRemeberMeChecked;
	}
	
	
	public boolean verifyUsernameFieldAfterLogout(WebDriver driver,String expectedUsername) throws IOException {
		boolean isUsernamePopulatedAfterLogout=false;
		if(WaitUtils.waitForElementToBeVisible(driver, usernameAfterLogout)) {
			 String actualUsername=usernameAfterLogout.getAttribute("aria-label");
		    // String expectedUsername=DataUtils.readLoginTestData("valid.username");
		     if(actualUsername.contains(expectedUsername)) {
		    	 isUsernamePopulatedAfterLogout=true;
		    	 logger.info("PASS: Validation of username displayed is as expected");
		     }else {
					logger.error("Username is not populated in the <username>field");
				}
		     }
		return isUsernamePopulatedAfterLogout;
		
	}
	
	public boolean verifyHomePageIsDisplayed(WebDriver driver,String homePageTitle) {
		
		logger.info("Verifying Home Page is Displayed");
         
		WebDriverWait wait = new WebDriverWait(driver,WaitConstants.WAIT_FOR_ELEMENT_TO_BE_VISIBLE);
		boolean isHomePageDisplayed=false;
		if(wait.until(ExpectedConditions.titleContains(homePageTitle))) {
		String actualHomePageTitle=driver.getTitle();
		//"Home Page";
		
		if(actualHomePageTitle.contains(homePageTitle)) 
		{
			isHomePageDisplayed=true;
			logger.info("PASS: Home Page is Opened");
		
	}
		
		}
		return isHomePageDisplayed;
	}
	
	public boolean verifyApplicationPageIsDisplayed(WebDriver driver) {
		boolean isApplicationPageDisplayed=false;
		logger.info("Verifying ApplicationPage is Displayed");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8000));
		wait.until(ExpectedConditions.titleContains("Login | Salesforce"));
		String actualTitle=driver.getTitle();
		String expectedTitle="Login | Salesforce";
		if(actualTitle.contains(expectedTitle)) {
			isApplicationPageDisplayed=true;
			logger.info("PASS: ApplicationPage is Displayed");
		}else {
			logger.error("ApplicationPage is not Displayed");
		}
			return isApplicationPageDisplayed;
	}
	
	public  LoginPage enterUserName(WebDriver driver,String userEmail) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8000));
		wait.until(ExpectedConditions.visibilityOf(username));
		if(this.username.isDisplayed()) {
			
			this.username.clear();
			this.username.sendKeys(userEmail);	
			
			logger.info("Entered Username in the username field");
		}else {
			logger.error("Enter Username into username field");
		}
		return this;
	}
		
//public  boolean verifyLoginToSFDC(WebDriver driver,String userEmail,String password) throws IOException {
//		boolean isLoginToSFDCSuccessfull=false;
//		if(this.username.isDisplayed()) {
//			if(!this.username.getAttribute("value").isEmpty())
//			this.username.clear();
//			this.username.sendKeys(userEmail);	
//			verifyUserNameIsDisplayedInUserNameField(driver);
//			logger.info("Entered Username in the username field");
//		}else {
//			logger.error("Username is displayed into username field");
//		}
//		if(this.password.isDisplayed()) {
//			if(this.password.getAttribute("value").isEmpty())
//				this.password.clear();
//			    this.password.sendKeys(password);
//			    if(!this.password.getAttribute("value").isEmpty()) {
//			    logger.info("Entered Password in password field");
//		}else {
//			logger.error("Password is not entered in password field");
//		}
//		}
//		
//		}

	public boolean verifyUserNameIsDisplayedInUserNameField(WebDriver driver) throws IOException {
		//String expectedUsername=DataUtils.readLoginTestData("valid.username");
		boolean isUserNameDisplayedInUserNameField=false;
		if(WaitUtils.waitForElementToBeVisible(driver, username)) {
			if(!username.getAttribute("value").isEmpty()) {
				isUserNameDisplayedInUserNameField=true;
				logger.info("PASS: Verified: UserName Displayed in username field");
			}else {
				logger.error("Verified: UserName not Displayed in username field");
			}
			
		}
		
		return isUserNameDisplayedInUserNameField;	
	}
		
	
	
	
	public  LoginPage enterPassword(String password) {//change for other testcase
		
		if(this.password.isDisplayed()) {
			this.password.clear();
			this.password.sendKeys(password);
			if(this.password.getAttribute("value").isEmpty()) {
				logger.warn("Password field is Empty");
			}else {
				logger.info(password + "Password Entered");
			}
			
		} 
		return this;
		
	}
	public boolean verifyEmptyPasswordFieldDisplayed(WebDriver driver) throws IOException {
		boolean isPasswordFieldEmpty=false;
		if(password.getAttribute("value").isEmpty()) {
			 isPasswordFieldEmpty=true;
			}
		return isPasswordFieldEmpty;
		}

	public  LoginPage clickLoginButton(WebDriver driver) {
		//boolean isLoginButtonDisplayed=false;
		if(WaitUtils.elementToBeClickable(driver, loginButton)) {
			//isLoginButtonDisplayed=true;
			loginButton.click();
			logger.info("Clicked on loginButton");
	}
	else {
    	  logger.warn("Login Button is not Clickable");
      }
		return this;
	}
	
	public  boolean verfiyPasswordErrorMessageDisplayed(WebDriver driver,String expectedPasswordErrorMsg) {
		//String expectedPasswordErrorMsg="Please enter your password.";     
		boolean isexpectedPasswordErrorMsgDisplayed=false;
		     String actualPasswordErrorMsg="";
		     
		if(WaitUtils.waitForElementToBeVisible(driver, errorMsg)) {
			actualPasswordErrorMsg=errorMsg.getText();
		
		if(actualPasswordErrorMsg.contains(expectedPasswordErrorMsg)) {
			isexpectedPasswordErrorMsgDisplayed=true;
			logger.info("Error message is Displayed as expected");
		}
		else {
			logger.error("Error message is not displayed as expected");
		}
	}
	return isexpectedPasswordErrorMsgDisplayed;
	}
	
	
	public void checkRememberMeCheckBox() {
		if(!rememberMe.isSelected()) {
			rememberMe.click();
			logger.info("Checked the rememberMe checkbox");
		}else {
			System.out.println("RememberMe CheckBox is already checked");
		}
		
	}
	public void clickLogOut(WebDriver driver) {
		if(userMenu.isDisplayed() && userMenu.isEnabled()) {
			
		if(BaseSteps.mouseHover(driver, userMenu)) {
			userMenu.click();
			if(logOut.isDisplayed()) {
				logOut.click();
				logger.info("Clicked on Logout Button");
			}else {
				logger.warn("logOut button not Displayed ");
			}
			
		}
		}
	}
	
//	public void clickOnLogOut() {
//		if(logOut.isDisplayed()) {
//			logOut.click();
//		}else {
//			logger.warn("logOut button not Displayed");
//		}
//	}

}

