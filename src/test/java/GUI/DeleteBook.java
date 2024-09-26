package GUI;

import static com.codeborne.selenide.Selenide.$x;

import test.BaseGuiTest;

public class DeleteBook extends BaseGuiTest {

  public void deleteBook() {
    $x("//span[@id='delete-record-undefined']").click();
    $x("//button[@id='closeSmallModal-ok']").click();
  }
}
