package test;

import com.codeborne.selenide.Configuration;
import config.config;
import org.aeonbits.owner.ConfigFactory;

public class BaseGuiTest {

  public static config cfg = ConfigFactory.create(config.class);

  public static void setup() {
    Configuration.pageLoadStrategy = cfg.pageLoadStrategy();
    Configuration.browserSize = cfg.browserSize();
  }
}
