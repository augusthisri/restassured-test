package com.test.crud;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PostAPI932394 extends BaseConfig {

	@BeforeClass
	void postUser() {

		logger.info("*********Started PostAPI932394 **********");

		// Specifying request Payload in JSON format
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Johnson");
		requestParams.put("LastName", "Xavier");

		// Specify body type is Json/content type
		reqSpec.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		reqSpec.body(requestParams.toJSONString());

		// POST request
		response = RestAssured.given().spec(reqSpec).basePath("api/users/2").body(requestParams).post();

	}

	@Test
	void checkResponseBody() {
		logger.info("***********  Checking Response Body **********");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkResponseBody1() {
		logger.info("***********  Checking Response Body **********");
		JsonPath responseJson = response.getBody().jsonPath();
		logger.info("Response Body==>" + response.getBody().asString());
		Assert.assertEquals(responseJson.get("FirstName"), "Johnson");
		Assert.assertEquals(responseJson.get("LastName"), "Xavier");

		logger.info("ressponseJson " + responseJson.prettify());
		logger.info("***********  Checking Response Body **********");
	}

	@Test
	void checkStatusCode() {
		logger.info("***********  Checking Status Code **********");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 201);

	}

	@Test
	void checkstatusLine() {
		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");

	}

	@Test
	void checkContentType() {
		logger.info("***********  Checking Content Type **********");

		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished PostAPI932394 **********");
	}
}
