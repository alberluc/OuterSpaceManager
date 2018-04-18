package lucien.albert.outerspacemanager.api.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import lucien.albert.outerspacemanager.Config;

public class AuthModel {

    public AuthModel() {}

    @SuppressLint("ApplySharedPref")
    public static void storeToken (Context context, String token)
    {
        SharedPreferences settings = context.getSharedPreferences(Config.FILE_PREFERENCES, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TokenModel.TOKEN_KEY_NAME, token);
        editor.commit();
    }

    public static String getToken (Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(Config.FILE_PREFERENCES,0);
        return settings.getString(TokenModel.TOKEN_KEY_NAME, "");
    }

    public static void removeUserToken (Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(Config.FILE_PREFERENCES, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

}
