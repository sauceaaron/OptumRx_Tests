package com.optumrx.test;

import com.saucelabs.saucerest.SauceREST;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

public class OptumRxTestBase
{
	public static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY"); // set environment variable
	public static final String TESTOBJECT_ENDPOINT_US = "https://us1.appium.testobject.com/wd/hub";

	protected AppiumDriver driver;
	protected WebDriverWait wait;

	@Before
	public void setup() throws MalformedURLException
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

		driver = new AppiumDriver(appiumURL, capabilities);
		driver.manage().timeouts().implicitlyWait(30, SECONDS);
		wait = new WebDriverWait(driver, 60);
	}

	@After
	public void teardown()
	{
		driver.quit();
	}


	// HELPER FUNCTIONS

	protected String switchToWebView()
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
}