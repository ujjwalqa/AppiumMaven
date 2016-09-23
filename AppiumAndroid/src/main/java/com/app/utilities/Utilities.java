package com.app.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;


public class Utilities

{
	
	static int timeOut = Integer.valueOf(Configs.getConfig("timeOut"));
	static int pollingTime = Integer.valueOf(Configs.getConfig("pollingTime"));
	
	/**
	 * take screen shot
	 * @param {@link WebDriver} driver
	 * @param testStatus Failed/Passed
	 */

	public static void takeScreenshot(WebDriver driver, String testStatus)
	{
		String destDir="";
		DateFormat dateFormat;

		// Set folder name to store screenshots.
		if(testStatus.equalsIgnoreCase("FAILED"))
			destDir = "screenshots/Failed";

		if(testStatus.equalsIgnoreCase("PASSED"))
			destDir = "screenshots/Passed";

		// Capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Set date format to set It as screenshot file name.
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		// Create folder under project with name "screenshots" provided to destDir.
		new File(destDir).mkdirs();

		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Custom wait for any element
	 * This take a type of {@link WebElement}
	 * @param {@link WebElement} element
	 * @return {@link WebElement}
	 */
	public static WebElement waitForElement(WebElement element) {

		WebElement ele = element;

		new FluentWait<WebElement>(ele).withTimeout(timeOut, TimeUnit.SECONDS).pollingEvery(pollingTime,TimeUnit.MILLISECONDS).
		until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement ele) {
				return ele.isDisplayed();
			}
		});

		return ele;
	}
}

