package lucien.albert.outerspacemanager.services;

import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.auth.user.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OuterSpaceManagerService {

    String BASE_URL = "https://outer-space-manager-staging.herokuapp.com/api/";

    /* Authentication */

    @POST("v1/auth/create")
    public Call<AuthModel> createUser (@Body UserModel user);

    @POST("v1/auth/login")
    public Call<AuthModel> loginUser (@Body UserModel user);

    @GET("v1/users/get")
    public Call<UserModel> getUser (@Header("x-access-token") String token);

}
