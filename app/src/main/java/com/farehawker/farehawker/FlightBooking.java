package com.farehawker.farehawker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
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

public class FlightBooking extends AppCompatActivity implements OnClickListener {

    Button oneWayButton, roundTripButton, multiCityButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_booking);
        oneWayButton = findViewById(R.id.oneWayButton);
        roundTripButton = findViewById(R.id.roundTripButton);
        multiCityButton = findViewById(R.id.multicityButton);
        oneWayButton.setOnClickListener(this);
        roundTripButton.setOnClickListener(this);
        multiCityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.oneWayButton:
                Intent intent = new Intent(getApplicationContext(), OneWay.class);
                startActivity(intent);
                break;
            case R.id.roundTripButton:
                intent = new Intent(getApplicationContext(), RoundTripBooking.class);
                startActivity(intent);
                break;
            case R.id.multicityButton:
                intent = new Intent(getApplicationContext(), MultiCityBooking.class);
                startActivity(intent);
                break;
        }
    }
}//End of FlightBooking class