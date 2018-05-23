package lucien.albert.outerspacemanager.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import lucien.albert.outerspacemanager.Config;
import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.api.models.AuthModel;
import lucien.albert.outerspacemanager.auth.AuthActivity;
import lucien.albert.outerspacemanager.api.models.UserModel;
import lucien.albert.outerspacemanager.building.BuildingActivity;

public class MainActivity extends AppCompatActivity implements MainViewInterface, View.OnClickListener {

    private MainPresenterInterface mainPresenter;

    private TextView textViewMainUsername;
    private TextView textViewMainScore;
    private Button buttonMainGeneral;
    private Button buttonMainBuilding;
    private Button buttonMainShip;
    private Button buttonMainSearch;
    private Button buttonMainSpaceConstruction;
    private Button buttonMainSpace;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Realm.init(this.getApplicationContext());

        String token = AuthModel.getToken(this.getApplicationContext());

        this.mainPresenter = new MainPresenter(this);
        if (!this.mainPresenter.checkUserHasToken(token)) {
            Intent mainIntent = new Intent(this.getApplicationContext(), AuthActivity.class);
            this.startActivity(mainIntent);
        }
        this.mainPresenter.getUser(token);

        this.textViewMainUsername = this.findViewById(R.id.textViewMainUsername);
        this.textViewMainScore = this.findViewById(R.id.textViewMainScore);
        this.buttonMainGeneral = this.findViewById(R.id.buttonMainGeneral);
        this.buttonMainBuilding = this.findViewById(R.id.buttonMainBuilding);
        this.buttonMainShip = this.findViewById(R.id.buttonMainShip);
        this.buttonMainSearch = this.findViewById(R.id.buttonMainSearch);
        this.buttonMainSpaceConstruction = this.findViewById(R.id.buttonMainSpaceConstruction);
        this.buttonMainSpace = this.findViewById(R.id.buttonMainSpace);
        this.buttonLogout = this.findViewById(R.id.buttonLogout);

        this.buttonLogout.setOnClickListener(this);
        this.buttonMainBuilding.setOnClickListener(this);
    }

    @Override
    public void onUserExist(UserModel user)
    {
        this.textViewMainUsername.setText(user.getUsername());
        this.textViewMainScore.setText(String.valueOf(user.getPoints()));
    }

    @Override
    public void onUserNotExist () {}

    @Override
    public void onClick (View v)
    {
        if (v.getId() == this.buttonLogout.getId()) {
            AuthModel.removeUserToken(this.getApplicationContext());
            this.startAuthActivity();
        }
        else if (v.getId() == this.buttonMainBuilding.getId()) {
            this.startBuildingActivity();
        }
    }

    private void startAuthActivity()
    {
        Intent intent = new Intent(this.getApplicationContext(), AuthActivity.class);
        this.startActivity(intent);
    }

    public void startBuildingActivity ()
    {
        Intent intent = new Intent(this.getApplicationContext(), BuildingActivity.class);
        this.startActivity(intent);
    }

}
