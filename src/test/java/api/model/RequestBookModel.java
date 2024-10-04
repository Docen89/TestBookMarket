package api.model;

import java.util.List;
import lombok.Data;

@Data
public class RequestBookModel {

  private List<api.model.IsbnPartialModel> collectionOfIsbns;
  private String userId;
}