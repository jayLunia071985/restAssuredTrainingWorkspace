package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import io.restassured.RestAssured;
import io.restassured.internal.support.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/*
 * HashMap
 * ORG.JSON
 * POJO
 * External File: JSON
 * External File: JavaPropertiesFile
 */

public class DifferentWaysToCreatePostRequestBody {
	
	@Test(priority = 1)
	void postUsingHashMap() {
		
		HashMap data = new HashMap();
		data.put("postId","1");
		data.put("body","Added Via HashMap");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/comments/")
			
		.then()
			.statusCode(201)
			.log().all()
			.body("body",containsString("HashMap"));
			
			//.header("contentType", "application/json;charset=uft-8");
	}
	
	//@Test (priority = 2)
	void delete1() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/comments/13")
			
		.then();
	}
	
	@Test (priority = 3)
	void postUsingOrgJSON() {
		
		JSONObject data = new JSONObject();
		
		data.put("postId","2");
		data.put("body","Added Via Orgjson");
		
		given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/comments/")
			
		.then()
			.statusCode(201)
			.log().all()
			.body("body",containsString("Orgjson"));	
	}
	
	//@Test(priority = 4)
	void delete2() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/comments/13")
			
		.then();
	}
	
	@Test (priority = 5)
	void postUsingPOJO() {
		
		PojoForPostRequest data = new PojoForPostRequest();
		data.setBody("Using Pojo class");
		data.setPostId(1);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/comments/")
		
		.then()
			.statusCode(201);
	}
	
	@Test (priority = 6)
	void postUsingExternalJSONFile() throws FileNotFoundException {
		
		///restAssuredTraining/src/test/resources/body.json
		
		FileInputStream f = new FileInputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\body.json");
		//System.out.println(f);
		InputStreamReader fr = new InputStreamReader(f);
		JSONTokener jt = new JSONTokener(fr);
		//System.out.println("Jt =" +jt);
		
		JSONObject data = new JSONObject(jt);		
		
		given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/comments/")
		
		.then();
			
	}
	
	@Test (priority = 9)
	void postUsingJavaPropertiesFile() throws IOException {
		
		//https://www.youtube.com/watch?v=w7D5YB2U2jU
		
		Properties p = new Properties();
		
		/*abstract class: We can not create Object of Abstract Class*/
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day2.properties");
				
		p.setProperty("baseUrl", "http://localhost:3000/comments/");
		p.setProperty("body", "Added Via Java Property File");
		p.setProperty("postId","2");
		
		/*to store, null is the comments*/
		p.store(os, null);
		
		HashMap data = new HashMap();
		data.put("body", p.getProperty("body"));
		data.put("postId", p.getProperty("postId"));
		
		String baseUrl = p.getProperty("baseUrl");
		
		given()
			.contentType("application/json")
			.body(data)		
		
		.when()
			.post(baseUrl)
		
		.then();	
	}

}
