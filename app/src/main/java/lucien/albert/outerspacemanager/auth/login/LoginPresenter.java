package lucien.albert.outerspacemanager.auth.login;

import lucien.albert.outerspacemanager.api.models.AuthUserModel;
import lucien.albert.outerspacemanager.api.models.TokenModel;
import lucien.albert.outerspacemanager.auth.AuthViewFragmentsInterface;
import lucien.albert.outerspacemanager.api.models.UserModel;
import lucien.albert.outerspacemanager.api.services.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginPresenterInterface, Callback<TokenModel> {

    private AuthViewFragmentsInterface authViewFragments;

    public LoginPresenter(AuthViewFragmentsInterface authViewFragments)
    {
        this.authViewFragments = authViewFragments;
    }

    @Override
    public void login (String username, String password)
    {
        AuthUserModel user = new AuthUserModel(username, password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OuterSpaceManagerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService osmService = retrofit.create(OuterSpaceManagerService.class);
        Call<TokenModel> request = osmService.loginUser(user);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<TokenModel> call, Response<TokenModel> response)
    {
        if (response.isSuccessful()) {
            this.authViewFragments.onAuthSuccess(response.body());
        }
        else {
            this.authViewFragments.onAuthFailure(response.code());
            this.authViewFragments.resetFields();
        }
    }

    @Override
    public void onFailure(Call<TokenModel> call, Throwable t)
    {
        this.authViewFragments.onAuthFailure(504);
    }

}
