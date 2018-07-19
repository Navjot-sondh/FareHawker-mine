package com.farehawker.farehawker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
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

public class FlightBooking extends AppCompatActivity {
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
    //Number of travellers Alert Dialog
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

    public void changePassengers(View view)
    {
        //multiCityTextView =findViewById(R.id.multiCityTextView);
        travellersTextView=findViewById(R.id.travellersTextView);
        int totalTravellers=0;
        Log.i("Log", "changePassengers");
       //Log.i("Here", Integer.toString(view.getId()));
        int adults, children, infants;
        children = Integer.parseInt(childrenTextView.getText().toString());
        adults = Integer.parseInt(adultsTextView.getText().toString());
        infants = Integer.parseInt(infantsTextView.getText().toString());
        try
        {
            Log.i("Log", childrenTextView.getText().toString());
            String tag = (String) view.getTag();
            Log.i("Log", tag);
            switch (tag)
            {
                //Adults
                case "1":
                    if (adults > 1 && (adults > infants))
                    {
                        adults--;
                        adultsTextView.setText(Integer.toString(adults));

                    } else if (adults == infants && adults > 1) {
                        adults--;
                        infants--;
                        adultsTextView.setText(Integer.toString(adults));
                        infantsTextView.setText(Integer.toString(infants));
                    }
                    break;
                case "2":
                    if (adults + children < 9) {
                        adults = adults + 1;
                        adultsTextView.setText(Integer.toString(adults));
                    }
                    break;
                //children
                case "3":
                    if (children > 0) {

                        children--;
                        childrenTextView.setText(Integer.toString(children));
                    }
                    break;
                case "4":
                    if ((adults + children) < 9) {
                        children = children + 1;
                        childrenTextView.setText(Integer.toString(children));
                    }
                    break;
                //- textView pressed decrease number of infants
                case "5":

                    if (infants > 0) {
                        infants--;
                        infantsTextView.setText(Integer.toString(infants));
                    }
                    break;
                    //+ sign on
                case "6":
                    if (infants < adults) {
                        infants++;
                        infantsTextView.setText(Integer.toString(infants));
                    }
                    break;

            }
            totalTravellers=adults+children+infants;
            Log.i("TotalNumberOfTravellers",Integer.toString(totalTravellers));

            travellersTextView.setText(Integer.toString(totalTravellers));
        }//End of try block
        catch (Exception exception)
        {
            exception.printStackTrace();
        }//End of catch block

    }//End of changePassengers fumctions
    public void changeMcPassengers(View view)
    {
        multiCityTextView =findViewById(R.id.multiCityTextView);
        int totalTravellers=0;
        Log.i("Log", "changeMcPassengers");
        //Log.i("Here", Integer.toString(view.getId()));
        int adults, children, infants;
        children = Integer.parseInt(childrenTextView.getText().toString());
        adults = Integer.parseInt(adultsTextView.getText().toString());
        infants = Integer.parseInt(infantsTextView.getText().toString());
        try
        {
            Log.i("Log", childrenTextView.getText().toString());
            String tag = (String) view.getTag();
            Log.i("Log", tag);
            switch (tag)
            {
                //Adults
                case "1":
                    if (adults > 1 && (adults > infants))
                    {
                        adults--;
                        adultsTextView.setText(Integer.toString(adults));

                    } else if (adults == infants && adults > 1) {
                        adults--;
                        infants--;
                        adultsTextView.setText(Integer.toString(adults));
                        infantsTextView.setText(Integer.toString(infants));
                    }
                    break;
                case "2":
                    if (adults + children < 9) {
                        adults = adults + 1;
                        adultsTextView.setText(Integer.toString(adults));
                    }
                    break;
                //children
                case "3":
                    if (children > 0) {

                        children--;
                        childrenTextView.setText(Integer.toString(children));
                    }
                    break;
                case "4":
                    if ((adults + children) < 9) {
                        children = children + 1;
                        childrenTextView.setText(Integer.toString(children));
                    }
                    break;
                //- textView pressed decrease number of infants
                case "5":

                    if (infants > 0) {
                        infants--;
                        infantsTextView.setText(Integer.toString(infants));
                    }
                    break;
                //+ sign on
                case "6":
                    if (infants < adults) {
                        infants++;
                        infantsTextView.setText(Integer.toString(infants));
                    }
                    break;

            }
            totalTravellers=adults+children+infants;
            Log.i("TotalNumberOfTravellers",Integer.toString(totalTravellers));

            multiCityTextView.setText(Integer.toString(totalTravellers));
        }//End of try block
        catch (Exception exception)
        {
            exception.printStackTrace();
        }//End of catch block

    }//End of changePassengers fumctions

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
    public void displayMcTravelClassDialog(View view) {
        Log.i("Log", "EditText Tapped");
        final String[] classes = getResources().getStringArray(R.array.travelClassArray);
        AlertDialog.Builder builder = new AlertDialog.Builder(FlightBooking.this);
        builder.setTitle("Travel Class");
        builder.setItems(classes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                // will set  your selection on EditText

                displayMcTravelClassDialog.setText(classes[item]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void displayTravellersDialog(View view)
    {
        Log.i("Log", "displayTravellersDialog method");
        TravellersBuilder = new AlertDialog.Builder(FlightBooking.this);
        LayoutInflater inflater = FlightBooking.this.getLayoutInflater();
        TravellersBuilder.setView(inflater.inflate(R.layout.number_of_travellers, null));
        alertDialog = TravellersBuilder.show();
        //Number of passengers
        adultsTextView = (TextView) alertDialog.findViewById(R.id.adultTextView);
        childrenTextView = (TextView) alertDialog.findViewById(R.id.childrenTextView);
        infantsTextView = (TextView) alertDialog.findViewById(R.id.infantsTextView);
        Log.i("Here", adultsTextView.getText().toString());
    }//End of displayTravellersDialog
    public void displayMcTravellersDialog(View view)
    {
        Log.i("Log", "displayTravellersDialog method");
        TravellersBuilder = new AlertDialog.Builder(FlightBooking.this);
        LayoutInflater inflater = FlightBooking.this.getLayoutInflater();
        TravellersBuilder.setView(inflater.inflate(R.layout.number_of_travellers2, null));
        alertDialog = TravellersBuilder.show();
        //Number of passengers
        adultsTextView = (TextView) alertDialog.findViewById(R.id.adultTextView);
        childrenTextView = (TextView) alertDialog.findViewById(R.id.childrenTextView);
        infantsTextView = (TextView) alertDialog.findViewById(R.id.infantsTextView);
        Log.i("Here", adultsTextView.getText().toString());
    }//End of displayTravellersDialog

    public void dismiss(View view) {
        alertDialog.dismiss();
    }

    public void getTag() {

    }

    public void showDatePicker(View view) {
        Log.i("Log", "showDatePicker method invoked");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "Date Picker");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flight_booking);
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

        //connecting add and remove city buttons
        removeCityButton= findViewById(R.id.removeCityButton);
        addCityButton= findViewById(R.id.addCityButton);
        //MultiCity views
        linearLayout2=findViewById(R.id.linearLayout2);
        linearLayout3=findViewById(R.id.linearLayout3);
        linearLayout4=findViewById(R.id.linearLayout4);
        linearLayout5=findViewById(R.id.linearLayout5);
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
                                day=dayOfMonth;
                                month=monthOfYear;
                                 year_=year;

                            }
                        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.DAY_OF_MONTH);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        travellersDialog = (LinearLayout) findViewById(R.id.travellersDialog);
        okButton = (Button) findViewById(R.id.okButton);

    }// End of onCreate

    public void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        flightDateEditText.setText(sdf.format(myCalendar.getTime()));
    }//End of updateLabel method
    /*
    * Method invoked when round trip TextView*/
    public void setRoundTrip(View view)
    {
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year_);
        Log.i("341",day+"/"+(month+1)+"/"+year_);
        Log.i("","setRoundTrip");
        DatePickerDialog datePickerDialog = new DatePickerDialog(FlightBooking.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        roundTripTextView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        returnDay=dayOfMonth;
                        returnMonth=monthOfYear;
                        returnYear=year;
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.DAY_OF_MONTH);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR,1);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }//End of setRoundTrip method
    public void changeNoOfCities(View view)
    {


        Log.i("377","changeNoOfCities method ");

        String tag=view.getTag().toString();
        if(tag.equals("1"))
        {
            Log.i("tag","1");
            switch(noOfCities)
            {
                case 1: linearLayout2.setVisibility(View.VISIBLE);
                        noOfCities++;
                    break;
                case 2:
                    linearLayout3.setVisibility(View.VISIBLE);
                    noOfCities++;
                    break;
                case 3:linearLayout4.setVisibility(View.VISIBLE);
                    noOfCities++;
                    break;
                case 4:linearLayout5.setVisibility(View.VISIBLE);
                    noOfCities++;
                    break;

                default:

            }//End of switch
        }//End of if
        else
        {
            Log.i("tag","2");
            switch(noOfCities)
            {
                case 2: linearLayout2.setVisibility(View.GONE);
                noOfCities--;
                    break;
                case 3:
                    linearLayout3.setVisibility(View.GONE);
                    noOfCities--;
                    break;
                case 4:linearLayout4.setVisibility(View.GONE);
                    noOfCities--;
                    break;
                case 5:linearLayout5.setVisibility(View.GONE);
                    noOfCities--;
                    break;
                default:
            }//End of switch
        }//End of else
    }//End of changeNoOfCities method
}//End of FlightBooking class