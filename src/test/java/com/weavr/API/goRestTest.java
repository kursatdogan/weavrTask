package com.weavr.API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class goRestTest {

    int newId;


    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="https://gorest.co.in/public/v2";
    }

    @Test
    public void getRequest(){

    //create a new user with post request and using Map

        Map<String,Object> map = new HashMap<>();
        map.put("name","Michael Jordan");
        map.put("email","Mjordan@nba.com");
        map.put("gender","male");
        map.put("status","inactive");
        Response postResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(map)
                .when().post("/users");
        postResponse.prettyPrint();
        assertEquals(201,postResponse.statusCode());
        assertEquals("application/json; charset=utf-8",postResponse.contentType());
        assertEquals("Michael Jordan",map.get("name"));
        assertEquals("Mjordan@nba.com",map.get("email"));
        assertEquals("male",map.get("gender"));
        assertEquals("inactive",map.get("status"));
        newId=postResponse.path("id");
        System.out.println("the user whom id:" + newId + " has been created with POST request");
        System.out.println("And the the response body has been checked by using MAP");

    //With get response the payload is checked and the path method is used for getting each info
        Response getResponse = given().accept(ContentType.JSON)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                        .pathParam("id",newId)
                                .when().get("/users/{id}");
        assertEquals(200,getResponse.statusCode());
        assertEquals("application/json; charset=utf-8",getResponse.contentType());
        assertEquals("Michael Jordan",getResponse.path("name"));
        assertEquals("Mjordan@nba.com",getResponse.path("email"));
        assertEquals("male",getResponse.path("gender"));
        assertEquals("inactive",getResponse.path("status"));
        System.out.println("The payload is checked by using GET response and path method");

    //Updating the user who has been already created by post request with using map;
        map.put("name","Michael Jordan");
        map.put("email","mikejordan@nba.com");
        map.put("status","active");
        Response putResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",newId)
                .and().auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(map)
                .when().put("/users/{id}");
        putResponse.prettyPrint();
        assertEquals(200,putResponse.statusCode());
        assertEquals("application/json; charset=utf-8",putResponse.contentType());
        assertEquals("Michael Jordan",map.get("name"));
        assertEquals("mikejordan@nba.com",map.get("email"));
        assertEquals("active",map.get("status"));
        assertEquals("male",putResponse.path("gender"));
        System.out.println("the user who already exist has been updating by using PUT request");


    //updating the user name and email info with patch method using Map
    //for assertion JsonPath object has been used


        map.put("email","mcjordan2@nba.com");
        Response patchResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",newId)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(map)
                .when().patch("/users/{id}");
        patchResponse.prettyPrint();
        assertEquals(patchResponse.statusCode(),200);
        assertEquals("application/json; charset=utf-8",patchResponse.contentType());

        JsonPath json = patchResponse.jsonPath();

        assertEquals("Michael Jordan",json.getString("name"));
        assertEquals("mcjordan2@nba.com",json.getString("email"));
        System.out.println("Only the user email info has been updating by usin PATCH request");


    //delete the user who created by post request
        Response deleteResponse = given().accept(ContentType.JSON)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .and().pathParam("id",newId )
                .when().delete("/users/{id}");
        assertEquals(204,deleteResponse.statusCode());
        System.out.println("********* After DELETE request *********");
        System.out.println("The user whom id:" + newId + " has been deleted");

    }

}
