package lucien.albert.outerspacemanager.main;

import lucien.albert.outerspacemanager.auth.user.UserModel;

public interface MainViewInterface {

    void onUserExist(UserModel body);

    void onUserNotExist();

}
