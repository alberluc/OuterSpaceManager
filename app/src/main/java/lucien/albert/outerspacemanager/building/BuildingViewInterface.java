package lucien.albert.outerspacemanager.building;

import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;

/**
 * Created by lalbert on 18/04/2018.
 */

public interface BuildingViewInterface {

    public void onBuildingsListSuccess (BuildingsListModel buildingsListModel);

    public void onBuildingCreateSuccess ();

    public void onBuildingCreateFailure ();

    public void onClickItem (BuildingModel building);

}
