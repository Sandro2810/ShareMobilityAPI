package heg.tb.ShareMobilityAPI;

import java.util.List;
import javax.lang.model.util.Elements;

public class ApiResponse {

  public Geometry geometry;

  public Attributes attributes;


  @Override
  public String toString() {
    return "ApiResponse{" +
        "geometry=" + geometry +
        ", attributes=" + attributes +
        '}';
  }
}
