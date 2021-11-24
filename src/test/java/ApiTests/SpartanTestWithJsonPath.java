package ApiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import  io.restassured.RestAssured;
import org.testng.annotations.Test;


public class SpartanTestWithJsonPath {
    @BeforeClass                // if import  static io.restassured.RestAssured.*;
    public void setUpClass(){  // or  baseURI = "http://100.26.222.8:8000";

        RestAssured.baseURI = "http://100.26.222.8:8000";
    }


    /*
    Given accept type is Json
    And id parameter value is 11
    When user sends get request to api/spartans/{id}
    Then response status code should be 200
    And response content-type is json
    And "id": 11,
        "name": "Nona",
        "gender": "Female",
        "phone": 7959094216
     */

    @Test
    public void  test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        // how to read value with path() method
         int id = response.path("id");
        System.out.println("id = " + id);

        // how to read value with JsonPath
        JsonPath jsonData = response.jsonPath();

        int id1 = jsonData.getInt("id");
        String name = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        // how to read value with JsonPath
        assertEquals(id1,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone,7959094216l);


    }
}
