package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import test.BaseApiTest;

public class ApiGetToken extends BaseApiTest {

  public String token;
  public String expires;

  public ApiGetToken() {
    addMap();
  }

  public void addMap() {
    RestAssured.baseURI = cfg.baseUri();
    Response response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .log().all()
        .when()
        .post(cfg.tokenPath())
        .then()
        .log().all()
        .extract().response();
    Map mapA = new HashMap();
    mapA.put("token", response.path("token"));
    mapA.put("expires", response.path("expires"));
    token = (String) mapA.get("token");
    expires = (String) mapA.get("expires");
  }
}
//  public Response getTokenAndExpires() {
//    RestAssured.baseURI = cfg.baseUri();
//    if (responseValue != null) {
//      return responseValue;
//    }
//
//  }
//
//  public String getTokenValue() {
//
//    return getTokenAndExpires().path("token");
//  }
//
//  public String getExpiresValue() {
//
//    return getTokenAndExpires().path("expires");
////  }
//}
