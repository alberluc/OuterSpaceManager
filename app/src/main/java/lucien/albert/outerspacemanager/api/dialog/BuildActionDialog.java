package lucien.albert.outerspacemanager.api.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import lucien.albert.outerspacemanager.R;

/**
 * Created by lalbert on 18/04/2018.
 */

public class BuildActionDialog implements DialogInterface.OnClickListener {

    private Context context;

    public BuildActionDialog (Context context)
    {
        this.context = context;
    }

    public void show ()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(R.string.alert_build_action_title)
                .setMessage(R.string.alert_build_action_message)
                .setPositiveButton(android.R.string.yes, this)
                .setNegativeButton(android.R.string.no, this)
                .setIcon(R.drawable.icon_tools_black)
                .show();
    }

    @Override
    public void onClick (DialogInterface dialog, int which)
    {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE : {
                this.handlePositiveResponse();
                break;
            }
            case DialogInterface.BUTTON_NEGATIVE : {
                this.handleNegativeResponse();
                break;
            }
        }
    }

    private void handleNegativeResponse ()
    {
        Toast.makeText(this.context, "Negative", Toast.LENGTH_SHORT).show();
    }

    private void handlePositiveResponse ()
    {
        Toast.makeText(this.context, "Positive", Toast.LENGTH_SHORT).show();
    }
}
