package lucien.albert.outerspacemanager.dao;
import java.util.Calendar;
import lucien.albert.outerspacemanager.api.models.BuildingModel;

public class BuildingDAO extends DAO {

    public void createBuilding (BuildingModel buildingModel) {
        this.realm.beginTransaction();
        buildingModel.setLastDateBuild(Calendar.getInstance().getTime());
        this.realm.insertOrUpdate(buildingModel);
        this.realm.commitTransaction();
    }

    public BuildingModel getById (Integer id) {
        return this.realm.where(BuildingModel.class).equalTo("buildingId", id).findFirst();
    }

}
