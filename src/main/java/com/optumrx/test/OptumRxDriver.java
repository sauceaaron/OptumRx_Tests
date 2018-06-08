package com.optumrx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OptumRxDriver extends MobileObject
{
	public UpdateScreen updateScreen;
	public WelcomeScreen welcomeScreen;
	public SignInScreen signInScreen;
	public RegisterScreen registerScreen;

	public OptumRxDriver(AppiumDriver<MobileElement> driver)
	{
		super(driver);
		init();
	}
	protected void init()
	{
		shortWait = new WebDriverWait(driver, 10, 1000);
		longWait = new WebDriverWait(driver, 60, 5000);

		updateScreen = new UpdateScreen(driver);
		welcomeScreen = new WelcomeScreen(driver);
		registerScreen = new RegisterScreen(driver);
		signInScreen = new SignInScreen(driver);
	}

	public OptumRxDriver updateApp()
	{
		updateScreen.update();
		return this;
	}

	public OptumRxDriver register(String firstName, String lastName, String dateOfBirth, String zipCode, String memberId)
	{
		updateScreen.update();
		welcomeScreen.clickRegister();
		registerScreen.register(firstName, lastName, dateOfBirth, zipCode, memberId);
		return this;
	}
}
