package lucien.albert.outerspacemanager.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.auth.AuthViewFragmentsInterface;
import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.main.MainActivity;

public class LoginFragment extends Fragment implements AuthViewFragmentsInterface, View.OnClickListener {

    private LoginPresenter loginProvider;
    private EditText editTextLoginUsername;
    private EditText editTextLoginPassword;
    private Button buttonLogin;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View currentView = inflater.inflate(R.layout.fragment_login, container, false);
        this.loginProvider = new LoginPresenter(this);
        this.editTextLoginUsername = currentView.findViewById(R.id.editTextLoginUsername);
        this.editTextLoginPassword = currentView.findViewById(R.id.editTextLoginPassword);
        this.buttonLogin = currentView.findViewById(R.id.buttonLogin);

        this.buttonLogin.setOnClickListener(this);

        return currentView;
    }

    @Override
    public void onClick (View v)
    {
        if (v.getId() == this.buttonLogin.getId()) {
            this.loginProvider.login(
                this.editTextLoginUsername.getText().toString(),
                this.editTextLoginPassword.getText().toString()
            );
        }
    }

    @Override
    public void onAuthSuccess (AuthModel authModel)
    {
        Toast.makeText(this.getContext(), R.string.success_login, Toast.LENGTH_SHORT).show();
        authModel.storeToken(this.getContext());
        Intent mainIntent = new Intent(this.getContext(), MainActivity.class);
        this.startActivity(mainIntent);
    }

    @Override
    public void onAuthFailure (int code)
    {
        switch (code) {
            case 401: {
                Toast.makeText(this.getContext(), R.string.unauthorized_login, Toast.LENGTH_SHORT).show();
                break;
            }
            case 504: {
                Toast.makeText(this.getContext(), R.string.server_not_respond, Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(this.getContext(), String.valueOf(code), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void resetFields()
    {
        this.editTextLoginPassword.setText("");
    }

}
