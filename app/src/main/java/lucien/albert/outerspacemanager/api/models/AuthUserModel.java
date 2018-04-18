package lucien.albert.outerspacemanager.api.models;

/**
 * Created by lalbert on 18/04/2018.
 */

public class AuthUserModel {

    private String username;
    private String email;
    private String password;

    public AuthUserModel(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AuthUserModel(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

}
