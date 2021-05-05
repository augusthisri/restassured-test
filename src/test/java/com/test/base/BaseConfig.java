package com.test.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseConfig {

	public static Logger logger;
	
	public static Response getRequest (String requestUri) {
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.get(requestUri);
		return response;
	}

	public static Response postRequest (String requestUri,  String requestJSON) {
		RequestSpecification requestSpecification = RestAssured.given().body(requestJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.post(requestUri);
		return response;
	}
	
	public static Response putRequest (String requestUri,  String requestJSON) {
		RequestSpecification requestSpecification = RestAssured.given().body(requestJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.put(requestUri);
		return response;
	}
	
	public static Response deleteRequest (String requestUri) {
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.delete(requestUri);
		return response;
	}

	@BeforeClass
	public void setupLogger() {
		logger = Logger.getLogger("reqresAPI"); // added Logger
		PropertyConfigurator.configure("log4j.properties"); // added logger
		logger.setLevel(Level.DEBUG);

	}
}
