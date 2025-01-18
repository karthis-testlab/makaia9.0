package com.makaia.uibank.api.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;

public class AccountService extends UiBank {
	
	public AccountService() {
		requestBuilder = globalRequest();
	}
	
	public AccountService fetchAllAccounts(String token) {
		response = restAssured.get(requestBuilder
				                   .setHeaderKey("Authorization")
				                   .setHeaderValue("Bearer "+token)
				                   .build(), 
				                   "accounts?filter[where][userId]=64290731ba9f8a0047adacfc");
		return this;
	}
	
	public AccountService validateAccountIsPresent(String expect) {
		JSONArray array = new JSONArray(response.getBody());
		String actual = array.getJSONObject(array.length() - 1).getString("friendlyName");
		MatcherAssert.assertThat(actual, Matchers.equalTo(expect));
		return this;
	}

}