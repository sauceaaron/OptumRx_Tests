package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class TestObjectBase
{
	static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY");

	AppiumDriver driver;
	WebDriverWait wait;

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL("https://us1.appium.testobject.com/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion",         "1.8.0");
		capabilities.setCapability("automationName",        "uiautomator2");
		capabilities.setCapability("platformName",          "Android");
		capabilities.setCapability("platformVersion",       "6.0.1");
		capabilities.setCapability("deviceName",            "Samsung Galaxy S6");
		capabilities.setCapability("testobject_api_key",    TESTOBJECT_API_KEY);
		capabilities.setCapability("testobject_test_name",  "Switch Context");
		capabilities.setCapability("testobject_suite_name", "BriovaRx");
		capabilities.setCapability("appPackage",            "com.catamaranrx.briovarx");
		capabilities.setCapability("appActivity",           "com.catamaranrx.briovarx.SplashActivity");

		driver = new AndroidDriver(url, capabilities);
		wait = new WebDriverWait(driver, 60);
	}

	@After
	public void teardown()
	{
		driver.quit();
	}
}