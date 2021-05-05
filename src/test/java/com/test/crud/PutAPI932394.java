package com.test.crud;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.BaseConfig;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutAPI932394 extends BaseConfig {

	String requestUri = "https://reqres.in/api/users/2";

	@Test
	public void putUserAssertion() {

		logger.info("*********Started PutAPI932394  **********");

		// Specifying request Payload in JSON format
		JSONObject str = new JSONObject();
		str.put("FirstName", "morpheus");
		str.put("LastName", "zion resident");

		logger.info("Input Json Data::: " + str.toString());
		Response response = BaseConfig.putRequest(requestUri, str.toString());

		logger.info("***********  Checking Response Body **********");

		JsonPath responseJson = response.getBody().jsonPath();
		logger.info("Response Body==>" + response.getBody().asString());
		Assert.assertEquals(responseJson.get("FirstName"), "morpheus");
		Assert.assertEquals(responseJson.get("LastName"), "zion resident");

		logger.info("ressponseJson " + responseJson.prettify());
		logger.info("***********  Checking Response Body **********");

		logger.info("***********  Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		String contentType = response.header("Content-Type");
		logger.info("Content-Type is ==>" + response.getHeader(contentType));
	}


}
