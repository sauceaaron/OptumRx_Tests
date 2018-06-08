package com.optumrx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class WelcomeScreen extends MobileObject
{
	public static By registerButton = By.id("hsIdRegisterBtn");
	public static By appVersion = By.id("versionNbr");

	public WelcomeScreen(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}

	public WelcomeScreen clickRegister()
	{
		click(registerButton);
		return this;
	}
}
