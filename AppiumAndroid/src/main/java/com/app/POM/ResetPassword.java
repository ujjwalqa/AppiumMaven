package com.app.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Utilities;

public class ResetPassword 
{
	WebDriver driver;
	
	public ResetPassword(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/*
	 * All web element declaration will go here
	 * 
	 */
	
	@FindBy(how=How.ID, using="de.komoot.android:id/txt_user_name")
	WebElement txtEmail;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/btn_submit")
	WebElement btnReset;
	
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
	 * Fill user name in the email field
	 * @param username
	 * @return {@link ResetPassword}
	 */
	public ResetPassword fillEmail(String username) 
	{
		txtEmail.sendKeys(username);
		return this;
	}
		
	/**
	 * Click on Reset button
	 * @return
	 */
	public ResetPassword clickReset()
	{
		btnReset.click();
		return this;
	}
	
	/**
	 * Provide text in the alert title
	 * @return
	 */
	public String getAlertTitle()
	{
		return Utilities.waitForElement(alertTitle).getText();
	}
	
	/**
	 * Provide text in the alert message
	 * @return
	 */
	public String getAlertMessage()
	{
		return Utilities.waitForElement(alertMessage).getText();
	}
	
	/**
	 * Click on Alert OK
	 * @return
	 */
	public ResetPassword clickAlertOK()
	{
		Utilities.waitForElement(alertOK).click();
		return this;
	}
}
