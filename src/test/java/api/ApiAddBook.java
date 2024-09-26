package api;

import api.model.IsbnPartialModel;
import api.model.RequestBookModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.Collections;
import test.BaseApiTest;

public class ApiAddBook extends BaseApiTest {

  public ApiLogin apiLogin = new ApiLogin();

  public void addBooK() {
    String userId = apiLogin.getUserIdValue();
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn("9781449325862");
    requestBookModel.setUserId(userId);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));

    RestAssured.given()
        .auth().preemptive().basic(cfg.userName(), cfg.password())
        .contentType(ContentType.JSON)
        .body(requestBookModel)
        .log().all()
        .post(cfg.booksPath())
        .then()
        .log().all();
  }
}
