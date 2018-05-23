package lucien.albert.outerspacemanager.api.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.building.BuildingViewInterface;

/**
 * Created by lalbert on 18/04/2018.
 */

public interface BuildActionDialogInterface {

    public void onClickPositiveCreateBuilding();

    public void onClickNegativeCreateBuilding();

}
