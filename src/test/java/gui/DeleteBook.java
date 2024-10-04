package gui;

import static com.codeborne.selenide.Selenide.$x;



public class DeleteBook  {

  public void deleteBook() {
    $x("//span[@id='delete-record-undefined']").click();
    $x("//button[@id='closeSmallModal-ok11']").click();

  }
}
