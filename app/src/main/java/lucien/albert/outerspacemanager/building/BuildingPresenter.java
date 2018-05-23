package lucien.albert.outerspacemanager.building;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import lucien.albert.outerspacemanager.api.models.AuthModel;
import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;
import lucien.albert.outerspacemanager.api.services.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingPresenter implements BuildingPresenterInterface{

    BuildingViewInterface buildingView;

    public BuildingPresenter(BuildingViewInterface buildingView)
    {
        this.buildingView = buildingView;
    }

    @Override
    public void getBuildingsList(String token)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OuterSpaceManagerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService osmService = retrofit.create(OuterSpaceManagerService.class);
        Call<BuildingsListModel> request = osmService.getBuildingsList(token);
        request.enqueue(new Callback<BuildingsListModel>() {
            @Override
            public void onResponse(Call<BuildingsListModel> call, Response<BuildingsListModel> response) {
                if (response.isSuccessful()) {
                    BuildingPresenter.this.buildingView.onBuildingsListSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<BuildingsListModel> call, Throwable t) {}
        });
    }

    @Override
    public void createBuilding(String token, final BuildingModel buildingModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OuterSpaceManagerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService osmService = retrofit.create(OuterSpaceManagerService.class);
        Call<Object> request = osmService.createBuilding(token, buildingModel.getBuildingId());
        request.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    BuildingPresenter.this.buildingView.onBuildingCreateSuccess();
                    BuildingPresenter.this.createBuildingRealm(buildingModel);
                }
                else BuildingPresenter.this.buildingView.onBuildingCreateFailure();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {}
        });
    }

    private void createBuildingRealm (BuildingModel buildingModel) {
        Realm realm = Realm.getDefaultInstance();
        BuildingModel buildingModelRealm = realm.createObject(BuildingModel.class, buildingModel.getBuildingId());
        buildingModelRealm.setLastDateBuild(Calendar.getInstance().getTime());
        realm.insertOrUpdate(buildingModelRealm);
    }

}
