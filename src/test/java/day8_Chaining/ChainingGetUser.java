package day8_Chaining;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class ChainingGetUser {
	
	@Test()
	void getUser(ITestContext context) throws IOException {
		
		/*this should come from create User*/
		int id = (int) context.getAttribute("id");
		
		System.out.print("Id = "+id);
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day8.properties");
		
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/api/Customer");
		p.store(os, null);
		
		String fetchBaseUrl = p.getProperty("baseUrl");

		System.out.print("fetchBaseUrl = "+fetchBaseUrl);
		
		given()
			.contentType("application/json")
			.queryParam("id", id)
		
		.when()
			.get(fetchBaseUrl)
			
		.then()
			.statusCode(200)
			.log().body();

		
	}

}
