package runner;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import stepdefinitions.BaseSteps;
import stepdefinitions.LoginSteps;
import utils.CommonUtils;


@CucumberOptions(
		plugin= {"pretty","html:target/cucumber-reports/report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features="src\\main\\java\\features\\Loginfeature.feature", glue={"stepdefinitions"},
		monochrome=true)

public class TestRunner extends AbstractTestNGCucumberTests{
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> threadExtentTest=new ThreadLocal<ExtentTest>();
   

    @BeforeClass
    public void setUp()  {
   	
    	Properties prop=new Properties();
    	try {
			FileInputStream fis=new FileInputStream("src/test/resources/extent.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String outputPath=prop.getProperty("extent.reporter.spark.out");//"test-output/SparkReports/ExtentSparkReport.html");
    	
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(outputPath+CommonUtils.getTimeStamp()+".html");
        try {
			spark.loadXMLConfig(getClass().getClassLoader().getResource("spark-config.xml").getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        extent.attachReporter(spark); 

       
        
    }
   
   

    
    public static ExtentTest getTest() {
    	return threadExtentTest.get();
    }
    
    @AfterClass
    public void tearDown() {
    	if(extent!=null) {
    		extent.flush();
    	}
    }   
    	
    	@After
    	public void afterScenario(Scenario scenario) { 
    		
    		if (scenario.isFailed()) { 
    			test.fail("Test failed"); 
    		} 
    		else { 
    			test.pass("Test passed"); 
    			} 
    		extent.flush(); 
    		} 
    	}
    
	



	

	
