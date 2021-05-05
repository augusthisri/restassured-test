package com.test.crud;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.BaseConfig;

import io.restassured.response.Response;

public class DeleteAPI932394 extends BaseConfig {

	String requestUri = "https://reqres.in/api/users/2";

	@Test
	void deleteUserAssertion() {

		logger.info("*********Started DeleteAPI932394 **********");

		Response response = BaseConfig.deleteRequest(requestUri);

		int statusCode = BaseConfig.deleteRequest(requestUri).getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 204);

		logger.info("*********** Content-Length **********");

		String contentLength = response.header("Content-Length");
		logger.info("content Length is ==>" + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);

		logger.info("***********  Checking Response Body **********");

		logger.info("*********  Finished DeleteAPI932394 **********");

	}

}
