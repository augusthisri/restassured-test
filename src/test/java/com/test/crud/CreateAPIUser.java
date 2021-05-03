package com.test.crud;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;

public class CreateAPIUser extends BaseConfig {

	@BeforeClass
	void createUser() {

		logger.info("*********Started CreateAPIUser **********");
		// Specifying request Payload in JSON format
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "morpheus");
		requestParams.put("job", "leader");

		// Specify body type is Json/content type
		reqSpec.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		reqSpec.body(requestParams.toJSONString());

		// POST request
		response = RestAssured.given().spec(reqSpec).basePath("/users").body(requestParams).post();

	}

	@Test
	void checkStatusCode() {
		logger.info("***********  Checking Status Code **********");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 404);

	}

	@Test
	void checkstatusLine() {
		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 404 Not Found");

	}
//	@Test
//	void checkResponseBody1() {
//		String responseBody = response.getBody().asString();
//		logger.info("Response Body==>" + responseBody);
//
//		logger.info("***********  Checking Response Body **********");
//	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished CreateAPIUser **********");
	}
}
