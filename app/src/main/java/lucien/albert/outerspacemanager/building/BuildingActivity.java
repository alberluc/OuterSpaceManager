package lucien.albert.outerspacemanager.building;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.api.arrayadapter.BuildingAdapter;
import lucien.albert.outerspacemanager.api.dialog.BuildActionDialog;
import lucien.albert.outerspacemanager.api.dialog.BuildActionDialogInterface;
import lucien.albert.outerspacemanager.api.models.AuthModel;
import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;

public class BuildingActivity extends Activity implements BuildingViewInterface {

    BuildingPresenterInterface buildingPresenter;
    RecyclerView recyclerViewBuildings;
    BuildingsListModel buildingsListModel;
    BuildingAdapter buildingAdapter;
    BuildActionDialog buildActionDialog;
    BuildingModel buildingModelClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_building);

        this.buildingPresenter = new BuildingPresenter(this);
        this.buildingPresenter.getBuildingsList(AuthModel.getToken(this.getApplicationContext()));
        this.buildingsListModel = new BuildingsListModel();

        this.recyclerViewBuildings = this.findViewById(R.id.recyclerViewBuildings);
        this.recyclerViewBuildings.setHasFixedSize(true);
        LinearLayoutManager layoutManagerBuilding = new LinearLayoutManager(this);
        this.recyclerViewBuildings.setLayoutManager(layoutManagerBuilding);


        this.buildingAdapter = new BuildingAdapter(this.getApplicationContext(), this.buildingsListModel.getBuildings(), this);
        this.recyclerViewBuildings.setAdapter(this.buildingAdapter);
    }

    @Override
    public void onBuildingsListSuccess (BuildingsListModel buildingsListModel)
    {
        this.buildingAdapter.addAll(buildingsListModel);
        this.buildingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItem (final BuildingModel buildingModel) {
        if (buildingModel.isBuildable()) {
            this.buildingModelClicked = buildingModel;
            this.buildActionDialog = new BuildActionDialog(this, new BuildActionDialogInterface() {
                @Override
                public void onClickPositiveCreateBuilding() {
                    BuildingActivity.this.buildingPresenter.createBuilding(AuthModel.getToken(getApplicationContext()), buildingModel);
                    // todo: Faire une callback pour récuperer le building crée et changer son état
                }
                @Override
                public void onClickNegativeCreateBuilding() {}
            });
            this.buildActionDialog.show();
        }
    }

    @Override
    public void onBuildingCreateSuccess () {
        Toast.makeText(this.getApplicationContext(), "Succès", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBuildingCreateFailure () {
        Toast.makeText(this.getApplicationContext(), "Echec", Toast.LENGTH_SHORT).show();
    }

}
