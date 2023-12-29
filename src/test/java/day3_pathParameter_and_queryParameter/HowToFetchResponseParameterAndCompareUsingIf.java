package day3_pathParameter_and_queryParameter;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;
import java.util.Properties;
import java.io.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class HowToFetchResponseParameterAndCompareUsingIf {
	
	@Test()
	void testJSONResponse() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day3a.properties");
		
		p.setProperty("baseUrl", "http://localhost:3000/comments");
		p.setProperty("body", "Validating JSON Response");
		p.setProperty("postId", "200");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		String body = p.getProperty("body");
		String postId = p.getProperty("postId");
		
		System.out.print("body From File is:" +body+'\n');
		System.out.print("postId From File is:" +postId+'\n');
		
		HashMap data=new HashMap();
		data.put("body", body);
		data.put("postId", postId);
		
		
		Response res = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post(baseUrl);
		
		
		JsonPath jsonPath = res.jsonPath();
		
		String bodyFromResponse = jsonPath.getString("body").toString();
		String postIdFromResponse = jsonPath.getString("postId").toString();

		
		System.out.print("body From Response is:" +bodyFromResponse+'\n');
		System.out.print("postId From Response is:" +postIdFromResponse+'\n');
		
		if(bodyFromResponse.equals(body))
		{
			System.out.print("body is Validated in Response"+'\n');
		}
		else {
			System.out.print("body validation FAILED"+'\n');
		}
		
		if(postIdFromResponse.equals(postId))
		{
			System.out.print("postId is Validated in Response"+'\n');
		}
		else {
			System.out.print("postId validation FAILED"+'\n');
		}	
		
	}
}
