package test;


import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Cookie;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ApiLoginOld {
  public Response response;
  public String tokenvalue;
  public String useridvalue;


//  @Test
//  @Order(1)
//    public   void getProfileUserIdAndToken() {
//        response=RestAssured.given()
//        .contentType(ContentType.JSON)
//        .body(new File("src/test/resources/AuthData.json"))
//        .when()
//        .post("https://demoqa.com/Account/v1/Login")
//        .then()
//        .log().all()
//        .statusCode(200)
//        .extract().response();
//
//        String token = response.path("token");
//        String userId = response.path("userId");
//        System.out.println(token+userId);
//        tokenvalue=token;
//        useridvalue=userId;
//
//
//
//  }
//  @Test
//  @Order(2)
//     public void getProfilCookie() {
//      Response responsegetcookie = RestAssured.given()
//              .contentType(ContentType.JSON)
//             .body(new File("src/test/resources/AuthData.json"))
//        .when()
//             .log().uri()
//        .post("https://demoqa.com/Account/v1/GenerateToken");
//    responsegetcookie
//        .then()
//        .log().all()
//        .statusCode(200)
//        .extract().response();
//
//    String cookieValueExpires = responsegetcookie.getCookie("expires");
//    String cookieValueUserId = responsegetcookie.getCookie("userID");
//    String cookieValueToken = responsegetcookie.getCookie("token");
//    System.out.println(cookieValueToken);
//    System.out.println(cookieValueExpires);
//    System.out.println(cookieValueUserId);
//  }
//
//
//  @Test
//  @Order(3)
//  public void addbooksMarket() {
//    Response getBook = RestAssured.given()
//        .auth().preemptive().basic("docen","Docen1313!")
//        .header("Authorization","Bearer "+tokenvalue)
//        .header("Content-Type","application/json;charset=utf-8")
//        .body(new File("src/test/resources/Array.json"))
//        .when()
//        .log().all()
//        .post("https://demoqa.com/BookStore/v1/Books")
//            .then()
//                .log().body()
//            .extract().response();
//  }

  @Test
   void getLogin2(){
    Response responseLogin = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .log().all()
        .when()
        .post("https://demoqa.com/Account/v1/Login")
        .then()
        .log().all()
        .extract().response();
    String userId=responseLogin.path("userId");


    Response  responseGenerateToken = RestAssured. given()
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/AuthData.json"))
        .log().all()
        .when()
        .post("https://demoqa.com/Account/v1/GenerateToken")
        .then()
        .log().all()
        .extract().response();
    String tokenValue2 = responseGenerateToken.path("token");
    String expiresValue2 = responseGenerateToken.path("expires");
    System.out.println(tokenValue2);
    System.out.println(expiresValue2);
    System.out.println(userId);


    Response responseAddBook2 =RestAssured.given()
        .auth().preemptive().basic("docen","Docen1313!")
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/Array.json"))
        .log().all()
        .post("https://demoqa.com/BookStore/v1/Books")
        .then()
        .log().all()
        .extract().response();


    open("https://demoqa.com");
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID",userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token",tokenValue2));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires",expiresValue2));
    open("https://demoqa.com/profile");
    Selenide.sleep(30000);
  }
}