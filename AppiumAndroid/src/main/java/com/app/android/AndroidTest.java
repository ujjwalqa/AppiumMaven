package com.app.android;

import java.io.File;
import java.net.URL;

import com.app.POM.FirstLandingScreen;
import com.app.POM.LoginForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;



public class AndroidTest{

	public WebDriver driver = null;

	AppiumDriverLocalService appiumService;
	String appiumServiceUrl;

	String cmd = "";


	public WebDriver getDriver() {
		return driver;
	}

	@Parameters({"appiumServer", "apkPath"})
	@BeforeMethod
	public void setup(String serverURL, String apkRelativePath) throws Exception
	{
		// Creating Absolute path using relative path retrieved from testng.xml file
		File apkFilePath = new File(apkRelativePath);
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.APP, apkFilePath);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidDevice");
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30);

		driver = new AndroidDriver<>(new URL(serverURL), capabilities);

	}

	@AfterMethod
	public void tearDown()
	{
		if (driver != null) {
			driver.quit();
		}
	}


	@Test(groups="Functional", dataProvider = "credentials", dataProviderClass=TestDataProvider.class)
	public void validateInvalidEmail(String username, String password)
	{

		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickLoginHere();

		LoginForm loginForm = new LoginForm(driver);
		loginForm.fillUsernamePassword(username, password);
		loginForm.clickOnLogin();
		Assert.assertEquals("Email address invalid.", loginForm.getAlertMessage());
		loginForm.clickAlertOK();
	}

	@Test(groups="Functional", dataProvider = "credentials", dataProviderClass=TestDataProvider.class)
	public void SecondTestCase(String username, String password)
	{
		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickLoginHere();

		LoginForm loginForm = new LoginForm(driver);
		loginForm.fillUsernamePassword(username, password);
		loginForm.clickOnLogin();
		Assert.assertEquals("Email address invalid.", loginForm.getAlertMessage());
		loginForm.clickAlertOK();
	}

}


