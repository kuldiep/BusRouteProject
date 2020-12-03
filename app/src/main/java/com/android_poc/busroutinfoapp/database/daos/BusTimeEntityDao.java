package com.android_poc.busroutinfoapp.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android_poc.busroutinfoapp.database.models.BusTimeEntity;

import java.util.List;

@Dao
public interface BusTimeEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBusTimingData(BusTimeEntity busTimeEntity);

    @Query("SELECT * FROM BusTimeEntity")
    LiveData<List<BusTimeEntity>> getAllBusTimingPojoFromDB();

    @Query("SELECT * FROM BusTimeEntity WHERE keyId = :routeInfoId")
    LiveData<List<BusTimeEntity>> getAllBussesOnThisRouteId(String routeInfoId);

}
