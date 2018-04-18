package lucien.albert.outerspacemanager.api.models;

/**
 * Created by lalbert on 18/04/2018.
 */

public class TokenModel {

    public final static String TOKEN_KEY_NAME = "token";

    private String token;
    private String expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
