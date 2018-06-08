package com.briovarx.test;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class HomeScreen
{
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
