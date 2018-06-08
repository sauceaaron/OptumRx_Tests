package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class BriovaRxSauceRealDeviceTest
{
	static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY");

	@Test
	public void switch_context() throws MalformedURLException
	{
		URL url = new URL("https://us1.appium.testobject.com/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion",         "1.8.0");
		capabilities.setCapability("automationName",        "uiautomator2");
//		capabilities.setCapability("autoWebview",           true);
		capabilities.setCapability("platformName",          "Android");
		capabilities.setCapability("platformVersion",       "6.0.1");
		capabilities.setCapability("deviceName",            "Samsung Galaxy S6");
		capabilities.setCapability("testobject_api_key",    TESTOBJECT_API_KEY);
		capabilities.setCapability("testobject_test_name",  "Switch Context");
		capabilities.setCapability("testobject_suite_name", "BriovaRx");
		capabilities.setCapability("appPackage",            "com.catamaranrx.briovarx");
		capabilities.setCapability("appActivity",           "com.catamaranrx.briovarx.SplashActivity");

		AppiumDriver driver = new AndroidDriver(url, capabilities);
		WebDriverWait wait = new WebDriverWait(driver, 60);

		System.out.println("available contexts: " + driver.getContextHandles());
		System.out.println("context before switching: " + driver.getContext());

		driver.context(webview);

		System.out.println("context after switching: " + driver.getContext());

		wait.until(ExpectedConditions.elementToBeClickable(LoginButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(SignInLink)).click();

		driver.quit();
	}

	// LOCATORS
	public static String webview = "WEBVIEW_com.catamaranrx.briovarx";
	public static By LoginButton = By.id("btnLogin");
	public static By SignInLink = By.partialLinkText("Sign In");
}