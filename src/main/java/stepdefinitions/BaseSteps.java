package stepdefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import pages.LoginPage;
import runner.TestRunner;


public class BaseSteps {
	public static WebDriver driver;
	
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
       
    public static Logger logger = LogManager.getLogger("BaseSteps");
    public static ExtentTest test;
    public static LoginPage lp;
    //public static final String HUB_URL = "http://192.168.254.13:4444/wd/hub"; 

		
    public static void setDriver(String browserName,  boolean headless) throws MalformedURLException {
		WebDriver driver = BaseSteps.getBrowserDriver(browserName, headless);
	    threadLocalDriver.set(driver);
	}
	
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
    
	public static WebDriver getBrowserDriver(String bname,boolean headless) throws MalformedURLException {
		
		
		bname=bname.toLowerCase();
		switch (bname) {
		 
		case "chrome": 
			if(headless) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--headless,--disable-gpu");
				driver=new ChromeDriver(options);
				
			}else {
				
				driver=new ChromeDriver();
			}
			break;
			
		case "firefox":
			if(headless) {
				FirefoxOptions firefoxoptions=new FirefoxOptions();
				firefoxoptions.addArguments("--headless,--disable-gpu");
				driver=new FirefoxDriver(firefoxoptions);
			}else {
				FirefoxOptions firefoxoptions=new FirefoxOptions();
				//driver=new RemoteWebDriver(new URL(HUB_URL),firefoxoptions);
			}
		break;
			default:
				driver=null;
				logger.error("Driver Configuration failed");
				System.out.println("This method is suitable for chrome and edge browsers");
				break;
		}
		
		return driver;
	}
	
	public static boolean mouseHover(WebDriver driver,WebElement element) {
		boolean isMouseHovered=false;
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
		isMouseHovered=true;
		return isMouseHovered;
	}
	
	public static void cropPhotoClickNHold(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.clickAndHold(element);
		action.moveByOffset(40,50).release().build().perform();
	}
	public static boolean selectOption(WebElement element,String text) {
		boolean optionSelected=false;
		Select option=new Select(element);
		 option.selectByVisibleText(text);
		optionSelected=true;
		return optionSelected;
	}
	public static String selectOptionFromAllOptions(WebElement element,String selectOption) {
		String text="";
		Select option=new Select(element);
		List<WebElement> allOptions=option.getOptions();
		for(WebElement eachOption:allOptions) 
		{
		 if(eachOption.getText().contains(selectOption)) {
			   text=eachOption.getText();
			   break;
		   }
		}
	  return text;
	}
	
	public static String listDropDown(WebElement element) {
		String list="";
		Select options=new Select(element);
		List<WebElement> allOptions=options.getOptions();
		
		for(WebElement eachOption:allOptions) {
		     list=eachOption.getText();
		}
		return list;
	}
	public static List<WebElement> allOptions(WebElement element) {
		Select options=new Select((WebElement) element);
		List<WebElement> allOptions=options.getOptions();
		return allOptions;
	}
	
	public static String firstSelectedOption(WebElement element) {
		String list="";
		Select options=new Select(element);
		String firstSelectedOption=options.getFirstSelectedOption().getText();
		return firstSelectedOption;
	}
	
	public static String getSelectedOption(WebElement element) {
		Select selected=new Select(element);
		WebElement option=selected.getFirstSelectedOption();
		return option.getText();
		
	}
	public static String parentWindowId(WebDriver driver) {
		String parentWindow=driver.getWindowHandle();
		return parentWindow;
	}
	public static WebDriver switchToChildWindow(WebDriver driver,String parentWindowId) {
		Set<String> windowIds=driver.getWindowHandles();
		 for(String childWindow:windowIds) {
			 System.out.println(childWindow + " ");
			 if(!childWindow.equals(parentWindowId)) {
				    driver=driver.switchTo().window(childWindow);
				    logger.info("Switched To Child Window");
	}
			 
		 }
		return driver;
	}
	
	public static void scrollToElement(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static boolean clickElement(WebElement element) {
		boolean isElementClicked=false;
		element.click();
		if(element.isEnabled()) {
			System.out.println("Clicked on " +element);
			isElementClicked=true;
		}else {
			System.out.println(element+ "not Clicked");
		}
		return isElementClicked;
	}
	
}



