package lucien.albert.outerspacemanager.auth;

import lucien.albert.outerspacemanager.auth.user.AuthModel;

public interface AuthViewFragmentsInterface {

    public void onAuthSuccess (AuthModel authModel);

    public void onAuthFailure (int code);

    public void resetFields ();

}
