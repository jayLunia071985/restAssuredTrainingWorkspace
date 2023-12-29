package day3_pathParameter_and_queryParameter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class LogDemo {
	
	@Test (priority=3)
	void getUser(){
		
		given()
		
		.when()
			.get("http://localhost:3000/comments/3")
		
		.then()
			.statusCode(200)
			//.log().all() //Prints Body/Cookies/Headers
			.log().cookies() //no Cookies Present
			.log().body()
			.log().headers();
	}

}
