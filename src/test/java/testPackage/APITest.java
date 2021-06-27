package testPackage;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.core.IsEqual;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.Ordering;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import frameworkCommons.Commons;
import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;
import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

public class APITest extends Commons{
	ExtentReports report;
	ExtentTest extentReport;
	
	@BeforeSuite
	public void StartReport() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtendReport\\Reports\\"+getClass().getSimpleName()+".html");
	}
	

	@Parameters({"url","expStatusCode"})
	@Test(priority = 1)
	public void validateResponseSize(String url, String expStatusCode) {
		extentReport = report.startTest("StatusCodeValidation");
		try {
	          given().
			  when().
			       get(url).
			  then().
			      log().all().
			      assertThat().
			      statusCode(Integer.parseInt(expStatusCode)).
			      body("data", is(not(emptyArray())),
			    		  "data.size()", equalTo(20));	
			   extentReport.log(LogStatus.PASS, "Response Size validated Successfully, Status Code is "+get("https://gorest.co.in/public-api/posts").statusCode());
			}catch (Exception e) {
				extentReport.log(LogStatus.FAIL, "Response Size Validation Failed");
				}
		}

	
	@Parameters({"url", "expectedContentType", "expectedServerName","expectedContentEncoding",
		"expectedTransferEncoding","expectedProtectionType","expectedReferrerPolicy" })
	@Test(priority = 2)
	public void validateHeaders(String url, String expectedContentType, String expectedServerName,String expectedContentEncoding,String expectedTransferEncoding, String expectedProtectionType,String expectedReferrerPolicy)
	{
		extentReport = report.startTest("Header Validation");
		try {			
	     RequestSpecification httpRequest = RestAssured.given();
	     Response response = httpRequest.get(url);
	     String contentType = response.header("Content-Type");
	     String serverType =  response.header("Server");
	     String acceptLanguage = response.header("Content-Encoding");
	     String transferEncoding = response.header("Transfer-Encoding");
	     String protectionType =  response.header("X-XSS-Protection");
	     String referrerPolicy = response.header("Referrer-Policy");
	     Assert.assertEquals(expectedContentType, contentType,"Content Type is invalid - not matching with expected value"+expectedContentType);
	     Assert.assertEquals(expectedServerName, serverType,"server Type is invalid - not matching with expected value"+expectedServerName);
	     Assert.assertEquals(expectedContentEncoding,acceptLanguage ,"acceptLanguage is invalid - not matching with expected value"+expectedContentEncoding);
	     Assert.assertEquals(expectedTransferEncoding,transferEncoding,"Content Type is invalid - not matching with expected value"+expectedTransferEncoding );
	     Assert.assertEquals(expectedProtectionType,protectionType,"Protection Type is invalid - not matching with expected value"+expectedProtectionType );
	     Assert.assertEquals(expectedReferrerPolicy,referrerPolicy,"referrer Policy is invalid - not matching with expected value"+expectedReferrerPolicy);
	     extentReport.log(LogStatus.PASS, "Header validated Successfully. contentType is "+contentType+",serverType is "+serverType+", acceptLanguage is "+acceptLanguage+",transferEncoding"+ transferEncoding+", protectionType"+protectionType+"");
		}catch (Exception e) {
			extentReport.log(LogStatus.FAIL, "Header Validation Failed");
			}
	}

	@Parameters({"url"})
	@Test(priority = 3)
	public void validateUserIDInResponse(String url) {
		extentReport = report.startTest("User ID Validation");
		try {
			List<String> user_IdList = given()
			        .when()
			        .get(url)
			        .then()
			        .extract()
			        .jsonPath()
			        .getList("data.user_id");
			System.out.println(user_IdList);
			boolean isInAscOrder = isInOrder(user_IdList, Ordering.natural());			
			  extentReport.log(LogStatus.PASS, "UserID validated Successfully");
		}catch (Exception e) {
						extentReport.log(LogStatus.FAIL, "UserID Validation Failed");
						}
	}
	
	@Test(priority = 4)
	public void validateKeysRetrived() {
		extentReport = report.startTest("Keys Validation");
		try {
		given().
		when().
		       get("https://gorest.co.in/public-api/posts").
		then().
		      assertThat().
		      statusCode(200).
		      body("data[0]", hasKey("id"),
		    		  "data[1]", hasKey("user_id"),
		    		  "data[2]",hasKey("title"),
		    		  "data[3]",hasKey("body"),
		    		  "data[4]",hasKey("created_at"),
		    		  "data[5]",hasKey("updated_at"));	   
	       extentReport.log(LogStatus.PASS, "Keys validated Successfully");
			}catch (Exception e) {
				extentReport.log(LogStatus.FAIL, "Keys Validation Failed");
				}
	}
	
	
	
	@Test(priority = 5)
	public void validateResponseTitle() {
		extentReport = report.startTest("Response Title Validation");
		try {
		given().
		when().
		       get("https://gorest.co.in/public-api/posts").
		then().
		      assertThat().
		      body("data.title",notNullValue()); 	
		 extentReport.log(LogStatus.PASS, "Response Title validated Successfully");
		}catch (Exception e) {
			extentReport.log(LogStatus.FAIL, "Response Title Validation Failed");
			}
	}

	@AfterSuite()
	public void cleanReport() {
		report.endTest(extentReport);
		report.flush();
	}
}