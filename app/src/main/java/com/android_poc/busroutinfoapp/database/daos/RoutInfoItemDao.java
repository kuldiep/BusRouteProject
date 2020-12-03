package com.android_poc.busroutinfoapp.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android_poc.busroutinfoapp.database.models.RouteInfoItem;

import java.util.List;

@Dao
public interface RoutInfoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoutInfoItemDao(RouteInfoItem routeInfoItem);

    @Query("SELECT * FROM RoutInfoItem")
    LiveData<List<RouteInfoItem>> getAllRoutInfoItems();
}
