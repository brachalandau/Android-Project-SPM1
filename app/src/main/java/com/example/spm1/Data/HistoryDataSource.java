package com.example.spm1.Data;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.spm1.Entities.Parcel;
import com.example.spm1.Utils.Convert;


@Database(entities = {Parcel.class}, version = 1,exportSchema = false)
@TypeConverters(Convert.class)//Convert special types for the room
public abstract class HistoryDataSource extends RoomDatabase{
    public abstract ParcelDAO getParcelDAO();
}