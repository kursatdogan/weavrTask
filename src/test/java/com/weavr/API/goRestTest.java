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

    int newId;

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="https://gorest.co.in/public/v2";
    }



    @Test
    public void getRequest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",1)
                .when().get("/users/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        int id = response.path("id");
        String name = response.path("name");
        String email = response.path("email");
        String gender = response.path("gender");
        String status = response.path("status");

        assertEquals(id,1);
        assertEquals(name,"Menka Pilla");
        assertEquals(email,"pilla_menka@pouros.biz");
        assertEquals(gender,"female");
        assertEquals(status,"inactive");

    }

    @Test
    public void postRequest(){
        Map<String,Object> postMap = new HashMap<>();
        postMap.put("name","Michael Jordan");
        postMap.put("email","mjordan@nba.com");
        postMap.put("gender","male");
        postMap.put("status","inactive");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(postMap)
                .when().post("/users");
        response.prettyPrint();
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertEquals(postMap.get("name"),"Michael Jordan");
        assertEquals(postMap.get("email"),"mjordan@nba.com");
        assertEquals(postMap.get("gender"),"male");
        assertEquals(postMap.get("status"),"inactive");
        //newId = response.path("id");
        //System.out.println("newId = " + newId);
        int localId = response.path("id");
        newId = localId;
        System.out.println("newId = " + newId);

    }

    @Test
    public void putRequest(){
        Map<String,Object> postMap = new HashMap<>();
        postMap.put("name","Michael Jordan");
        postMap.put("email","mjordan@nba.com");
        postMap.put("status","active");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",newId)
                .and().auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(postMap)
                .when().put("/users/{id}");
        response.prettyPrint();
        System.out.println("newId = " + newId);
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertEquals(postMap.get("name"),"Michael Jordan");
        assertEquals(postMap.get("email"),"mjordan@nba.com");
        assertEquals(postMap.get("status"),"active");
        assertEquals(response.path("gender"),"male");

    }

    @Test
    public void patchRequest(){

        Map<String,Object> patchMap = new HashMap<>();
        patchMap.put("name","Michael Jordan2");
        patchMap.put("email","michaeljordan2@nba.com");
        //goRest gorest = new goRest();
        //gorest.setName("Michael Jordan2");
        //gorest.setEmail("michaeljordan2@nba.com");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",newId)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .body(patchMap)
                .when().patch("/users/{id}");
        response.prettyPrint();
        System.out.println("newId = " + newId);
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        JsonPath json = response.jsonPath();

        assertEquals(json.getString("name"),"Michael Jordan2");
        assertEquals(json.getString("email"),"michaeljordan2@nba.com");

    }

    @Test
    public void deleteRequest(){
        Response response = given().accept(ContentType.JSON)
                .auth().oauth2("6397d92422bc792818606ac610306220d8c14bb27271bbf4c54e0a5968507737")
                .when().delete("/users/3518");
        assertEquals(response.statusCode(),204);

    }

}
