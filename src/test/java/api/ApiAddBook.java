package api;

import api.JsonArray.ArrayItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import config.config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import org.aeonbits.owner.ConfigFactory;

public class ApiAddBook {

  private config cfg = ConfigFactory.create(config.class);
  public ApiLogin apiLogin;
  public ApiGetToken apiGetToken;

  public void addBooK() {
    apiLogin = new ApiLogin();
    apiGetToken = new ApiGetToken();
    String userId = apiLogin.getUserIdValue();
    JsonArray jsonArray = new JsonArray();
    jsonArray.setUserId(userId);
    jsonArray.setCollectionOfIsbns(List.of(ArrayItem.builder().isbn("9781449325862").build()));
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    RestAssured.given()
        .auth().preemptive().basic(cfg.userName(), cfg.password())
        .contentType(ContentType.JSON)
        .body(jsonArray)
        .log().all()
        .post(cfg.booksPath())
        .then()
        .log().all();
  }
}
