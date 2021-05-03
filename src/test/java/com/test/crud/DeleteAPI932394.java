package com.test.crud;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;

public class DeleteAPI932394 extends BaseConfig {

	@BeforeClass
	public void testDelUsers() {
		logger.info("*********  Starting DeleteAPI932394 **********");
		
		response = RestAssured.given().spec(reqSpec).basePath("api/users/1").delete();
	}

	@Test
	void checkStatusCode() {
		logger.info("***********  Checking Status Code **********");

		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); // 200
		Assert.assertEquals(statusCode, 204);

	}

	@Test
	void checkContentLength() {
		logger.info("*********** Content-Length **********");

		String contentLength = response.header("Content-Length");
		logger.info("content Length is ==>" + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished DeleteAPI932394 **********");
	}
}
