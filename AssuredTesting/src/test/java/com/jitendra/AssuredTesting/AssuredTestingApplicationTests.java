package com.jitendra.AssuredTesting;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssuredTestingApplicationTests {

	/*@Test
	public void getPeopleInfo()
	{
		RestAssured.baseURI = "https://swapi.dev/api/";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.get("/people/1/");

		System.out.println("Response Body is =>  " + response.asString());
	}*/

}
