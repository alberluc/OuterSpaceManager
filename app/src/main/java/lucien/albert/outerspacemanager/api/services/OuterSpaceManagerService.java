package lucien.albert.outerspacemanager.api.services;

import lucien.albert.outerspacemanager.api.models.AuthUserModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;
import lucien.albert.outerspacemanager.api.models.AuthModel;
import lucien.albert.outerspacemanager.api.models.TokenModel;
import lucien.albert.outerspacemanager.api.models.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OuterSpaceManagerService {

    String BASE_URL = "https://outer-space-manager-staging.herokuapp.com/api/";

    /* Authentication */

    @POST("v1/auth/create")
    public Call<TokenModel> createUser (@Body AuthUserModel user);

    @POST("v1/auth/login")
    public Call<TokenModel> loginUser (@Body AuthUserModel user);

    @GET("v1/users/get")
    public Call<UserModel> getUser (@Header("x-access-token") String token);

    @GET("v1/buildings/list")
    public Call<BuildingsListModel> getBuildingsList (@Header("x-access-token") String token);

    @POST("v1/buildings/create/{buildingId}")
    public Call<Object> createBuilding (@Header("x-access-token") String token, @Path("buildingId") Integer buildingId);

}
