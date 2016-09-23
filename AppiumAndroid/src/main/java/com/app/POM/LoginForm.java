package com.app.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Utilities;

public class LoginForm
{

	WebDriver dribver;

	public LoginForm(WebDriver driver) {
		this.dribver = driver;
		PageFactory.initElements(this.dribver, this);
	}

	/*
	 * All elements deceleration will go here
	 */

	@FindBy(how=How.ID, using="de.komoot.android:id/edittext_email")
	WebElement txtUsername;

	@FindBy(how=How.ID, using="de.komoot.android:id/edittext_password")
	WebElement txtPassword;

	@FindBy(how=How.ID, using="de.komoot.android:id/button_login")
	WebElement btnLogin;

	@FindBy(how=How.ID, using="android:id/alertTitle")
	WebElement alertTitle;
	
	@FindBy(how=How.ID, using="android:id/message")
	WebElement alertMessage;

	@FindBy(how=How.ID, using="android:id/button1")
	WebElement alertOK;

	@FindBy(how=How.ID, using="de.komoot.android:id/textview_forgot_password")
	WebElement linkForgotPassword;
	
	/*
	 * All actions will go here
	 */

	/**
	 * Fill the user name and password
	 * @param username
	 * @param password
	 * @return {@link LoginForm}
	 */
	public LoginForm fillUsernamePassword(String username , String password)
	{
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		return this;
	}

	/**
	 * Fill only user name
	 * @param username
	 * @return {@link LoginForm}
	 */
	public LoginForm fillUsername(String username)
	{
		txtUsername.sendKeys(username);
		return this;
	}

	/**
	 * Fill only password
	 * @param password
	 * @return {@link LoginForm}
	 */
	public LoginForm fillPassword(String password)
	{
		txtPassword.sendKeys(password);
		return this;
	}

	/**
	 * Click on the Login button
	 * @return {@link LoginForm}
	 */
	public LoginForm clickOnLogin() 
	{
		btnLogin.click();
		return this;
	}	
	
	/**
	 * This will throw time out error if message box not displayed, if displayed do nothing
	 * @return {@link LoginForm}s
	 */
	public LoginForm verifyMessageApperance() 
	{
		Utilities.waitForElement(alertTitle);
		return this;
	}
	
	/**
	 * Get the text from validation pop message if appear
	 * @return {@link String}
	 */
	public String getAlertTitle() 
	{
		return Utilities.waitForElement(alertTitle).getText();
	}

	/**
	 * Get the text from validation pop message if appear
	 * @return {@link String}
	 */
	public String getAlertMessage() 
	{
		return Utilities.waitForElement(alertMessage).getText();
	}

	/**
	 * Click on OK of validation message if appear other wise throw time out error
	 * @return {@link LoginForm}
	 */
	public LoginForm clickAlertOK()
	{
		Utilities.waitForElement(alertOK).click();
		return this;
	}
	
	/**
	 * Click on Forgot password link
	 * @return
	 */
	public LoginForm clickForgotPassword()
	{
		linkForgotPassword.click();
		return this;
	}
}
