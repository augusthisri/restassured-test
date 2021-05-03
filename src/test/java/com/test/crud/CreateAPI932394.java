package com.test.crud;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;

public class CreateAPI932394 extends BaseConfig {

	@BeforeClass
	void createUser() {

		logger.info("*********Started CreateAPI932394 **********");
		// Specifying request Payload in JSON format
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eve.holt@reqres.in");
		requestParams.put("password", "cityslicka");

		// Specify body type is Json/content type
		reqSpec.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		reqSpec.body(requestParams.toJSONString());

		// POST request
		response = RestAssured.given().spec(reqSpec).basePath("api/login").body(requestParams).post();

	}

	@Test
	void checkResponseBody() {
		logger.info("***********  Checking Response Body **********");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkStatusCode() {
		logger.info("***********  Checking Status Code **********");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkstatusLine() {
		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkContentType() {
		logger.info("***********  Checking Content Type **********");

		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	void checkserverType() {
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "cloudflare");
	}

	@Test
	void checkContentLenght() {
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength) < 150);
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished CreateAPI932394 **********");
	}
}
