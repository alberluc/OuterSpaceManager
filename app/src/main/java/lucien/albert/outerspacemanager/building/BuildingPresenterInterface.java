package lucien.albert.outerspacemanager.building;

import android.content.Context;

import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;

/**
 * Created by lalbert on 18/04/2018.
 */

public interface BuildingPresenterInterface {

    public void getBuildingsList (String token);

    public void createBuilding (String token, BuildingModel buildingModel, Integer position);

}
