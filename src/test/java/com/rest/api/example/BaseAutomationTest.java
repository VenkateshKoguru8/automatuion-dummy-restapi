package com.rest.api.example;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAutomationTest {

	protected Response response = null;

	protected RequestSpecification request = null;

	protected RequestSpecification negativeRequest = null;

	protected static Properties expectedAssertionsProp = null;

	protected Properties testDataProp = null;

	protected static File reqJSON = null;

	protected static File resJSON = null;

	protected static String baseURI = null;

	private static final Logger logger = Logger.getLogger(BaseAutomationTest.class);

	public Response authV1Response(String clientId, String clientSecret, String grantType) {

		Response resp = given().formParam("client_id", clientId).formParam("Client Secret", clientSecret)
				.formParam("grant_type", grantType).post("token");

		return resp;
	}
	public String randomNumber(int digits) {
		logger.info("Starting of randomNumber method");
		logger.info("Ending of randomNumber method");

		return String.valueOf(RandomStringUtils.randomNumeric(digits));
	}

	public String getUniqueCharString(int length) {
		logger.info("Starting of CharatorString method");

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		logger.info("Random String is: " + randomString);

		logger.info("Starting of CharatorString method");

		return randomString;
	}
	public String getUniqueDoubleString(int length) {
		logger.info("Starting of getUniqueDoubleString method");

		String alphabet = "7.87";

		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		logger.info("Random String is: " + randomString);

		logger.info("Starting of getUniqueDoubleString method");

		return randomString;

	}


	@BeforeSuite
	public void initTestData() {
		FileReader assertionsReader = null;
		FileReader testDataReader = null;

		try {
			testDataReader = new FileReader("src/main/resources/testdata.properties");
			assertionsReader = new FileReader("src/main/resources/expectedassertions.properties");

			testDataProp = new Properties();
			testDataProp.load(testDataReader);

			expectedAssertionsProp = new Properties();
			expectedAssertionsProp.load(assertionsReader);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				assertionsReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void getReqFile(String path) {

		reqJSON = new File(path);

	}

	public void getResFile(String path) {

		resJSON = new File(path);

	}

	@BeforeMethod
	public void setRequestHeaders() {

		request = given().header("Connection", "keep-alive").header("Host", "<calculated when request is sent>")
				.header("accept", "*/*");

	}

}
