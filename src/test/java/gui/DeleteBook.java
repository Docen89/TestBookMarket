package gui;

import static com.codeborne.selenide.Selenide.$x;

import test.BaseTest;

public class DeleteBook extends BaseTest {

  public void deleteBook() {
    $x("//span[@id='delete-record-undefined']").click();
    $x("//button[@id='closeSmallModal-ok']").click();

  }
}
