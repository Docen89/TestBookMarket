package test;

import static com.codeborne.selenide.Selenide.switchTo;

import GUI.DeleteBook;
import api.ApiAddBook;
import GUI.AddCookie;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.testng.Assert;

public class ApiTestBookMarket extends BaseApiTest {

  public ApiAddBook apiAddBook = new ApiAddBook();
  public AddCookie addCookie = new AddCookie();
  public DeleteBook deleteBook = new DeleteBook();

  @Test
  @DisplayName("Удаляем книгу из коллекции")
  public void deleteBook() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    apiAddBook.addBooK();
    addCookie.addCookie();
    deleteBook.deleteBook();
    Alert delBook = switchTo().alert();
    String delBookText = delBook.getText();
    Assert.assertEquals(delBookText, "Book deleted.");
  }
}
