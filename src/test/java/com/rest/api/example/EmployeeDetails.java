package com.rest.api.example;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class EmployeeDetails extends BaseAutomationTest {

	private static final Logger logger = Logger.getLogger(EmployeeDetails.class.getName());
	
	

	@BeforeClass
	@Parameters("baseURI")
	public void initAuthentication(String baseURI) {
		logger.info("Starting of initAuthentication method");
		RestAssured.baseURI = baseURI;
		logger.info("Ending of initAuthentication method");
	}

	@Test(priority = 1, description = "Verify postCreate ")
	public void testPostCreate() {
		logger.info("Starting of postCreate method");

		this.getReqFile(testDataProp.getProperty("postCreate.req.body.path.text"));
		
		this.getResFile(testDataProp.getProperty("postCreate.res.body.path.text"));

		request.when().post("create");
		
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);
		
		
		logger.info("Ending of postCreate method");
	}

	@Test(priority = 2, description = "Verify putUpdateId ")
	public void testPutUpdateId() {
		logger.info("Starting of putUpdateId method");

		this.getResFile(testDataProp.getProperty("putUpdateId.res.body.path.text"));

		request.when().put("update/21");
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of putUpdateId method");
	}

	@Test(priority = 3, description = "Verify getEmployeeDetails ")
	public void testGetEmployeeDetails() {
		logger.info("Starting of testGetEmployeeDetails method");

		this.getResFile(testDataProp.getProperty("getEmployeeDetails.res.body.path.text"));

		request.when().get("employee");
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of testGetEmployeeDetails method");
	}

	@Test(priority = 4, description = "Verify getEmployeeId ")
	public void testGetEmployeeId() {
		logger.info("Starting of getEmployeeId method");

		this.getResFile(testDataProp.getProperty("getEmployeeId.res.body.path.text"));

		request.when().get("employee/1");
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of getEmployeeId method");
	}

	@Test(priority = 5, description = "Verify deleteId ")
	public void testDeleteId() {
		logger.info("Starting of deleteId method");

		this.getResFile(testDataProp.getProperty("deleteId.res.body.path.text"));

		request.when().delete("delete/2");
		
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of deleteId method");
	}
	
}
