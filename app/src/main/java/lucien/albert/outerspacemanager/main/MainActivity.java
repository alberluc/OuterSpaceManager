package lucien.albert.outerspacemanager.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.auth.user.UserModel;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private MainPresenterInterface mainPresenter;
    private TextView textViewMainUsername;
    private TextView textViewMainScore;

    private Button buttonMainGeneral;
    private Button buttonMainBuilding;
    private Button buttonMainShip;
    private Button buttonMainSearch;
    private Button buttonMainSpaceConstruction;
    private Button buttonMainSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.textViewMainUsername = this.findViewById(R.id.textViewMainUsername);
        this.textViewMainScore = this.findViewById(R.id.textViewMainScore);
        this.buttonMainGeneral = this.findViewById(R.id.buttonMainGeneral);
        this.buttonMainBuilding = this.findViewById(R.id.buttonMainBuilding);
        this.buttonMainShip = this.findViewById(R.id.buttonMainShip);
        this.buttonMainSearch = this.findViewById(R.id.buttonMainSearch);
        this.buttonMainSpaceConstruction = this.findViewById(R.id.buttonMainSpaceConstruction);
        this.buttonMainSpace = this.findViewById(R.id.buttonMainSpace);

        this.mainPresenter = new MainPresenter(this);
    }

    @Override
    public void onUserExist(UserModel user) {

    }

    @Override
    public void onUserNotExist() {

    }

}
