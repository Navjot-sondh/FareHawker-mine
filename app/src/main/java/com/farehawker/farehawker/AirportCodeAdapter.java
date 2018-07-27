package com.farehawker.farehawker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<Airport>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Airport> FilteredArrList = new ArrayList<Airport>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Product>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).name;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Airport(mOriginalValues.get(i).name,mOriginalValues.get(i).price));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }


}