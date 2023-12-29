package day8_Chaining;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class ChainingCreateUser {
	
	@Test()
	void testCreateUser (ITestContext context) throws IOException {
		
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
		
		int id = given()
			.contentType("application/json")
			.body(pojoData)
		
		.when()
			.post(fetchBaseUrl)
			.jsonPath().getInt("id");

		System.out.print("Generated Id = "+id+'\n');
		context.setAttribute("id", id);
		
		/*
		String jo = res.body().asString();
		
		System.out.print("Response = "+jo+'\n');
		
		ObjectMapper om = new ObjectMapper();
		PojoForChaining jsonResponseData = om.readValue(jo, PojoForChaining.class);
		
		int idFromResponse = jsonResponseData.getId();
		String emailFromResponse = jsonResponseData.getEmail();
		String nameFromResponse = jsonResponseData.getName();
		String locationFromResponse = jsonResponseData.getLocation();
		
		System.out.print("Id = "+idFromResponse+'\n');
		System.out.print("Name = "+nameFromResponse+'\n');
		System.out.print("Email = "+emailFromResponse+'\n');
		System.out.print("Location = "+locationFromResponse+'\n');
		*/
	}

}
