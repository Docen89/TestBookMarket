package test;

import static com.codeborne.selenide.Selenide.open;

import api.ApiGetToken;
import api.ApiLogin;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


public class AddBookToMarket {
   private Response response;
  private ApiLogin apiLogin;
  private ApiGetToken apiGetToken;

  @Test
  @DisplayName("Добавляем книгу в магазин")
  public void addBooK(){
    Response responceAddBook = RestAssured.given()
        .auth().preemptive().basic("docen","Docen1313!")
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/Array.json"))
        .log().all()
        .post("https://demoqa.com/BookStore/v1/Books")
        .then()
        .log().all()
        .extract().response();
  }

  @Test
  @DisplayName("Удаляем книгу из магазина")
  public  void  deleteBool(){
    String userId=apiLogin.getUserIdvalue();
    String  token =apiGetToken.getTokenValue();
    String expires =apiGetToken.getExpiresValue();
    System.out.println(userId);
    apiLogin.authLoginGetUserid();
    apiGetToken.getTokenAndExpires();
    open("https://demoqa.com");
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID",userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token",token));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires",expires));
    open("https://demoqa.com/profile");
  }


}
