package lucien.albert.outerspacemanager.auth;

import android.content.Context;

import java.util.Objects;

import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.auth.user.UserModel;
import lucien.albert.outerspacemanager.services.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lalbert on 27/03/2018.
 */

public class AuthPresenter implements AuthPresenterInterface {

    private AuthViewInterface authView;

    public AuthPresenter(AuthViewInterface authView) {
        this.authView = authView;
    }

    @Override
    public boolean checkUserHasToken(Context context) {
        return !new AuthModel().getToken(context).equals("");
    }

}
