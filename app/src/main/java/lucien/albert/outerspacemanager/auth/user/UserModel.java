package lucien.albert.outerspacemanager.auth.user;

public class UserModel {

    private String username;
    private String email;
    private String password;

    public UserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
