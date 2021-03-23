package com.jitendra.AssuredTesting;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class AssuredTestingApplicationTests {

	@Test
	public void getUsersInfo()
	{
		RestAssured.baseURI = "https://reqres.in/api/";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.get("/users/1");

        int statusCode = response.getStatusCode();

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);

        JsonPath jsonPathEvaluator = response.jsonPath();

        System.out.println("id ="+jsonPathEvaluator.get("data.id"));
        System.out.println("email ="+jsonPathEvaluator.get("data.email"));
        System.out.println("first_name ="+jsonPathEvaluator.get("data.first_name"));
        System.out.println("last_name ="+jsonPathEvaluator.get("data.last_name"));
        System.out.println("avatar ="+jsonPathEvaluator.get("data.avatar"));

        get("/users/1").then().assertThat().body("data.first_name", equalTo("George"));
	}




    @Test
    public void postUsers(){

	    System.out.println(" this is your post method ---------------->>> ");
        RestAssured.baseURI ="https://reqres.in/api/";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("email", "jiten123@gmail,com");
        requestParams.put( "first_name", "Jitendra");
        requestParams.put( "last_name","Tanwar");
        requestParams.put("avatar", "https://reqres.in/img/faces/7-image.jpg");

        request.body(requestParams.toJSONString());

        Response response = request.post("/users");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        Assertions.assertEquals(statusCode, 201);

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);
    }

    @Test
    public void updateUsersById(){

        System.out.println(" this is your put method ---------------->>> ");
        RestAssured.baseURI ="https://reqres.in/api/";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("email", "hello@gmail,com");
        requestParams.put( "first_name", "mahindra");
        requestParams.put( "last_name","singh");
        requestParams.put("avatar", "https://reqres.in/img/faces/7-image.jpg");

        request.body(requestParams.toJSONString());

        Response response = request.put("/users/1");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        Assertions.assertEquals(statusCode, 200);

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);
    }

    @Test
    public void deleteUsersById(){

        System.out.println(" this is your delete method ---------------->>> ");
        RestAssured.baseURI ="https://reqres.in/api/";
        RequestSpecification request = RestAssured.given();

        Response response = request.delete("/users/1");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        Assertions.assertEquals(statusCode, 204);

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);
    }


    @Test
    public void registerUserUsingPostMethod(){

        String email_and_password = "{\n"+
                "  \"email\": \"eve.holt@reqres.in\",\n" +
                "  \"password\": \"piston\" \n}";

        Response response = given()
                .auth()
                .preemptive()
                .basic("required_username", "required_password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(email_and_password)
                .post("https://reqres.in/api/register")
                .then().extract().response();

        Assertions.assertEquals(200, response.getStatusCode());
        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+response.getStatusCode());

    }

    @Test
    public void loginUserUsingPostMethod(){

        String email_and_password = "{\n"+
                "  \"email\": \"eve.holt@reqres.in\",\n" +
                "  \"password\": \"cityslicka\" \n}";

        Response response = given()
                .auth()
                .preemptive()
                .basic("required_username", "required_password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(email_and_password)
                .post("https://reqres.in/api/login")
                .then().extract().response();

        Assertions.assertEquals(200, response.getStatusCode());
        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+response.getStatusCode());

    }
}