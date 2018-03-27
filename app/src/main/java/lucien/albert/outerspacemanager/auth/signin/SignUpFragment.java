package lucien.albert.outerspacemanager.auth.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.auth.AuthViewFragmentsInterface;
import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.main.MainActivity;

public class SignUpFragment extends Fragment implements OnClickListener, AuthViewFragmentsInterface {

    private SignUpPresenter signUpPresenter;
    private EditText editTextSignUpUsername;
    private EditText editTextSignUpEmail;
    private EditText editTextSignUpPassword;
    private Button buttonSignUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View currentView = inflater.inflate(R.layout.fragment_signup, container, false);
        this.editTextSignUpUsername = currentView.findViewById(R.id.editTextSignUpUsername);
        this.editTextSignUpEmail = currentView.findViewById(R.id.editTextSignUpEmail);
        this.editTextSignUpPassword = currentView.findViewById(R.id.editTextSignUpPassword);
        this.buttonSignUp = currentView.findViewById(R.id.buttonSignUp);

        this.signUpPresenter = new SignUpPresenter(this);
        this.buttonSignUp.setOnClickListener(this);

        return currentView;
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == this.buttonSignUp.getId()) {
            this.signUpPresenter.signup(
                this.editTextSignUpUsername.getText().toString(),
                this.editTextSignUpEmail.getText().toString(),
                this.editTextSignUpPassword.getText().toString()
            );
        }
    }

    @Override
    public void onAuthSuccess(AuthModel authModel)
    {
        Toast.makeText(this.getContext(), R.string.signin_success, Toast.LENGTH_SHORT).show();
        authModel.storeToken(this.getContext());
        Intent mainIntent = new Intent(this.getContext(), MainActivity.class);
        this.startActivity(mainIntent);
    }

    @Override
    public void onAuthFailure(int code)
    {
        switch (code) {
            case 400: {
                Toast.makeText(this.getContext(), R.string.bad_request_signin, Toast.LENGTH_SHORT).show();
                break;
            }
            case 401: {
                Toast.makeText(this.getContext(), R.string.unauthorized_signin, Toast.LENGTH_SHORT).show();
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
        this.editTextSignUpPassword.setText("");
    }
}
