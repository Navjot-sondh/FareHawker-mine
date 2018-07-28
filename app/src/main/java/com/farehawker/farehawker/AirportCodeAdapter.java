package com.farehawker.farehawker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AirportCodeAdapter extends RecyclerView.Adapter<AirportCodeAdapter.ViewHolder>
{

    /**
     * Created by Navjot on 27/07/2018.
     */

        private Context context;
        private List<Airport> list;

        public AirportCodeAdapter(Context context, List<Airport> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.airport, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Airport airport = list.get(position);


            holder.airportCode.setText(String.valueOf(airport.getAirportCode()));
            holder.cityName.setText(String.valueOf(airport.getCityName()));
            holder.countryCode.setText(String.valueOf(airport.getCountryCode()));
            holder.countryCode.setText(String.valueOf(airport.getCountryCode()));
            holder.countryName.setText(String.valueOf(airport.getCountryName()));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView  cityName,countryName,countryCode,airportCode;

            public ViewHolder(View itemView) {
                super(itemView);

                cityName = itemView.findViewById(R.id.cityName);
                countryName = itemView.findViewById(R.id.countryName);
                countryCode = itemView.findViewById(R.id.countryCode);
                airportCode=itemView.findViewById(R.id.airportCode);
            }
        }//End of ViewHolder


}