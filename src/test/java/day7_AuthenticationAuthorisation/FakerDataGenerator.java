package day7_AuthenticationAuthorisation;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test()
	void testGenarateFakeData() {
		
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String userName = faker.name().username();
		String pwd = faker.internet().password();
		String pwdLength = faker.internet().password(6, 20);
		String email = faker.internet().emailAddress();
		
		System.out.print(name+'\n');
		System.out.print(firstName+'\n');
		System.out.print(lastName+'\n');
		System.out.print(userName+'\n');
		System.out.print(pwd+'\n');
		System.out.print(pwdLength+'\n');
		System.out.print(email+'\n');
		
	}

}
