package api;

import static test.BaseTest.cfg;

import api.model.IsbnPartialModel;
import api.model.RequestBookModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Collections;
import java.util.Map;


public class ApiAddBook {

  public api.ApiLogin apiLogin = new api.ApiLogin();


  public void addBooK() {
    Map<String, String> tokenAndExpiresAndUserIdValue = apiLogin.authLoginGetUserid();
    String userId = apiLogin.authLoginGetUserid().get("userId");
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn("9781449325862");
    requestBookModel.setUserId(userId);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));

    RestAssured.given()
        .auth()
        .preemptive()
        .basic(cfg.userName(), cfg.password())
        .contentType(ContentType.JSON)
        .body(requestBookModel)
        .log()
        .all()
        .post(cfg.booksPath())
        .then()
        .log()
        .all();

  }
}
