package test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

import api.ApiGetToken;
import api.ApiLogin;
import api.JsonArray;
import api.JsonArray.ArrayItem;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import config.config;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.testng.Assert;


public class TestBookMarket {

  private config cfg = ConfigFactory.create(config.class);
  public ApiLogin apiLogin;
  public ApiGetToken apiGetToken;

  @BeforeEach
  public void setup() {
    apiLogin = new ApiLogin();
    apiGetToken = new ApiGetToken();
    RestAssured.baseURI = cfg.baseUri();
    Configuration.pageLoadStrategy = cfg.pageLoadStrategy();
    Configuration.browserSize = cfg.browserSize();
  }

  @Test
  @DisplayName("Добавляем книгу в коллекцию")
  @Step("Добавляем книгу в коллекцию")
  public void addBooK() {
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

  @Test
  @Step("Удаляем книгу из коллекции")
  @DisplayName("Удаляем книгу из коллекции")
  public void deleteBool() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    String userId = apiLogin.getUserIdValue();
    String token = apiGetToken.getTokenValue();
    String expires = apiGetToken.getExpiresValue();
    open(cfg.baseUri());
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", expires));
    step("Открываем авторизованную под пользователе страницу" + cfg.profilePath(), () -> {
      open(cfg.profilePath());
    });
    step("Кликаем по кнопку 'удалить'", () -> {
      $x("//span[@id='delete-record-undefined']").click();
    });
    step("Потверждаем удаление книги", () -> {
      $x("//button[@id='closeSmallModal-ok']").click();
    });
    step("Получаем сообщение об удаление книги", () -> {
      Alert delBook = switchTo().alert();
      String delBook_text = delBook.getText();
      System.out.println(delBook_text);
      Assert.assertEquals(delBook_text, "Book deleted.");
    });
  }
}
