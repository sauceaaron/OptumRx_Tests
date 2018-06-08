package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class BriovaRxSauceEmulator extends BriovaRxTestBase
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	@Test
	public void run() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion",         "1.8.0");
		capabilities.setCapability("platformName",          "Android");
		capabilities.setCapability("platformVersion",       "7.0");
		capabilities.setCapability("deviceName",            "Samsung Galaxy S6 GoogleAPI Emulator");
		capabilities.setCapability("deviceName",            "Samsung Galaxy S6 GoogleAPI Emulator");
		capabilities.setCapability("deviceOrientation",     "portrait");
		capabilities.setCapability("browserName",           "");
		capabilities.setCapability("app",                   "sauce-storage:Brx.apk");
		capabilities.setCapability("appPackage",            "com.catamaranrx.briovarx");
		capabilities.setCapability("appActivity",           "SplashActivity");

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

