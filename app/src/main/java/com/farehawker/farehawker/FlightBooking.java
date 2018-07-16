package com.farehawker.farehawker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FlightBooking extends AppCompatActivity {
    LinearLayout oneAndRoundLinearLayout;
    Button oneWayButton, roundTripButton, multiCityButton;
    TextView returnTextView;
    EditText roundTripEditText;
    LinearLayout roundTripLinearLayout;
    LinearLayout multicityLinearLayout;
    EditText flightDateEditText;
    EditText displayTravelClassDialog;
    Calendar myCalendar = Calendar.getInstance();
    ImageView fligtSeatImageView;
    //Day month and year for DatePickerDilog
    int year = Calendar.YEAR;
    int month = Calendar.MONTH;
    int day = Calendar.DAY_OF_MONTH;


    //Images in front of buttons
    ImageView oneWaybuttonImageView, roundTripButtonImageView, multicityButtonImageView;

    AlertDialog.Builder builder;

    public void changeTextViewColor(View view) {

        String tag = (String) view.getTag();
        Log.i("Log", "" + view.getTag());
        switch (tag) {

            case "1":
                //Changing text Color of buttons when one way button pressed
                oneWayButton.setTextColor(Color.parseColor("#FFFFFF"));
                roundTripButton.setTextColor(Color.parseColor("#000000"));
                multiCityButton.setTextColor(Color.parseColor("#000000"));

                //Changing state of button
                oneWayButton.setBackground(getResources().getDrawable(R.drawable.pressed));
                roundTripButton.setBackground(getResources().getDrawable(R.drawable.normal));
                multiCityButton.setBackground(getResources().getDrawable(R.drawable.normal));

                roundTripLinearLayout.setVisibility(View.GONE);
                multicityLinearLayout.setVisibility(View.GONE);
                oneAndRoundLinearLayout.setVisibility(View.VISIBLE);
                //Changing the visibility of buttons
                oneWaybuttonImageView.setVisibility(View.VISIBLE);
                roundTripButtonImageView.setVisibility(View.GONE);
                multicityButtonImageView.setVisibility(View.GONE);
                break;
            case "2":
                //Changing text Color of buttons when round trip button pressed
                //selected button text color will be set as white and others are
                //black
                oneWayButton.setTextColor(Color.parseColor("#000000"));
                roundTripButton.setTextColor(Color.parseColor("#FFFFFF"));
                multiCityButton.setTextColor(Color.parseColor("#000000"));
                roundTripButton.setBackground(getResources().getDrawable(R.drawable.pressed));
                oneWayButton.setBackground(getResources().getDrawable(R.drawable.normal));
                multiCityButton.setBackground(getResources().getDrawable(R.drawable.normal));
                roundTripLinearLayout.setVisibility(View.VISIBLE);
                oneAndRoundLinearLayout.setVisibility(View.VISIBLE);
                multicityLinearLayout.setVisibility(View.GONE);

                //Changing the visibility of buttons
                oneWaybuttonImageView.setVisibility(View.GONE);
                roundTripButtonImageView.setVisibility(View.VISIBLE);
                multicityButtonImageView.setVisibility(View.GONE);

                break;
            case "3":
                //Changing text Color of buttons when muticity button pressed
                //selected button text color will be set as white and others are
                //black
                oneWayButton.setTextColor(Color.parseColor("#000000"));
                roundTripButton.setTextColor(Color.parseColor("#000000"));
                multiCityButton.setTextColor(Color.parseColor("#FFFFFF"));

                multiCityButton.setBackground(getResources().getDrawable(R.drawable.pressed));
                oneWayButton.setBackground(getResources().getDrawable(R.drawable.normal));
                roundTripButton.setBackground(getResources().getDrawable(R.drawable.normal));
                roundTripLinearLayout.setVisibility(View.GONE);
                oneAndRoundLinearLayout.setVisibility(View.GONE);
                multicityLinearLayout.setVisibility(View.VISIBLE);

                //Changing the visibility of buttons
                oneWaybuttonImageView.setVisibility(View.GONE);
                roundTripButtonImageView.setVisibility(View.GONE);
                multicityButtonImageView.setVisibility(View.VISIBLE);

                break;
        }

    }

    public void displayTravelClassDialog(View view) {
        Log.i("Log", "EditText Tapped");
        final String[] classes = getResources().getStringArray(R.array.travelClassArray);
        AlertDialog.Builder builder = new AlertDialog.Builder(FlightBooking.this);
        builder.setTitle("Travel Class");
        builder.setItems(classes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                // will set  your selection on EditText

                displayTravelClassDialog.setText(classes[item]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void showDatePicker(View view) {
        Log.i("showDatePicker", "method invoked");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "Date Picker");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_booking);
        oneWayButton = (Button) findViewById(R.id.oneWayButton);
        roundTripButton = (Button) findViewById(R.id.roundTripButton);
        multiCityButton = (Button) findViewById(R.id.multicityButton);
        roundTripEditText = (EditText) findViewById(R.id.roundTripEditText);
        returnTextView = (TextView) findViewById(R.id.returnTextView);
        roundTripLinearLayout = (LinearLayout) findViewById(R.id.roundTripLinearLayout);
        oneAndRoundLinearLayout = (LinearLayout) findViewById(R.id.oneAndRoundLinearLayout);

        //images in front of buttons
        oneWaybuttonImageView = (ImageView) findViewById(R.id.oneWaybuttonImageView);
        roundTripButtonImageView = (ImageView) findViewById(R.id.roundTripbuttonImageView);
        multicityButtonImageView = (ImageView) findViewById(R.id.multicityTripbuttonImageView);

        //connecting EditText with displayTravelClassDialog to java program here
        displayTravelClassDialog = (EditText) findViewById(R.id.displayTravelClassDialog);

        //oneWayButton.setCompoundDrawablesWithIntrinsicBounds( tickIcon, null, null, null );
        multicityLinearLayout = (LinearLayout) findViewById(R.id.multicityLinearLayout);
        oneAndRoundLinearLayout.setVisibility(View.VISIBLE);

        oneWaybuttonImageView.setVisibility(View.VISIBLE);
        roundTripButtonImageView.setVisibility(View.GONE);
        multicityButtonImageView.setVisibility(View.GONE);

        //Changing state of button
        oneWayButton.setBackground(getResources().getDrawable(R.drawable.pressed));
        roundTripButton.setBackground(getResources().getDrawable(R.drawable.normal));
        multiCityButton.setBackground(getResources().getDrawable(R.drawable.normal));
        //Changing colour of flight one way button  to white
        oneWayButton.setTextColor(Color.parseColor("#FFFFFF"));


        //Edit Text For Date Picker Dialog
        flightDateEditText = (EditText) findViewById(R.id.flightDateEditText);
        flightDateEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /* new DatePickerDialog(FlightBooking.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        */
                DatePickerDialog datePickerDialog = new DatePickerDialog(FlightBooking.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                flightDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.DAY_OF_MONTH);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


    }// End of onCreate

    public void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        flightDateEditText.setText(sdf.format(myCalendar.getTime()));
    }//End of updateLabel method

}//End of FlightBooking class