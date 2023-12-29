package day1;

import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequest {
	
	int id;
	int newId;
	
	@Test (priority=3)
	void getUser(){
		
		given()
		
		.when()
			.get("http://localhost:3000/comments/"+id)
		
		.then()
			.statusCode(200)
			.body("body",equalTo("JoyUpdated"))
			.body("body",containsString("Joy"))
			.log().all();	
		
	}
	
	@Test (priority=1)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("body", "Joy");
		data.put("postId", "14");
		
				
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/comments")
			.jsonPath().getInt("id");
		
			
		/*.then()
			.statusCode(201)
			.log().all();*/
	}
	
	@Test (priority = 2)
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("body","JoyUpdated");
		data.put("postId",+id);		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("http://localhost:3000/comments/"+id)
		
		
		.then()
			.statusCode(200)
			.log().all();		
	}
	
	@Test (priority=4)
	void deleteUser(){
		
		newId = id-1;
		
		given()
		
		.when()
			.delete("http://localhost:3000/comments/"+newId)
		
		.then()
			.log().all();
		
	}
}