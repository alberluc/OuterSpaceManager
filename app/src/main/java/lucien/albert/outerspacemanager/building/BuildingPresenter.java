package lucien.albert.outerspacemanager.building;

import android.os.Build;

import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;
import lucien.albert.outerspacemanager.api.services.OuterSpaceManagerService;
import lucien.albert.outerspacemanager.dao.BuildingDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingPresenter implements BuildingPresenterInterface{

    BuildingViewInterface buildingView;
    BuildingDAO buildingDAO;

    public BuildingPresenter(BuildingViewInterface buildingView)
    {
        this.buildingView = buildingView;
        this.buildingDAO = new BuildingDAO();
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
                    for (BuildingModel buildingModel : response.body().getBuildings()) {
                        buildingModel.completeFromRealm();
                    }
                    BuildingPresenter.this.buildingView.onBuildingsListSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<BuildingsListModel> call, Throwable t) {}
        });
    }

    @Override
    public void createBuilding(String token, final BuildingModel buildingModel, final Integer position) {
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
                    BuildingPresenter.this.buildingDAO.createBuilding(buildingModel);
                    BuildingPresenter.this.buildingView.onBuildingCreateSuccess(position, buildingModel);
                }
                else BuildingPresenter.this.buildingView.onBuildingCreateFailure();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {}
        });
    }

}
