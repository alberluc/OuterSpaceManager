package lucien.albert.outerspacemanager.building;

import android.content.Context;

import lucien.albert.outerspacemanager.api.models.AuthModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;
import lucien.albert.outerspacemanager.api.services.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingPresenter implements BuildingPresenterInterface, Callback<BuildingsListModel> {

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
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<BuildingsListModel> call, Response<BuildingsListModel> response) {
        if (response.isSuccessful()) {
            this.buildingView.onBuildingsListSuccess(response.body());
        }
    }

    @Override
    public void onFailure(Call<BuildingsListModel> call, Throwable t) {}
}
