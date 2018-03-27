package lucien.albert.outerspacemanager.auth.login;

import lucien.albert.outerspacemanager.auth.AuthViewFragmentsInterface;
import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.auth.user.UserModel;
import lucien.albert.outerspacemanager.services.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginPresenterInterface, Callback<AuthModel> {

    private AuthViewFragmentsInterface authViewFragments;

    public LoginPresenter(AuthViewFragmentsInterface authViewFragments)
    {
        this.authViewFragments = authViewFragments;
    }

    @Override
    public void login (String username, String password)
    {
        UserModel user = new UserModel(username, password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OuterSpaceManagerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService osmService = retrofit.create(OuterSpaceManagerService.class);
        Call<AuthModel> request = osmService.loginUser(user);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<AuthModel> call, Response<AuthModel> response)
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
    public void onFailure(Call<AuthModel> call, Throwable t)
    {
        this.authViewFragments.onAuthFailure(504);
    }

}
