package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.FileConstants;
import runner.TestRunner;
import stepdefinitions.BaseSteps;
import stepdefinitions.LoginSteps;
import utils.CommonUtils;

public class TestListener implements ITestListener{
	

//	@Override
//	public void onTestFailure(ITestResult result) {
//		TestRunner.threadExtentTest.get().addScreenCaptureFromPath(CommonUtils.captureScreenShot(LoginSteps.getDriver()));
//		TestRunner.threadExtentTest.get().fail(MarkupHelper.createLabel("FAILED: " +result.getName(),ExtentColor.RED));
//	}
//	@Override
//	public void onTestStart(ITestResult name){
//		BaseSteps.test=TestRunner.extent.createTest(name.getMethod().getMethodName());
//		TestRunner.threadExtentTest.set(TestRunner.test);
//		System.out.println("ExtentTest created and set for method: "+ name.getMethod().getMethodName());
//	}
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		if (result.getStatus() == ITestResult.SUCCESS) {
//		    TestRunner.test.log(Status.PASS, "Pass Test case is: " + result.getName());
//		TestRunner.threadExtentTest.get().pass(result.getName());
//	}
//	}
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		//BaseTest.threadExtentTest.get().skip(result.getName());
//		String testName = result.getName();
//        String className = result.getTestClass().getName();
//        String reason = result.getThrowable().getMessage(); // If available
//        System.out.println("Skipped test: " + className + "." + testName + " (" + reason + ")");
//	}
	
//	@Override 
//	public void onFinish(ITestContext context) 
//	{ 
//		extent.flush();
//	}
	}

