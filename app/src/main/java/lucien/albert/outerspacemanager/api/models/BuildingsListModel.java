package lucien.albert.outerspacemanager.api.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalbert on 18/04/2018.
 */

public class BuildingsListModel {

    private Integer size;
    private List<BuildingModel> buildings;

    public BuildingsListModel() {
        this.buildings = new ArrayList<>();
    }

    public Integer getSize ()
    {
        return size;
    }

    public void setSize (Integer size)
    {
        this.size = size;
    }

    public List<BuildingModel> getBuildings ()
    {
        return buildings;
    }

    public void setBuildings (List<BuildingModel> buildings)
    {
        this.buildings = buildings;
    }
}
