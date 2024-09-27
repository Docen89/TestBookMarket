package test;

import static com.codeborne.selenide.Selenide.switchTo;

import gui.DeleteBook;
import api.ApiAddBook;
import gui.AddCookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.testng.Assert;

public class ApiTestBookMarket extends BaseTest {

  public ApiAddBook apiAddBook = new ApiAddBook();
  public AddCookie addCookie = new AddCookie();
  public DeleteBook deleteBook = new DeleteBook();

  @Test
  @DisplayName("Удаляем книгу из коллекции")
  public void deleteBook() {
    apiAddBook.addBooK();
    addCookie.addCookie();
    deleteBook.deleteBook();
    Alert delBook = switchTo().alert();
    String delBookText = delBook.getText();
    Assert.assertEquals(delBookText, "Book deleted.");

  }
}
