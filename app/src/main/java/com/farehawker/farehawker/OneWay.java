package com.farehawker.farehawker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OneWay extends AppCompatActivity
{
    ScrollView multicityScrollView;
    LinearLayout oneAndRoundLinearLayout,roundTripLinearLayout,multicityLinearLayout,travellersDialog;
    LinearLayout linearLayout2,linearLayout3,linearLayout4,linearLayout5;
    TextView multiCityTextView;
    TextView roundTripTextView;
    Button oneWayButton, roundTripButton, multiCityButton;
    TextView returnTextView;
    TextView flightDateEditText,travellersTextView;
    TextView displayTravelClassDialog;
    TextView displayMcTravelClassDialog;
    Calendar myCalendar = Calendar.getInstance();
    Button okButton;
    //Day month and year for DatePickerDilog
    int year_ = Calendar.YEAR;
    int month = Calendar.MONTH;
    int day = Calendar.DAY_OF_MONTH;

    //return dates for round trip
    int returnYear,returnMonth,returnDay;

    TextView adultsTextView;
    TextView childrenTextView;
    TextView infantsTextView;

    //Images in front of buttons
    ImageView oneWaybuttonImageView, roundTripButtonImageView, multicityButtonImageView;

    AlertDialog.Builder TravellersBuilder;
    AlertDialog alertDialog;

    //Add and remove city in multicity buttons
    Button addCityButton;
    Button removeCityButton;
    static int noOfCities=1;
    public void changeTextViewColor(View view)
    {

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
                multicityScrollView.setVisibility(View.GONE);
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
                multicityScrollView.setVisibility(View.GONE);
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

                /*
                 * It means that the setBackground(Drawable) method was added in API Level 16,
                 * and older devices do not have it. However, your app's minSdkVersion is 14.
                 * So, unless you take steps to avoid this line on API Level 14 and 15 devices,
                 * your app will crash on those devices.
                 * Do not change it unless you want to run it devices running API level 14 or lower
                 * */
                multiCityButton.setBackground(getResources().getDrawable(R.drawable.pressed));
                oneWayButton.setBackground(getResources().getDrawable(R.drawable.normal));
                roundTripButton.setBackground(getResources().getDrawable(R.drawable.normal));
                roundTripLinearLayout.setVisibility(View.GONE);
                oneAndRoundLinearLayout.setVisibility(View.GONE);
                multicityScrollView.setVisibility(View.VISIBLE);
                multicityLinearLayout.setVisibility(View.VISIBLE);

                //Changing the visibility of buttons
                oneWaybuttonImageView.setVisibility(View.GONE);
                roundTripButtonImageView.setVisibility(View.GONE);
                multicityButtonImageView.setVisibility(View.VISIBLE);

                break;
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_booking);
        Log.i("","Inside one way onCreate method");
        multicityScrollView= findViewById(R.id.multicityScrollView);
        roundTripTextView=(TextView) findViewById(R.id.roundTripTextView);
        travellersTextView=(TextView) findViewById(R.id.travellersTextView);
        multiCityTextView=findViewById(R.id.multiCityTextView);
        oneWayButton = (Button) findViewById(R.id.oneWayButton);
        roundTripButton = (Button) findViewById(R.id.roundTripButton);
        multiCityButton = (Button) findViewById(R.id.multicityButton);
        roundTripTextView = (TextView) findViewById(R.id.roundTripTextView);
        returnTextView = (TextView) findViewById(R.id.returnTextView);
        roundTripLinearLayout = (LinearLayout) findViewById(R.id.roundTripLinearLayout);
        oneAndRoundLinearLayout = (LinearLayout) findViewById(R.id.oneAndRoundLinearLayout);

        //images in front of buttons
        oneWaybuttonImageView = (ImageView) findViewById(R.id.oneWaybuttonImageView);
        roundTripButtonImageView = (ImageView) findViewById(R.id.roundTripbuttonImageView);
        multicityButtonImageView = (ImageView) findViewById(R.id.multicityTripbuttonImageView);

        //connecting EditText with displayTravelClassDialog to java program here
        displayTravelClassDialog = (TextView) findViewById(R.id.displayTravelClassDialog);
        displayMcTravelClassDialog=  findViewById(R.id.displayMcTravelClassDialog);
        //oneWayButton.setCompoundDrawablesWithIntrinsicBounds( tickIcon, null, null, null );
        multicityLinearLayout = (LinearLayout) findViewById(R.id.multicityLinearLayout);

        //oneAndRoundLinearLayout.setVisibility(View.VISIBLE);

        oneWaybuttonImageView.setVisibility(View.VISIBLE);
        roundTripButtonImageView.setVisibility(View.GONE);
        multicityButtonImageView.setVisibility(View.GONE);

        //Changing state of button
        oneWayButton.setBackground(getResources().getDrawable(R.drawable.pressed));
        roundTripButton.setBackground(getResources().getDrawable(R.drawable.normal));
        multiCityButton.setBackground(getResources().getDrawable(R.drawable.normal));
        //Changing colour of flight one way button  to white
        oneWayButton.setTextColor(Color.parseColor("#FFFFFF"));

        //connecting add and remove city buttons
        removeCityButton= findViewById(R.id.removeCityButton);
        addCityButton= findViewById(R.id.addCityButton);
        //MultiCity views
        linearLayout2=findViewById(R.id.linearLayout2);
        linearLayout3=findViewById(R.id.linearLayout3);
        linearLayout4=findViewById(R.id.linearLayout4);
        linearLayout5=findViewById(R.id.linearLayout5);
    }
}//End of OneWay class