package lucien.albert.outerspacemanager.main;

import lucien.albert.outerspacemanager.api.models.UserModel;

public interface MainViewInterface {

    void onUserExist(UserModel body);
    void onUserNotExist();

}
