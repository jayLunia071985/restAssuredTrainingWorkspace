package day4_ValidatingJSONResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Properties;
import java.io.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ValidateJsonResonseUsingTestNGAssertion {
	
	@Test()
	void testJsonResonseUsingTestNGAssertion() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day4a.properties");
		
		p.setProperty("baseUrl", "http://localhost:3000/comments");
		p.setProperty("body", "Validating JSON Response");
		p.setProperty("postId", "200");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		String body = p.getProperty("body");
		String postId = p.getProperty("postId");
		
		HashMap data=new HashMap();
		data.put("body", body);
		data.put("postId", postId);
		
		Response res = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post(baseUrl);
		
		/*Applying Assertion Directly*/
		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		Assert.assertEquals(res.jsonPath().getString("body"), "Validating JSON Response");
		Assert.assertEquals(res.jsonPath().get("body").toString(), "Validating JSON Response");
		Assert.assertEquals(res.jsonPath().get("body"), "Validating JSON Response");
		
		/*Storing Response in Variable and then applying Assertion*/
		JsonPath json = res.jsonPath();
		String bodyFromResponse = json.getString("body");
		String postIdFromResponse = json.getString("postId");
		
		Assert.assertEquals(bodyFromResponse, body);
		Assert.assertEquals(postIdFromResponse, postId);
	}
}
	
	
