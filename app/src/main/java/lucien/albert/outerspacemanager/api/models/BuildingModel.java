package lucien.albert.outerspacemanager.api.models;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lucien.albert.outerspacemanager.R;

public class BuildingModel extends RealmObject {

    @PrimaryKey
    private Integer buildingId;
    private Integer level;
    private Integer amountOfEffectByLevel;
    private Integer amountOfEffectLevel0;
    private Boolean building;
    private String effect;
    private Integer gasCostByLevel;
    private Integer gasCostLevel0;
    private String imageUrl;
    private Integer mineralCostByLevel;
    private Integer mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;
    private Date lastDateBuild;

    public Integer getLevel ()
    {
        return level;
    }

    public void setLevel (Integer level)
    {
        this.level = level;
    }

    public Integer getAmountOfEffectByLevel ()
    {
        return amountOfEffectByLevel;
    }

    public void setAmountOfEffectByLevel (Integer amountOfEffectByLevel)
    {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
    }

    public Integer getAmountOfEffectLevel0 ()
    {
        return amountOfEffectLevel0;
    }

    public void setAmountOfEffectLevel0 (Integer amountOfEffectLevel0)
    {
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
    }

    public Integer getBuildingId ()
    {
        return buildingId;
    }

    public void setBuildingId (Integer buildingId)
    {
        this.buildingId = buildingId;
    }

    public Boolean getBuilding ()
    {
        return building;
    }

    public void setBuilding (Boolean building)
    {
        this.building = building;
    }

    public String getEffect ()
    {
        return effect;
    }

    public void setEffect (String effect)
    {
        this.effect = effect;
    }

    public Integer getGasCostByLevel ()
    {
        return gasCostByLevel;
    }

    public void setGasCostByLevel (Integer gasCostByLevel)
    {
        this.gasCostByLevel = gasCostByLevel;
    }

    public Integer getGasCostLevel0 ()
    {
        return gasCostLevel0;
    }

    public void setGasCostLevel0 (Integer gasCostLevel0)
    {
        this.gasCostLevel0 = gasCostLevel0;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public Integer getMineralCostByLevel ()
    {
        return mineralCostByLevel;
    }

    public void setMineralCostByLevel (Integer mineralCostByLevel)
    {
        this.mineralCostByLevel = mineralCostByLevel;
    }

    public Integer getMineralCostLevel0 ()
    {
        return mineralCostLevel0;
    }

    public void setMineralCostLevel0 (Integer mineralCostLevel0)
    {
        this.mineralCostLevel0 = mineralCostLevel0;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Integer getTimeToBuildByLevel ()
    {
        return timeToBuildByLevel;
    }

    public void setTimeToBuildByLevel (Integer timeToBuildByLevel)
    {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public Integer getTimeToBuildLevel0 ()
    {
        return timeToBuildLevel0;
    }

    public void setTimeToBuildLevel0 (Integer timeToBuildLevel0)
    {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }

    public Date getLastDateBuild () {
        return lastDateBuild;
    }

    public void setLastDateBuild (Date lastDateBuild) {
        this.lastDateBuild = lastDateBuild;
    }

    public boolean isBuildable ()
    {
        return true;
    }

    public long getTimeToBuild () {
        return this.getTimeToBuildLevel0() + (this.getTimeToBuildByLevel() * this.getLevel());
    }

    public long getGasToBuild () {
        return this.getGasCostLevel0() + (this.getGasCostByLevel() * this.getLevel());
    }

    public long getMineralToBuild () {
        return this.getMineralCostLevel0() + (this.getMineralCostByLevel() * this.getLevel());
    }

    public String getResourceString (Context context) {
        String resourceString = this.getMineralToBuild() + " " + context.getResources().getString(R.string.resource_meniral) + " - " + this.getGasToBuild() + ' ' + context.getResources().getString(R.string.resource_gas);
        return context.getResources().getString(R.string.building_label_resource) + " : "  + resourceString;
    }

    public String levelToString (Context context) {
        return context.getResources().getString(R.string.building_label_level) + " : " + String.valueOf(this.getLevel());
    }

    public String timeToString (Context context) {
        return context.getResources().getString(R.string.building_label_time) + " : "  + String.valueOf(this.getTimeToBuild());
    }

    public Float getRemainingTime () {
        long diff = Calendar.getInstance().getTime().getTime() - this.getLastDateBuild().getTime();
        return (float) diff / this.getTimeToBuild();
    }

}
