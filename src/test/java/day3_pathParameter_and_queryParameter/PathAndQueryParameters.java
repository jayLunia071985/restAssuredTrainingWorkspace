package day3_pathParameter_and_queryParameter;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
	
	@Test
	void testQueryAndPathParameters() throws IOException{
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream ("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day3.properties");
		
		p.setProperty("baseUrl", "http://localhost:3000/comments");
		//p.setProperty("pathParam", "comments");
		p.setProperty("id", "5");
		p.setProperty("body", "Added Via HashMap");
		
		p.store(os, null);
		
		String body = p.getProperty("body");
		String id = p.getProperty("id");
		String baseUrl = p.getProperty("baseUrl");
		
		System.out.print("Fetched Value for Body is = "+body+'\n');
		System.out.print("Fetched Value for id is = "+id+'\n');
		System.out.print("Fetched Value for baseUrl is = "+baseUrl+'\n');

		given()
			//.pathParam("comments", p.getProperty("pathParam"))
			.queryParam("id", p.getProperty("id"))
			.queryParam("body", p.getProperty("body"))
		
		.when()
			.get(p.getProperty("baseUrl"))
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
