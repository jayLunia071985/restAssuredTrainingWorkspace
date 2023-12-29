package day8_Chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class ChainingUpdateUser {
	
	@Test()
	void testUpdateUser (ITestContext context) throws IOException {
		
		
		int id = (int) context.getAttribute("id");
		Faker faker = new Faker();
		
		String name = faker.name().firstName();
		String email = faker.internet().emailAddress();
		String location = faker.address().country();
		
		PojoForChaining pojoData = new PojoForChaining();
		
		pojoData.setName(name);
		pojoData.setEmail(email);
		pojoData.setLocation(location);
		
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day8.properties");
		
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/api/Customer");
		p.setProperty("name", name);
		p.setProperty("email", email);
		p.setProperty("location", location);
		
		p.store(os, null);
		
		String fetchBaseUrl = p.getProperty("baseUrl");
		
		given()
			.contentType("application/json")
			.queryParam("id", id)
			.body(pojoData)
		
		.when()
			.put(fetchBaseUrl+id)
			
			
		.then()
			.log().body();
		
		System.out.print("Generated Id = "+id+'\n');
		
	}


}
