package com.app.listeners;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.app.android.AndroidTest;
import com.app.utilities.Utilities;


public class TestListeners implements ITestListener

{

	/**
	 * After finish of any test 
	 */
	@Override
	public void onFinish(ITestContext context)
	{
		System.out.println("On Finish");

		/*
		 * Implementation of resetting of no of executed test
		 * in case of retry of test in case of failure or skip 
		 */

		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();

		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}

		for (ITestResult temp : skippedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getSkippedTests().getResults(method).size() > 1) {
				skippedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					skippedTests.remove(temp);
				}
			}
		}
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("On Start");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult itr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult itr) {

		/*
		 * taking screen shot using class drivers
		 * in case of multiple test case class
		 * add the below if statement and change
		 * accordingly 
		 */


		String insName = itr.getInstance().getClass().getSimpleName();


		if(insName.equalsIgnoreCase("AndroidTest"))
		{
			AndroidTest currentClass = (AndroidTest) itr.getInstance();
			WebDriver driver = currentClass.getDriver();
			Utilities.takeScreenshot(driver, "Failed");
		}

	}

	@Override
	public void onTestSkipped(ITestResult itr) {
		// TODO Auto-generated method stub
		//ExtentReportCustom.createReport(itr.getName(), itr.getMethod().getGroups().toString(), itr.getMethod().getDescription(), "SKIPPED", "Method skipped");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("On Test Start");
	}

	@Override
	public void onTestSuccess(ITestResult itr)
	{
		String insName = itr.getInstance().getClass().getSimpleName();

		/*
		 * taking screen shot using class drivers
		 * in case of multiple test case class
		 * add the below if statement and change
		 * accordingly 
		 */
		if(insName.equalsIgnoreCase("AndroidTest"))
		{
			AndroidTest currentClass = (AndroidTest) itr.getInstance();
			WebDriver driver = currentClass.getDriver();
			Utilities.takeScreenshot(driver, "Passed");
		}

	}

}
