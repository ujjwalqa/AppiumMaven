package com.app.listeners;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;


public class ReporterListener implements IReporter {

	@SuppressWarnings(value={"unused"})
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outPutDirectory) {
		// TODO Auto-generated method stub


		System.out.println("*****Custom Report******");

		/*
		 * Retrieve list of suites
		 */
		ISuite suite = suites.get(0);

		/*
		 * Getting all test methods by group
		 */
		Map<String, Collection<ITestNGMethod>> methodsByGroup = suite.getMethodsByGroups();
		List<ITestNGMethod>  allMethods = suite.getAllMethods();

		/*
		 * Collecting results by grouping suites in Map
		 */
		Map<String, ISuiteResult> tests = suite.getResults();

		/*
		 * 
		 */
		for (String key : tests.keySet()) {
			System.out.println("Key: " + key + ", Value: " + tests.get(key));
		}

		/*
		 * Collecting values of test results
		 */
		Collection<ISuiteResult> suiteResults = tests.values();

		/*
		 * 
		 */
		ISuiteResult suiteResult = (ISuiteResult)suiteResults.iterator().next();

		/*
		 * 
		 */
		ITestContext testContext = suiteResult.getTestContext();

		/*
		 * 
		 */
		Collection<String> groupList = methodsByGroup.keySet();

		/*
		 * get test case list by method name
		 */
		for (ITestNGMethod method : allMethods) {
			
			String groupName = "";
			
			for (String group : method.getGroups()) {
				groupName = groupName+group+" ";
			}
			
			if(groupName.equalsIgnoreCase(""))
				groupName = "Default ";
			
			
			IResultMap failedTests = testContext.getFailedTests();

			Set<ITestResult> testResultSetFailed = failedTests.getResults(method);
			for (ITestResult testResultFailed : testResultSetFailed) {
				System.out.println("Test " + "Group: "+ groupName+" " + testResultFailed.getName() + " failed, error " + testResultFailed.getThrowable());
			}

			IResultMap passedTests = testContext.getPassedTests();

			Set<ITestResult> testResultSetPassed = passedTests.getResults(method);
			for (ITestResult testResultPassed : testResultSetPassed) {
				System.out.println("Test " + "Group: "+ groupName+" " +testResultPassed.getName() + " passed, time took " + 
						(testResultPassed.getStartMillis() - testResultPassed.getEndMillis()));
			}
		}
		
		/*
		 * get test case list by group name
		 */
/*		
		for (String group : groupList) {

			Collection<ITestNGMethod> perfMethods = methodsByGroup.get(group);
			IResultMap failedTests = testContext.getFailedTests();


			for (ITestNGMethod perfMethod : perfMethods) {
				Set<ITestResult> testResultSet = failedTests.getResults(perfMethod);
				for (ITestResult testResult : testResultSet) {
					System.out.println("Test " + testResult.getName() + " failed, error " + testResult.getThrowable());
				}
			}

			IResultMap passedTests = testContext.getPassedTests();
			for (ITestNGMethod perfMethod : perfMethods) {
				Set<ITestResult> testResultSet = passedTests.getResults(perfMethod);
				for (ITestResult testResult : testResultSet) {
					System.out.println("Test " + testResult.getName() + " passed, time took " + 
							(testResult.getStartMillis() - testResult.getEndMillis()));
				}
			}

		}
*/


		System.out.println("*****End of Report******");
	}


}
