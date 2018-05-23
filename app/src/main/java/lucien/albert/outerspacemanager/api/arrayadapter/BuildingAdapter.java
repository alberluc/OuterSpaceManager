package lucien.albert.outerspacemanager.api.arrayadapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.api.models.BuildingModel;
import lucien.albert.outerspacemanager.api.models.BuildingsListModel;
import lucien.albert.outerspacemanager.building.BuildingViewInterface;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private BuildingViewInterface buildingView;
    private Context context;
    private List<BuildingModel> buildings;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewRowBuildingName) TextView textViewRowBuildingName;
        @BindView(R.id.imageViewRowBuilding) ImageView imageViewRowBuilding;
        @BindView(R.id.textViewBuildingLevel) TextView textViewBuildingLevel;
        @BindView(R.id.textViewBuildingResource) TextView textViewBuildingResource;
        @BindView(R.id.textViewBuildingTime) TextView textViewBuildingTime;
        @BindView(R.id.buttonActionBuilding) Button buttonActionBuilding;
        @BindView(R.id.row_building_state_stopping) LinearLayout layoutStateStopping;
        @BindView(R.id.row_building_state_building) LinearLayout layoutStateBuilding;

        public ViewHolder (View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

    public BuildingAdapter (Context context, List<BuildingModel> buildings, BuildingViewInterface buildingView) {
        this.buildings = buildings;
        this.context = context;
        this.buildingView = buildingView;
    }

    @Override
    public BuildingAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_building, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        final BuildingModel building = this.buildings.get(position);
        Glide.with(this.context).load(building.getImageUrl()).into(holder.imageViewRowBuilding);
        holder.textViewRowBuildingName.setText(building.getName());
        holder.textViewBuildingLevel.setText(building.levelToString(this.context));
        holder.textViewBuildingResource.setText(building.getResourceString(this.context));
        holder.textViewBuildingTime.setText(building.timeToString(this.context));
        holder.buttonActionBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildingAdapter.this.buildingView.onClickItem(building);
            }
        });
        if (building.getBuilding()) {
            holder.layoutStateStopping.setVisibility(View.INVISIBLE);
            holder.layoutStateBuilding.setVisibility(View.VISIBLE);
        }
        else {
            holder.layoutStateStopping.setVisibility(View.VISIBLE);
            holder.layoutStateBuilding.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount () {
        return this.buildings.size();
    }

    public void addAll (BuildingsListModel buildingsListModel) {
        this.buildings = buildingsListModel.getBuildings();
    }

}
