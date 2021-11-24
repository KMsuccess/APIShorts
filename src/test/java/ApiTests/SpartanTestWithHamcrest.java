package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileFilter;

public class SpartanTestWithHamcrest {

    @BeforeClass
    public void SetUpClass(){
        baseURI="http://3.82.129.38:8000";
    }

/*
    Given accept type is Json
    And id parameter value is 15
    When user sends get request to spartans/{id}
    Then  status code is 200
    And  content-type is json
    And jason data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */

    @Test
    public void test1(){
        //request
        given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and()
                .assertThat().contentType("application/json");
    }


    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", Matchers.equalTo(15),
                "name",Matchers.equalTo("Meta"),
                "gender",Matchers.equalTo("Female"),
                "phone",Matchers.equalTo(1938695106));
    }

}
