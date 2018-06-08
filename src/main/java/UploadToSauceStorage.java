package com.briovarx.test;

import com.saucelabs.saucerest.SauceREST;

import java.io.File;
import java.io.IOException;

public class UploadToSauceStorage
{
	public static void main(String[] args) throws IOException
	{
		String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
		String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
		String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

		SauceREST api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);

		File apk = new File("/Users/aaronevans/clients/UHG/Brx.apk");
		api.uploadFile(apk, "BriovaRx.apk", true);

		String files = api.getStoredFiles();
		System.out.println(files);
	}
}
