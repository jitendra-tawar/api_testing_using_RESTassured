package com.jitendra.AssuredTesting;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class AssuredTestingApplicationTests {

	/*@Test
	public void getPeopleInfo()
	{
		RestAssured.baseURI = "https://swapi.dev/api/";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.get("/people/1/");

        int statusCode = response.getStatusCode();

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);

        JsonPath jsonPathEvaluator = response.jsonPath();

        System.out.println("name ="+jsonPathEvaluator.get("name"));
        System.out.println("height ="+jsonPathEvaluator.get("height"));
        System.out.println("eye_color ="+jsonPathEvaluator.get("eye_color"));
        System.out.println("films ="+jsonPathEvaluator.get("films"));
        System.out.println("url ="+jsonPathEvaluator.get("url"));

        get("/people/1/").then().assertThat().body("height", equalTo("172"));
	}*/

    //this is not working   it give 405 status code */
    /*@Test
    public void PostPeople(){
        RestAssured.baseURI ="https://swapi.dev/api/";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "Jitendra tanwar");
        requestParams.put("height", "172");
        requestParams.put("mass", "77");
        requestParams.put("hair_color", "black");
        requestParams.put("skin_color", "fair");
        requestParams.put("eye_color", "black");
        requestParams.put("birth_year ", "1998");
        requestParams.put("gender", "Male");
        requestParams.put("homeworld", "Indore");
        requestParams.put("films", "http://swapi.dev/api/films/1/");
        requestParams.put("species", "");
        requestParams.put("vehicles", "hsadjchsd");
        requestParams.put("starships", "black");
        requestParams.put("created", "fair");
        requestParams.put("edited", "black");
        requestParams.put("url ", "https://swapi.dev");

        request.body(requestParams.toJSONString());

        Response response = request.post("/people/1000");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        Assertions.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assertions.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }*/

    // ITS GIVE 301  status code
    @Test
    public void PostPeople(){
        RestAssured.baseURI ="https://swapi.dev/api/";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "Jitendra tanwar");
        requestParams.put("height", "172");
        requestParams.put("mass", "77");
        requestParams.put("hair_color", "black");
        requestParams.put("skin_color", "fair");
        requestParams.put("eye_color", "black");
        requestParams.put("birth_year ", "1998");
        requestParams.put("gender", "Male");
        requestParams.put("homeworld", "Indore");
        requestParams.put("films", "http://swapi.dev/api/films/1/");
        requestParams.put("species", "");
        requestParams.put("vehicles", "hsadjchsd");
        requestParams.put("starships", "black");
        requestParams.put("created", "fair");
        requestParams.put("edited", "black");
        requestParams.put("url ", "https://swapi.dev");

        request.body(requestParams.toJSONString());

        Response response = request.put("/people/1");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        Assertions.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assertions.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }

}