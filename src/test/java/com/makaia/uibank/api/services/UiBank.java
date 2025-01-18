package com.makaia.uibank.api.services;

import static com.makaia.general.utils.PropertiesHandler.config;

import com.makaia.api.design.ResponseAPI;
import com.makaia.api.rest.assured.base.RequestSpecBuilder;
import com.makaia.api.rest.assured.base.RestAssuredBase;

import io.restassured.http.ContentType;

public class UiBank {
	
	protected ResponseAPI response;
	protected RestAssuredBase restAssured = new RestAssuredBase();	
	protected RequestSpecBuilder requestBuilder;	
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()				   
				   .setBaseUri(config("uibank.backend.base.uri"))	
				   .setBasePath(config("uibank.backend.base.path"))
				   .setContentType(ContentType.JSON)
				   .setAccept(ContentType.JSON);
	}

}