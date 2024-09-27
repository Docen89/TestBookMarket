package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import test.BaseTest;


public class ApiLogin extends BaseTest {


  public Response authLoginGetUserid() {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .log()
        .all()
        .when()
        .post(cfg.loginPath())
        .then()
        .log()
        .all()
        .extract()
        .response();

  }

  public String getUserIdValue() {

    return authLoginGetUserid().path("userId");

  }
}
