package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.junit.jupiter.api.Test;

public class ApiLogin {
  public Response responseLogin;

  public Response authLoginGetUserid(){
     return RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .log().all()
        .when()
        .post("https://demoqa.com/Account/v1/Login")
        .then()
        .log().all()
        .extract().response();


  }
  public String getUserIdValue(){
    return authLoginGetUserid().path("userId");
  }
}
