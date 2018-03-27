package lucien.albert.outerspacemanager.auth.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import lucien.albert.outerspacemanager.Config;

public class AuthModel {

    public final static String TOKEN_KEY_NAME = "token";

    private String token;
    private String expires;

    public AuthModel() {}

    @SuppressLint("ApplySharedPref")
    public void storeToken (Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(Config.FILE_PREFERENCES, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(AuthModel.TOKEN_KEY_NAME, this.token);
        editor.commit();
    }

    public String getToken (Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(Config.FILE_PREFERENCES,0);
        return settings.getString(AuthModel.TOKEN_KEY_NAME, "");
    }

}
