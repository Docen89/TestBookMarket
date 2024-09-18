package api;

import config.config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

public class ApiLogin {
  private config cfg = ConfigFactory.create(config.class);
  public Response responseLogin;

  public Response authLoginGetUserid(){
    RestAssured.baseURI=cfg.baseUri();
     return RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .log().all()
        .when()
        .post(cfg.loginPath())
        .then()
        .log().all()
        .extract().response();


  }
  public String getUserIdValue(){
    return authLoginGetUserid().path("userId");
  }
}
