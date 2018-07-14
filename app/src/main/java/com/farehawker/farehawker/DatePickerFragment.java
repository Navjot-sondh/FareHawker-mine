package com.farehawker.farehawker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH+1);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),dateSetListener,year,month,day);
    }
    DatePickerDialog.OnDateSetListener dateSetListener  = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day)
        {
            //Toast.makeText(getActivity(),"Selected date is "+datePicker.getYear()+"/"+datePicker.getMonth()+"/"+datePicker.getDayOfMonth(),Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }


}
