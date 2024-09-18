package test;

import static com.codeborne.selenide.Selenide.open;

import api.ApiGetToken;
import api.ApiLogin;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;


public class AddBookToMarket {
  public ApiLogin apiLogin;
  public ApiGetToken apiGetToken;

  @BeforeEach
  public   void setup(){
    apiLogin = new ApiLogin();
    apiGetToken = new ApiGetToken();
  }

  @Test
  @DisplayName("Добавляем книгу в магазин")
  public void addBooK(){
         RestAssured.given()
        .auth().preemptive().basic("docen","Docen1313!")
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/Array.json"))
        .log().all()
        .post("https://demoqa.com/BookStore/v1/Books")
        .then()
        .log().all();
  }

  @Test
  @DisplayName("Удаляем книгу из магазина")
  public  void  deleteBool(){
    String userId=apiLogin.getUserIdValue();
    String  token =apiGetToken.getTokenValue();
    String expires =apiGetToken.getExpiresValue();
    System.out.println(userId);
    System.out.println(token);
    System.out.println(expires);
    open("https://demoqa.com");
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID",userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token",token));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires",expires));
    open("https://demoqa.com/profile");
    Selenide.sleep(30000);
  }


}
