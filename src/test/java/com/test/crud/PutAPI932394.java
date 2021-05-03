package com.test.crud;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PutAPI932394 extends BaseConfig {

	@BeforeClass
	void postUser() {
		logger.info("*********Started PutAPI932394 **********");
		
		// Specifying request Payload in JSON format
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "morpheus");
		requestParams.put("LastName", "zion resident");

		// Specify body type is Json/content type
		reqSpec.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		reqSpec.body(requestParams.toJSONString());

		// POST request
		response = RestAssured.given().spec(reqSpec).basePath("api/users/2").body(requestParams).put();

	}

	@Test
	void checkResponseBody() {
		logger.info("***********  Checking Response Body **********");

		JsonPath responseJson = response.getBody().jsonPath();
		logger.info("Response Body==>" + response.getBody().asString());
		Assert.assertEquals(responseJson.get("FirstName"), "morpheus");
		Assert.assertEquals(responseJson.get("LastName"), "zion resident");

		logger.info("ressponseJson " + responseJson.prettify());
		logger.info("***********  Checking Response Body **********");
	}

	@Test
	void checkstatusLine() {
		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		String contentType = response.header("Content-Type");
		logger.info("Content-Type is ==>" + response.getHeader(contentType));
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished PutAPI932394 **********");
	}
}
