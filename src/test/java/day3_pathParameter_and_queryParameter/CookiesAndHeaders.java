package day3_pathParameter_and_queryParameter;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookiesAndHeaders {
	
	@Test()
	void CookiesAndHeadersTest() {
		
		given()
		
		.when()
			.get("https://www.seek.com.au/")
		
		.then()
			//.log().all()
			.cookie("JobseekerSessionId") //got it via postman
			.cookie("JobseekerVisitorId") //got it via postman
			.cookie("__cf_bm") //got it via postman
			
			.header("Connection", "keep-alive"); //got it via postman
	}
	
	@Test()
	void CookiesAndHeadersInfoTest() {
		
		Response res =given()
		
		.when()
			.get("https://www.seek.com.au/");
		
		
		/*GET SINGLE COOKIES INFO*/
		String Cookie1 = res.cookie("JobseekerSessionId");
		System.out.print("Cookie 1 = " +Cookie1+'\n');
		
		String Cookie2 = res.cookie("JobseekerVisitorId");
		System.out.print("Cookie 2 =" +Cookie2+'\n');
		
		String Cookie3 = res.cookie("__cf_bm");
		System.out.print("Cookie 3 =" +Cookie3+'\n');
		
		/*GET SINGLE Header INFO*/
		String Header = res.header("Connection");
		System.out.print("Header value = " +Header+'\n');
		
		
		
		/*GET MULTIPLE COOKIES INFO, Map Stores all Key/Value Pair in Variable*/
		Map<String,String> allCookie = res.getCookies();
		
		/*Fetch all Keys*/
		Set<String> allKeys = allCookie.keySet();
		
		System.out.print(allKeys+"\n");
		
		/*Printing all Cookie key/Values*/
		for(String k:allCookie.keySet()){
			String cookie = res.cookie(k);	
			System.out.print("Cookie Name = " +k+" ,Value for Cookie is = "+cookie+'\n');
		}
		
		
		/*GET MULTIPLE HEADER INFO, Headers all Key/Value Pair in Variable*/
		Headers allHeader = res.getHeaders();
		
		/* Header is a Set of key/Value Pair, while Header have single Key/Value, Hence "Header" class object hd is used*/
		for(Header hd: allHeader) {
			String headerKey = hd.getName();
			String headerValue = hd.getValue();
			
			System.out.print("Header Key = "+headerKey+ " ,Header Value ="+headerValue+'\n');	
		}
		
		
	}
}
