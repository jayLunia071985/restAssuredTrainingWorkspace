package day6_JSONXMLSchemaValidation_SerialisationDeserialisation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XmlSchemaValidation {
	
	@Test()
	void testXmlSchemaValidation() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day6.properties");
	
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		
		given()
			.contentType("application/xml")
		
		.when()
			.get(baseUrl+"api/Traveler")
			
		.then()
			.log().body()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("day6XmlSchemaDefination.xsd"));
		
		
	}

}
