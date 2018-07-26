package com.farehawker.farehawker.Adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AirportCodeAdapter extends RecyclerView.Adapter<AirportCodeAdapter.ViewHolder>
{
    @Override
    public AirportCodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(AirportCodeAdapter.ViewHolder holder, int position) {

    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public String cityName;
        public String countryName;
        public String countryCode;
        public String airportCode;
        public ViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
