package day7_AuthenticationAuthorisation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

/*
 Basic using https://postman-echo.com/basic-auth
 Digest
 Preemptive
 
 BearerToken
 Oauth1.0
 Oauth2.0
 API Key
 
 */

public class Authentication {
	
	@Test(priority = 1)
	void testBasicAuth() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.log().all()
			.statusCode(200)
			.body("authenticated",equalTo( true));
	}
	
	@Test(priority = 2)
	void testDigestAuth() {
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.log().all()
			.statusCode(200)
			.body("authenticated",equalTo( true));
	}
	
	@Test(priority = 3)
	void testPreemptiveAuth() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.log().all()
			.statusCode(200)
			.body("authenticated",equalTo( true));
	}
	
	@Test(priority = 4)
	void testBearerTokenAuth() {
		
		
		/*Token generated from github personal account via developer setting*/
		String bearerToken = "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";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.log().all()
			.statusCode(200);
	}
	
	//@Test(priority = 5)
	void testOauth1() {
		
		given()
			.auth().oauth(DEFAULT_URI, DEFAULT_SESSION_ID_VALUE, DEFAULT_PATH, DEFAULT_BODY_ROOT_PATH)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test(priority = 6)
	void testOauth2() {
		
		given()
			.auth().oauth2("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")
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test(priority = 7)
	void testAPIKey() {
		
		//https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7
		
		given()
			.queryParam("appid", "")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
		
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily")
		
		.then()
			.log().all()
			.statusCode(200);
	}

}
