package com.test.crud;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.test.BaseConfig;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetAPI932394 extends BaseConfig {

	@BeforeClass
	public void testGetUsers() {
		logger.info("*********Started GetAPI932394 **********");
		response = RestAssured.given().spec(reqSpec).basePath("api/users").get();
	}

	@Test
	void checkResponseBody() {
		logger.info("***********  Checking Response Body **********");

		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkResponseBody1() {
		logger.info("***********  Checking Response Body **********");
		JsonPath responseJson = response.getBody().jsonPath();
		logger.info("Response Body==>" + responseJson.prettify());
		logger.info("Page ==>" + responseJson.get("page"));
		Assert.assertEquals(responseJson.get("page").toString(), "1");
		Assert.assertEquals(responseJson.get("per_page").toString(), "6");
		Assert.assertEquals(responseJson.get("total").toString(), "12");
		Assert.assertEquals(responseJson.getList("data").size(), 6);

		List<Object> responseDataList = responseJson.getList("data");
		responseDataList.stream().forEach(System.out::println);

	}

	@Test
	void checkStatusCode() {
		logger.info("***********  Checking Status Code **********");

		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); // 200
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkstatusLine() {
		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished GetAPI932394 **********");
	}
}
