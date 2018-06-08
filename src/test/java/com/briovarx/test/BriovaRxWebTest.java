package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BriovaRxWebTest
{
	public static By copyright = By.cssSelector(".copyright");
	public static By LoginButton = By.id("btnLogin");
	public static By SignInLink = By.partialLinkText("Sign In");

	@Test
	public void login() throws MalformedURLException
	{
		URL url = new URL("https://us1.appium.testobject.com/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("deviceName", "Samsung Galaxy S6");
		capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY"));

		AppiumDriver<WebElement> driver = new AppiumDriver(url, capabilities);

		driver.get("https://uat.briovarx.com/");

		driver.findElement(LoginButton);

		driver.quit();

	}

}
