package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestWithPassParameters {


    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI = "http://100.26.222.8:8000";

    }
/*
    Given accept type is Json
    And id parameter value is 18
    When user sends get request to api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And "Allen" should be in response payload
     */


    @Test
    public void PathTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");

        //verify status code
       assertEquals(response.statusCode(),200);  // Assert.assertEquals(response.statusCode(),200);

       //verify content type
        assertEquals(response.contentType(), "application/json");

        // verify Allen exists
        assertTrue(response.body().asString().contains("Allen"));

        response.body().prettyPrint();

}

    /*
    Given accept type is Json
    And id parameter value is 500
    When user sends get request to api/spartans/{id}
    Then response status code should be 404
    And response content-type: application/json;charset=UTF-8
    And "Spartan Not Found" massage should be in response payload
     */
@Test
    public void negativePathParamTest(){

    Response response = RestAssured.given().accept(ContentType.JSON)
            .and().pathParam("id", 500)
            .when().get("/api/spartans/{id}");

        //verify status code is 404
    Assert.assertEquals(response.statusCode(),404);

    //verify content type
    Assert.assertEquals(response.contentType(), "application/json");

    // verify Allen exists
    Assert.assertTrue(response.body().asString().contains("Spartan Not Found")); //new "Not Found" check postman!

      response.body().prettyPrint();

}


}
