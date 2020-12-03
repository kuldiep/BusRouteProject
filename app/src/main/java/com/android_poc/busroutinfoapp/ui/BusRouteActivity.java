package com.android_poc.busroutinfoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.android_poc.busroutinfoapp.R;
import com.android_poc.busroutinfoapp.arch_component.viewmodel.BusInfoViewModel;
import com.android_poc.busroutinfoapp.arch_component.viewmodel.ViewModelFactory;
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity;
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem;

import java.util.List;

public class BusRouteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_route);

        ViewModelFactory viewModelFactory = new ViewModelFactory();
        BusInfoViewModel busInfoViewModel = ViewModelProviders.of(
                this,viewModelFactory).get(BusInfoViewModel.class);
       /* busInfoViewModel.fillRouteInfoItemTable();
        busInfoViewModel.fillBusTimingRouteTable();*/
        busInfoViewModel.getRouteInfoItemsFromRepo().observe(this, routeInfoItems ->
                Log.d("LOG",routeInfoItems.toString()));

        busInfoViewModel.getBusTimingInfoFromRepo().observe(this, busTimeEntities ->
                Log.d("LOG", busTimeEntities.toString()));

        busInfoViewModel.getBusTimingOnGivenRoute("r002").observe(this,busTimeEntities ->
                Log.d("LOG",busTimeEntities.toString())
                );


    }




}