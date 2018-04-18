package lucien.albert.outerspacemanager.auth;

import lucien.albert.outerspacemanager.api.models.TokenModel;

public interface AuthViewFragmentsInterface {

    public void onAuthSuccess (TokenModel tokenModel);

    public void onAuthFailure (int code);

    public void resetFields ();

}
