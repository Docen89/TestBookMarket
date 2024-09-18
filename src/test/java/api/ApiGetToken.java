package api;

import config.config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.aeonbits.owner.ConfigFactory;

public class ApiGetToken {
  private config cfg = ConfigFactory.create(config.class);
  private Response responseValue =null;


   public Response getTokenAndExpires(){
     RestAssured.baseURI=cfg.baseUri();
     if(responseValue!=null){
       return responseValue;
     }
    responseValue= RestAssured. given()
         .contentType(ContentType.JSON)
         .body(new File("src/test/resources/AuthData.json"))
         .log().all()
         .when()
         .post(cfg.tokenPath())
         .then()
         .log().all()
         .extract().response();
     return responseValue;
  }

  public  String getTokenValue(){

     return getTokenAndExpires().path("token");
  }

  public String getExpiresValue(){

     return getTokenAndExpires().path("expires");
  }

}
