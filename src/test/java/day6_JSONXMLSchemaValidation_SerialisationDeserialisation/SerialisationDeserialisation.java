package day6_JSONXMLSchemaValidation_SerialisationDeserialisation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


//POJO To JSON (Request) ===>Serialisation
//JSON (Response) To POJO  ===>DeSerialisation

public class SerialisationDeserialisation {
	
	@Test()
	void PojoToJSONSerialisation()throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream ("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\SerialisationDeserialisation.properties");
		
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/");
		p.setProperty("name", "JoyLunia44");
		p.setProperty("email", "jlunia555@gmail.com");
		p.setProperty("location", "NewZealand44");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		String name = p.getProperty("name");
		String email = p.getProperty("email");
		String location = p.getProperty("location");
		
		
		PojoForSerialisationDeserialisation data = new PojoForSerialisationDeserialisation();
		data.setName(name);
		data.setEmail(email);
		data.setLocation(location);
		
		/*SERIALISATION*/
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		System.out.print("*****CONVNTERED JSON USING SERIALISATION****** : " +jsonData + '\n');
		
		JSONObject jsondata = new JSONObject(data);
		
		System.out.print("*****CONVNTERED JSON USING POJO****** : " +jsondata + '\n');
		
		
		/*Note above Serialisations using ObjectMapper is not Required as we can use directly "data" as its in json format, no need to use "jsonData"*/
		Response res = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post(baseUrl+"api/Customer");
		
		/*Coverting Json Body jo into string so that it can be passed used below*/
		String jo = res.body().asString();
		
		System.out.print("===========RESPONSE JSON============"+jo+'\n');
		
		/*JSON Response used for DESERIALISATION*/
		ObjectMapper obj = new ObjectMapper();
		PojoForSerialisationDeserialisation pojoData = obj.readValue(jo, PojoForSerialisationDeserialisation.class);
		
		String name1 = pojoData.getName();
		String email1 = pojoData.getEmail();
		String location1 = pojoData.getLocation();
		
		System.out.print(name1+'\n');
		System.out.print(email1+'\n');
		System.out.print(location1+'\n');	
	}
	
	//@Test()
	void JSONToPojoDeserialisation () throws JsonProcessingException {
		
		/*Sample JSON String for DESERIALISATION*/
		String jsonData = "{\r\n"
				+ "  \"name\" : \"JoyLunia33\",\r\n"
				+ "  \"email\" : \"jlunia33@gmail.com\",\r\n"
				+ "  \"location\" : \"NewZealand33\"\r\n"
				+ "}";
		
		/*DESERIALISATION*/
		ObjectMapper obj = new ObjectMapper();
		PojoForSerialisationDeserialisation pojoData = obj.readValue(jsonData, PojoForSerialisationDeserialisation.class);
		
		String name = pojoData.getName();
		String email = pojoData.getEmail();
		String location = pojoData.getLocation();
		
		System.out.print(name+'\n');
		System.out.print(email+'\n');
		System.out.print(location+'\n');
		
	}
	
	
	

}
