package heg.tb.ShareMobilityAPI;

import java.util.List;
import okhttp3.Callback;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {

  @GET("identify")
  //définition des paramètres de la requette
  public Call<List<ApiResponse>> getData(@Query("filters") String filters,@Query("Geometry") String geometry, @Query("Tolerance") String tolerance, @Query("offset") String offset ,@Query("geometryFormat") String geometryFormat);


}
