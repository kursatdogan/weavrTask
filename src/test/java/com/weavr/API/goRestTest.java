package com.weavr.API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
//import static org.testng.Assert.assertEquals;

public class goRestTest {

    public int newId;


    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="https://gorest.co.in/public/v2";
    }



    @Test
    public void getRequest(){
        //get request from member who already exist
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",4064)
                .when().get("/users/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        int id = response.path("id");
        String name = response.path("name");
        String email = response.path("email");
        String gender = response.path("gender");
        String status = response.path("status");

        assertEquals(4064,id);
        assertEquals("Triloki Iyengar",name);
        assertEquals("iyengar_triloki@pfannerstill.com",email);
        assertEquals(gender,"male");
        assertEquals(status,"inactive");

    }

    @Test
    public void postRequest(){
        //create a new user with post request and using Map
        Map<String,Object> postMap = new HashMap<>();
        postMap.put("name","Michael Jordan");
        postMap.put("email","Mjordan@nba.com");
        postMap.put("gender","male");
        postMap.put("status","inactive");
        Response postResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(postMap)
                .when().post("/users");
        postResponse.prettyPrint();
        assertEquals(201,postResponse.statusCode());
        assertEquals("application/json; charset=utf-8",postResponse.contentType());
        assertEquals("Michael Jordan",postMap.get("name"));
        assertEquals("Mjordan@nba.com",postMap.get("email"));
        assertEquals("male",postMap.get("gender"));
        assertEquals("inactive",postMap.get("status"));
        newId=postResponse.path("id");
    }

    @Test
    public void putRequest(){
        //Updating the user who has been already created by post request with using map
        Map<String,Object> postMap = new HashMap<>();
        postMap.put("name","Michael Jordan");
        postMap.put("email","micjordan@nba.com");
        postMap.put("status","active");
        Response putResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",4604)
                .and().auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(postMap)
                .when().put("/users/{id}");
        putResponse.prettyPrint();
        assertEquals(200,putResponse.statusCode());
        assertEquals("application/json; charset=utf-8",putResponse.contentType());
        assertEquals("Michael Jordan",postMap.get("name"));
        assertEquals("micjordan@nba.com",postMap.get("email"));
        assertEquals("active",postMap.get("status"));
        assertEquals("male",putResponse.path("gender"));

    }

    @Test
    public void patchRequest(){
        //updating the user name and email info with patch method using Map
        //for assertion JsonPath object has been used
        Map<String,Object> patchMap = new HashMap<>();
        patchMap.put("name","Michael Jordan2");
        patchMap.put("email","mcjordan2@nba.com");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",4604)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(patchMap)
                .when().patch("/users/{id}");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals("application/json; charset=utf-8",response.contentType());

        JsonPath json = response.jsonPath();

        assertEquals("Michael Jordan2",json.getString("name"));
        assertEquals("mcjordan2@nba.com",json.getString("email"));

    }

    @Test
    public void deleteRequest(){
        //delete the user who created by post request
        Response response = given().accept(ContentType.JSON)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .when().delete("/users/4604");
        assertEquals(204,response.statusCode());

    }

}
