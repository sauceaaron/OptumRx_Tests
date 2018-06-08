package com.optumrx.test;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class OptumRx_NavigateToRegistration
{
	public static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY"); // set environment variable
	public static final String TESTOBJECT_ENDPOINT_US = "https://us1.appium.testobject.com/wd/hub";

	// LOCATORS

	public static final By updateButton = By.xpath("//*[@id='btnStart']");
	public static final By signInButton = By.id("hsIdSignInBtn");
	public static By registerButton = By.id("hsIdRegisterBtn");
	public static By appVersion = By.id("versionNbr");
	public static By registrationHeading = By.xpath("//*[@id='header']/h1/strong");

	public static void main(String[] args) throws MalformedURLException
	{
		URL appiumURL = new URL(TESTOBJECT_ENDPOINT_US);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("testobject_api_key",    TESTOBJECT_API_KEY);
		capabilities.setCapability("testobject_test_name",  "Simple Registration Test");
		capabilities.setCapability("testobject_suite_name", "OptumRx");
		capabilities.setCapability("appiumVersion",         "1.7.2");
		capabilities.setCapability("platformName",          "Android");
		capabilities.setCapability("platformVersion",       "6.0.1");
		capabilities.setCapability("deviceName",            "Samsung Galaxy S6");
		capabilities.setCapability("appPackage",            "com.optum.rx");
		capabilities.setCapability("appActivity",           "com.optum.rx.OptumRx");

		AppiumDriver driver = new AppiumDriver(appiumURL, capabilities);
//		driver.manage().timeouts().implicitlyWait(60, SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);

		switchToWebView(driver);

		wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
		System.out.println("version: " + driver.findElement(appVersion).getText());

		switchWindows(driver);

		WebElement registration = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationHeading));
		System.out.println(registration.getText());

		driver.quit();
	}

	// HELPER FUNCTIONS

	private static String switchToWebView(AppiumDriver driver)
	{
		Set<String> contextHandles = driver.getContextHandles();
		System.out.println("contextHandles: " + contextHandles);

		for (String contextHandle : contextHandles)
		{
			if (contextHandle.startsWith("WEBVIEW"))
			{
				driver.context(contextHandle);
			}
		}

		return driver.getContext();
	}

	private static String switchWindows(AppiumDriver driver)
	{
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("windowHandles: " + windowHandles);

		for (String windowHandle : windowHandles)
		{
			if (!windowHandle.equalsIgnoreCase(driver.getWindowHandle()))
			{
				driver.switchTo().window(windowHandle);
			}
		}

		return driver.getWindowHandle();
	}
}