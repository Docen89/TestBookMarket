package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import test.BaseTest;


public class ApiGetToken extends BaseTest {

  public String token;
  public String expires;

  public  Map<String, String> getTokenAndExpiresValue() {
    RestAssured.baseURI = cfg.baseUri();
    Response response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File
            ("src/test/resources/AuthData.json"))
        .log().all()
        .when()
        .post(cfg.tokenPath())
        .then()
        .log().all()
        .extract().response();
    Map<String, String> tokenAndExpiresValue = new HashMap<>();
    tokenAndExpiresValue.put("token", response.path("token"));
    tokenAndExpiresValue.put("expires", response.path("expires"));
    return tokenAndExpiresValue;
  }
}
