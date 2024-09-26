package test;

import config.config;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = cfg.baseUri();
  }
}
