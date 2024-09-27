package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.config;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class BaseTest {

  public static config cfg = ConfigFactory.create(config.class);

  public static void setup() {
    Configuration.pageLoadStrategy = cfg.pageLoadStrategy();
    Configuration.browserSize = cfg.browserSize();
    RestAssured.baseURI = cfg.baseUri();
    SelenideLogger.addListener("allure", new AllureSelenide());

  }
}
