package com.makaia.uibank.tests;

import org.testng.annotations.Test;

import com.makaia.testng.hooks.UiBankHooks;
import com.makaia.uibank.api.services.AccountService;
import com.makaia.uibank.api.services.AuthService;
import com.makaia.uibank.pages.LoginPage;

public class UIBankTest extends UiBankHooks {
	
	private static String nickName = "TEST Nick Name";	
	
	@Test
	public void userShouldAbleToLogin() {
		new LoginPage()
		    .enterUsername("FebApiuser") 
		    .enterPassword("Eagle@123")
		    .clickLoginButton()
		    .clickOnThePrivacyPolicyButton()
		    .clickApplyNewAccountButton()
		    .enterNickname(nickName)
		    .selectAccountTypeAsSavings()
		    .clickOnTheApplyButton()
		    .validateAccountCreation();
	}
	
	@Test
	public void validateAccountCreatedInBackend() {
		String id = new AuthService().createToken().extractId();
		new AccountService().fetchAllAccounts(id).validateAccountIsPresent(nickName);
	}

}