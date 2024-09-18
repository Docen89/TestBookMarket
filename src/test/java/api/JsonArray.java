package api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonArray {
  private String userId;
  private List<ArrayItem> collectionOfIsbns;
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ArrayItem{
    private String isbn;
  }
}
