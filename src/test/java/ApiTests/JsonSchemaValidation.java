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
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSchemaValidation {

    @BeforeClass
    public void setup(){
        baseURI="http://3.82.129.38:8000";
    }

    @Test
    public void test1(){

given().accept(ContentType.JSON)
        .pathParams("id",193)
        .when().get("/api/spartans/{id}")
        .then().assertThat().statusCode(200)  // next step checks if response matching with schema
        .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
// we use method  matchesJsonSchemaInClasspath () and put path to our resources file  "SingleSpartanSchema.json"

    }
}
