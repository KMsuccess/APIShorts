package ApiTests;

import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SpartanTestWithPathMethod {

    @BeforeClass
    public  void setUpClass() {
        RestAssured.baseURI = "http://100.26.222.8:8000/";
    }

    /*
    Given accept type is Json
    And path parameter id is 10
    When user sends get request to "api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json"
    And response payload values match the following:
    id is 10,
    name is "Lorenza"
    gender is "Female"
    phone is 3312820936
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        // printing values of json keys
        System.out.println("id:" + response.body().path("id").toString());
        System.out.println("name:" + response.body().path("name").toString());
        System.out.println("gender:" + response.body().path("gender").toString());
        System.out.println("phone:" + response.body().path("phone").toString());

         int id = response.path("id");
         String name = response.body().path("name");
         String gender = response.body().path("gender");
         long phone = response.body().path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
//verify json keys and values
        Assert.assertEquals(id,10);
        Assert.assertEquals(name,"Lorenza");
        Assert.assertEquals(gender,"Female");
        Assert.assertEquals(phone,3312820936l);

    }
  @Test
    public  void  test2(){

        Response response = RestAssured.get("/api/spartans");  // import static io.restassured.RestAssured. *; !?

      // extract 1 id
      int firstId = response.path("id[0]");
      System.out.println("firstId = " + firstId);

      // extract name
      String first1Name = response.path("name[0]");
      System.out.println("first1Name = " + first1Name);

      //get the last first name
      String  last1stName = response.path("name[-1]");
      System.out.println("last1stName = " + last1stName);

      //extract all first names and print them
      List<String> names = response.path("name");
      System.out.println(names);
      System.out.println("names = " + names.size());

      List<Object>phoneNumbers = response.path("phone");

      for (Object phoneNumber:phoneNumbers){
          System.out.println(phoneNumber);
      }

  }

}
