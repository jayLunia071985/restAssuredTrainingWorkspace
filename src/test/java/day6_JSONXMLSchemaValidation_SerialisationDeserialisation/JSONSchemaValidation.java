package day6_JSONXMLSchemaValidation_SerialisationDeserialisation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

/*Package for JSON Schema Validation*/
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class JSONSchemaValidation {
	
	@Test()
	void testJSONSchemaValidation() throws IOException {
		
		/*Storing Properties*/
		Properties p = new Properties();
		OutputStream os = new FileOutputStream ("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day6.properties");
		
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/api/");
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		
		/*Reading JSON Schema from external File*/
		FileInputStream f = new FileInputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day6JSONSchemaDefination.json");
		InputStreamReader fr = new InputStreamReader(f);
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject jo = new JSONObject(jt);
		
		System.out.print(jo.toString());
		
		given()
			.contentType("application/json")
		
		.when()
			.get(baseUrl+"Feed/GetNewsFeed")
		
		/*Note: File should be under src/test/resource/ and name of the file is provided*/
		.then()
			.log().body()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("day6JSONSchemaDefination.json"));
		
		//String resSchema = res.
			
	}

}
