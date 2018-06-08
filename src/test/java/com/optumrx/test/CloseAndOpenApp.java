package com.optumrx.test;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CloseAndOpenApp extends OptumRxTestBase
{
	@Test
	public void navigateToRegistration()
	{
		switchToWebView();

		wait.until(ExpectedConditions.elementToBeClickable(UpdateScreen.updateButton)).click();

		driver.closeApp();
		driver.launchApp();

		wait.until(ExpectedConditions.elementToBeClickable(WelcomeScreen.registerButton)).click();
		System.out.println("version: " + driver.findElement(WelcomeScreen.appVersion).getText());

		switchWindows();

		WebElement registration = wait.until(ExpectedConditions.visibilityOfElementLocated(RegisterScreen.heading));
		System.out.println(registration.getText());

	}
}
