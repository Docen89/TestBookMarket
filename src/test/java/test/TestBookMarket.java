package test;

import static com.codeborne.selenide.Selenide.open;

import api.ApiGetToken;
import api.ApiLogin;
import api.JsonArray;
import api.JsonArray.ArrayItem;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import config.config;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.File;
import java.util.List;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


public class TestBookMarket {
  private config cfg = ConfigFactory.create(config.class);
  public ApiLogin apiLogin;
  public ApiGetToken apiGetToken;

  @BeforeEach
  public   void setup(){
    apiLogin = new ApiLogin();
    apiGetToken = new ApiGetToken();
    RestAssured.baseURI=cfg.baseUri();
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @Test
  @Step("Добавляем книгу в коллекцию")
  @DisplayName("1")
  public void addBooK(){
    String userId=apiLogin.getUserIdValue();
    JsonArray jsonArray =new JsonArray();
    jsonArray.setUserId(userId);
    jsonArray.setCollectionOfIsbns(List.of(ArrayItem.builder().isbn("9781449325862").build()));
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//    String json =null;
//    try{
//       json = ow.writeValueAsString(jsonArray);
//    }catch (Exception EXp){}

         RestAssured.given()
        .auth().preemptive().basic(cfg.userName(),cfg.password())
        .contentType(ContentType.JSON)
        .body(jsonArray)
        .log().all()
        .post(cfg.booksPath())
        .then()
        .log().all();
  }

  @Test
  @Step("Удаляем книгу из коллекции")
  @DisplayName("2")
  public  void  deleteBool(){
    String userId=apiLogin.getUserIdValue();
    String  token =apiGetToken.getTokenValue();
    String expires =apiGetToken.getExpiresValue();
    System.out.println(userId);
    System.out.println(token);
    System.out.println(expires);
    open(cfg.baseUri());
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID",userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token",token));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires",expires));
    open(cfg.profilePath());
    Selenide.sleep(30000);
  }


}
