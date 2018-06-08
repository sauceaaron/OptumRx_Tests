package com.optumrx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UpdateScreen extends MobileObject
{
	public static By updateButton = By.xpath("//*[@id='btnStart']");

	public UpdateScreen(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}

	public UpdateScreen update()
	{
		click(updateButton);
		waitFor(WelcomeScreen.appVersion);
		return this;
	}

	public WebElement updateButton()
	{
		return find(updateButton);
	}
}
