package com.briovarx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInTest extends BriovaRxTestBase
{
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

	@Test
	public void SignInSimple()
	{
		driver.context(Native.context);

		whenPresent(Native.HomePage);

		driver.findElementByAccessibilityId("Select Login").click();
		driver.findElementByAccessibilityId("Sign In").click();

		driver.findElementById("btnLogin");

	}

	@Test
	public void goToSignInPage()
	{
		System.out.println("context: " + driver.getContext());
		System.out.println("contexts: " + driver.getContextHandles());

		wait.until(ExpectedConditions.presenceOfElementLocated(Native.background));


		System.out.println("context: " + driver.getContext());
		System.out.println("contexts: " + driver.getContextHandles());

		pause(30);

		driver.context(Native.context);
		wait.until(ExpectedConditions.elementToBeClickable(Native.LoginButton)).click();

		pause(30);

		System.out.println("context: " + driver.getContext());
		System.out.println("contexts: " + driver.getContextHandles());

		wait.until(ExpectedConditions.elementToBeClickable(Native.SignInLink)).click();

		pause(30);

		System.out.println("context: " + driver.getContext());
		System.out.println("contexts: " + driver.getContextHandles());
	}
}
