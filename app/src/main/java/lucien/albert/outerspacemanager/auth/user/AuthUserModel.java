package lucien.albert.outerspacemanager.auth.user;

/**
 * Created by lalbert on 27/03/2018.
 */

public class AuthUserModel {

    private static AuthUserModel intance = null;
    private UserModel user;

    public void setUser (UserModel user)
    {
        this.user = user;
    }

    public static AuthUserModel getIntance ()
    {
        if (AuthUserModel.intance == null) {
            AuthUserModel.intance = new AuthUserModel();
        }
        return AuthUserModel.intance;
    }

}
