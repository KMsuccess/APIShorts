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

public class SpartanDELETE {

    @BeforeClass
    public void setup(){
        baseURI="http://3.82.129.38:8000/";
    }

    @Test
    public void test1(){

        given().pathParams("id",218)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        // verify part
        given().pathParams("id",218)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);   // no content
    }

 }
