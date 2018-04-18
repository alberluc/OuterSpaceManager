package lucien.albert.outerspacemanager.building;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.api.arrayadapter.BuildingsListArrayAdapter;
import lucien.albert.outerspacemanager.api.dialog.BuildActionDialog;
import lucien.albert.outerspacemanager.api.models.AuthModel;
import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;

public class BuildingActivity extends Activity implements BuildingViewInterface, AdapterView.OnItemClickListener {

    BuildingPresenterInterface buildingPresenter;
    ListView listViewBuildings;
    BuildingsListModel buildingsListModel;
    BuildingsListArrayAdapter buildingsListArrayAdapter;
    BuildActionDialog buildActionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_building);

        this.buildingPresenter = new BuildingPresenter(this);
        this.buildingPresenter.getBuildingsList(AuthModel.getToken(this.getApplicationContext()));
        this.buildingsListModel = new BuildingsListModel();

        this.listViewBuildings = this.findViewById(R.id.listViewBuildings);

        this.buildActionDialog = new BuildActionDialog(this);

        this.buildingsListArrayAdapter = new BuildingsListArrayAdapter(this.getApplicationContext(), this.buildingsListModel.getBuildings());
        this.listViewBuildings.setAdapter(buildingsListArrayAdapter);
        this.listViewBuildings.setOnItemClickListener(this);
    }

    @Override
    public void onBuildingsListSuccess (BuildingsListModel buildingsListModel)
    {
        this.buildingsListModel = buildingsListModel;
        this.buildingsListArrayAdapter.addAll(this.buildingsListModel.getBuildings());
        this.buildingsListArrayAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        BuildingModel buildingModel = this.buildingsListModel.getBuildings().get(position);
        if (buildingModel.isBuildable()) {

        }
    }

}
