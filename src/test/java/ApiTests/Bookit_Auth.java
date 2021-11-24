package ApiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
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
public class Bookit_Auth {
     // token // authorization --> token  // yo can use token with "Bearer eyJ...." and without "eyJ...."
    String accessToken ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeClass
    public void setup(){
        baseURI="https://cybertek-reservation-api-qa3.herokuapp.com";
    }

    @Test
    public void test1(){
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

    }
}
