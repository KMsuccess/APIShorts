package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import  static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileFilter;
import java.util.List;
import java.util.Map;

public class SpartanJsonToCollections {

    @BeforeClass
    public void SetUpClass(){
        baseURI="http://100.26.222.8:8000/";
    }

    /*
    Given accept type is Json
    And id parameter value is 11
    When user sends get request to spartans/{id}
    Then  status code is 200
    And  content-type is json
    And    "id": 11,
        "name": "Nona",
        "gender": "Female",
        "phone": 7959094216
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        // convert Json response to Java Collections(Map)

       Map<String ,Object> spartanMap =  response.body().as(Map.class);

        System.out.println(spartanMap.get("name"));          // Nona
        System.out.println(spartanMap.get("id"));           // 11.0
        System.out.println(spartanMap.get("gender"));      // Female
      //  System.out.println(spartanMap.get("phone"));    // ???   7.959094216E9

        //Assert.assertEquals()
        // one example verification one side Map/ expected valueth
        assertEquals(spartanMap.get("name"),"Nona");

  //???   //   assertEquals(spartanMap.get("phone"),7959094216l);

    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

       // response.prettyPrint();
        // convert full json body to list of maps
      List<Map<String,Object>> listOfSpartans = response.body().as(List.class);

      //print all data of first spartan
        System.out.println(listOfSpartans.get(0));

        Map<String,Object>  firstSpartan = listOfSpartans.get(0);
        System.out.println(firstSpartan.get("name"));

        int count = 1;
        for(Map<String,Object> map : listOfSpartans ){
            System.out.println(count + " _ spartan "+ map);
            count++;
        }
    }


}
