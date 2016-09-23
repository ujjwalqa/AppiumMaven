package com.app.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer
{
	
	@SuppressWarnings("rawtypes") 
	@Override
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		IRetryAnalyzer retry = testAnnotation.getRetryAnalyzer();
		
		if (retry == null)	{
			testAnnotation.setRetryAnalyzer(Retry.class);
		}

		
	}

}
