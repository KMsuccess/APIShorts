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

public class SpartanPUT_PUTCH {
    @BeforeClass
    public void setup(){
        baseURI="http://3.82.129.38:8000/";
    }

    @Test
    public void PutRequest(){

        //different ways to send body
        //-String
        //-Using collection (Map)
        //-Pojo

        //Using Map
        Map<String,Object> putMap = new HashMap<>();

       putMap.put("name","MikePUT");
        putMap.put("gender","Male");
        putMap.put("phone",8877423456L);



       // we going to send  response body with updated value, and content type header

         given().contentType(ContentType.JSON)
                .and().pathParams("id", 209)
                .and().body(putMap)
                 .when().put("/api/spartans/{id}")
                 .then().assertThat().statusCode(204);
    }

    @Test
    public void PUTCHRequest(){

        //different ways to send body
        //-String
        //-Using collection (Map)
        //-Pojo

        //Using Map
        Map<String,Object> putchMap = new HashMap<>();
        putchMap.put("name","JackPATCH");

        // we going to send  response body with updated value, and content type header

        given().contentType(ContentType.JSON)
                .and().pathParams("id",209)
                .and().body(putchMap)
                 .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }

}
