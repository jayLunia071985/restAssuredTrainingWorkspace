package day4_ValidatingJSONResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateJsonResonseforParticularFieldValueUsingJSONObjectAndExtJSONFile {
	
	@Test()
	void validateJSONFileFieldParameter() throws FileNotFoundException {
		
		FileInputStream f = new FileInputStream("C:\\Joy Lunia\\restAssuredTrainingWorkspace\\restAssuredTraining\\src\\test\\resources\\day4c.json");
		
		InputStreamReader fr = new InputStreamReader(f);
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject jo = new JSONObject(jt);
		
		/*Here we do not have to run any APi request as JSON Object is in File*/
		
		boolean status = false;
		
		for(int i=0;i<jo.getJSONArray("books").length();i++) {
			
			/*Using get*/
			String booksFromJSONObject1 = jo.getJSONArray("books").getJSONObject(i).get("body").toString();
			System.out.print(booksFromJSONObject1+'\n');
			
			/*Using getString*/
			String booksFromJSONObject2 = jo.getJSONArray("books").getJSONObject(i).getString("body");
			System.out.print(booksFromJSONObject2+'\n');
			
			if(booksFromJSONObject1.equals("Added Via HashMap4"))
			{
				status = true;
				System.out.print("Status = "+status+'\n');
				System.out.print("Index Position is at : "+i+'\n');
				
				break;
			}
			else
			{
				System.out.print("NOTHING FOUND"+'\n');
				System.out.print("Status = "+status+'\n');
			}
		}
		
		/*Assertion*/
		Assert.assertEquals(status, true);	
	}

}
