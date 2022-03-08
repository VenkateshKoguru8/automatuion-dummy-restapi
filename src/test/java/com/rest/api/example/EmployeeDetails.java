package com.rest.api.example;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

@Epic("CreateAndDeleteEmployeeDetails")
@Feature("EmployeeDetails")
public class EmployeeDetails extends BaseAutomationTest {

	private static final Logger logger = Logger.getLogger(EmployeeDetails.class.getName());
	
	@BeforeClass
	@Parameters("baseURI")
	public void initAuthentication(String baseURI) {
		logger.info("Starting of initAuthentication method");
		RestAssured.baseURI = baseURI;
		logger.info("Ending of initAuthentication method");
	}
	
	@Test(priority = 1, description = "Verify Create new record in database ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Successfully Create new record in database")
	@Story("Successfully Create new record in database")
	public void testPostCreate() {
		logger.info("Starting of postCreate method");

		this.getReqFile(testDataProp.getProperty("postCreate.req.body.path.text"));
		
		this.getResFile(testDataProp.getProperty("postCreate.res.body.path.text"));

		request.when().post("create");
		
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);
		
		
		logger.info("Ending of postCreate method");
	}
	
	@Test(priority = 2, description = "Verify Update an employee record")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Successfully Update an employee record")
	@Story("Successfully Update an employee record")
	public void testPutUpdateId() {
		logger.info("Starting of putUpdateId method");

		this.getResFile(testDataProp.getProperty("putUpdateId.res.body.path.text"));

		request.when().put("update/21");
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of putUpdateId method");
	}

	@Test(priority = 3, description = "Verify single employee data")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Successfully Get a single employee data")
	@Story("Successfully Get a single employee data")
	public void testGetEmployeeId() {
		logger.info("Starting of getEmployeeId method");

		this.getResFile(testDataProp.getProperty("getEmployeeId.res.body.path.text"));

		request.when().get("employee/1");
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of getEmployeeId method");
	}
	
	@Test(priority = 4, description = "Verify all employee data")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Successfully Get all employee data")
	@Story("Successfully Get all employee data")
	public void testGetEmployeeDetails() {
		logger.info("Starting of testGetEmployeeDetails method");

		this.getResFile(testDataProp.getProperty("getEmployeeDetails.res.body.path.text"));

		request.when().get("employee");
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of testGetEmployeeDetails method");
	}
	
	@Test(priority = 5, description = "Verify Delete an employee record")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Successfully Delete an employee record")
	@Story("Successfully Delete an employee record")
	public void testDeleteId() {
		logger.info("Starting of deleteId method");

		this.getResFile(testDataProp.getProperty("deleteId.res.body.path.text"));

		request.when().delete("delete/2");
		
		request.then().body(matchesJsonSchema(resJSON)).log().all().statusCode(200);

		logger.info("Ending of deleteId method");
	}
	
}
