package lucien.albert.outerspacemanager.api.arrayadapter;


import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.guilhe.circularprogressview.CircularProgressView;

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

        private static final int STATE_STOPPING = 1;
        private static final int STATE_BUILDING = 2;

        @BindView(R.id.textViewRowBuildingName) public TextView textViewRowBuildingName;
        @BindView(R.id.imageViewRowBuilding) public ImageView imageViewRowBuilding;
        @BindView(R.id.textViewBuildingLevel) public TextView textViewBuildingLevel;
        @BindView(R.id.textViewBuildingResource) public TextView textViewBuildingResource;
        @BindView(R.id.textViewBuildingTime) public TextView textViewBuildingTime;
        @BindView(R.id.buttonActionBuilding) public Button buttonActionBuilding;
        @BindView(R.id.row_building_state_stopping) public LinearLayout layoutStateStopping;
        @BindView(R.id.row_building_state_building) public LinearLayout layoutStateBuilding;
        @BindView(R.id.row_building_progress) public CircularProgressView circleProgressBuidling;

        private CountDownTimer countDownTimer;

        public ViewHolder (View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void setState (Integer state, BuildingModel buildingModel) {
            switch (state) {
                case ViewHolder.STATE_BUILDING: {
                    this.layoutStateStopping.setVisibility(View.INVISIBLE);
                    this.layoutStateBuilding.setVisibility(View.VISIBLE);
                    this.initStateBuilding(buildingModel);
                    break;
                }
                case ViewHolder.STATE_STOPPING: {
                    this.layoutStateStopping.setVisibility(View.VISIBLE);
                    this.layoutStateBuilding.setVisibility(View.INVISIBLE);
                    break;
                }
            }
        }

        public void initStateBuilding (final BuildingModel buildingModel) {
            ViewHolder.this.circleProgressBuidling.setProgress(buildingModel.getRemainingTime());
            ViewHolder.this.countDownTimer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    ViewHolder.this.circleProgressBuidling.setProgress(buildingModel.getRemainingTime());
                }

                @Override
                public void onFinish() {
                    ViewHolder.this.countDownTimer.start();
                }
            };
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
    public void onBindViewHolder (final ViewHolder holder, final int position) {
        final BuildingModel building = this.buildings.get(position);
        Glide.with(this.context).load(building.getImageUrl()).into(holder.imageViewRowBuilding);
        holder.textViewRowBuildingName.setText(building.getName());
        holder.textViewBuildingLevel.setText(building.levelToString(this.context));
        holder.textViewBuildingResource.setText(building.getResourceString(this.context));
        holder.textViewBuildingTime.setText(building.timeToString(this.context));
        holder.buttonActionBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildingAdapter.this.buildingView.onClickItem(building, position);
            }
        });
        if (building.getBuilding()) {
            holder.setState(ViewHolder.STATE_BUILDING, building);
        }
        else {
            holder.setState(ViewHolder.STATE_STOPPING, null);
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
