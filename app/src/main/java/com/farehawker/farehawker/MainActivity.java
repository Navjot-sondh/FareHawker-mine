package com.farehawker.farehawker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.farehawker.farehawker.IconAdapter;
import com.farehawker.farehawker.Icon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Icon> iconList = new ArrayList<>();
    private RecyclerView iconRecyclerView;
    private IconAdapter IconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconRecyclerView = findViewById(R.id.idRecyclerViewHorizontalList);
        // add a divider after each item for more clarity
        iconRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL));
        IconAdapter = new IconAdapter(iconList, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        iconRecyclerView.setLayoutManager(horizontalLayoutManager);
        iconRecyclerView.setAdapter(IconAdapter);
        populateiconList();
    }

    private void populateiconList()
    {
        Icon flight = new Icon( R.drawable.flight_icon,"FLIGHT");
        Icon hotel = new Icon( R.drawable.hotel_icon,"HOTEL");
        Icon helicopter = new Icon(R.drawable.helicopter_icon,"HELICOPTER");
        Icon privetJet= new Icon(R.drawable.privet_jet,"PRIVET JETS");
        Icon groupBooking = new Icon(R.drawable.group_booking,"GROUP BOOKING");

        iconList.add(flight);
        iconList.add(hotel);
        iconList.add(helicopter);
        iconList.add(privetJet);
        iconList.add(groupBooking);
        IconAdapter.notifyDataSetChanged();
    }
}