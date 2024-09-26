package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import test.BaseApiTest;

public class ApiLogin extends BaseApiTest {


  public Response authLoginGetUserid() {
    RestAssured.baseURI = cfg.baseUri();
    return RestAssured.given().contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json")).log().all().when().post(cfg.loginPath())
        .then().log().all().extract().response();
  }

  public String getUserIdValue() {
    return authLoginGetUserid().path("userId");
  }
}
