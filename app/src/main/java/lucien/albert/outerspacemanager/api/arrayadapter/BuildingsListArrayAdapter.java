package lucien.albert.outerspacemanager.api.arrayadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.api.models.BuildingModel;

/**
 * Created by lalbert on 18/04/2018.
 */

public class BuildingsListArrayAdapter extends ArrayAdapter<BuildingModel> {

    private Context context;
    private List<BuildingModel> buildingModels;

    public BuildingsListArrayAdapter (Context context, List<BuildingModel> buildingModels)
    {
        super(context, R.layout.row_building, buildingModels);
        this.context = context;
        this.buildingModels = buildingModels;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        BuildingModel buildingModel = this.buildingModels.get(position);
        View rowView = this.getRowView(parent);
        TextView textViewRowBuildingName = rowView.findViewById(R.id.textViewRowBuildingName);
        ImageView imageViewRowBuilding = rowView.findViewById(R.id.imageViewRowBuilding);
        Glide.with(this.getContext()).load(buildingModel.getImageUrl()).into(imageViewRowBuilding);
        textViewRowBuildingName.setText(buildingModel.getName());
        return rowView;
    }

    private View getRowView (ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.row_building, parent, false);
    }

}
