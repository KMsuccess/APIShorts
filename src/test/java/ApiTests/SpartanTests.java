package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests {

    String spartanBaseURL = "http://100.26.222.8:8000";

    @Test
    public void viewSpartanTest1(){
       // RestAssured.get(spartanBaseURL+"/api/spartans");
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        //print the status code
        System.out.println(response.statusCode());


        //print body
        System.out.println(response.body().prettyPrint());
    }

        /* When user send request to /api/spartans end point
             Then status code must be 200
             And body should contains Allen
        */
    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        // verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        // verify body contains Allen
        Assert.assertTrue(response.body().asString().contains("Allen"));
    }

    /* Given accept type is Json
    When user sends a get request to spartanAllURL
    Then response status code is 200
    And response body should be json format
        */
    @Test
    public void viewSpartanTest3(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseURL + "/api/spartans");

        // verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        // response body  json
        Assert.assertEquals(response.contentType(),"application/json");
    }

}
