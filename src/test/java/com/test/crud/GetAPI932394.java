package com.test.crud;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.BaseConfig;

import io.restassured.path.json.JsonPath;

public class GetAPI932394 extends BaseConfig {

	String requestUri = "https://reqres.in/api/users";

	@Test
	void getUserAssertion() {

		logger.info("*********Started GetAPI932394 **********");
		logger.info("***********  Checking Response Body **********");

		String responseBody = BaseConfig.getRequest(requestUri).getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);

		logger.info("***********  Checking Response Body **********");

		JsonPath responseJson = BaseConfig.getRequest(requestUri).getBody().jsonPath();

		logger.info("Response Body==>" + responseJson.prettify());
		logger.info("Page ==>" + responseJson.get("page"));

		Assert.assertEquals(responseJson.get("page").toString(), "1");
		Assert.assertEquals(responseJson.get("per_page").toString(), "6");
		Assert.assertEquals(responseJson.get("total").toString(), "12");
		Assert.assertEquals(responseJson.getList("data").size(), 6);

		List<Object> responseDataList = responseJson.getList("data");
		responseDataList.stream().forEach(System.out::println);
		 
	
		int statusCode = BaseConfig.getRequest(requestUri).getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

		logger.info("***********  Checking Status Line **********");

		String statusLine = BaseConfig.getRequest(requestUri).getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		logger.info("*********  Finished GetAPI932394 **********");
	}
}
