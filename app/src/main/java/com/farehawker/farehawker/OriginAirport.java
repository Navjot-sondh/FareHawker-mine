package com.farehawker.farehawker;
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
import java.util.ArrayList;
import java.util.List;

public class OriginAirport extends AppCompatActivity  implements TextWatcher{

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVAirports;
    private AirportCodeAdapter mAdapter;
    EditText search;
    AirportCodeAdapter adapter;
    List<Airport> data=new ArrayList<Airport>();
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
            adapter.getFilter().filter(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            filter(editable.toString());
        }
    };
    public void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Airport> filterdNames = new ArrayList<>();

        //looping through existing elements
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport_list);
        //Make call to AsyncTask
        search=findViewById(R.id.search);
        new AsyncFetch().execute();
        search.addTextChangedListener(watcher);
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
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
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
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
                Log.i("","");
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