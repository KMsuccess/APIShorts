package ApiTests;


import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import  static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileFilter;
import java.util.List;
import java.util.Map;





public class SpartanTestPOJODeserialization {

    @BeforeClass
    public void SetUpClass(){
        baseURI="http://100.26.222.8:8000/";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        // GSON ---> de-serialization
       // how to convert json response to our spartan class
        Spartan spartan1 = response.body().as(Spartan.class);

         // verify each key with spartan object
       assertEquals(spartan1.getName(),"Meta");
       assertEquals(spartan1.getId(),15);
       assertEquals(spartan1.getGender(),"Female");
       assertEquals(spartan1.getPhone(),new Long(1938695106l));


    }
    @Test
    public void  gsonExample(){
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
        //using gson method do de serialize our json body  // converting json body to Spartan class (custom class)
        Spartan spartanMeta = gson.fromJson(myJsonBody,Spartan.class);

        System.out.println(spartanMeta.toString());

    //serialization Java object ---> JSON Body

        Spartan spartan = new Spartan(101,"Mike","Male",321342123l);
        //converting custom class to json (serialization)
        String jsonbody = gson.toJson(spartan);

        System.out.println(jsonbody);

       
    }
}
