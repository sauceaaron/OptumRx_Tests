package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BriovaRxTestBase
{
	public static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY"); // set environment variable

	public static final String TESTOBJECT_ENDPOINT_US = "https://us1.appium.testobject.com/wd/hub";
	public static final String LOCAL_ENDPOINT = "http://localhost:4723/wd/hub";
	public static final String SAUCELABS_ENDPOINT = "";

	protected AppiumDriver driver;
	protected WebDriverWait wait;

	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	String LOCAL_URL = "http://localhost:4723/wd/hub";
	String TESTOBJECT_URL = "https://us1.appium.testobject.com/wd/hub";

	public void SetupLocalEmulator() throws MalformedURLException
	{
		URL appiumServer = new URL(LOCAL_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", "1.8.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("deviceName", "Pixel");
		capabilities.setCapability("app", "/Users/aaronevans/Downloads/Brx.apk");
		capabilities.setCapability("appPackage", "com.catamaranrx.briovarx");
		capabilities.setCapability("appActivity", "SplashActivity");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("autoWebView", true);

		driver = new AndroidDriver(appiumServer, capabilities);
		wait = new WebDriverWait(driver, 15);
	}

	public void setupTestObjectDevice() throws MalformedURLException
	{

	}

	public void setupSauceLabsEmulator() throws MalformedURLException
	{}


//	@Before
//	public void setup() throws MalformedURLException
//	{
//		URL appiumURL = new URL(TESTOBJECT_ENDPOINT_US);
//
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("testobject_api_key",    TESTOBJECT_API_KEY);
//		capabilities.setCapability("testobject_test_name",  "Sign In Test");
//		capabilities.setCapability("testobject_suite_name", "BriovaRx");
//		capabilities.setCapability("appiumVersion",         "1.8.0");
//		capabilities.setCapability("platformName",          "Android");
//		capabilities.setCapability("platformVersion",       "6.0.1");
//		capabilities.setCapability("deviceName",            "Samsung Galaxy S6");
//		capabilities.setCapability("appPackage",            "com.catamaranrx.briovarx");
//		capabilities.setCapability("appActivity",           "com.catamaranrx.briovarx.SplashActivity");
//
////		URL appiumURL = new URL(LOCAL_ENDPOINT);
//
////		DesiredCapabilities capabilities = new DesiredCapabilities();
////		capabilities.setCapability("appiumVersion",         "1.7.2");
////		capabilities.setCapability("platformName",          "Android");
////		capabilities.setCapability("platformVersion",       "8.0");
////		capabilities.setCapability("deviceName",            "Pixel");
////		capabilities.setCapability("app",                   "/Users/aaronevans/clients/UHG/BriovaRx-release.apk");
////		capabilities.setCapability("appPackage",            "com.catamaranrx.briovarx");
////		capabilities.setCapability("appActivity",           "SplashActivity");
//
//		driver = new AppiumDriver(appiumURL, capabilities);
////		driver.manage().timeouts().implicitlyWait(30, SECONDS);
//		wait = new WebDriverWait(driver, 60);
//	}

	@After
	public void teardown()
	{
		driver.quit();
	}


	// HELPER FUNCTIONS

	protected String switchContext(String contextHandle)
	{
		getContexts();
		driver.context(contextHandle);
		return driver.getContext();
	}

	protected String switchToNativeView()
	{
		getContexts();
		driver.context("NATIVE_APP");
		return driver.getContext();
	}

	public Set<String> getContexts()
	{
		System.out.println("currentContext: " + driver.getContext());

		Set<String> contextHandles = driver.getContextHandles();
		System.out.println("contextHandles: " + contextHandles);

		return contextHandles;
	}

	protected String switchToWebView()
	{
		for (String contextHandle : getContexts())
		{
			if (contextHandle.startsWith("WEBVIEW"))
			{
				driver.context(contextHandle);
			}
		}

		return driver.getContext();
	}


	protected String switchWindows()
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

	protected void pause(long seconds)
	{
		try
		{
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public WebElement whenPresent(By locator)
	{
		System.out.println("whenPresent: " + locator);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement whenActive(By locator)
	{
		System.out.println("whenPresent: " + locator);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement moveTo(WebElement element)
	{
		System.out.println("moveTo: " + element);

		new TouchAction(driver).moveTo(element).perform();
		return element;
	}

	public WebElement find(By locator)
	{
		System.out.println("find: " + locator);
		WebElement element = whenPresent(locator);
		System.out.println("found: " + element);

		if (! element.isDisplayed())
		{
			moveTo(element);
		}

		return element;
	}

	// LOCATORS
	public static class Native
	{
		public static String context = "NATIVE_APP";

		public static By background = MobileBy.id("android:id/navigationBarBackground");
		public static By LoginButton = MobileBy.AccessibilityId("Select Login");
		public static By SignInLink = MobileBy.AccessibilityId("Sign In");
		public static By HomePage = MobileBy.AccessibilityId("Home Page | BriovaRx®");

	}

	public static class Webview
	{
		public static String context = "WEBVIEW_com.catamaranrx.briovarx";

		public static By copyright = By.cssSelector(".copyright");
		public static By LoginButton = By.id("btnLogin");
		public static By SignInLink = By.partialLinkText("Sign In");

	}
}