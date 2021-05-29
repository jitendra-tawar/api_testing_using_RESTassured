package com.testapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

//@Data
@NoArgsConstructor @Getter @Setter
@AllArgsConstructor @ToString
public class LombokAnnotetiontest {

//    @Getter @Setter
    private String baseURL = "https://reqres.in/api/";
//    @Getter @Setter
    private String email ="eve.holt@reqres.in";
//    @Getter @Setter
    private String password = "piston";

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
}
