package com.optumrx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class MobileObject
{
	AppiumDriver<MobileElement> driver;
	WebDriverWait wait;
	WebDriverWait shortWait;
	WebDriverWait longWait;

	public MobileObject(AppiumDriver<MobileElement> driver)
	{
		this.driver = driver;
		init();
	}

	protected void init()
	{
		wait = new WebDriverWait(driver, 30, 1000);
		shortWait = new WebDriverWait(driver, 10, 500);
		longWait = new WebDriverWait(driver, 120, 5000);
	}

	public MobileElement find(By locator)
	{
		MobileElement element = null;

		// look for element
		try
		{
			element = (MobileElement) shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (TimeoutException e)
		{
			e.printStackTrace();
		}

		// check whether element is off screen
		if (element != null && ! element.isDisplayed())
		{
			moveTo(element);
		}

		return element;
	}

	public MobileElement click(By locator)
	{
		MobileElement element = find(locator);
		return click(element);
	}

	public MobileElement click(MobileElement element)
	{
		try
		{
			element.click();
		}
		catch (Exception e)
		{
			shortWait.until(ExpectedConditions.elementToBeClickable(element)).click();
		}

		return element;
	}

	public MobileElement waitFor(By locator)
	{
		return (MobileElement) longWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public MobileElement moveTo(MobileElement element)
	{
		TouchAction action = new TouchAction(driver);
		action.moveTo(element).perform();
		return element;
	}
}