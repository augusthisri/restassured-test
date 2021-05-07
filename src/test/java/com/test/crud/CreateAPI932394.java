package com.test.crud;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.BaseConfig;
import com.test.utils.TestUtils;

import io.restassured.response.Response;

public class CreateAPI932394 extends BaseConfig {

	String createRequestUri = "https://reqres.in/api/users";
	String registerRequestUri = "https://reqres.in/api/register";

	@Test
	public void createUser() {

		String filePath = "src/test/resources/createUser.json";

		Response response = BaseConfig.postRequest(createRequestUri, TestUtils.getPayload(filePath).toString());

		logger.info("***********  Checking Response Body **********");
		logger.info("Response Body==>" + response.asString());
		Assert.assertTrue(response != null);

		logger.info("***********  Checking Status Code **********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 201);

		logger.info("***********  Checking Status Line **********");
		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");

		logger.info("***********  Checking Content Type **********");
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

		logger.info("***********  Checking Server Type **********");
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "cloudflare");

		logger.info("***********  Checking Content Length **********");
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength) < 150);
	}

	@Test
	public void registerUser() {

		logger.info("*********Started CreateAPIUser **********");

		String filePath = "src/test/resources/registerUser.json";

		Response response = BaseConfig.postRequest(registerRequestUri, TestUtils.getPayload(filePath).toString());

		logger.info("***********  Checking Response Body **********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 400);

		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
	}

}
