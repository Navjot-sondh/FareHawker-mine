package com.farehawker.farehawker;
import javax.net.ssl.SSLSocketFactory;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.webkit.WebStorage;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class OriginAirport extends AppCompatActivity  {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVAirports;
    private AirportCodeAdapter mAdapter;
    EditText search;
    AirportCodeAdapter adapter;
    ArrayList<Airport> data=new ArrayList<Airport>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport_list);
        //Make call to AsyncTask
        search=findViewById(R.id.search);
        new AsyncFetch().execute();
        search.setTextIsSelectable(true);
        search.setFocusable(true);
        search.setFocusableInTouchMode(true);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }
            public void listUpdate(ArrayList<Airport> data)
            {
                mRVAirports = findViewById(R.id.main_list);
                mAdapter = new AirportCodeAdapter(OriginAirport.this, data);
                mRVAirports.setAdapter(mAdapter);
                mRVAirports.setLayoutManager(new LinearLayoutManager(OriginAirport.this));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }//End of onTextChanged method

            @Override
            public void afterTextChanged(Editable editable)
            {
                Log.i("","onTextChanged method invoked");
                ArrayList<Airport> type_name_filter = new ArrayList<Airport>();

                String text =editable.toString();

                for (int i_ = 0; i_ < data.size(); i_++)
                {
                    Log.i("",data.get(i_).toString());
                    if ((data.get(i_).toString().toLowerCase()).contains(text
                            .toLowerCase())) {
                        Log.i("Filtered",String.valueOf(i_));
                        type_name_filter.add(data.get(i_));

                    }
                }



                listUpdate(type_name_filter);
            }//End of afterTextChanged method
        });
        //search.addTextChangedListener(watcher);
    }

    private class AsyncFetch extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(OriginAirport.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("https://www.farehawker.com/api/airpot-code.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try
            {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
           // data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);
                Log.i("Here",jArray.toString());
                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);

                    Airport airportData = new Airport();
                    airportData.setCityName(json_data.getString("city_name"));
                    airportData.setCountryName(json_data.getString("country_name"));
                    airportData.setCountryCode(json_data.getString("country_code"));
                    airportData.setAirportCode(json_data.getString("airport_code"));
                    Log.i("city_name",json_data.getString("city_name"));
                    data.add(airportData);
                    Log.i("i=",Integer.toString(i));
                }


                // Setup and Handover data to recyclerview
                mRVAirports = findViewById(R.id.main_list);
                mAdapter = new AirportCodeAdapter(OriginAirport.this, data);
                mRVAirports.setAdapter(mAdapter);
                mRVAirports.setLayoutManager(new LinearLayoutManager(OriginAirport.this));

            } catch (JSONException e) {
                Toast.makeText(OriginAirport.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }//End of AsyncFetch
}