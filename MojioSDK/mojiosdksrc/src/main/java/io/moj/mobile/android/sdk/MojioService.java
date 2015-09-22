package io.moj.mobile.android.sdk;

import java.util.List;

import io.moj.mobile.android.sdk.models.Mojio;
import io.moj.mobile.android.sdk.models.User;
import io.moj.mobile.android.sdk.models.Vehicle;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by skidson on 15-09-21.
 */
public class MojioService {

    private final String appId;
    private final String redirectUri;

    private String accessToken;
    private String apiHost;
    private String signalRHost;

    public MojioService(final String appId, final String redirectUri) {
        this.appId = appId;
        this.redirectUri = redirectUri;
    }

    public interface API {
        @GET("/v1/Vehicles")
        Call<List<Vehicle>> getVehicles();

        @GET("/v1/Users/{id}/Vehicles")
        Call<List<Vehicle>> getUserVehicles();

        @GET("/v1/Users/{id}/Mojios")
        Call<List<Mojio>> getUserMojios();

        @GET("/v1/Users")
        Call<List<User>> getUsers();

        @GET("/v1/Users/{id}")
        Call<User> getUser(@Path("id") String userId);
    }

}
