package com.base.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseConfig {

	public static RequestSpecification reqSpec;
	public static Response response;
	public Logger logger;

	@BeforeClass
	public static void setup() {
		reqSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
	}

	@BeforeClass
	public void setupLogger() {
		logger = Logger.getLogger("reqresAPI"); // added Logger
		PropertyConfigurator.configure("log4j.properties"); // added logger
		logger.setLevel(Level.DEBUG);

	}
}
