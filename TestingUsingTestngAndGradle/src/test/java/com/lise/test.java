package com.lise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class test {

    private String url= null;
    private String email = null;
    private String password =null;
    LombokAnnotationtest obj1 = new LombokAnnotationtest(url,email,password);

    LombokAnnotationtest obj = new LombokAnnotationtest();

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite -->>>>>>>>>>");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite ----->>>>>>>  ");
    }
    @BeforeMethod
    public void beforeMethod(){

        System.out.println("Before mathod ---- >>>>");
    }
    @AfterMethod
    public void afterMethod(){

        System.out.println("after method---- >>>>");
    }
    @BeforeClass
    public void beforeclass(){

        System.out.println("Before Class ---- >>>>");
    }
    @AfterClass
    public void afterclass(){

        System.out.println("after class---- >>>>");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before  Test   ->>>>");
        url= obj.getBaseURL();
        email = obj.getEmail();
        password = obj.getPassword();
    }
    @AfterTest
    public void afterTest(){

        System.out.println("After  Test---- >>>>");
    }

    @Test(priority = 1)
    public void getUsersInfo()
    {
        System.out.println(obj1.toString());
        System.out.println(" this is your get method ---------------->>> TEST 1");
        RestAssured.baseURI = url;  //  or RestAssured.baseURI ="https://reqres.in/api/"
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/users/1");

        JsonPath jsonPathEvaluator = response.jsonPath();

        System.out.println("id ="+jsonPathEvaluator.get("data.id"));
        System.out.println("email ="+jsonPathEvaluator.get("data.email"));
        System.out.println("first_name ="+jsonPathEvaluator.get("data.first_name"));
        System.out.println("last_name ="+jsonPathEvaluator.get("data.last_name"));
        System.out.println("avatar ="+jsonPathEvaluator.get("data.avatar"));

        response.then().assertThat().body("data.first_name", equalTo("George"));

        System.out.println(response.statusCode());
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println(response.statusLine());

        assertThat(response.getStatusCode(), is(equalTo(200)));
        assertThat(jsonPathEvaluator.get("data.email"),is(notNullValue()));
        assertThat(jsonPathEvaluator.get("data.password"),is(nullValue()));

//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertNotNull(jsonPathEvaluator.get("data.email"));
//        Assert.assertNull(jsonPathEvaluator.get("data.password"));
    }


    @Test(priority = 2)
    public void postUsers(){

        System.out.println(" this is your post method ---------------->>> TEST 2");
        RequestSpecification request = given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("email", "jiten123@gmail.com");
        requestParams.put( "first_name", "Jitendra");
        requestParams.put( "last_name","Tanwar");
        requestParams.put("avatar", "https://reqres.in/img/faces/7-image.jpg");

        request.body(requestParams.toJSONString());

        Response response = request.post("/users");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        assertThat(statusCode, is(equalTo(201)));
        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);
    }

    @Test(priority = 3)
    public void updateUsersById(){
        System.out.println(" this is your put or update method ---------------->>> TEST 3");
//        RestAssured.baseURI ="https://reqres.in/api/";
        RequestSpecification request = given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("email", "hello@gmail,com");
        requestParams.put( "first_name", "mahindra");
        requestParams.put( "last_name","singh");
        requestParams.put("avatar", "https://reqres.in/img/faces/7-image.jpg");

        request.body(requestParams.toJSONString());

        Response response = request.put("/users/1");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        assertThat(statusCode, is(equalTo(200)));

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);
    }

    @Test(priority = 4)
    public void deleteUsersById(){

        System.out.println(" this is your delete method ---------------->>> TEST 4");
//        RestAssured.baseURI ="https://reqres.in/api/";
        RequestSpecification request = given();

        Response response = request.delete("/users/1");

        int statusCode = response.getStatusCode();
        System.out.println(" status code is ="+statusCode);
        assertThat(statusCode, is(equalTo(204)));

        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+statusCode);
    }


    @Test(priority =5)
    public void registerUserUsingPostMethod(){
        System.out.println(" this is your register  method ---------------->>> TEST 5");
        String email_and_password = "{\n"+
                "  \"email\": \""+email+"\",\n" +
                "  \"password\": \""+password+"\" \n}";

        Response response = given()
                .auth()
                .preemptive()
                .basic("required_username", "required_password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(email_and_password)
                .post("https://reqres.in/api/register")
                .then().extract().response();

        assertThat(response.getStatusCode(), is(equalTo(200)));
        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+response.getStatusCode());

    }

    @Test(priority = 6)
    public void loginUserUsingPostMethod(){
        System.out.println(" this is your login  method ---------------->>> TEST 6");
        String email_and_password = "{\n"+
                "  \"email\": \""+email+"\",\n" +
                "  \"password\": \""+password+"\" \n}";

        Response response = given()
                .auth()
                .preemptive()
                .basic("required_username", "required_password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(email_and_password)
                .post("https://reqres.in/api/login")
                .then().extract().response();

        assertThat(response.getStatusCode(), is(equalTo(200)));
        System.out.println("Response Body is =>  " + response.asString()+" status code is ="+response.getStatusCode());
    }
}
