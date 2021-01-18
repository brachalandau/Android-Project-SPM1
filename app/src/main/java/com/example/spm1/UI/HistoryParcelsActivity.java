package com.example.spm1.UI;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.spm1.Entities.Parcel;
import com.example.spm1.R;
import com.example.spm1.Utils.ParcelArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryParcelsActivity extends AppCompatActivity {

    HistoryViewModel historyVM;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_parcels);

        final ArrayList<Parcel> itemList = new ArrayList<>();
        ParcelArrayAdapter itemArrayAdapter = new ParcelArrayAdapter(this, R.layout.activity_list_parcel, itemList);
        listView = (ListView) findViewById(R.id.item_list);
        listView.setAdapter(itemArrayAdapter);
        historyVM = ViewModelProviders.of(this).get(HistoryViewModel.class);

        historyVM.getAllParcel().observe(this, new Observer<List<Parcel>>()
                {
                    @Override
                    public void onChanged(List<Parcel> parcelList) {
                        boolean listChanged = false;
                        for (Parcel parcel: parcelList)
                        {
                            itemList.add(parcel);
                            listChanged = true;
                        }
                        if(listChanged)
                            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged(); }
                }
        );
    }



}







