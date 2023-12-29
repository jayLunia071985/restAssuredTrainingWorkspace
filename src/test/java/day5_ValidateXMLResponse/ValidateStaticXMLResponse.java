package day5_ValidateXMLResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.annotations.Test;

public class ValidateStaticXMLResponse {
	
	//http://restapi.adequateshop.com/swagger/ui/index
	//https://www.youtube.com/watch?v=IB3G7IbdD1k&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=5
	
	
	@Test()
	void testStaticXMLResponseUsingFreeSwaggerAPI() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day5.properties");
		
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/api/");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		
		given()
			.contentType("application/xml")
			.queryParam("page", 2)
		
		.when()
			.get(baseUrl+"/Traveler")
		
		.then()
			//.log().body()
			.log().all()
			.statusCode(200)
			.contentType("application/xml")
			.body("TravelerinformationResponse.page", equalTo("2"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("2"));	
	}
}
