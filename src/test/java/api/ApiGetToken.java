package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;

public class ApiGetToken {
   public Response responseGetToken;

   public void getTokenAndExpires(){
     Response  responseGenerateToken = RestAssured. given()
         .contentType(ContentType.JSON)
         .body(new File("src/test/resources/AuthData.json"))
         .log().all()
         .when()
         .post("https://demoqa.com/Account/v1/GenerateToken")
         .then()
         .log().all()
         .extract().response();
  }

  public  String getTokenValue(){
     return responseGetToken.path("token");
  }

  public String getExpiresValue(){
     return responseGetToken.path("expires");
  }

}
