package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import test.BaseTest;


public class ApiLogin extends BaseTest {


  public Map<String, String> authLoginGetUserid() {
    Response response= RestAssured.given()
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
    Map<String, String> tokenAndExpiresAndUserIdValue = new HashMap<>();
    tokenAndExpiresAndUserIdValue.put("token", response.path("token"));
    tokenAndExpiresAndUserIdValue.put("expires", response.path("expires"));
    tokenAndExpiresAndUserIdValue.put("userId",response.path("userId"));
    return tokenAndExpiresAndUserIdValue;

  }

}
