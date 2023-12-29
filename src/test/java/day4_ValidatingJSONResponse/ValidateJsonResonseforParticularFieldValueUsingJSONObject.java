package day4_ValidatingJSONResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ValidateJsonResonseforParticularFieldValueUsingJSONObject {
	
	@Test()
	void testJsonResponseforParticularFieldValue() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day4b.properties");
	
		p.setProperty("baseUrl", "http://localhost:3000/comments");
		p.setProperty("postId", "200");
		p.setProperty("body", "Validating JSON Response");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		String postId = p.getProperty("postId");
		String body = p.getProperty("postId");
		
		Response res = given()
			.contentType("application/json")
		
		.when()
			.get(baseUrl);
		
		/*ALSO REFER ValidateJsonResonseforParticularFieldValueUsingJSONObjectAndExtJSONFile TEST*/
		
		/*Sample JSON OBJECT*/
		/*
		{
			"books": [
				  {
				    "postId": "1",
				    "body": "Added Via HashMap",
				    "id": 1
				  },
				  {
				    "postId": "2",
				    "body": "java.util.HashMap",
				    "id": 2
				  },
				  {
				    "postId": "3",
				    "body": "Added Via HashMap",
				    "id": 3
				  },
				  {
				    "postId": "4",
				    "body": "java.util.HashMap",
				    "id": 4
				  }
			  ]
		}*/
		
		
		/*For Above JSON Object if we need to Print all body field value from JSON Object*/
		/*Refer from @29mins*/
		/*YOU TUBE: https://www.youtube.com/watch?v=5fWDqLFbJnA&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=4*/
		
		JSONObject jo = new JSONObject(res.toString()); //converting Response to Json Object
		
		for(int i=0;i<jo.getJSONArray("books").length();i++) {
			String bodyFromResponse = jo.getJSONArray("Books").getJSONObject(i).get("body").toString();
			System.out.print(bodyFromResponse);		
		}		
	}

}
