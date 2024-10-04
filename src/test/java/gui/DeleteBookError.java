package gui;

import static com.codeborne.selenide.Selenide.$x;

public class DeleteBookError {

    public void deleteBookError() {
      $x("//span[@id='delete-record-undefined']").click();
      $x("//button[@id='closeSmallModal-ok1']").click();

    }

}
