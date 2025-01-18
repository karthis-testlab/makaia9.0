package com.makaia.uibank.api.services;

import org.json.JSONObject;

public class AuthService extends UiBank {
	
	public AuthService() {
		requestBuilder = globalRequest();
	}
	
	public AuthService createToken() {
		String request_paylod = """
				{
				  "username": "FebApiuser",
				  "password": "Eagle@123"
				}
				""";
		response = restAssured.post(requestBuilder.build(), "users/login", request_paylod);
		return this;
	}
	
	public String extractId() {
		System.out.println(response.getBody());
		return new JSONObject(response.getBody()).getString("id");
	}

}