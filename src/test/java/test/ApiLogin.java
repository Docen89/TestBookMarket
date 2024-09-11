package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ApiLogin {
  public Response response;
  public String tokenvalue;
  public String useridvalue;
  @Test
    public   void getProfileUserIdAndToken() {
        response=RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .when()
        .post("https://demoqa.com/Account/v1/Login")
        .then()
        .log().all()
        .statusCode(200)
        .extract().response();

        String token = response.path("token");
        String userId = response.path("userId");
        System.out.println(token+userId);
        tokenvalue=token;
        useridvalue=userId;



  }
  @Test
     public void getProfilCookie() {
      Response responsegetcookie = RestAssured.given()
             .header("Content-Type", "application/json")
             .header("Authorization", "Bearer "+tokenvalue)
        .when()
             .log().uri()
        .get("https://demoqa.com/Account/v1/User/"+useridvalue);
    responsegetcookie
        .then()
        .log().all()
        .statusCode(200)
        .extract().response();

    String cookieValueExpires = responsegetcookie.getCookie("expires");
    String cookieValueUserId = responsegetcookie.getCookie("userID");
    String cookieValueToken = responsegetcookie.getCookie("token");
    System.out.println(cookieValueToken);
    System.out.println(cookieValueExpires);
    System.out.println(cookieValueUserId);


  }


  @Test
  public void addbooksMarket(){

  }
}