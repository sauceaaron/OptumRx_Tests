package com.optumrx.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class RegisterScreen extends MobileObject
{
	public RegisterScreen(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}

	// LOCATORS

	public static By heading  = By.xpath("//*[@id='header']/h1/strong");
	public static By firstNameField = By.id("piFirstName");
	public static By lastNameField = By.id("piLastName");
	public static By dateOfBirthField = By.id("piDoB");
	public static By zipCodeField = By.id("piZipCode");
	public static By memberIdField = By.id("piMemberId4Rx");
	public static By continueButton = By.xpath("//*[contains(@content-desc, 'Continue')]");
	public static By errorMessage = By.xpath("//*[contains(@content-desc, 'Error')]");

	public static class WebLocators
	{
		public static By heading  = By.xpath("//*[@id='header']/h1/strong");
		public static By firstNameField = By.id("piFirstName");
		public static By lastNameField = By.id("piLastName");
		public static By dateOfBirthField = By.id("piDoB");
		public static By zipCodeField = By.id("piZipCode");
		public static By memberIdField = By.id("piMemberId4Rx");
		public static By continueButton = By.xpath("//*[contains(@content-desc, 'Continue')]");
		public static By errorMessage = By.xpath("//*[contains(@content-desc, 'Error')]");
	}

	public static class NativeLocators
	{
		public static By PageIdentifier = MobileBy.AccessibilityId("HealthSafe ID - Registration – step1");
		public static By Heading = MobileBy.AccessibilityId("HealthSafe ID");
		public static By FirstNameField = MobileBy.id("piFirstName");
		public static By FirstNameMessage = MobileBy.xpath("//android.view.View[starts-with(@content-desc, 'Be sure your first name')]");
		public static By LastNameField = MobileBy.id("piLastName");
		public static By LastNameMessage = MobileBy.xpath("//android.view.View[starts-with(@content-desc, 'Be sure your last name')]");
		public static By DateOfBirthField = MobileBy.id("piDoB");
		public static By ZipCodeField = MobileBy.id("piZipCode");
		public static By MemberIdField = MobileBy.id("piMemberId4Rx");
		public static By ContinueButton = MobileBy.AccessibilityId("Continue");
		public static By ErrorMessage = MobileBy.xpath("//*[contains(@content-desc, 'Error')]");
	}

	// ACCESSORS

	// FIRST NAME

	public MobileElement firstNameField()
	{
		return find(firstNameField);
	}

	public String getFirstName()
	{
		return firstNameField().getText();
	}

	public RegisterScreen setFirstName(String firstName)
	{
		firstNameField().sendKeys(firstName);
		return this;
	}

	// LAST NAME

	public MobileElement lastNameField()
	{
		return find(lastNameField);
	}

	public String getLastName()
	{
		return lastNameField().getText();
	}

	public RegisterScreen setLastName(String lastName)
	{
		lastNameField().sendKeys(lastName);
		return this;
	}

	// DATE OF BIRTH

	public MobileElement dateOfBirthField()
	{
		return find(dateOfBirthField);
	}

	public String getDateOfBirth()
	{
		return dateOfBirthField().getText();
	}

	public RegisterScreen setDateOfBirth(String dateOfBirth)
	{
		dateOfBirthField().sendKeys(dateOfBirth);
		return this;
	}


	// ZIP CODE

	public MobileElement zipCodeField()
	{
		return find(zipCodeField);
	}

	public String getZipCode()
	{
		return zipCodeField().getText();
	}

	public RegisterScreen setZipCode(String zipCode)
	{
		zipCodeField().sendKeys(zipCode);
		return this;
	}

	// MEMBER ID


	public MobileElement memberIdField()
	{
		return find(memberIdField);
	}

	public String getMemberId()
	{
		return memberIdField().getText();
	}

	public RegisterScreen setMemberId(String memberId)
	{
		memberIdField().sendKeys(memberId);
		return this;
	}

	// ACTIONS
	public boolean isDisplayed()
	{
		try
		{
			waitFor(NativeLocators.PageIdentifier);
		}
		catch (TimeoutException e)
		{
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public RegisterScreen fillForm(String firstName, String lastName, String dateOfBirth, String zipCode, String memberId)
	{
		this.setFirstName(firstName)
				.setLastName(lastName)
				.setDateOfBirth(dateOfBirth)
				.setZipCode(zipCode)
				.setMemberId(memberId);

		return this;
	}

	public RegisterScreen clickContinue()
	{
		click(continueButton);
		return this;
	}

	public RegisterScreen register(String firstName, String lastName, String dateOfBirth, String zipCode, String memberId)
	{
		fillForm(firstName, lastName, dateOfBirth, zipCode, memberId);
		clickContinue();
		return this;
	}
}
