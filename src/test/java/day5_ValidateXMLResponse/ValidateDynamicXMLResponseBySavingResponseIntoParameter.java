package day5_ValidateXMLResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ValidateDynamicXMLResponseBySavingResponseIntoParameter {
	
	//http://restapi.adequateshop.com/swagger/ui/index
	//https://www.youtube.com/watch?v=IB3G7IbdD1k&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=5
	//Sample XML Response at the end
	
	@Test()
	void testStaticXMLResponseUsingFreeSwaggerAPI() throws IOException {
		
		Properties p = new Properties();
		OutputStream os = new FileOutputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day5.properties");
		
		p.setProperty("baseUrl", "http://restapi.adequateshop.com/api/");
		
		p.store(os, null);
		
		String baseUrl = p.getProperty("baseUrl");
		
		Response res = given()
			.contentType("application/xml")
			.queryParam("page", 2)
		
		.when()
			.get(baseUrl+"/Traveler");
		
		/*STATIC VALIDATION*/
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.contentType(), "application/xml; charset=utf-8");
		/*Using get*/
		Assert.assertEquals(res.xmlPath().get("TravelerinformationResponse.page").toString(), "2");
		/*Using getString*/
		Assert.assertEquals(res.xmlPath().getString("TravelerinformationResponse.page"), "2");
		Assert.assertEquals(res.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].id"), "11144");
		
		
		/*DYNAMIC VALIDATION by Storing Response in a Parameter*/
		XmlPath xmlObject = new XmlPath(res.asString());
		
		String page = xmlObject.getString("TravelerinformationResponse.page");
		System.out.print("*****This is Page value****** : " +page + '\n');
		
		/*List all Travelers*/
		List<String> travelers = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation");
		System.out.print(travelers+"\n");
		
		int count = travelers.size();
		System.out.print("*****Travellers Count****** : " +count + '\n');
		
		/*to Validate travelers count*/
		Assert.assertEquals(travelers.size(), 10);
		
		/*List all Travelers name*/
		List<String> travelers_name = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		System.out.print("All Names =" +travelers_name+'\n');
		
		/*To Validate Particular name is Present*/
		boolean status = false;
		for(String name: travelers_name)
		{
			System.out.print(name+'\n');
			if(name.equals("traveler"))
			{
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		System.out.print("=================== Name TEST COMPLETED ==================="+'\n');
		
		/*Validate Travelers email contain jkhaumann2*/
		List<String> traveler_email = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation.email");
		
		boolean email_status = false;
		
		for(String email:traveler_email)
		{
			System.out.print(email+'\n');
			if(email.contains("jkhaumann2"))
			{
				email_status = true;
				break;
			}
		}
		
		Assert.assertEquals(email_status, true);
		
		System.out.print("=================== Email TEST COMPLETED ==================="+'\n');
		
		//XML RESPONSE
		/*
		<TravelerinformationResponse xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <page>2</page>
    <per_page>10</per_page>
    <totalrecord>18304</totalrecord>
    <total_pages>1831</total_pages>
    <travelers>
        <Travelerinformation>
            <id>11144</id>
            <name>ASCAS</name>
            <email>edoedo@mail.ru</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11145</id>
            <name>jkhaumann</name>
            <email>jkhaumann@gmail.com</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11146</id>
            <name>Albert Petoyan</name>
            <email>royalalbertprtoyan@gmail.com</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11147</id>
            <name>traveler</name>
            <email>jkhaumann2@gmail.com</email>
            <adderes>DK</adderes>
            <createdat>2021-11-18T16:37:31.0470565</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11148</id>
            <name>Albert Petoyan</name>
            <email>account-business@mail.ru</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11149</id>
            <name>mirza</name>
            <email>mirza@gmail.com</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11150</id>
            <name>Albert Petoyan</name>
            <email>aaaaaaa@mail.com</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11151</id>
            <name>Albert Petoyan</name>
            <email>aaaaqaaa@mail.com</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11152</id>
            <name>Albert Petoyan</name>
            <email>aaaa@mail.ru</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>11153</id>
            <name>Albert Petoyan</name>
            <email>qq@mail.ru</email>
            <adderes>USA</adderes>
            <createdat>0001-01-01T00:00:00</createdat>
        </Travelerinformation>
    </travelers>
</TravelerinformationResponse>
		
		 
		
		*/
		
		
		
		
		
		
	}
}
