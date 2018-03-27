package lucien.albert.outerspacemanager.main;

import android.content.Context;

import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.auth.user.UserModel;
import lucien.albert.outerspacemanager.services.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainPresenterInterface, Callback<UserModel> {

    private MainViewInterface mainView;

    public MainPresenter(MainViewInterface mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getUser(Context context) {
        String token = new AuthModel().getToken(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OuterSpaceManagerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService osmService = retrofit.create(OuterSpaceManagerService.class);
        Call<UserModel> request = osmService.getUser(token);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
        if (response.isSuccessful()) this.mainView.onUserExist(response.body());
        else this.mainView.onUserNotExist();
    }

    @Override
    public void onFailure(Call<UserModel> call, Throwable t) {
        this.mainView.onUserNotExist();
    }

}

