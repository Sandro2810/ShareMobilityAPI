package heg.tb.ShareMobilityAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.util.List;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
        Request request = new Request.Builder()
            .url("https://api.sharedmobility.ch/v1/sharedmobility/identify?\n"
                + "filters=ch.bfe.sharedmobility.vehicle_type=Car"
                + "&Geometry=7.2467909,47.1367785\n"
                + "&Tolerance=200\n"
                + "&offset=0\n"
                + "&geometryFormat=esrijson")
            .method("GET", null)
            .build();
        try {
            okhttp3.Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }




        //création Builder okHttpClient
        OkHttpClient.Builder httpClient = new Builder();

        //requette de base + transformation de reponse en Gson
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.sharedmobility.ch/v1/sharedmobility/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

        //appel à l'interface
        IService service = retrofit.create(IService.class);

        // Requettes avec les paramètres
        //ShareMobility
        //Trotinnettes
        Call<List<ApiResponse>> callSyncTrot = service.getData("ch.bfe.sharedmobility.vehicle_type=E-Scooter","6.6327025,46.5218269", "200","0", "esrijson");
        //voitures
        Call<List<ApiResponse>> callSyncVoiture = service.getData("ch.bfe.sharedmobility.vehicle_type=Car","6.6327025,46.5218269", "1200","0", "esrijson");
        //velo
        Call<List<ApiResponse>> callSyncBike = service.getData("ch.bfe.sharedmobility.vehicle_type=Bike","6.6327025,46.5218269", "200","0", "esrijson");
        //E-CargoBike
        Call<List<ApiResponse>> callSyncCargoBike = service.getData("ch.bfe.sharedmobility.vehicle_type=E-CargoBike","6.6327025,46.5218269", "200","0", "esrijson");
        //E-Car
        Call<List<ApiResponse>> callSyncECar = service.getData("ch.bfe.sharedmobility.vehicle_type=E-Car","6.6327025,46.5218269", "200","0", "esrijson");
        //	E-Bike
        Call<List<ApiResponse>> callSyncEBike = service.getData("ch.bfe.sharedmobility.vehicle_type=E-Bike","6.6327025,46.5218269", "200","0", "esrijson");

        try {
            //Trot
            Response<List<ApiResponse>> responseTrot = callSyncTrot.execute();
            List<ApiResponse> apiResponsetrot = responseTrot.body();
            System.out.println(apiResponsetrot);

            //Bike
            Response<List<ApiResponse>> responseBike = callSyncBike.execute();
            List<ApiResponse> apiResponsebike = responseBike.body();
            System.out.println(apiResponsebike);

            //Car
            Response<List<ApiResponse>> responseCar = callSyncVoiture.execute();
            List<ApiResponse> apiResponseCar = responseCar.body();
            System.out.println(apiResponseCar);

            //CargoBike
            Response<List<ApiResponse>> responseCargoBike = callSyncCargoBike.execute();
            List<ApiResponse> apiResponseCargoBike = responseCargoBike.body();
            System.out.println(apiResponseCargoBike);

            //E-car
            Response<List<ApiResponse>> responseEcar = callSyncECar.execute();
            List<ApiResponse> apiResponseEcar = responseEcar.body();
            System.out.println(apiResponseEcar);

            //E-Bike
            Response<List<ApiResponse>> responseEbike = callSyncEBike.execute();
            List<ApiResponse> apiResponseEbike = responseEbike.body();
            System.out.println(apiResponseEbike);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
