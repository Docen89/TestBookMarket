package test;

import static com.codeborne.selenide.Selenide.switchTo;

import api.ApiAddBook;
import api.ApiAddCookie;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.config;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

public class TestBookMarket {

  private config cfg = ConfigFactory.create(config.class);
  public ApiAddBook apiAddBook;
  public ApiAddCookie apiAddCookie;

  @BeforeEach
  public void setup() {
    apiAddBook = new ApiAddBook();
    apiAddCookie = new ApiAddCookie();
    RestAssured.baseURI = cfg.baseUri();
    Configuration.pageLoadStrategy = cfg.pageLoadStrategy();
    Configuration.browserSize = cfg.browserSize();
  }

  @Test
  @Step("Удаляем книгу из коллекции")
  @DisplayName("Удаляем книгу из коллекции")
  public void deleteBook() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    apiAddBook.addBooK();
    apiAddCookie.addCookie();
    Alert delBook = switchTo().alert();
    String delBookText = delBook.getText();
    System.out.println(delBookText);
  }
}
