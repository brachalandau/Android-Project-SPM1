package com.example.spm1.Data;


import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import com.example.spm1.Entities.Parcel;
import com.example.spm1.Utils.AddParcelState;

import java.util.List;

public class ParcelRepository extends Application {

    //********Variables********//
    ParcelDataSource parcelDataSource;
    ParcelDAO parcelDAO;
    HistoryDataSource databaseRoom = null;
    private static Context mContext;
    MutableLiveData<List<Parcel>> parcelsInRoom;
    //********Constructor********//


    public ParcelRepository(Context app) {
        databaseRoom = Room.databaseBuilder(app, HistoryDataSource.class, "my_room")
                .allowMainThreadQueries()
                .build();

        parcelDAO = databaseRoom.getParcelDAO();//Access to ROOM through HistoryDataSource
        //   List<Parcel>m=parcelDAO.getAllParcels();
        parcelsInRoom = new MutableLiveData<>();
        Parcel parcel=new Parcel();
        //  parcel.setParcel_id("44");
        //   parcelDAO.Insert(parcel);
        // List<Parcel>m=parcelDAO.getAllParcels();
        parcelsInRoom.postValue(parcelDAO.getAllParcels());//First fill the MutableLiveData<List<Parcel>> in packages from ROOM
        parcelDataSource = new ParcelDataSource();

        parcelDataSource.notifyToParcelList(new ParcelDataSource.NotifyDataChange<List<Parcel>>() {
            //Go through the whole PARCELLIST list, and only packages that do not exist in ROOM will be added to ROOM
            @Override
            public void OnDataChanged(List<Parcel> parcelList) {
                for (Parcel parcel : parcelList) {
                    if (parcelDAO.getParcelById(parcel.getId()) == null)
                        parcelDAO.Insert(parcel);//add to ROOM
                }
                parcelsInRoom.postValue(parcelDAO.getAllParcels());//add to MutableLiveData<List<Parcel>>
            }

            @Override
            public void onFailure(Exception exception) {
                // Toast.makeText(getBaseContext(), "error to get parcel list\n" + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //********Functions********/
    //The addParcel method invokes the addParcel method in the ParcelDataSource layer
    public void addParcelToFirebase(final Parcel parcel) {
        parcelDataSource.addParcelToFirebase(parcel);
    }

    //A method that calls the getParcelChange method in the ParcelDataSource layer
    public LiveData<AddParcelState> getParcelChange() {
        return parcelDataSource.getParcelChange();
    }


    //********getter********/
    public LiveData<List<Parcel>> getAllParcels() {
        return parcelsInRoom;
    }
}
