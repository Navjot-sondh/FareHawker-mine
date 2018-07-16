package com.farehawker.farehawker;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
public class AlertDialogWithList extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final String[] travelClassesArray = getResources().getStringArray(R.array.travelClassArray);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.titleTravelClassDialogFragment)
                .setItems(R.array.travelClassArray, new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), travelClassesArray[which], Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog dialog = alertDialogBuilder.create();
       return  alertDialogBuilder.create();
        //return dialog;
    }
}//End of AlertDialogWithList class

