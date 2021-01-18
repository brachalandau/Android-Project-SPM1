package com.example.spm1.UI;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.spm1.Data.ParcelRepository;
import com.example.spm1.Entities.Parcel;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    //********Variables********//
    public static ParcelRepository parcelRepository;
    Context r = getApplication();//Because the constructor in layer ParcelRepository receives Context, we created such an instance.

    //********Constructor********//
    public HistoryViewModel(@NonNull Application application) {
        super(application);
        //We did it to run the default Constructor in layer ParcelRepository
        // parcelRepository = new ParcelRepository();
        //We did it to run the Constructor with the parameter in layer ParcelRepository
        parcelRepository = new ParcelRepository(r);
    }

    public LiveData<List<Parcel>> getAllParcel() {
        return parcelRepository.getAllParcels();

    }
}