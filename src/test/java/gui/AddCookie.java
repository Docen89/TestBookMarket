package gui;

import static com.codeborne.selenide.Selenide.open;
import static test.BaseTest.cfg;


import api.ApiLogin;
import com.codeborne.selenide.WebDriverRunner;
import java.util.Map;
import org.openqa.selenium.Cookie;


public class AddCookie {

  public ApiLogin apiLogin = new ApiLogin();

  public void addCookie() {
    Map<String, String> tokenAndExpiresAndUserIdValue = apiLogin.authLoginGetUserid();
    open(cfg.baseUri());
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("userID",tokenAndExpiresAndUserIdValue.get("userId")));
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("token", tokenAndExpiresAndUserIdValue.get("token")));
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("expires", tokenAndExpiresAndUserIdValue.get("expires")));
    open(cfg.profilePath());

  }
}