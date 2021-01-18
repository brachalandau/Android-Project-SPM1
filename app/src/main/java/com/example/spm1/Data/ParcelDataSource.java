package com.example.spm1.Data;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.spm1.Entities.Parcel;
import com.example.spm1.Utils.AddParcelState;
import com.example.spm1.Utils.ParcelStatus;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParcelDataSource {
    public MutableLiveData<AddParcelState> parcelChange;//MutableLiveData

    //constructor
    public ParcelDataSource() {
        parcelChange = new MutableLiveData<>();
    }

    //Listener interface
    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);
        void onFailure(Exception exception);
    }

    static DatabaseReference ParcelsRef;
    static List<Parcel> parcelList;//list of all the parcels

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();//Get access from the database
        ParcelsRef = database.getReference("parcels");//getReference to parcels(in the firebase)
        parcelList = new ArrayList<>();
    }
    public void addParcel(final Parcel parcel)
    {

    }

    public void addParcelToFirebase(final Parcel parcel) {
        String key = ParcelsRef.push().getKey();//get Unique key from the firebase
        parcel.setId(key);//Updating the ID field
        stopNotifyToParcelList();

        //Puts the parcel in the appropriate place.
        //After adding, wait to see what the answer is by the listener
        ParcelsRef.child(key).setValue(parcel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                parcelRefValueEventListener=tmp;

                ParcelsRef.addValueEventListener(parcelRefValueEventListener);

                AddParcelState p = new AddParcelState(false, true, false);
                parcelChange.postValue(p);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                parcelRefValueEventListener=tmp;
                ParcelsRef.addValueEventListener(parcelRefValueEventListener);

                AddParcelState p = new AddParcelState(false, false, true);
                parcelChange.postValue(p);
            }
        });
    }

    public LiveData<AddParcelState> getParcelChange() {//////MutableLiveData?
        return parcelChange;
    }

    private static ValueEventListener parcelRefValueEventListener,tmp;

    //After adding, wait to see what the answer is by the listener
    public static void notifyToParcelList(final NotifyDataChange<List<Parcel>> notifyDataChange) {
        if (notifyDataChange != null) {
            if (parcelRefValueEventListener != null) {
                notifyDataChange.onFailure(new Exception("first unNotify parcel list"));
                return;
            }
            parcelList.clear();
            parcelRefValueEventListener = new ValueEventListener() {

                //if we you use value
                //  @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    int size=parcelList.size();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Parcel parcel = data.getValue(Parcel.class);
                        if(parcel.getParcelStatus()== ParcelStatus.RECEIVED) {
                            if(!(parcelList.contains(parcel))){
                                parcelList.add(parcel);
                            }
                        }
                    }
                    if(size<parcelList.size()){//Update only after all the packages have come in and not on each package separately
                        notifyDataChange.OnDataChanged(parcelList);
                    }
                }
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            ParcelsRef.addValueEventListener(parcelRefValueEventListener);
        }
    }
    public static void stopNotifyToParcelList() {
        if (parcelRefValueEventListener != null) {
            ParcelsRef.removeEventListener(parcelRefValueEventListener);
            tmp=parcelRefValueEventListener;
            parcelRefValueEventListener = null;
        }
    }
}