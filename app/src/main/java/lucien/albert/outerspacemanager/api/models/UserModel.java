package lucien.albert.outerspacemanager.api.models;

public class UserModel {

    private String username;
    private Integer points;
    private Float gas;
    private Integer gasModifier;
    private Float minerals;
    private Integer mineralsModifier;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public Integer getPoints ()
    {
        return points;
    }

    public void setPoints (Integer points)
    {
        this.points = points;
    }

    public Float getGas ()
    {
        return gas;
    }

    public void setGas (Float gas)
    {
        this.gas = gas;
    }

    public Integer getGasModifier ()
    {
        return gasModifier;
    }

    public void setGasModifier (Integer gasModifier)
    {
        this.gasModifier = gasModifier;
    }

    public Float getMinerals ()
    {
        return minerals;
    }

    public void setMinerals (Float minerals)
    {
        this.minerals = minerals;
    }

    public Integer getMineralsModifier ()
    {
        return mineralsModifier;
    }

    public void setMineralsModifier (Integer mineralsModifier)
    {
        this.mineralsModifier = mineralsModifier;
    }
}
