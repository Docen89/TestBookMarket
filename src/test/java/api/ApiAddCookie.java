package api;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.WebDriverRunner;
import config.config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;

public class ApiAddCookie {

  private config cfg = ConfigFactory.create(config.class);
  public ApiLogin apiLogin;
  public ApiGetToken apiGetToken;

  public void addCookie() {
    apiLogin = new ApiLogin();
    apiGetToken = new ApiGetToken();
    String userId = apiLogin.getUserIdValue();
    String token = apiGetToken.getTokenValue();
    String expires = apiGetToken.getExpiresValue();
    open(cfg.baseUri());
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", expires));
    open(cfg.profilePath());
    $x("//span[@id='delete-record-undefined']").click();
    $x("//button[@id='closeSmallModal-ok']").click();
  }
}