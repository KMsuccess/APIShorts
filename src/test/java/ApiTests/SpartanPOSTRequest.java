package ApiTests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import  static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpartanPOSTRequest {
    @BeforeClass
    public void SetUpClass(){
        baseURI="http://100.26.222.8:8000/";
    }

    @Test
    public void PostWithString(){

        //sending json body as String
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "       \"gender\": \"Male\",\n" +
                        "       \"name\": \"Mike\",\n" +
                        "       \"phone\": 2025478854\n" +
                        "    }")
                .when().post("/api/spartans/");

      //  response.prettyPrint();

        //validations
        //verify status code is 201
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify success massage
        assertEquals(response.path("success"),"A Spartan is Born!");

        //verify request body

        JsonPath json = response.jsonPath();

        assertEquals(json.getString("data.name"),"Mike");
        assertEquals(json.getString("data.gender"),"Male");
        assertEquals(json.getLong("data.phone"),8877445596L);

    }

    @Test
    public void PostMethodWithMap(){
        //create Map to be used as body for post request
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("name","MikeMAP");
        requestMap.put("gender","Male");
        requestMap.put("phone",8877445596L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans/");

        assertEquals(response.statusCode(),201);
        response.prettyPrint();
    }

    @Test
    public void POSTWithPOJO(){

        //create spartan object and use as body for post request
        Spartan spartan = new Spartan();
        spartan.setName("MikePOJO");
        spartan.setGender("Male");
        spartan.setPhone(5412312312L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans/");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        response.prettyPrint();

        // ============================ GET REQUEST ====================
   // deserialization
        Response response2 = given().accept(ContentType.JSON)
                .pathParam("id", 157)
                .get("/api/spartans/{id}");

        Spartan spartanResponse = response2.body().as(Spartan.class);

        System.out.println(spartanResponse.toString());

    }




}
