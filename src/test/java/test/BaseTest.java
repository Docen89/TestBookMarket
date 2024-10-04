package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;

import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;

import java.io.File;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

  public static config.MyConfig cfg = ConfigFactory.create(config.MyConfig.class,
      System.getProperties());

  @BeforeAll
  public static void setup() {
    String browserSize =
        System.getProperty("browserSize");
    String baseUrl =
        System.getProperty("baseURI");
    String pageLoadStrategy =
        System.getProperty("pageLoadStrategy");
    Configuration.pageLoadStrategy =
        pageLoadStrategy != null ? pageLoadStrategy : cfg.pageLoadStrategy();
    Configuration.browserSize =
        browserSize != null ? browserSize : cfg.browserSize();
    RestAssured.baseURI =
        baseUrl != null ? baseUrl : cfg.baseUri();
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));

  }

  @AfterEach
  public void tearDown() throws IOException {
    screenshot();
  }

  @Attachment(type = "image/png")
  public byte[] screenshot() throws IOException {
    File screenshot = Screenshots.getLastScreenshot();
    return screenshot == null ? null : Files.toByteArray(screenshot);
  }
}


