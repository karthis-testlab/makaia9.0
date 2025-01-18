package com.makaia.testng.hooks;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static com.makaia.general.utils.PropertiesHandler.*;

import java.io.File;
import java.io.IOException;

import com.makaia.jira.services.IssueService;
import com.makaia.selenium.api.base.SeleniumBase;

public class UiBankHooks extends SeleniumBase {
	
	@BeforeMethod
	public void beforeMethod() {
		browserLaunch("chrome");
		loadUrl(config("uibank.frontend.url"));
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (!result.isSuccess()) {
			File src = getDriver().getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(src, new File("./images/" + result.getName() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			new IssueService()
		    .createBugIssue("[Bug] - Automatio Script - "+result.getMethod().getMethodName())
		    .extractIssueId()
		    .testEvidenceAttachment("./images/" + result.getName() + ".png");
		}

		//quit();
	}

}