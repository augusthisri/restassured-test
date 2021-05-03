package com.test.crud;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;

public class Crudvalidation extends BaseConfig {

	@BeforeClass
	void createUser() {

		logger.info("*********Started Crudvalidation **********");
		// Specifying request Payload in JSON format
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "");
		requestParams.put("password", "cityslicka");

		// Specify body type is Json/content type
		reqSpec.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		reqSpec.body(requestParams.toJSONString());

		// POST request
		response = RestAssured.given().spec(reqSpec).basePath("api/users?page=2").body(requestParams).post();

	}

	@Test
	void checkResponseBody() {
		logger.info("***********  Checking status  Code***");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 400);

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);

		logger.info("*********Finished Crudvalidation **********");

	}
}
