package com.app.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Utilities;

public class FirstLandingScreen 
{
	WebDriver driver;
	
	/**
	 * Initializing all elements of this screen
	 * @param driver
	 */
	public FirstLandingScreen(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		
	}
	
	/*
	 * All elements deceleration will go here
	 */
	
	@FindBy(how=How.ID, using="de.komoot.android:id/button_facebook")
	WebElement btnLoginWithFacebook;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/button_mail_register")
	WebElement btnSignUpWithEmail;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/textview_tos")
	WebElement linkTermsOfService;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/textview_login2")
	WebElement linkHaveAnAccount;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/textview_login")
	WebElement linkLogin;
	
	@FindBy(how=How.ID, using="android:id/alertTitle")
	WebElement alertTitle;
	
	@FindBy(how=How.ID, using="android:id/message")
	WebElement alertMessage;
	
	@FindBy(how=How.ID, using="android:id/button3")
	WebElement alertOK;
	
	/*
	 * All actions will go here
	 */
	
	/**
	 * Click on LOG IN WITH FACEBOOK
	 * @return {@link FirstLandingScreen}
	 */
	public FirstLandingScreen clickLoginWithFacebook()
	{
		btnLoginWithFacebook.click();
		return this;
	}
	
	/**
	 * Click on SIGN UP WITH EMAIL
	 * @return {@link FirstLandingScreen}
	 */
	public FirstLandingScreen clickSignUpWithEmail()
	{
		btnSignUpWithEmail.click();
		return this;
	}
	
	/**
	 * Click on TERMS OF SERVICE
	 * @return {@link FirstLandingScreen}
	 */
	public FirstLandingScreen clickTermsOfService()
	{
		linkTermsOfService.click();
		return this;
	}
	
	/**
	 * Click on HAVE AN ACCOUNT
	 * @return {@link FirstLandingScreen}
	 */
	public FirstLandingScreen clickHaveAnAccount()
	{
		linkHaveAnAccount.click();
		return this;
	}
	
	/**
	 * Click on LOG IN HERE
	 * @return {@link FirstLandingScreen}
	 */
	public FirstLandingScreen clickLoginHere()
	{
		linkLogin.click();
		return this;
	}
	
	/**
	 * Get alert title
	 * @return
	 */
	public String getAlertTitle() 
	{
		return Utilities.waitForElement(alertTitle).getText();		
	}
	
	/**
	 * Get alert message
	 * @return
	 */
	public String getAlertMeggase() 
	{
		return Utilities.waitForElement(alertMessage).getText();		
	}
	
	/**
	 * Click on alert OK
	 * @return
	 */
	public FirstLandingScreen clickAlertOK() 
	{
		Utilities.waitForElement(alertOK).click();
		return this;
		
	}
	
}
