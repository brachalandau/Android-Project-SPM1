package com.example.spm1.Data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.spm1.Entities.Parcel;

import java.util.List;

@Dao
public interface ParcelDAO {
    @Insert
    void Insert(Parcel parcel);
    //@Update
    //void Update(Parcel... parcels);
    //@Delete
    //void Delete(Parcel parcel);
    @Query("SELECT * FROM parcels ORDER BY sentDate")
    List<Parcel> getAllParcels();
    @Query("SELECT * FROM parcels WHERE id = :id")
    Parcel getParcelById(String id);
    @Query("DELETE FROM parcels")
    void deleteAllParcels();
}

