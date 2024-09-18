package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface config extends Config {

  @Key("base.uri")
  String baseUri();

  @Key("login.path")
  String loginPath();

  @Key("token.path")
  String tokenPath();

  @Key("books.path")
  String booksPath();

  @Key("profile.path")
  String profilePath();

  @Key("user.name")
  String userName();

  @Key("password")
  String password();
}