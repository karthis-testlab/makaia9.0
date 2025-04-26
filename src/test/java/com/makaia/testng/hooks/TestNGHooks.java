package com.makaia.testng.hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.makaia.jira.services.IssueService;
import com.makaia.selenium.api.base.SeleniumBase;

import io.qameta.allure.Allure;

public class TestNGHooks extends SeleniumBase {

	protected static String incidentNumber;

	@BeforeMethod
	public void beforeMethod() {
		browserLaunch("chrome");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (!result.isSuccess()) {
			
			try {
				Allure.addAttachment("Failure Screen Snapshot", FileUtils
						.openInputStream(new File(takeSnap(result.getName()))));
			} catch (IOException e) {				
				e.printStackTrace();
			}

			// When UI Test got failed, Bug ticket will create in the JIRA with screenshot as attachment
			
			new IssueService()
			   .createBugIssue("[BUG] TS Case Failed!")
			   .extractIssueId()
			   .testEvidenceAttachment(takeSnap(result.getName()));
		}

		quit();
	}

}