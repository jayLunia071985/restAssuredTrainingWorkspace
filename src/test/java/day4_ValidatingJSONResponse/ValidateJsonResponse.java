package day4_ValidatingJSONResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Properties;
import java.io.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class ValidateJsonResponse {
	
	@Test()
	void testJSONResponse() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day4.properties");
		
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
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post(baseUrl)
			
		.then()
			.statusCode(201)
			.log().body()
			.body("body", equalTo("Validating JSON Response"));
	}
}
