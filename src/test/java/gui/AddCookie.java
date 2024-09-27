package gui;

import static com.codeborne.selenide.Selenide.open;

import api.ApiGetToken;
import api.ApiLogin;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import java.util.Map;
import org.openqa.selenium.Cookie;
import test.BaseTest;

public class AddCookie extends BaseTest {

  public ApiLogin apiLogin = new ApiLogin();
  public ApiGetToken apiGetToken = new ApiGetToken();

  public void addCookie() {
    Map<String,String> tokenAndExpiresValue =apiGetToken.getTokenAndExpiresValue();
    String userId = apiLogin.getUserIdValue();
    open(cfg.baseUri());
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", userId));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", tokenAndExpiresValue.get("token")));
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", tokenAndExpiresValue.get("expires")));
    open(cfg.profilePath());

  }
}