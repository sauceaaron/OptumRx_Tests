package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class BriovaRxLocalEmulator extends BriovaRxTestBase
{
	@Test
	public void switch_context() throws MalformedURLException
	{
		URL url = new URL("http://localhost:4723/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", "1.8.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("deviceName", "Pixel");
		capabilities.setCapability("app", "/Users/aaronevans/Downloads/Brx.apk");
		capabilities.setCapability("appPackage", "com.catamaranrx.briovarx");
		capabilities.setCapability("appActivity", "SplashActivity");
		capabilities.setCapability("automationName", "uiautomator2");

		AppiumDriver driver = new AndroidDriver(url, capabilities);
		WebDriverWait wait = new WebDriverWait(driver, 60);

		System.out.println("available contexts: " + driver.getContextHandles());
		System.out.println("context before switching: " + driver.getContext());

		wait.until(ExpectedConditions.elementToBeClickable(HomeScreen.Native.LoginButton)).click();
		driver.context("WEBVIEW_com.catamaranrx.briovarx");

		System.out.println("context after switching: " + driver.getContext());


		driver.quit();
	}
}

