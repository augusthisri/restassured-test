package com.test.crud;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.BaseConfig;
import com.test.utils.TestUtils;

import io.restassured.response.Response;

public class SigninAPIUser932394 extends BaseConfig {

	String requestUri = "https://reqres.in/api/login";

	@Test
	public void createUser() {

		logger.info("*********Started CreateAPIUser **********");

		String filePath = "src/test/resources/createUser.json";

		Response response = BaseConfig.postRequest(requestUri, TestUtils.getPayload(filePath).toString());

		logger.info("***********  Checking Response Body **********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(400, statusCode);

		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");

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

}
