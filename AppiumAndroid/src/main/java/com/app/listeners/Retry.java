package com.app.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.app.utilities.Configs;

public class Retry  implements IRetryAnalyzer
{

	private int retryCount = 0;
    private int maxRetryCount = Integer.parseInt(Configs.getConfig("RetryCount"));
	
    @Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stubS
		if (retryCount < maxRetryCount) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
	}
	
	public String getResultStatusName(int status) {
    	String resultName = null;
    	if(status==1)
    		resultName = "SUCCESS";
    	if(status==2)
    		resultName = "FAILURE";
    	if(status==3)
    		resultName = "SKIP";
		return resultName;
    }
}
