package com.test.crud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.BaseConfig;

import io.restassured.response.Response;

public class CreateAPI932394 extends BaseConfig {

	String requestUri = "https://reqres.in/api/users/2";

	@Test
	public void createUserAasertion1() {

		logger.info("*********Started CreateAPIUser **********");

		StringBuffer str = new StringBuffer();
		File file = new File("src/test/resources/data.json");
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = in.readLine()) != null) {
				str.append(line);
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Input Json Data::: " + str.toString());
		Response response = BaseConfig.postRequest(requestUri, str.toString());

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
	public void createUserAasertion2() {
		logger.info("*********Started CreateAPIUser **********");

		StringBuffer str = new StringBuffer();
		File file = new File("src/test/resources/putData2.json");
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = in.readLine()) != null) {
				str.append(line);
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Input Json Data::: " + str.toString());
		Response response = BaseConfig.postRequest(requestUri, str.toString());

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
